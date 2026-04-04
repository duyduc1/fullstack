import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // ── Global prefix ──────────────────────────────────────────────────────────
  app.setGlobalPrefix('api/v1/order');

  // ── Validation Pipe ────────────────────────────────────────────────────────
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,          // strip unknown fields
      forbidNonWhitelisted: true,
      transform: true,          // auto-transform types
    }),
  );

  // ── CORS ───────────────────────────────────────────────────────────────────
  app.enableCors({
    origin: process.env.ALLOWED_ORIGINS?.split(',') || '*',
    methods: ['GET', 'POST', 'PATCH', 'DELETE'],
    credentials: true,
  });

  // ── Swagger UI ─────────────────────────────────────────────────────────────
  const config = new DocumentBuilder()
    .setTitle('NestJS CRUD API')
    .setDescription('REST API với MySQL (AWS RDS) — NestJS + TypeORM')
    .setVersion('1.0')
    .addTag('Items', 'Quản lý sản phẩm')
    .build();

  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api/docs', app, document, {
    swaggerOptions: { persistAuthorization: true },
  });

  const port = process.env.APP_PORT || 3000;
  await app.listen(port);

  console.log(`🚀 App running:     http://localhost:${port}/api/v1`);
  console.log(`📖 Swagger docs:    http://localhost:${port}/api/docs`);
}

bootstrap();
