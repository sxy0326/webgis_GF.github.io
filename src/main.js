import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElmentPlus from 'element-plus';
import 'element-plus/dist/index.css'
// 将自动注册所有组件为全局组件
import dataV from '@jiaminghi/data-view'
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import * as echarts from 'echarts'


const app = createApp(App)
app.use(ElmentPlus)
app.use(router)
app.use(store)
app.use(echarts)
app.use(dataV)
app.mount('#app')