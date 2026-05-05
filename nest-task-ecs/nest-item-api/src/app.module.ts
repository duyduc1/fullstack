import { Module } from '@nestjs/common';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ItemsModule } from './items/items.module';
import databaseConfig from './config/database.config';
import { Item } from './items/item.entity';

@Module({
  imports: [
    // ── Config ──────────────────────────────────────────────────────────────
    ConfigModule.forRoot({
      isGlobal: true,
      load: [databaseConfig],
      envFilePath: '.env',
    }),

    // ── TypeORM / MySQL (AWS RDS compatible) ─────────────────────────────────
    TypeOrmModule.forRootAsync({
      inject: [ConfigService],
      useFactory: (config: ConfigService) => ({
        type: 'mysql',
        host: config.get<string>('database.host'),
        port: config.get<number>('database.port'),
        username: config.get<string>('database.username'),
        password: config.get<string>('database.password'),
        database: config.get<string>('database.name'),
        entities: [Item],
        synchronize: config.get<boolean>('database.synchronize'), // false ở production
        logging: config.get<boolean>('database.logging'),
        // AWS RDS SSL (bật khi dùng RDS thật)
        // ssl: process.env.NODE_ENV === 'production'
        //   ? { rejectUnauthorized: false }
        //   : false,
        extra: {
          connectionLimit: 10,          // connection pool
          connectTimeout: 60000,
          waitForConnections: true,
        },
        retryAttempts: 5,
        retryDelay: 3000,
      }),
    }),

    // ── Feature modules ──────────────────────────────────────────────────────
    ItemsModule,
  ],
})
export class AppModule {}
