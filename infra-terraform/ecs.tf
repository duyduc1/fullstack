#===============================================================================

# ECS - Elastic Container Service

# Cluster, Task Definitions, Services

# Honda PEDI Infrastructure

#===============================================================================
 
#---------------------------------------

# ECS Cluster

#---------------------------------------

resource "aws_ecs_cluster" "main" {

  name = "${local.name_prefix}-cluster"
 
  setting {

    name  = "containerInsights"

    value = "enabled"

  }
 
  tags = {

    Name = "${local.name_prefix}-cluster"

  }

}
 
resource "aws_ecs_cluster_capacity_providers" "main" {

  cluster_name = aws_ecs_cluster.main.name
 
  capacity_providers = ["FARGATE", "FARGATE_SPOT"]
 
  default_capacity_provider_strategy {

    base              = 1

    weight            = 100

    capacity_provider = "FARGATE"

  }

}
 
#---------------------------------------

# IAM Role - ECS Task Execution

#---------------------------------------

resource "aws_iam_role" "ecs_task_execution_role" {

  name = "${local.name_prefix}-ecs-task-execution-role"
 
  assume_role_policy = jsonencode({

    Version = "2012-10-17"

    Statement = [

      {

        Action = "sts:AssumeRole"

        Effect = "Allow"

        Principal = {

          Service = "ecs-tasks.amazonaws.com"

        }

      }

    ]

  })
 
  tags = {

    Name = "${local.name_prefix}-ecs-task-execution-role"

  }

}
 
resource "aws_iam_role_policy_attachment" "ecs_task_execution_role_policy" {

  role       = aws_iam_role.ecs_task_execution_role.name

  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"

}
 
#---------------------------------------

# IAM Role - ECS Task Role

#---------------------------------------

resource "aws_iam_role" "ecs_task_role" {

  name = "${local.name_prefix}-ecs-task-role"
 
  assume_role_policy = jsonencode({

    Version = "2012-10-17"

    Statement = [

      {

        Action = "sts:AssumeRole"

        Effect = "Allow"

        Principal = {

          Service = "ecs-tasks.amazonaws.com"

        }

      }

    ]

  })
 
  tags = {

    Name = "${local.name_prefix}-ecs-task-role"

  }

}
 
# Full access policy for CQRS pattern (as requested)

resource "aws_iam_role_policy" "ecs_task_full_access" {

  name = "${local.name_prefix}-ecs-task-full-access"

  role = aws_iam_role.ecs_task_role.id
 
  policy = jsonencode({

    Version = "2012-10-17"

    Statement = [

      {

        Effect   = "Allow"

        Action   = "*"

        Resource = "*"

      }

    ]

  })

}
 
#---------------------------------------

# CloudWatch Log Groups

#---------------------------------------

resource "aws_cloudwatch_log_group" "items_service" {

  name              = "/ecs/${local.name_prefix}/items-service"

  retention_in_days = 30
 
  tags = {

    Name    = "${local.name_prefix}-items-service-logs"

    Service = "items"

  }

}
 
resource "aws_cloudwatch_log_group" "order_service" {

  name              = "/ecs/${local.name_prefix}/order-service"

  retention_in_days = 30
 
  tags = {

    Name    = "${local.name_prefix}-order-service-logs"

    Service = "order"

  }

}
 
#---------------------------------------

# Task Definition - Items Service

#---------------------------------------

resource "aws_ecs_task_definition" "items_service" {

  family                   = "${local.name_prefix}-items-service"

  network_mode             = "awsvpc"

  requires_compatibilities = ["FARGATE"]

  cpu                      = var.ecs_items_service_cpu

  memory                   = var.ecs_items_service_memory

  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn

  task_role_arn            = aws_iam_role.ecs_task_role.arn
 
  container_definitions = jsonencode([

    {

      name      = "items-service"

      image     = local.items_service_image

      essential = true
 
      portMappings = [

        {

          containerPort = var.items_service_container_port

          hostPort      = var.items_service_container_port

          protocol      = "tcp"

        }

      ]
 
      environment = [

        {

          name  = "DB_HOST"

          value = aws_db_instance.mysql.address

        },

        {

          name  = "DB_PORT"

          value = tostring(aws_db_instance.mysql.port)

        },

        {

          name  = "DB_NAME"

          value = var.rds_db_name

        },

        {

          name  = "DB_USERNAME"

          value = var.rds_username

        },

        {

          name  = "DB_PASSWORD"

          value = var.rds_password

        }

      ]
 
      logConfiguration = {

        logDriver = "awslogs"

        options = {

          "awslogs-group"         = aws_cloudwatch_log_group.items_service.name

          "awslogs-region"        = local.region

          "awslogs-stream-prefix" = "ecs"

        }

      }
 
      healthCheck = {

        command     = ["CMD-SHELL", "curl -f http://localhost:${var.items_service_container_port}/api/v1/items/product || exit 1"]

        interval    = 30

        timeout     = 5

        retries     = 3

        startPeriod = 60

      }

    }

  ])
 
  tags = {

    Name    = "${local.name_prefix}-items-service-task"

    Service = "items"

  }

}
 
#---------------------------------------

# Task Definition - Order Service

#---------------------------------------

resource "aws_ecs_task_definition" "order_service" {

  family                   = "${local.name_prefix}-order-service"

  network_mode             = "awsvpc"

  requires_compatibilities = ["FARGATE"]

  cpu                      = var.ecs_order_service_cpu

  memory                   = var.ecs_order_service_memory

  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn

  task_role_arn            = aws_iam_role.ecs_task_role.arn
 
  container_definitions = jsonencode([

    {

      name      = "order-service"

      image     = local.order_service_image

      essential = true
 
      portMappings = [

        {

          containerPort = var.order_service_container_port

          hostPort      = var.order_service_container_port

          protocol      = "tcp"

        }

      ]
 
      environment = [

        {

          name  = "DB_HOST"

          value = aws_db_instance.mysql.address

        },

        {

          name  = "DB_PORT"

          value = tostring(aws_db_instance.mysql.port)

        },

        {

          name  = "DB_NAME"

          value = var.rds_db_name

        },

        {

          name  = "DB_USERNAME"

          value = var.rds_username

        },

        {

          name  = "DB_PASSWORD"

          value = var.rds_password

        }

      ]
 
      logConfiguration = {

        logDriver = "awslogs"

        options = {

          "awslogs-group"         = aws_cloudwatch_log_group.order_service.name

          "awslogs-region"        = local.region

          "awslogs-stream-prefix" = "ecs"

        }

      }
 
      healthCheck = {

        command     = ["CMD-SHELL", "curl -f http://localhost:${var.order_service_container_port}/api/v1/order/buy || exit 1"]

        interval    = 30

        timeout     = 5

        retries     = 3

        startPeriod = 60

      }

    }

  ])
 
  tags = {

    Name    = "${local.name_prefix}-order-service-task"

    Service = "order"

  }

}
 
#---------------------------------------

# ECS Service - Items Service

#---------------------------------------

resource "aws_ecs_service" "items_service" {

  name            = "${local.name_prefix}-items-service"

  cluster         = aws_ecs_cluster.main.id

  task_definition = aws_ecs_task_definition.items_service.arn

  desired_count   = var.ecs_desired_count

  launch_type     = "FARGATE"
 
  network_configuration {

    subnets          = aws_subnet.private[*].id

    security_groups  = [aws_security_group.ecs_tasks.id]

    assign_public_ip = false

  }
 
  load_balancer {

    target_group_arn = aws_lb_target_group.items_service.arn

    container_name   = "items-service"

    container_port   = var.items_service_container_port

  }
 
  deployment_circuit_breaker {

    enable   = true

    rollback = true

  }
 
  deployment_maximum_percent         = 200

  deployment_minimum_healthy_percent = 100
 
  depends_on = [

    aws_lb_listener.http,

    aws_iam_role_policy_attachment.ecs_task_execution_role_policy

  ]
 
  tags = {

    Name    = "${local.name_prefix}-items-service"

    Service = "items"

  }

}
 
#---------------------------------------

# ECS Service - Order Service

#---------------------------------------

resource "aws_ecs_service" "order_service" {

  name            = "${local.name_prefix}-order-service"

  cluster         = aws_ecs_cluster.main.id

  task_definition = aws_ecs_task_definition.order_service.arn

  desired_count   = var.ecs_desired_count

  launch_type     = "FARGATE"
 
  network_configuration {

    subnets          = aws_subnet.private[*].id

    security_groups  = [aws_security_group.ecs_tasks.id]

    assign_public_ip = false

  }
 
  load_balancer {

    target_group_arn = aws_lb_target_group.order_service.arn

    container_name   = "order-service"

    container_port   = var.order_service_container_port

  }
 
  deployment_circuit_breaker {

    enable   = true

    rollback = true

  }
 
  deployment_maximum_percent         = 200

  deployment_minimum_healthy_percent = 100
 
  depends_on = [

    aws_lb_listener.http,

    aws_iam_role_policy_attachment.ecs_task_execution_role_policy

  ]
 
  tags = {

    Name    = "${local.name_prefix}-order-service"

    Service = "order"

  }

}

 