resource "aws_security_group" "ec2" {

  name        = "${local.name_prefix}-ec2-sg"

  description = "Security group for EC2 source code server"

  vpc_id      = aws_vpc.main.id
 
  # SSH access

  ingress {

    description = "SSH"

    from_port   = 22

    to_port     = 22

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  # HTTP

  ingress {

    description = "HTTP"

    from_port   = 80

    to_port     = 80

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  # HTTPS

  ingress {

    description = "HTTPS"

    from_port   = 443

    to_port     = 443

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  # Application ports

  ingress {

    description = "App Port 3000"

    from_port   = 3000

    to_port     = 3001

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  egress {

    description = "Allow all outbound"

    from_port   = 0

    to_port     = 0

    protocol    = "-1"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  tags = {

    Name = "${local.name_prefix}-ec2-sg"

  }

}
 
#---------------------------------------

# Security Group - ALB

#---------------------------------------

resource "aws_security_group" "alb" {

  name        = "${local.name_prefix}-alb-sg"

  description = "Security group for Application Load Balancer"

  vpc_id      = aws_vpc.main.id
 
  ingress {

    description = "HTTP"

    from_port   = 80

    to_port     = 80

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  ingress {

    description = "HTTPS"

    from_port   = 443

    to_port     = 443

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  egress {

    description = "Allow all outbound"

    from_port   = 0

    to_port     = 0

    protocol    = "-1"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  tags = {

    Name = "${local.name_prefix}-alb-sg"

  }

}
 
#---------------------------------------

# Security Group - ECS Tasks

#---------------------------------------

resource "aws_security_group" "ecs_tasks" {

  name        = "${local.name_prefix}-ecs-tasks-sg"

  description = "Security group for ECS tasks"

  vpc_id      = aws_vpc.main.id
 
  # Allow traffic from ALB

  ingress {

    description     = "From ALB - Items Service"

    from_port       = var.items_service_container_port

    to_port         = var.items_service_container_port

    protocol        = "tcp"

    security_groups = [aws_security_group.alb.id]

  }
 
  ingress {

    description     = "From ALB - Order Service"

    from_port       = var.order_service_container_port

    to_port         = var.order_service_container_port

    protocol        = "tcp"

    security_groups = [aws_security_group.alb.id]

  }
 
  egress {

    description = "Allow all outbound"

    from_port   = 0

    to_port     = 0

    protocol    = "-1"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  tags = {

    Name = "${local.name_prefix}-ecs-tasks-sg"

  }

}
 
#---------------------------------------

# Security Group - RDS MySQL

#---------------------------------------

resource "aws_security_group" "rds" {

  name        = "${local.name_prefix}-rds-sg"

  description = "Security group for RDS MySQL"

  vpc_id      = aws_vpc.main.id
 
  # Allow MySQL from EC2

  ingress {

    description     = "MySQL from EC2"

    from_port       = 3306

    to_port         = 3306

    protocol        = "tcp"

    security_groups = [aws_security_group.ec2.id]

  }
 
  # Allow MySQL from ECS tasks

  ingress {

    description     = "MySQL from ECS"

    from_port       = 3306

    to_port         = 3306

    protocol        = "tcp"

    security_groups = [aws_security_group.ecs_tasks.id]

  }
 
  # Allow MySQL from anywhere (Public Access requirement)

  ingress {

    description = "MySQL Public Access"

    from_port   = 3306

    to_port     = 3306

    protocol    = "tcp"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  egress {

    description = "Allow all outbound"

    from_port   = 0

    to_port     = 0

    protocol    = "-1"

    cidr_blocks = ["0.0.0.0/0"]

  }
 
  tags = {

    Name = "${local.name_prefix}-rds-sg"

  }

}

 