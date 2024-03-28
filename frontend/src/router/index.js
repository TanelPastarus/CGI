import { createRouter, createWebHashHistory } from 'vue-router'
import MoviesView from "../views/MoviesView.vue";
import AMovie from "../views/AMovie.vue";
import WatchedMovies from '@/views/WatchedMovies.vue';
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

  {
    path: "/watchedmovies",
    name: "WatchedMovies",
    component: WatchedMovies,
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
