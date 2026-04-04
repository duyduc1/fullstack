resource "aws_db_subnet_group" "main" {

  name       = "${local.name_prefix}-db-subnet-group"

  subnet_ids = aws_subnet.public[*].id
 
  tags = {

    Name = "${local.name_prefix}-db-subnet-group"

  }

}
 
#---------------------------------------

# RDS MySQL Instance

#---------------------------------------

resource "aws_db_instance" "mysql" {

  identifier = "${local.name_prefix}-mysql"
 
  engine               = "mysql"

  engine_version       = var.rds_engine_version

  instance_class       = var.rds_instance_class

  allocated_storage    = var.rds_allocated_storage

  max_allocated_storage = 100
 
  db_name  = var.rds_db_name

  username = var.rds_username

  password = var.rds_password
 
  # Public Access Configuration

  publicly_accessible    = true

  db_subnet_group_name   = aws_db_subnet_group.main.name

  vpc_security_group_ids = [aws_security_group.rds.id]
 
  # Storage

  storage_type      = "gp3"

  storage_encrypted = true
 
  # Backup & Maintenance

  backup_retention_period = 1

  backup_window           = "03:00-04:00"

  maintenance_window      = "sun:04:00-sun:05:00"
 
  # Other Settings

  multi_az                  = false

  auto_minor_version_upgrade = true

  skip_final_snapshot       = true

  final_snapshot_identifier = "${local.name_prefix}-mysql-final-snapshot"

  deletion_protection       = false

  copy_tags_to_snapshot     = true
 
  # Parameter Group

  parameter_group_name = aws_db_parameter_group.mysql.name
 
  tags = {

    Name = "${local.name_prefix}-mysql"

  }

}
 
#---------------------------------------

# DB Parameter Group

#---------------------------------------

resource "aws_db_parameter_group" "mysql" {

  name   = "${local.name_prefix}-mysql-params"

  family = "mysql8.0"
 
  parameter {

    name  = "character_set_server"

    value = "utf8mb4"

  }
 
  parameter {

    name  = "collation_server"

    value = "utf8mb4_unicode_ci"

  }
 
  parameter {

    name  = "max_connections"

    value = "500"

  }
 
  tags = {

    Name = "${local.name_prefix}-mysql-params"

  }

}

 