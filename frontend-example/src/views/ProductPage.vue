<template>
  <div class="crud-app">
    <!-- Header -->
    <header class="app-header">
      <div class="header-content">
        <h1>🛒 Quản Lý Sản Phẩm </h1>
      </div>
      <button @click="refreshList" class="btn btn-refresh" :disabled="loading">
        🔄 Làm mới
      </button>
    </header>

    <!-- Controls -->
    <div class="controls">
      <div class="search-box">
        <input
          v-model="searchTerm"
          type="text"
          placeholder="🔍 Tìm theo tên sản phẩm..."
          class="search-input"
          @keyup.enter="handleSearch"
        />
      </div>
      <button @click="openCreateModal" class="btn btn-primary">
        ➕ Thêm sản phẩm mới
      </button>
    </div>

    <!-- Table -->
    <div class="table-container">
      <table class="data-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Giá (VNĐ)</th>
            <th>Số lượng</th>
            <th>Mô tả</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="loading" class="loading-row">
            <td colspan="7">Đang tải dữ liệu...</td>
          </tr>
          <tr v-else-if="paginatedItems.length === 0">
            <td colspan="7" class="empty-row">Không có sản phẩm nào</td>
          </tr>
          <tr v-for="item in paginatedItems" :key="item.id" :class="{ deleted: !!item.deletedAt }">
            <td class="id-cell">{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td class="price-cell">{{ formatPrice(item.price) }}</td>
            <td class="qty-cell">{{ item.quantity ?? '—' }}</td>
            <td class="desc-cell">{{ item.description || '—' }}</td>
            <td>
              <span class="status-badge" :class="{ active: !item.deletedAt, deleted: !!item.deletedAt }">
                {{ item.deletedAt ? 'Đã xóa' : 'Hoạt động' }}
              </span>
            </td>
            <td class="actions-cell">
              <div class="action-buttons">
                <button v-if="!item.deletedAt" @click="openEditModal(item)" class="btn btn-edit" title="Chỉnh sửa">✏️</button>
                <button v-if="!item.deletedAt" @click="softDeleteItem(item.id)" class="btn btn-delete" title="Xóa mềm">🗑️</button>
                <button v-if="item.deletedAt" @click="restoreItem(item.id)" class="btn btn-restore" title="Khôi phục">🔄</button>
                <button v-if="item.deletedAt" @click="hardDeleteItem(item.id)" class="btn btn-hard-delete" title="Xóa vĩnh viễn">💀</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Pagination -->
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1" class="btn btn-page">Trước</button>
      <span class="page-info">Trang {{ currentPage }} / {{ totalPages }} • {{ totalItems }} sản phẩm</span>
      <button @click="nextPage" :disabled="currentPage === totalPages" class="btn btn-page">Sau</button>
    </div>

    <!-- Modal -->
    <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
      <div class="modal-card" @click.stop>
        <h2>{{ isEditing ? 'Chỉnh sửa sản phẩm' : 'Thêm sản phẩm mới' }}</h2>
        <form @submit.prevent="saveItem">
          <div class="form-group">
            <label>Tên sản phẩm <span class="required">*</span></label>
            <input v-model="form.name" type="text" required class="form-input" />
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>Giá (VNĐ) <span class="required">*</span></label>
              <input v-model.number="form.price" type="number" min="0" required class="form-input" />
            </div>
            <div class="form-group">
              <label>Số lượng <span class="required">*</span></label>
              <input v-model.number="form.quantity" type="number" min="0" required class="form-input" placeholder="0" />
            </div>
          </div>

          <div class="form-group">
            <label>Mô tả</label>
            <textarea v-model="form.description" rows="4" class="form-textarea"></textarea>
          </div>

          <div class="modal-actions">
            <button type="button" @click="closeModal" class="btn btn-cancel">Hủy</button>
            <button type="submit" class="btn btn-primary" :disabled="submitting">
              {{ submitting ? 'Đang lưu...' : isEditing ? 'Cập nhật' : 'Tạo mới' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue';
import { buyService, type Item } from '@/services/product.service';

const items = ref<Item[]>([]);
const loading = ref(false);
const submitting = ref(false);
const searchTerm = ref('');
const currentPage = ref(1);
const itemsPerPage = 8;
const showModal = ref(false);
const isEditing = ref(false);

// ✅ Thêm quantity vào form
const form = reactive({ name: '', price: 0, quantity: 0, description: '' });

const filteredItems = computed(() => {
  if (!searchTerm.value.trim()) return items.value;
  const term = searchTerm.value.toLowerCase().trim();
  return items.value.filter(item => item.name.toLowerCase().includes(term));
});

const totalItems = computed(() => filteredItems.value.length);
const totalPages = computed(() => Math.max(1, Math.ceil(totalItems.value / itemsPerPage)));
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  return filteredItems.value.slice(start, start + itemsPerPage);
});

const fetchItems = async () => {
  loading.value = true;
  try {
    items.value = await buyService.getAll();
  } catch (err) {
    alert('❌ Không thể kết nối API. Kiểm tra backend đang chạy chưa?');
  } finally {
    loading.value = false;
  }
};

const refreshList = async () => {
  currentPage.value = 1;
  await fetchItems();
};

const openCreateModal = () => {
  isEditing.value = false;
  form.name = '';
  form.price = 0;
  form.quantity = 0; // ✅ reset quantity
  form.description = '';
  showModal.value = true;
};

const openEditModal = (item: Item) => {
  isEditing.value = true;
  form.name = item.name;
  form.price = item.price;
  form.quantity = (item as any).quantity ?? 0; // ✅ load quantity
  form.description = item.description || '';
  (form as any).id = item.id;
  showModal.value = true;
};

const closeModal = () => {
  showModal.value = false;
  submitting.value = false;
};

const saveItem = async () => {
  submitting.value = true;
  try {
    const payload = {
      name: form.name.trim(),
      price: Number(form.price),
      quantity: Number(form.quantity), // ✅ gửi quantity lên backend
      description: form.description.trim(),
    };

    if (isEditing.value) {
      await buyService.update((form as any).id, payload);
      alert('✅ Cập nhật thành công!');
    } else {
      await buyService.create(payload);
      alert('✅ Thêm thành công!');
    }
    closeModal();
    await fetchItems();
  } catch (err: any) {
    alert('❌ ' + err.message);
  } finally {
    submitting.value = false;
  }
};

const softDeleteItem = async (id: number) => {
  if (!confirm('Xóa mềm sản phẩm này?')) return;
  try {
    await buyService.softDelete(id);
    alert('🗑️ Xóa mềm thành công');
    await fetchItems();
  } catch (err: any) { alert('❌ ' + err.message); }
};

const restoreItem = async (id: number) => {
  if (!confirm('Khôi phục sản phẩm?')) return;
  try {
    await buyService.restore(id);
    alert('🔄 Khôi phục thành công');
    await fetchItems();
  } catch (err: any) { alert('❌ ' + err.message); }
};

const hardDeleteItem = async (id: number) => {
  if (!confirm('XÓA VĨNH VIỄN? Không thể khôi phục!')) return;
  try {
    await buyService.hardDelete(id);
    alert('💀 Xóa vĩnh viễn thành công');
    await fetchItems();
  } catch (err: any) { alert('❌ ' + err.message); }
};

const handleSearch = () => { currentPage.value = 1; };
const prevPage = () => { if (currentPage.value > 1) currentPage.value--; };
const nextPage = () => { if (currentPage.value < totalPages.value) currentPage.value++; };

const formatPrice = (price: number) =>
  new Intl.NumberFormat('vi-VN').format(price) + ' VNĐ';

onMounted(fetchItems);
</script>

<style scoped>
.crud-app { font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #0f172a; color: #e2e8f0; min-height: 100vh; padding: 24px; }
.app-header { display: flex; justify-content: space-between; align-items: center; background: #1e2937; padding: 20px 28px; border-radius: 16px; margin-bottom: 24px; box-shadow: 0 10px 15px -3px rgb(0 0 0 / 0.2); }
.header-content h1 { margin: 0; font-size: 28px; font-weight: 700; color: #60a5fa; }
.controls { display: flex; gap: 16px; margin-bottom: 24px; flex-wrap: wrap; align-items: center; }
.search-box { flex: 1; min-width: 320px; }
.search-input { width: 100%; padding: 14px 20px; font-size: 16px; background: #1e2937; border: 2px solid #334155; border-radius: 9999px; color: #e2e8f0; outline: none; box-sizing: border-box; }
.search-input:focus { border-color: #60a5fa; box-shadow: 0 0 0 4px rgba(96,165,250,0.2); }
.btn { padding: 12px 24px; font-size: 15px; font-weight: 600; border-radius: 9999px; border: none; cursor: pointer; transition: all 0.3s; }
.btn-primary { background: #60a5fa; color: #0f172a; }
.btn-primary:hover { background: #3b82f6; transform: translateY(-2px); }
.btn-refresh { background: #334155; color: #e2e8f0; }
.btn-edit { background: #eab308; color: #0f172a; font-size: 18px; padding: 8px 12px; }
.btn-delete { background: #ef4444; color: #fff; font-size: 18px; padding: 8px 12px; }
.btn-restore { background: #10b981; color: #fff; font-size: 18px; padding: 8px 12px; }
.btn-hard-delete { background: #7f1d1d; color: #fff; font-size: 18px; padding: 8px 12px; }
.table-container { background: #1e2937; border-radius: 16px; overflow: hidden; box-shadow: 0 10px 15px -3px rgb(0 0 0 / 0.2); }
.data-table { width: 100%; border-collapse: collapse; }
.data-table th { background: #334155; padding: 18px 20px; text-align: left; font-weight: 600; font-size: 14px; color: #cbd5e1; }
.data-table td { padding: 18px 20px; border-top: 1px solid #334155; }
.data-table tr:hover { background: #283549; }
.data-table tr.deleted { opacity: 0.75; }
.id-cell { font-weight: 700; color: #60a5fa; width: 80px; }
.price-cell { font-weight: 600; color: #4ade80; }
.qty-cell { font-weight: 600; color: #fb923c; text-align: center; } /* ✅ style cột quantity */
.desc-cell { max-width: 300px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
.status-badge { padding: 4px 14px; border-radius: 9999px; font-size: 13px; font-weight: 600; }
.status-badge.active { background: #10b981; color: #052e16; }
.status-badge.deleted { background: #ef4444; color: #fff; }
.action-buttons { display: flex; gap: 8px; }
.pagination { display: flex; justify-content: center; align-items: center; gap: 16px; margin-top: 24px; }
.modal-overlay { position: fixed; inset: 0; background: rgba(15,23,42,0.85); display: flex; align-items: center; justify-content: center; z-index: 1000; }
.modal-card { background: #1e2937; width: 100%; max-width: 520px; border-radius: 20px; padding: 32px; box-shadow: 0 25px 50px -12px rgb(0 0 0 / 0.4); }
.form-group { margin-bottom: 20px; }
/* ✅ 2 cột Giá + Số lượng nằm cạnh nhau */
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
.form-group label { display: block; margin-bottom: 8px; font-weight: 600; color: #cbd5e1; }
.required { color: #f87171; }
.form-input, .form-textarea { width: 100%; padding: 14px 18px; background: #334155; border: 2px solid #475569; border-radius: 12px; color: #e2e8f0; font-size: 16px; box-sizing: border-box; }
.form-input:focus, .form-textarea:focus { border-color: #60a5fa; outline: none; }
.modal-actions { display: flex; gap: 12px; justify-content: flex-end; margin-top: 32px; }
.btn-cancel { background: #334155; color: #e2e8f0; }
.loading-row td, .empty-row { text-align: center; padding: 60px 20px; color: #94a3b8; }
</style>