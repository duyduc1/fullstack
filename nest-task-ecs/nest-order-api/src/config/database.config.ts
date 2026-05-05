import { registerAs } from '@nestjs/config';

export default registerAs('database', () => ({
  host: process.env.RDS_ENDPOINT || 'database-1.cnmyaogcoxlf.us-east-2.rds.amazonaws.com',
  port: parseInt(process.env.RDS_PORT, 10) || 3306,
  username: process.env.RDS_USERNAME || 'root',
  password: process.env.RDS_PASSWORD || 'M1aT4Tt7aeXc',
  name: process.env.RDS_DB_NAME || 'testdb',
  synchronize: process.env.DB_SYNCHRONIZE === 'true',
  logging: process.env.DB_LOGGING === 'true',
}));
