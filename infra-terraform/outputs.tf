
#===============================================================================

# Outputs

# Honda PEDI Infrastructure

#===============================================================================
 
#---------------------------------------

# VPC

#---------------------------------------

output "vpc_id" {

  description = "VPC ID"

  value       = aws_vpc.main.id

}
 
output "public_subnet_ids" {

  description = "Public subnet IDs"

  value       = aws_subnet.public[*].id

}
 
output "private_subnet_ids" {

  description = "Private subnet IDs"

  value       = aws_subnet.private[*].id

}
 
#---------------------------------------

# EC2

#---------------------------------------

output "ec2_instance_id" {

  description = "EC2 Source Code Server Instance ID"

  value       = aws_instance.source_code_server.id

}
 
output "ec2_public_ip" {

  description = "EC2 Source Code Server Public IP"

  value       = aws_instance.source_code_server.public_ip

}
 
output "ec2_public_dns" {

  description = "EC2 Source Code Server Public DNS"

  value       = aws_instance.source_code_server.public_dns

}
 
#---------------------------------------

# RDS MySQL

#---------------------------------------

output "rds_endpoint" {

  description = "RDS MySQL Endpoint"

  value       = aws_db_instance.mysql.endpoint

}
 
output "rds_address" {

  description = "RDS MySQL Address (hostname only)"

  value       = aws_db_instance.mysql.address

}
 
output "rds_port" {

  description = "RDS MySQL Port"

  value       = aws_db_instance.mysql.port

}
 
output "rds_connection_string" {

  description = "RDS MySQL Connection String"

  value       = "mysql -h ${aws_db_instance.mysql.address} -P ${aws_db_instance.mysql.port} -u ${var.rds_username} -p ${var.rds_password}"

  sensitive   = false

}
 
#---------------------------------------

# ECR

#---------------------------------------

output "ecr_items_service_url" {

  description = "ECR Repository URL for Items Service"

  value       = aws_ecr_repository.items_service.repository_url

}
 
output "ecr_order_service_url" {

  description = "ECR Repository URL for Order Service"

  value       = aws_ecr_repository.order_service.repository_url

}
 
output "ecr_login_command" {

  description = "ECR Docker Login Command"

  value       = "aws ecr get-login-password --region ${local.region} | docker login --username AWS --password-stdin ${local.account_id}.dkr.ecr.${local.region}.amazonaws.com"

}
 
#---------------------------------------

# ECS

#---------------------------------------

output "ecs_cluster_name" {

  description = "ECS Cluster Name"

  value       = aws_ecs_cluster.main.name

}
 
output "ecs_cluster_arn" {

  description = "ECS Cluster ARN"

  value       = aws_ecs_cluster.main.arn

}
 
output "ecs_items_service_name" {

  description = "ECS Items Service Name"

  value       = aws_ecs_service.items_service.name

}
 
output "ecs_order_service_name" {

  description = "ECS Order Service Name"

  value       = aws_ecs_service.order_service.name

}
 
#---------------------------------------

# ALB

#---------------------------------------

output "alb_dns_name" {

  description = "ALB DNS Name"

  value       = aws_lb.main.dns_name

}
 
output "alb_arn" {

  description = "ALB ARN"

  value       = aws_lb.main.arn

}
 
output "alb_zone_id" {

  description = "ALB Zone ID (for Route53)"

  value       = aws_lb.main.zone_id

}
 
output "items_target_group_arn" {

  description = "Items Service Target Group ARN"

  value       = aws_lb_target_group.items_service.arn

}
 
output "order_target_group_arn" {

  description = "Order Service Target Group ARN"

  value       = aws_lb_target_group.order_service.arn

}
 
#---------------------------------------

# API Gateway

#---------------------------------------

output "api_gateway_endpoint" {

  description = "API Gateway Endpoint URL"

  value       = aws_apigatewayv2_api.main.api_endpoint

}
 
output "api_gateway_id" {

  description = "API Gateway ID"

  value       = aws_apigatewayv2_api.main.id

}
 
#---------------------------------------

# Service URLs (via API Gateway)

#---------------------------------------

output "items_service_url" {

  description = "Items Service URL via API Gateway"

  value       = "${aws_apigatewayv2_api.main.api_endpoint}/api/v1/items"

}
 
output "order_service_url" {

  description = "Order Service URL via API Gateway"

  value       = "${aws_apigatewayv2_api.main.api_endpoint}/api/v1/order"

}
 
