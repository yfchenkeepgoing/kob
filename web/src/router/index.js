import { createRouter, createWebHistory } from 'vue-router'
// 引入5个页面
import PkindexView from '../views/pk/PkindexView.vue'
import RecordIndexView from './../views/record/RecordIndexView.vue'
import RanklistIndexView from './../views/ranklist/RanklistIndexView.vue'
import UserBotIndexView from './../views/user/bots/UserBotIndexView.vue'
import NotFound from './../views/error/NotFound.vue'
const routes = [
  // 根路径重定向到对战页面
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
  },
  {
    path: "/pk/", // 域名后的相对路径
    name: "pk_index",
    component: PkindexView,
  },
  {
    path: "/record/", // 域名后的相对路径
    name: "record_index",
    component: RecordIndexView,
  },
  {
    path: "/ranklist/", // 域名后的相对路径
    name: "ranklist_index",
    component: RanklistIndexView,
  },
  {
    path: "/user/bot/", // 域名后的相对路径
    name: "user_bot_index",
    component: UserBotIndexView,
  },
  {
    path: "/404/", // 域名后的相对路径
    name: "404",
    component: NotFound,
  },
  // 从上往下匹配，输入其他路径则跳转到404页面
  // :catchAll(.*)会匹配所有字符
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
