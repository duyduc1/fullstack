api_gateway.tf
 
#===============================================================================
# API Gateway (HTTP API v2)
# 4 Routes pointing to ALB via VPC Link
# - ANY /api/v1/items/{proxy+}
# - ANY /api/v1/order/{proxy+}
# - ANY /api/v1/items
# - ANY /api/v1/order
# Full CORS + Request Parameter Mapping
# Honda PEDI Infrastructure
#===============================================================================
 
#---------------------------------------
# VPC Link for API Gateway -> ALB
#---------------------------------------
resource "aws_apigatewayv2_vpc_link" "main" {
  name               = "${local.name_prefix}-vpc-link"
  security_group_ids = [aws_security_group.alb.id]
  subnet_ids         = aws_subnet.private[*].id
 
  tags = {
    Name = "${local.name_prefix}-vpc-link"
  }
}
 
#---------------------------------------
# API Gateway - HTTP API
#---------------------------------------
resource "aws_apigatewayv2_api" "main" {
  name          = "${local.name_prefix}-api"
  protocol_type = "HTTP"
  description   = "Honda PEDI API Gateway - Routes to ALB for Items & Order services"
 
  cors_configuration {
    allow_origins = ["*"]
    allow_methods = ["GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "*]
    allow_headers = ["*"]
    expose_headers = ["*"]
    max_age       = 3600
  }
 
  tags = {
    Name = "${local.name_prefix}-api-gateway"
  }
}
 
#---------------------------------------
# API Gateway Stage - Default ($default)
#---------------------------------------
resource "aws_apigatewayv2_stage" "default" {
  api_id      = aws_apigatewayv2_api.main.id
  name        = "$default"
  auto_deploy = true
 
  access_log_settings {
    destination_arn = aws_cloudwatch_log_group.api_gateway.arn
    format = jsonencode({
      requestId      = "$context.requestId"
      ip             = "$context.identity.sourceIp"
      requestTime    = "$context.requestTime"
      httpMethod     = "$context.httpMethod"
      routeKey       = "$context.routeKey"
      status         = "$context.status"
      protocol       = "$context.protocol"
      responseLength = "$context.responseLength"
      path           = "$context.path"
      integrationError = "$context.integrationErrorMessage"
    })
  }
 
  tags = {
    Name = "${local.name_prefix}-api-default-stage"
  }
}
 
#---------------------------------------
# CloudWatch Log Group for API Gateway
#---------------------------------------
resource "aws_cloudwatch_log_group" "api_gateway" {
  name              = "/apigateway/${local.name_prefix}"
  retention_in_days = 30
 
  tags = {
    Name = "${local.name_prefix}-api-gateway-logs"
  }
}
 
#===============================================================================
# INTEGRATIONS - ALB via VPC Link
#===============================================================================
 
#---------------------------------------
# Integration - Items Service (for proxy+ path)
#---------------------------------------
resource "aws_apigatewayv2_integration" "items_proxy" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  # Request parameter mapping - forward all path parameters
  request_parameters = {
    "overwrite:path" = "/api/v1/items/$request.path.proxy"
  }
 
  payload_format_version = "1.0"
 
  description = "Items service proxy integration via ALB"
}
 
#---------------------------------------
# Integration - Items Service (for root path)
#---------------------------------------
resource "aws_apigatewayv2_integration" "items_root" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  # Request parameter mapping - forward to root items path
  request_parameters = {
    "overwrite:path" = "/api/v1/items"
  }
 
  payload_format_version = "1.0"
 
  description = "Items service root integration via ALB"
}
 
#---------------------------------------
# Integration - Order Service (for proxy+ path)
#---------------------------------------
resource "aws_apigatewayv2_integration" "order_proxy" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  # Request parameter mapping - forward all path parameters
  request_parameters = {
    "overwrite:path" = "/api/v1/order/$request.path.proxy"
  }
 
  payload_format_version = "1.0"
 
  description = "Order service proxy integration via ALB"
}
 
#---------------------------------------
# Integration - Order Service (for root path)
#---------------------------------------
resource "aws_apigatewayv2_integration" "order_root" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  # Request parameter mapping - forward to root order path
  request_parameters = {
    "overwrite:path" = "/api/v1/order"
  }
 
  payload_format_version = "1.0"
 
  description = "Order service root integration via ALB"
}
 
#===============================================================================
# ROUTES - 4 Routes as required
#===============================================================================
 
#---------------------------------------
# Route 1: ANY /api/v1/items/{proxy+}
#---------------------------------------
resource "aws_apigatewayv2_route" "items_proxy" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/items/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.items_proxy.id}"
}
 
#---------------------------------------
# Route 2: ANY /api/v1/order/{proxy+}
#---------------------------------------
resource "aws_apigatewayv2_route" "order_proxy" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/order/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.order_proxy.id}"
}
 
#---------------------------------------
# Route 3: ANY /api/v1/items (root path)
#---------------------------------------
resource "aws_apigatewayv2_route" "items_root" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/items"
  target    = "integrations/${aws_apigatewayv2_integration.items_root.id}"
}
 
#---------------------------------------
# Route 4: ANY /api/v1/order (root path)
#---------------------------------------
resource "aws_apigatewayv2_route" "order_root" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/order"
  target    = "integrations/${aws_apigatewayv2_integration.order_root.id}"
}