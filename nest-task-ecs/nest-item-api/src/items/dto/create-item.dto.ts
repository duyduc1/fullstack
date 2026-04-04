import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';
import {
  IsString,
  IsNotEmpty,
  IsOptional,
  IsNumber,
  IsBoolean,
  Min,
  MaxLength,
} from 'class-validator';

export class CreateItemDto {
  @ApiProperty({ example: 'MacBook Pro 16"', description: 'Tên sản phẩm' })
  @IsString()
  @IsNotEmpty()
  @MaxLength(255)
  name: string;

  @ApiPropertyOptional({ example: 'Laptop Apple M3 Pro chip', description: 'Mô tả sản phẩm' })
  @IsString()
  @IsOptional()
  description?: string;

  @ApiProperty({ example: 59990000, description: 'Giá sản phẩm (VNĐ)' })
  @IsNumber()
  @Min(0)
  price: number;

  @ApiProperty({ example: 100, description: 'Số lượng tồn kho' })
  @IsNumber()
  @Min(0)
  quantity: number;

  @ApiPropertyOptional({ example: true, description: 'Trạng thái kích hoạt' })
  @IsBoolean()
  @IsOptional()
  isActive?: boolean;
}
