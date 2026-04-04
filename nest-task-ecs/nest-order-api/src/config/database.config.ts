import { registerAs } from '@nestjs/config';

export default registerAs('database', () => ({
  host: process.env.RDS_ENDPOINT || 'localhost',
  port: parseInt(process.env.RDS_PORT, 10) || 3306,
  username: process.env.RDS_USERNAME || 'root',
  password: process.env.RDS_PASSWORD || '',
  name: process.env.RDS_DB_NAME || 'testdb',
  synchronize: process.env.DB_SYNCHRONIZE === 'true',
  logging: process.env.DB_LOGGING === 'true',
}));
