import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  Query,
  ParseIntPipe,
  HttpCode,
  HttpStatus,
} from '@nestjs/common';
import {
  ApiTags,
  ApiOperation,
  ApiResponse,
  ApiParam,
  ApiQuery,
} from '@nestjs/swagger';
import { ItemsService } from './items.service';
import { CreateItemDto } from './dto/create-item.dto';
import { UpdateItemDto } from './dto/update-item.dto';
import { QueryItemDto } from './dto/query-item.dto';

@ApiTags('product')
@Controller('product')
export class ItemsController {
  constructor(private readonly itemsService: ItemsService) {}

  // ─── CREATE ──────────────────────────────────────────────────────────────────
  @Post()
  @HttpCode(HttpStatus.CREATED)
  @ApiOperation({ summary: 'Tạo item mới' })
  @ApiResponse({ status: 201, description: 'Tạo thành công' })
  @ApiResponse({ status: 409, description: 'Tên item đã tồn tại' })
  create(@Body() createItemDto: CreateItemDto) {
    return this.itemsService.create(createItemDto);
  }

  // ─── READ ALL (with pagination & filter) ────────────────────────────────────
  @Get()
  @ApiOperation({ summary: 'Lấy danh sách items (có phân trang & tìm kiếm)' })
  @ApiResponse({ status: 200, description: 'Danh sách items' })
  findAll(@Query() query: QueryItemDto) {
    return this.itemsService.findAll(query);
  }

  // ─── READ ONE ────────────────────────────────────────────────────────────────
  @Get(':id')
  @ApiOperation({ summary: 'Lấy chi tiết một item' })
  @ApiParam({ name: 'id', type: Number })
  @ApiResponse({ status: 200, description: 'Chi tiết item' })
  @ApiResponse({ status: 404, description: 'Không tìm thấy' })
  findOne(@Param('id', ParseIntPipe) id: number) {
    return this.itemsService.findOne(id);
  }

  // ─── UPDATE ──────────────────────────────────────────────────────────────────
  @Patch(':id')
  @ApiOperation({ summary: 'Cập nhật item' })
  @ApiParam({ name: 'id', type: Number })
  @ApiResponse({ status: 200, description: 'Cập nhật thành công' })
  @ApiResponse({ status: 404, description: 'Không tìm thấy' })
  update(
    @Param('id', ParseIntPipe) id: number,
    @Body() updateItemDto: UpdateItemDto,
  ) {
    return this.itemsService.update(id, updateItemDto);
  }

  // ─── SOFT DELETE ─────────────────────────────────────────────────────────────
  @Delete(':id')
  @ApiOperation({ summary: 'Xóa mềm item (soft delete)' })
  @ApiParam({ name: 'id', type: Number })
  @ApiResponse({ status: 200, description: 'Xóa thành công' })
  remove(@Param('id', ParseIntPipe) id: number) {
    return this.itemsService.remove(id);
  }

  // ─── RESTORE ─────────────────────────────────────────────────────────────────
  @Patch(':id/restore')
  @ApiOperation({ summary: 'Khôi phục item đã xóa mềm' })
  @ApiParam({ name: 'id', type: Number })
  restore(@Param('id', ParseIntPipe) id: number) {
    return this.itemsService.restore(id);
  }

  // ─── HARD DELETE ─────────────────────────────────────────────────────────────
  @Delete(':id/hard')
  @ApiOperation({ summary: 'Xóa vĩnh viễn item khỏi database' })
  @ApiParam({ name: 'id', type: Number })
  hardDelete(@Param('id', ParseIntPipe) id: number) {
    return this.itemsService.hardDelete(id);
  }
}
