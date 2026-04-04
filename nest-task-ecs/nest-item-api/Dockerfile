# ====== Stage 1: Build ======
FROM node:20-alpine AS builder

WORKDIR /app

# Copy package files trước để tận dụng cache
COPY package*.json ./

# Cài dependencies (bao gồm devDependencies để build)
RUN npm install

# Copy source code
COPY . .

# Build NestJS
RUN npm run build


# ====== Stage 2: Production ======
FROM node:20-alpine

WORKDIR /app

# Chỉ copy những gì cần thiết
COPY package*.json ./

# Chỉ cài production dependencies
RUN npm install --only=production

# Copy build từ stage builder
COPY --from=builder /app/dist ./dist

# Nếu dùng config/env file
# COPY .env .env

# Port NestJS mặc định
EXPOSE 3000

# Run app
CMD ["node", "dist/main.js"]