<script setup lang="ts">
import { siteConfig } from '@/config'

const { copyright } = siteConfig
</script>

<template>
  <div class="global-footer">
    <span class="global-footer__text">
      {{ copyright.prefix }} by
      <a
        class="global-footer__link"
        :href="copyright.link"
        target="_blank"
        rel="noopener noreferrer"
      >
        {{ copyright.author }}
      </a>
    </span>
  </div>
</template>

<style scoped>
.global-footer {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 8px 24px;
  font-size: 12px;
  line-height: 20px;
  color: rgba(0, 0, 0, 0.45);
  text-align: center;
  background-color: #fff;
  border-top: 1px solid #f0f0f0;
}

.global-footer__text {
  line-height: 10px;
}

.global-footer__link {
  color: #1677ff;
  transition: color 0.2s;
}

.global-footer__link:hover {
  color: #4096ff;
}

@media (max-width: 768px) {
  .global-footer {
    padding: 6px 16px;
  }
}
</style>
