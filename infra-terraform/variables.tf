#===============================================================================

# Variables - Honda PEDI Infrastructure

#===============================================================================
 
variable "aws_region" {

  description = "AWS Region to deploy resources"

  type        = string

  default     = "ap-southeast-1"

}
 
variable "project_name" {

  description = "Project name used for resource naming"

  type        = string

  default     = "honda-pedi"

}
 
variable "environment" {

  description = "Environment (dev, staging, prod)"

  type        = string

  default     = "dev"

}
 
#===============================================================================

# VPC & Networking

#===============================================================================
 
variable "vpc_cidr" {

  description = "CIDR block for VPC"

  type        = string

  default     = "10.0.0.0/16"

}
 
variable "public_subnet_cidrs" {

  description = "CIDR blocks for public subnets"

  type        = list(string)

  default     = ["10.0.1.0/24", "10.0.2.0/24"]

}
 
variable "private_subnet_cidrs" {

  description = "CIDR blocks for private subnets"

  type        = list(string)

  default     = ["10.0.10.0/24", "10.0.20.0/24"]

}
 
variable "availability_zones" {

  description = "Availability zones"

  type        = list(string)

  default     = ["ap-southeast-1a", "ap-southeast-1b"]

}
 
#===============================================================================

# EC2

#===============================================================================
 
variable "ec2_instance_type" {

  description = "EC2 instance type for source code server"

  type        = string

  default     = "c7i-flex.large"

}
 
variable "ec2_key_name" {

  description = "SSH key pair name for EC2"

  type        = string

  default     = "key-aws"

}
 
variable "ec2_ami_id" {

  description = "AMI ID for EC2 instance (Ubuntu 24.04 LTS)"

  type        = string

  default     = "ami-0ec10929233384c7f" # Will use data source if empty

}
 
#===============================================================================

# RDS MySQL

#===============================================================================
 
variable "rds_instance_class" {

  description = "RDS instance class"

  type        = string

  default     = "db.t3.medium"

}
 
variable "rds_db_name" {

  description = "Database name"

  type        = string

  default     = "honda_pedi_db"

}
 
variable "rds_username" {

  description = "Database master username"

  type        = string

  default     = "root"

}
 
variable "rds_password" {

  description = "Database master password"

  type        = string

  default     = "M1aT4Tt7aeXc"

  sensitive   = true

}
 
variable "rds_allocated_storage" {

  description = "RDS allocated storage in GB"

  type        = number

  default     = 20

}
 
variable "rds_engine_version" {

  description = "MySQL engine version"

  type        = string

  default     = "8.0"

}
 
#===============================================================================

# ECS

#===============================================================================
 
variable "ecs_items_service_cpu" {

  description = "CPU units for items service task"

  type        = number

  default     = 512

}
 
variable "ecs_items_service_memory" {

  description = "Memory (MiB) for items service task"

  type        = number

  default     = 1024

}
 
variable "ecs_order_service_cpu" {

  description = "CPU units for order service task"

  type        = number

  default     = 512

}
 
variable "ecs_order_service_memory" {

  description = "Memory (MiB) for order service task"

  type        = number

  default     = 1024

}
 
variable "items_service_container_port" {

  description = "Container port for items service"

  type        = number

  default     = 3000

}
 
variable "order_service_container_port" {

  description = "Container port for order service"

  type        = number

  default     = 3000

}
 
variable "ecs_desired_count" {

  description = "Desired number of ECS tasks"

  type        = number

  default     = 2

}
 
#===============================================================================

# Tags

#===============================================================================
 
variable "common_tags" {

  description = "Common tags for all resources"

  type        = map(string)

  default = {

    Project     = "Honda-PEDI"

    ManagedBy   = "Terraform"

    Environment = "dev"

  }

}

 