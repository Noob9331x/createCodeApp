<script setup lang="ts">
import { RouterView } from 'vue-router'
import { message } from 'ant-design-vue'
import GlobalFooter from '@/components/GlobalFooter.vue'
import GlobalHeader from '@/components/GlobalHeader.vue'

const handleLogin = () => {
  // TODO: 接入登录流程，登录成功后用头像 + 昵称替换导航栏中的登录按钮
  message.info('登录功能开发中，敬请期待')
}
</script>

<template>
  <a-layout class="basic-layout">
    <a-layout-header class="basic-layout__header">
      <GlobalHeader @login="handleLogin" />
    </a-layout-header>

    <a-layout-content class="basic-layout__content">
      <RouterView />
    </a-layout-content>

    <a-layout-footer class="basic-layout__footer">
      <GlobalFooter />
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
.basic-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #f5f5f5;
}

/* 顶部导航栏：吸顶展示 */
.basic-layout__header {
  position: sticky;
  top: 0;
  z-index: 100;
  height: 64px;
  padding: 0;
  line-height: normal;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
}

/* 内容区域：撑满剩余高度，使页脚在内容较少时也贴住底部 */
.basic-layout__content {
  flex: 1;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 24px;
}

.basic-layout__footer {
  position: sticky;
  bottom: 0;
  z-index: 100;
  padding: 0;
}

@media (max-width: 768px) {
  .basic-layout__header {
    height: 56px;
  }

  .basic-layout__content {
    padding: 16px;
  }
}
</style>
