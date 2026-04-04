#===============================================================================
# ALB - Application Load Balancer
# 2 Target Groups: Items Service & Order Service
# Honda PEDI Infrastructure
#===============================================================================
 
#---------------------------------------
# Application Load Balancer
#---------------------------------------
resource "aws_lb" "main" {
  name               = "${local.name_prefix}-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb.id]
  subnets            = aws_subnet.public[*].id
 
  enable_deletion_protection = false
 
  tags = {
    Name = "${local.name_prefix}-alb"
  }
}
 
#---------------------------------------
# Target Group - Items Service
#---------------------------------------
resource "aws_lb_target_group" "items_service" {
  name        = "${local.name_prefix}-items-tg"
  port        = var.items_service_container_port
  protocol    = "HTTP"
  vpc_id      = aws_vpc.main.id
  target_type = "ip"
 
  health_check {
    enabled             = true
    healthy_threshold   = 3
    unhealthy_threshold = 3
    timeout             = 10
    interval            = 30
    path                = "/api/v1/items/product"
    protocol            = "HTTP"
    matcher             = "200"
  }
 
  deregistration_delay = 30
 
  tags = {
    Name    = "${local.name_prefix}-items-tg"
    Service = "items"
  }
}
 
#---------------------------------------
# Target Group - Order Service
#---------------------------------------
resource "aws_lb_target_group" "order_service" {
  name        = "${local.name_prefix}-order-tg"
  port        = var.order_service_container_port
  protocol    = "HTTP"
  vpc_id      = aws_vpc.main.id
  target_type = "ip"
 
  health_check {
    enabled             = true
    healthy_threshold   = 3
    unhealthy_threshold = 3
    timeout             = 10
    interval            = 30
    path                = "/api/v1/order/buy"
    protocol            = "HTTP"
    matcher             = "200"
  }
 
  deregistration_delay = 30
 
  tags = {
    Name    = "${local.name_prefix}-order-tg"
    Service = "order"
  }
}
 
#---------------------------------------
# ALB Listener - HTTP (port 80)
#---------------------------------------
resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.main.arn
  port              = 80
  protocol          = "HTTP"
 
  default_action {
    type = "fixed-response"
 
    fixed_response {
      content_type = "application/json"
      message_body = "{\"message\": \"Honda PEDI - No matching route\"}"
      status_code  = "404"
    }
  }
 
  tags = {
    Name = "${local.name_prefix}-http-listener"
  }
}
 
#---------------------------------------
# Listener Rule - Items Service (/api/v1/items/*)
#---------------------------------------
resource "aws_lb_listener_rule" "items_service" {
  listener_arn = aws_lb_listener.http.arn
  priority     = 100
 
  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.items_service.arn
  }
 
  condition {
    path_pattern {
      values = ["/api/v1/items", "/api/v1/items/*"]
    }
  }
 
  tags = {
    Name    = "${local.name_prefix}-items-rule"
    Service = "items"
  }
}
 
#---------------------------------------
# Listener Rule - Order Service (/api/v1/order/*)
#---------------------------------------
resource "aws_lb_listener_rule" "order_service" {
  listener_arn = aws_lb_listener.http.arn
  priority     = 200
 
  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.order_service.arn
  }
 
  condition {
    path_pattern {
      values = ["/api/v1/order", "/api/v1/order/*"]
    }
  }
 
  tags = {
    Name    = "${local.name_prefix}-order-rule"
    Service = "order"
  }
}