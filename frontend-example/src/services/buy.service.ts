const API_BASE = process.env.VUE_APP_API_BASE_URL;

const ORDER_API = `${API_BASE}/api/v1/order/buy`;

console.log('ORDER_API loaded:', ORDER_API);

export interface Item {
  id: number;
  name: string;
  price: number;
  description?: string;
  quantity: number;
  createdAt: string;
  deletedAt?: string | null;
}

export const buyService = {
  async getAll(): Promise<Item[]> {
    if (!ORDER_API) throw new Error('API_BASE chưa được cấu hình');

    const res = await fetch(ORDER_API);
    if (!res.ok) throw new Error('Lỗi khi lấy danh sách');
    const data = await res.json();
    return Array.isArray(data) ? data : data.data || data.items || [];
  },

  async create(payload: { name: string; price: number; description?: string, quantity: number; }) {
    if (!ORDER_API) throw new Error('API_BASE chưa được cấu hình');
    const res = await fetch(ORDER_API, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    });
    if (!res.ok) {
      const err = await res.json().catch(() => ({}));
      throw new Error(err.message || 'Tạo thất bại');
    }
    return res.json();
  },

  async update(id: number, payload: { name: string; price: number; description?: string, quantity: number; }) {
    if (!ORDER_API) throw new Error('API_BASE chưa được cấu hình');
    const res = await fetch(`${ORDER_API}/${id}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    });
    if (!res.ok) {
      const err = await res.json().catch(() => ({}));
      throw new Error(err.message || 'Cập nhật thất bại');
    }
    return res.json();
  },

  async softDelete(id: number) {
    if (!ORDER_API) throw new Error('API_BASE chưa được cấu hình');
    const res = await fetch(`${ORDER_API}/${id}`, { method: 'DELETE' });
    if (!res.ok) throw new Error('Xóa mềm thất bại');
    return res.json();
  },

  async restore(id: number) {
    if (!ORDER_API) throw new Error('API_BASE chưa được cấu hình');
    const res = await fetch(`${ORDER_API}/${id}/restore`, { method: 'PATCH' });
    if (!res.ok) throw new Error('Khôi phục thất bại');
    return res.json();
  },

  async hardDelete(id: number) {
    if (!ORDER_API) throw new Error('API_BASE chưa được cấu hình');
    const res = await fetch(`${ORDER_API}/${id}/hard`, { method: 'DELETE' });
    if (!res.ok) throw new Error('Xóa vĩnh viễn thất bại');
    return res.json();
  },

};