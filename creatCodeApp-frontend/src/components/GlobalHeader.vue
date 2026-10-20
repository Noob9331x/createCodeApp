<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import { MenuOutlined, UserOutlined } from '@ant-design/icons-vue'
import { menuItems as defaultMenuItems, siteConfig, type MenuItemConfig } from '@/config'

const props = withDefaults(
  defineProps<{
    /** 网站标题 */
    title?: string
    /** 网站 Logo 地址 */
    logo?: string
    /** 导航菜单项配置 */
    menuItems?: MenuItemConfig[]
  }>(),
  {
    title: siteConfig.title,
    logo: siteConfig.logo,
    menuItems: () => defaultMenuItems,
  },
)

const emit = defineEmits<{
  /** 点击登录按钮（当前为占位，后续替换为头像 + 昵称） */
  login: []
}>()

const router = useRouter()
const route = useRoute()

/** 移动端抽屉菜单开关 */
const drawerOpen = ref(false)

/** 转换为 a-menu 需要的 items 结构 */
const antMenuItems = computed(() =>
  props.menuItems.map((item) => ({ key: item.key, label: item.label })),
)

/** 根据当前路由高亮对应菜单项，支持子路由 */
const selectedKeys = computed(() => {
  const activeItem = props.menuItems.find(
    (item) =>
      item.path === route.path || (item.path !== '/' && route.path.startsWith(`${item.path}/`)),
  )
  return activeItem ? [activeItem.key] : []
})

/** 菜单点击：跳转路由并关闭移动端抽屉 */
const handleMenuClick = ({ key }: { key: string | number }) => {
  drawerOpen.value = false
  const target = props.menuItems.find((item) => item.key === key)
  if (target && target.path !== route.path) {
    router.push(target.path)
  }
}

const handleLoginClick = () => {
  emit('login')
}

/** 路由变化时收起抽屉菜单 */
watch(
  () => route.path,
  () => {
    drawerOpen.value = false
  },
)
</script>

<template>
  <div class="global-header">
    <RouterLink to="/" class="global-header__brand" aria-label="返回首页">
      <img class="global-header__logo" :src="logo" :alt="`${title} logo`" />
      <span class="global-header__title">{{ title }}</span>
    </RouterLink>

    <!-- 桌面端横向菜单 -->
    <a-menu
      class="global-header__menu"
      mode="horizontal"
      :items="antMenuItems"
      :selected-keys="selectedKeys"
      @click="handleMenuClick"
    />

    <div class="global-header__actions">
      <a-button type="primary" @click="handleLoginClick">
        <template #icon>
          <UserOutlined />
        </template>
        登录
      </a-button>

      <!-- 移动端菜单入口 -->
      <a-button
        class="global-header__trigger"
        type="text"
        aria-label="打开导航菜单"
        @click="drawerOpen = true"
      >
        <template #icon>
          <MenuOutlined />
        </template>
      </a-button>
    </div>

    <!-- 移动端抽屉菜单 -->
    <a-drawer
      v-model:open="drawerOpen"
      placement="right"
      :width="240"
      :title="title"
      :body-style="{ padding: 0 }"
    >
      <a-menu
        mode="inline"
        :items="antMenuItems"
        :selected-keys="selectedKeys"
        @click="handleMenuClick"
      />
    </a-drawer>
  </div>
</template>

<style scoped>
.global-header {
  display: flex;
  align-items: center;
  gap: 16px;
  width: 100%;
  max-width: 1200px;
  height: 100%;
  margin: 0 auto;
  padding: 0 24px;
}

.global-header__brand {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  gap: 10px;
  color: rgba(0, 0, 0, 0.88);
}

.global-header__brand:hover {
  color: #1677ff;
}

.global-header__logo {
  display: block;
  width: 32px;
  height: 32px;
  object-fit: contain;
  border-radius: 8px;
}

.global-header__title {
  overflow: hidden;
  font-size: 17px;
  font-weight: 600;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.global-header__menu {
  flex: 1;
  min-width: 0;
  background: transparent;
  border-bottom: none;
}

.global-header__menu :deep(.ant-menu-item) {
  line-height: 64px;
}

.global-header__actions {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  gap: 8px;
  margin-left: auto;
}

.global-header__trigger {
  display: none;
  align-items: center;
  justify-content: center;
  font-size: 18px;
}

@media (max-width: 768px) {
  .global-header {
    height: 56px;
    gap: 12px;
    padding: 0 16px;
  }

  /* 小屏幕隐藏横向菜单，改用抽屉菜单 */
  .global-header__menu {
    display: none;
  }

  .global-header__trigger {
    display: inline-flex;
  }

  .global-header__title {
    font-size: 16px;
  }
}
</style>
