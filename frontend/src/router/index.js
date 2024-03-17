import { createRouter, createWebHashHistory } from 'vue-router'
import MoviesView from "../views/MoviesView.vue";
import AMovie from "../views/AMovie.vue";
const routes = [
  {
    path: '/',
    name: 'home',
    component: MoviesView
  },

  {
    path: "/amovie/:id",
    name: "AMovie",
    component: AMovie,
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
