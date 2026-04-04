terraform {

  required_version = ">= 1.5.0"
 
  required_providers {

    aws = {

      source  = "hashicorp/aws"

      version = "~> 5.0"

    }

  }
 
}
 
provider "aws" {

  region = var.aws_region
 
  default_tags {

    tags = var.common_tags

  }

}
 
#===============================================================================

# Data Sources

#===============================================================================
 
data "aws_caller_identity" "current" {}
 
data "aws_region" "current" {}
 
# Latest Ubuntu 24.04 LTS AMI

data "aws_ami" "ubuntu_2404" {

  most_recent = true

  owners      = ["099720109477"] 
 
  filter {

    name   = "name"

    values = ["ubuntu/images/hvm-ssd-gp3/ubuntu-noble-24.04-amd64-server-*"]

  }
 
  filter {

    name   = "virtualization-type"

    values = ["hvm"]

  }
 
  filter {

    name   = "state"

    values = ["available"]

  }

}
 
#===============================================================================

# Local values

#===============================================================================
 
locals {

  name_prefix = "${var.project_name}-${var.environment}"

  account_id  = data.aws_caller_identity.current.account_id

  region      = data.aws_region.current.name
 
  ami_id = var.ec2_ami_id != "" ? var.ec2_ami_id : data.aws_ami.ubuntu_2404.id
 
  # ECR repository URLs (will be constructed after creation)

  items_service_image = "${local.account_id}.dkr.ecr.${local.region}.amazonaws.com/${local.name_prefix}-items-service:latest"

  order_service_image = "${local.account_id}.dkr.ecr.${local.region}.amazonaws.com/${local.name_prefix}-order-service:latest"

}
