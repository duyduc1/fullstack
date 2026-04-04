# 🚀 NestJS CRUD API — MySQL / AWS RDS

REST API xây dựng bằng **NestJS + TypeORM + MySQL**, hỗ trợ kết nối **AWS RDS** và đóng gói bằng **Docker**.

---

## 📁 Cấu trúc dự án

```
nestjs-crud-api/
├── src/
│   ├── config/
│   │   └── database.config.ts      # Cấu hình DB từ biến môi trường
│   ├── items/
│   │   ├── dto/
│   │   │   ├── create-item.dto.ts  # Validation khi tạo
│   │   │   ├── update-item.dto.ts  # Validation khi cập nhật
│   │   │   └── query-item.dto.ts   # Phân trang & tìm kiếm
│   │   ├── item.entity.ts          # TypeORM entity (bảng items)
│   │   ├── items.controller.ts     # REST endpoints
│   │   ├── items.service.ts        # Business logic
│   │   └── items.module.ts
│   ├── app.module.ts               # Root module
│   └── main.ts                     # Bootstrap + Swagger
├── mysql/init/
│   └── 01_init.sql                 # Script khởi tạo DB
├── Dockerfile                      # Multi-stage build
├── docker-compose.yml              # Local (MySQL + App + phpMyAdmin)
├── docker-compose.rds.yml          # Override cho AWS RDS thật
├── .env                            # Biến môi trường local
└── .env.example                    # Template biến môi trường
```

---

## 🔧 Cài đặt & Chạy

### Cách 1 — Docker Compose (khuyến nghị)

```bash
# Clone & vào thư mục
git clone <repo-url> && cd nestjs-crud-api

# Copy env
cp .env.example .env

# Build & start (MySQL + App)
docker compose up --build -d

# Kèm phpMyAdmin (UI quản lý DB)
docker compose --profile dev up --build -d

# Xem log
docker compose logs -f app
```

### Cách 2 — Chạy local (cần MySQL sẵn)

```bash
npm install
cp .env.example .env   # điền thông tin DB vào .env
npm run start:dev
```

---

## 🌐 API Endpoints

Base URL: `http://localhost:3000/api/v1`

| Method   | Endpoint              | Mô tả                          |
|----------|-----------------------|--------------------------------|
| `POST`   | `/items`              | Tạo item mới                   |
| `GET`    | `/items`              | Danh sách (phân trang, search) |
| `GET`    | `/items/:id`          | Chi tiết một item              |
| `PATCH`  | `/items/:id`          | Cập nhật item                  |
| `DELETE` | `/items/:id`          | Xóa mềm (soft delete)          |
| `PATCH`  | `/items/:id/restore`  | Khôi phục item đã xóa          |
| `DELETE` | `/items/:id/hard`     | Xóa vĩnh viễn khỏi DB          |

### Query params cho `GET /items`

| Param      | Kiểu    | Ví dụ       | Mô tả                    |
|------------|---------|-------------|--------------------------|
| `page`     | number  | `1`         | Trang hiện tại           |
| `limit`    | number  | `10`        | Số item mỗi trang        |
| `search`   | string  | `MacBook`   | Tìm kiếm theo tên        |
| `isActive` | boolean | `true`      | Lọc theo trạng thái      |

### Swagger UI

```
http://localhost:3000/api/docs
```

---

## ☁️ Kết nối AWS RDS

### 1. Dùng docker-compose với RDS thật

```bash
# Tạo file .env.rds
cat > .env.rds << EOF
RDS_ENDPOINT=mydb.xxxx.ap-southeast-1.rds.amazonaws.com
RDS_PORT=3306
RDS_USERNAME=admin
RDS_PASSWORD=your_strong_password
RDS_DB_NAME=nestjs_crud_db
EOF

# Start với override RDS
docker compose \
  -f docker-compose.yml \
  -f docker-compose.rds.yml \
  --env-file .env.rds \
  up --build -d
```

### 2. Security Group AWS RDS

Mở inbound rule trên Security Group của RDS:
- **Type**: MySQL/Aurora
- **Port**: 3306
- **Source**: IP của EC2 / ECS task chạy app

### 3. SSL (production)

Bỏ comment đoạn `ssl` trong `app.module.ts`:

```typescript
ssl: process.env.NODE_ENV === 'production'
  ? { rejectUnauthorized: false }
  : false,
```

---

## 🧪 Test nhanh bằng curl

```bash
# Tạo item
curl -X POST http://localhost:3000/api/v1/items \
  -H "Content-Type: application/json" \
  -d '{"name":"MacBook Pro","description":"Laptop Apple","price":59990000,"quantity":50}'

# Lấy danh sách (trang 1, 5 item/trang, tìm "Mac")
curl "http://localhost:3000/api/v1/items?page=1&limit=5&search=Mac"

# Cập nhật
curl -X PATCH http://localhost:3000/api/v1/items/1 \
  -H "Content-Type: application/json" \
  -d '{"price":54990000}'

# Xóa mềm
curl -X DELETE http://localhost:3000/api/v1/items/1

# Khôi phục
curl -X PATCH http://localhost:3000/api/v1/items/1/restore
```

---

## 🛠️ Biến môi trường

| Biến            | Mặc định    | Mô tả                              |
|-----------------|-------------|------------------------------------|
| `APP_PORT`      | `3000`      | Port ứng dụng                      |
| `NODE_ENV`      | `development` | Môi trường                       |
| `DB_HOST`       | `mysql`     | Host MySQL / RDS endpoint          |
| `DB_PORT`       | `3306`      | Port MySQL                         |
| `DB_USERNAME`   | —           | Username                           |
| `DB_PASSWORD`   | —           | Password                           |
| `DB_NAME`       | —           | Tên database                       |
| `DB_SYNCHRONIZE`| `true`      | Auto-sync schema (**false ở prod**)|
| `DB_LOGGING`    | `false`     | Log SQL queries                    |

---

## 📋 Lệnh hữu ích

```bash
# Dừng tất cả services
docker compose down

# Dừng & xóa volumes (reset DB)
docker compose down -v

# Rebuild image sau khi sửa code
docker compose up --build -d app

# Vào shell container app
docker compose exec app sh

# Vào MySQL CLI
docker compose exec mysql mysql -u nestjs_user -pnestjs_password nestjs_crud_db
```
