ecr.tf
#===============================================================================

# ECR - Elastic Container Registry

# Honda PEDI Infrastructure

#===============================================================================
 
#---------------------------------------

# ECR Repository - Items Service

#---------------------------------------

resource "aws_ecr_repository" "items_service" {

  name                 = "${local.name_prefix}-items-service"

  image_tag_mutability = "MUTABLE"
 
  image_scanning_configuration {

    scan_on_push = true

  }
 
  encryption_configuration {

    encryption_type = "AES256"

  }
 
  tags = {

    Name    = "${local.name_prefix}-items-service"

    Service = "items"

  }

}
 
#---------------------------------------

# ECR Repository - Order Service

#---------------------------------------

resource "aws_ecr_repository" "order_service" {

  name                 = "${local.name_prefix}-order-service"

  image_tag_mutability = "MUTABLE"
 
  image_scanning_configuration {

    scan_on_push = true

  }
 
  encryption_configuration {

    encryption_type = "AES256"

  }
 
  tags = {

    Name    = "${local.name_prefix}-order-service"

    Service = "order"

  }

}
 
#---------------------------------------

# ECR Lifecycle Policy - Items Service

#---------------------------------------

resource "aws_ecr_lifecycle_policy" "items_service" {

  repository = aws_ecr_repository.items_service.name
 
  policy = jsonencode({

    rules = [

      {

        rulePriority = 1

        description  = "Keep last 10 images"

        selection = {

          tagStatus   = "any"

          countType   = "imageCountMoreThan"

          countNumber = 10

        }

        action = {

          type = "expire"

        }

      }

    ]

  })

}
 
#---------------------------------------

# ECR Lifecycle Policy - Order Service

#---------------------------------------

resource "aws_ecr_lifecycle_policy" "order_service" {

  repository = aws_ecr_repository.order_service.name
 
  policy = jsonencode({

    rules = [

      {

        rulePriority = 1

        description  = "Keep last 10 images"

        selection = {

          tagStatus   = "any"

          countType   = "imageCountMoreThan"

          countNumber = 10

        }

        action = {

          type = "expire"

        }

      }

    ]

  })

}

 