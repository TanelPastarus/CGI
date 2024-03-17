<template>
    <div class="item" v-for="movie in movies" :key="movie.id">
      <router-link :to="'/amovie/' + movie.id">
        <div class="date"> {{ movie.title }}  </div><br />
        <span class="body"> {{ movie.genre }} </span> <br />
      </router-link>
    </div>

</template>

<script>

export default {
  name: "MoviesView",
  components: {

  },
  data: function () {
    return {
      movies: []
    }
  },
  methods: {
    fetchMovies () {
      fetch("http://localhost:8080/movies")
          .then((response) => response.json())
          .then((data) => (this.movies = data))
          .catch((err) => console.log(err.message))
    }
  },

  mounted() {
    this.fetchMovies();
  }
}

</script>

<style scoped>

h1 {
  font-size: 20px;
}

.date {
  text-align: left;
  font-weight: bold;
}
a {
  text-decoration: none;
  color:#2c3e50;
  width: auto;
}
a:hover {
  text-decoration: underline;
}
.item {
  text-align: left;
  position: relative;
  display: block;
  background-color: #ddd;
  padding: 30px;
  border-radius: 10px;
  margin: 20px auto;
  width: 65%;
  height: auto;
  box-sizing: border-box;
}


button {
  background: rgb(97, 85, 85);
  margin: 20px 20px 20px 20px;
  color: white;
}
</style>