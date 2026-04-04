import {
  Injectable,
  NotFoundException,
  ConflictException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, Like, FindManyOptions } from 'typeorm';
import { Item } from './item.entity';
import { CreateItemDto } from './dto/create-item.dto';
import { UpdateItemDto } from './dto/update-item.dto';
import { QueryItemDto } from './dto/query-item.dto';

export interface PaginatedResult<T> {
  data: T[];
  meta: {
    total: number;
    page: number;
    limit: number;
    totalPages: number;
  };
}

@Injectable()
export class ItemsService {
  constructor(
    @InjectRepository(Item)
    private readonly itemRepository: Repository<Item>,
  ) {}

  async create(createItemDto: CreateItemDto): Promise<Item> {
    const existing = await this.itemRepository.findOne({
      where: { name: createItemDto.name },
    });
    if (existing) {
      throw new ConflictException(`Item với tên "${createItemDto.name}" đã tồn tại`);
    }

    const item = this.itemRepository.create(createItemDto);
    return await this.itemRepository.save(item);
  }

  async findAll(query: QueryItemDto): Promise<PaginatedResult<Item>> {
    const { page = 1, limit = 10, search, isActive } = query;
    const skip = (page - 1) * limit;

    const options: FindManyOptions<Item> = {
      skip,
      take: limit,
      order: { createdAt: 'DESC' },
      where: {},
    };

    if (search) {
      options.where = { ...options.where as object, name: Like(`%${search}%`) };
    }

    if (isActive !== undefined) {
      options.where = { ...options.where as object, isActive };
    }

    const [data, total] = await this.itemRepository.findAndCount(options);

    return {
      data,
      meta: {
        total,
        page,
        limit,
        totalPages: Math.ceil(total / limit),
      },
    };
  }

  async findOne(id: number): Promise<Item> {
    const item = await this.itemRepository.findOne({ where: { id } });
    if (!item) {
      throw new NotFoundException(`Item #${id} không tìm thấy`);
    }
    return item;
  }

  async update(id: number, updateItemDto: UpdateItemDto): Promise<Item> {
    const item = await this.findOne(id);

    if (updateItemDto.name && updateItemDto.name !== item.name) {
      const existing = await this.itemRepository.findOne({
        where: { name: updateItemDto.name },
      });
      if (existing) {
        throw new ConflictException(`Item với tên "${updateItemDto.name}" đã tồn tại`);
      }
    }

    Object.assign(item, updateItemDto);
    return await this.itemRepository.save(item);
  }

  async remove(id: number): Promise<{ message: string }> {
    const item = await this.findOne(id);
    await this.itemRepository.softDelete(item.id);
    return { message: `Item #${id} đã được xóa thành công` };
  }

  async restore(id: number): Promise<Item> {
    await this.itemRepository.restore(id);
    return await this.findOne(id);
  }

  async hardDelete(id: number): Promise<{ message: string }> {
    const item = await this.itemRepository.findOne({
      where: { id },
      withDeleted: true,
    });
    if (!item) throw new NotFoundException(`Item #${id} không tìm thấy`);
    await this.itemRepository.delete(id);
    return { message: `Item #${id} đã bị xóa vĩnh viễn` };
  }
}
