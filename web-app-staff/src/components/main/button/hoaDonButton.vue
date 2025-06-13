<template>
  <div class="invoice-tabs">
    <div class="tabs-list">
      <div
        v-for="(tab, idx) in tabs"
        :key="tab.id"
        :class="['tab-item', { active: idx === activeIndex }]"
        @click="activeIndex = idx"
      >
        <span class="tab-label">{{ tab.label }}</span>
        <span class="tab-close" @click.stop="removeTab(idx)">
          <i class="fa-solid fa-xmark"></i>
        </span>
      </div>
      <button class="add-btn" @click="addTab">
        <i class="fa-solid fa-plus"></i>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const tabs = ref([{ id: 1, label: "Hóa đơn 1" }]);
const activeIndex = ref(0);

const addTab = () => {
  tabs.value.push({
    id: Date.now(),
    label: `Hóa đơn ${tabs.value.length + 1}`,
  });
  activeIndex.value = tabs.value.length - 1;
};

const removeTab = (idx) => {
  if (tabs.value.length === 1) return;
  tabs.value.splice(idx, 1);
  if (activeIndex.value >= tabs.value.length) {
    activeIndex.value = tabs.value.length - 1;
  }
};
</script>

<style scoped>
.invoice-tabs {
  background: #fff;
  border-radius: 18px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
}

.tabs-list {
  display: flex;
  align-items: center;
  gap: 4px;
  padding-left: 16px;
}

.add-btn {
  background: #fff;
  border: 1.5px solid #606266;
  color: #606266;
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color))
    inset;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 8px rgba(34, 34, 34, 0.07);
  cursor: pointer;
  transition: border-color 0.18s cubic-bezier(0.4, 0, 0.2, 1),
    box-shadow 0.18s cubic-bezier(0.4, 0, 0.2, 1), background 0.15s, color 0.15s;
  outline: none;
  position: relative;
  font-size: 20px;
}

.add-btn:hover,
.add-btn:focus {
  border-color: #606266;
  color: #606266;
  background: #f5faff;
  box-shadow: 0 4px 18px 0 rgba(51, 143, 255, 0.13);
}

.add-btn i {
  transition: transform 0.22s cubic-bezier(0.4, 0, 0.2, 1);
}

.add-btn:hover i {
  transform: rotate(180deg) scale(1.14);
}
.tab-item {
  background: #fff;
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color))
    inset;
  padding: 9px 22px 9px 16px;
  margin-right: 3px;
  border: 1.5px solid transparent;
  position: relative;
  transition: background 0.18s, border 0.16s;
  cursor: pointer;
  display: flex;
  color: #606266;
  align-items: center;
  min-width: 100px;
  font-weight: 500;
  font-size: 15px;
  border-radius: 18px;
}

.tab-item.active {
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color))
    inset;
  background: #f7f7f9;
  z-index: 1;
  color: #515356;
  font-weight: 600;
  border-radius: 18px;
}

.tab-label {
  flex: 1;
}

.tab-close {
  margin-left: 10px;
  font-size: 13px;
  color: #606266;
  cursor: pointer;
  transition: color 0.15s;
  display: flex;
  align-items: center;
}

.tab-close:hover {
  color: #d92222;
  scale: 1.13;
}
</style>
