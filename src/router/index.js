import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import HomeView from '../views/HomeView.vue'
import Register from '../views/Register.vue'
import Header from '../components/Header.vue'
import Analyse from '../views/Analyse.vue'
import StoreManage from '../views/StoreManage.vue'
import Publishing from '../views/Publishing.vue'
import News from '../views/News.vue'
import SearchMap from '../views/SearchMap.vue'
import FootLeft from '../components/FootLeft.vue'
import Map from '../components/Map.vue'
import NewAn from '../views/NewAn.vue'
import Mark from '../views/Mark.vue'

const routes = [
  {
    path: '/mark',
    name: 'Mark',
    component: Mark
  },
  {
    path: '/newan',
    name: 'NewAn',
    component: NewAn
  },
  {
    path: '/map',
    name: 'Map',
    component: Map
  },
  {
    path: '/footleft',
    name: 'FootLeft',
    component: FootLeft
  },
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/header',
    name: 'Header',
    component: Header
  },
  {
    path: '/analyse',
    name: 'Analyse',
    component: Analyse
  },
  {
    path: '/manage',
    name: 'StoreManage',
    component: StoreManage
  },
  {
    path: '/upload',
    name: 'Publishing',
    component: Publishing
  },
  {
    path: '/search',
    name: 'SearchMap',
    component: SearchMap
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
}) 

export default router
