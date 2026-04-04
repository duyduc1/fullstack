#===============================================================================

# Terraform Variable Values

# Honda PEDI Infrastructure - Dev Environment

#===============================================================================
 
aws_region   = "ap-southeast-1"

project_name = "honda-pedi"

environment  = "dev"
 
# VPC

vpc_cidr            = "10.0.0.0/16"

public_subnet_cidrs = ["10.0.1.0/24", "10.0.2.0/24"]

private_subnet_cidrs = ["10.0.10.0/24", "10.0.20.0/24"]

availability_zones  = ["ap-southeast-1a", "ap-southeast-1b"]
 
# EC2

ec2_instance_type = "t2.micro"

ec2_key_name      = ""

ec2_ami_id        = "" # Auto-detect Amazon Linux 2023
 
# RDS MySQL

rds_instance_class    = "db.t3.micro"

rds_db_name           = "honda_pedi_db"

rds_username          = "root"

rds_password          = "M1aT4Tt7aeXc"

rds_allocated_storage = 20

rds_engine_version    = "8.0"
 
# ECS

ecs_items_service_cpu    = 512

ecs_items_service_memory = 1024

ecs_order_service_cpu    = 512

ecs_order_service_memory = 1024
 
items_service_container_port = 3000

order_service_container_port = 3000
 
ecs_desired_count = 2
 
# Tags

common_tags = {

  Project     = "Honda-PEDI"

  ManagedBy   = "Terraform"

  Environment = "dev"

  Team        = "Honda-Development"

}

 