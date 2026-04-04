resource "aws_iam_role" "ec2_role" {

  name = "${local.name_prefix}-ec2-role"
 
  assume_role_policy = jsonencode({

    Version = "2012-10-17"

    Statement = [

      {

        Action = "sts:AssumeRole"

        Effect = "Allow"

        Principal = {

          Service = "ec2.amazonaws.com"

        }

      }

    ]

  })
 
  tags = {

    Name = "${local.name_prefix}-ec2-role"

  }

}
 
# ECR access for EC2 (to push/pull images)

resource "aws_iam_role_policy" "ec2_ecr_policy" {

  name = "${local.name_prefix}-ec2-ecr-policy"

  role = aws_iam_role.ec2_role.id
 
  policy = jsonencode({

    Version = "2012-10-17"

    Statement = [

      {

        Effect = "Allow"

        Action = [

          "ecr:GetAuthorizationToken",

          "ecr:BatchCheckLayerAvailability",

          "ecr:GetDownloadUrlForLayer",

          "ecr:BatchGetImage",

          "ecr:PutImage",

          "ecr:InitiateLayerUpload",

          "ecr:UploadLayerPart",

          "ecr:CompleteLayerUpload",

          "ecr:DescribeRepositories",

          "ecr:ListImages"

        ]

        Resource = "*"

      },

      {

        Effect = "Allow"

        Action = [

          "ssm:StartSession",

          "ssm:DescribeSessions",

          "ssm:GetConnectionStatus",

          "ssmmessages:*",

          "ec2messages:*"

        ]

        Resource = "*"

      }

    ]

  })

}
 
resource "aws_iam_instance_profile" "ec2_profile" {

  name = "${local.name_prefix}-ec2-profile"

  role = aws_iam_role.ec2_role.name

}
 
#---------------------------------------

# EC2 Instance

#---------------------------------------

resource "aws_instance" "source_code_server" {

  ami                    = local.ami_id

  instance_type          = var.ec2_instance_type

  key_name               = var.ec2_key_name

  subnet_id              = aws_subnet.public[0].id

  vpc_security_group_ids = [aws_security_group.ec2.id]

  iam_instance_profile   = aws_iam_instance_profile.ec2_profile.name
 
  associate_public_ip_address = true
 
  root_block_device {

    volume_type           = "gp3"

    volume_size           = 50

    delete_on_termination = true

    encrypted             = true

  }
 
  user_data = <<-EOF

    #!/bin/bash

    set -ex
 
    # Update system (Ubuntu uses apt)

    apt-get update -y

    apt-get upgrade -y
 
    # Install prerequisites

    apt-get install -y ca-certificates curl gnupg lsb-release unzip
 
    # Install Docker (official Docker Engine for Ubuntu 24.04)

    sudo apt update

    sudo apt install -y apt-transport-https ca-certificates curl software-properties-common

    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

    echo "deb [signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

    sudo apt update

    sudo apt install -y docker-ce

    sudo systemctl start docker

    sudo systemctl enable docker

    sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

    sudo chmod +x /usr/local/bin/docker-compose

    docker --version

    docker-compose --version
 
    # Install AWS CLI v2

    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"

    unzip awscliv2.zip

    ./aws/install

    # Database install

    sudo apt install mysql-server -y

  EOF
 
  tags = {

    Name = "${local.name_prefix}-source-code-server"

  }

}

 