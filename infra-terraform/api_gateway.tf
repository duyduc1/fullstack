resource "aws_apigatewayv2_vpc_link" "main" {
  name               = "${local.name_prefix}-vpc-link"
  security_group_ids = [aws_security_group.alb.id]
  subnet_ids         = aws_subnet.private[*].id
 
  tags = {
    Name = "${local.name_prefix}-vpc-link"
  }
}

resource "aws_apigatewayv2_api" "main" {
  name          = "${local.name_prefix}-api"
  protocol_type = "HTTP"
  description   = "Honda PEDI API Gateway - Routes to ALB for Items and Order services"
 
  cors_configuration {
    allow_origins = ["*"]
    allow_methods = ["GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "*"]
    allow_headers = ["*"]
    expose_headers = ["*"]
    max_age       = 3600
  }
 
  tags = {
    Name = "${local.name_prefix}-api-gateway"
  }
}

resource "aws_apigatewayv2_stage" "default" {
  api_id      = aws_apigatewayv2_api.main.id
  name        = "$default"
  auto_deploy = true
 
  access_log_settings {
    destination_arn = aws_cloudwatch_log_group.api_gateway.arn
    format = jsonencode({
      requestId      = "$$context.requestId"
      ip             = "$$context.identity.sourceIp"
      requestTime    = "$$context.requestTime"
      httpMethod     = "$$context.httpMethod"
      routeKey       = "$$context.routeKey"
      status         = "$$context.status"
      protocol       = "$$context.protocol"
      responseLength = "$$context.responseLength"
      path           = "$$context.path"
      integrationError = "$$context.integrationErrorMessage"
    })
  }
 
  tags = {
    Name = "${local.name_prefix}-api-default-stage"
  }
}

resource "aws_cloudwatch_log_group" "api_gateway" {
  name              = "/apigateway/${local.name_prefix}"
  retention_in_days = 30
 
  tags = {
    Name = "${local.name_prefix}-api-gateway-logs"
  }
}
 
resource "aws_apigatewayv2_integration" "items_proxy" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  # Request parameter mapping - forward all path parameters
  request_parameters = {
    "overwrite:path" = "/api/v1/items/$$request.path.proxy"
  }
 
  payload_format_version = "1.0"
 
  description = "Items service proxy integration via ALB"
}

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
 
resource "aws_apigatewayv2_integration" "order_proxy" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  # Request parameter mapping - forward all path parameters
  request_parameters = {
    "overwrite:path" = "/api/v1/order/$$request.path.proxy"
  }
 
  payload_format_version = "1.0"
 
  description = "Order service proxy integration via ALB"
}

resource "aws_apigatewayv2_integration" "order_root" {
  api_id             = aws_apigatewayv2_api.main.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = aws_lb_listener.http.arn
  connection_type    = "VPC_LINK"
  connection_id      = aws_apigatewayv2_vpc_link.main.id
 
  request_parameters = {
    "overwrite:path" = "/api/v1/order"
  }
 
  payload_format_version = "1.0"
 
  description = "Order service root integration via ALB"
}
 
resource "aws_apigatewayv2_route" "items_proxy" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/items/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.items_proxy.id}"
}

resource "aws_apigatewayv2_route" "order_proxy" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/order/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.order_proxy.id}"
}

resource "aws_apigatewayv2_route" "items_root" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/items"
  target    = "integrations/${aws_apigatewayv2_integration.items_root.id}"
}

resource "aws_apigatewayv2_route" "order_root" {
  api_id    = aws_apigatewayv2_api.main.id
  route_key = "ANY /api/v1/order"
  target    = "integrations/${aws_apigatewayv2_integration.order_root.id}"
}