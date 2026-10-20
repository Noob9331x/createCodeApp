import logoUrl from '@/assets/logo.png'

/**
 * 导航菜单项配置
 */
export interface MenuItemConfig {
  /** 菜单唯一标识，同时用于 a-menu 的 selectedKeys 匹配 */
  key: string
  /** 菜单展示文案 */
  label: string
  /** 点击菜单后跳转的路由地址，需与 router 中注册的 path 保持一致 */
  path: string
}

/**
 * 站点全局配置
 */
export const siteConfig = {
  /** 网站标题，展示在导航栏 Logo 右侧 */
  title: '代码生成平台',
  /** 网站 Logo */
  logo: logoUrl,
  /** 底部版权信息 */
  copyright: {
    /** 版权主体文案 */
    prefix: '代码生成',
    /** 作者昵称 */
    author: 'Noob9331x',
    /** 作者主页链接 */
    link: 'https://github.com/Noob9331x',
  },
}

/**
 * 默认导航菜单项配置，可通过 GlobalHeader 的 menuItems 属性覆盖
 */
export const menuItems: MenuItemConfig[] = [
  { key: 'home', label: '首页', path: '/' },
  { key: 'about', label: '关于', path: '/about' },
]
