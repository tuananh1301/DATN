import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import Login from "../views/Login.vue";
import Authenticate from "../views/Authentication.vue";
const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/authenticate',
    component: Authenticate,
  },
  {
    path: '/about',
    component: AboutView,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;