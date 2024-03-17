<template>
  <body>
  <div class="movie-card">
    <h2>{{ movie.title }}</h2>
    <div class="movie-details">
      <p><span>Genre:</span> {{ movie.genre }}</p>
      <p><span>Starting Time:</span> {{ formatStartingTime(movie.startTime) }}</p>
      <p><span>Free Seats:</span> {{ movie.freeSeats }}</p>
      <p><span>Language:</span> {{ movie.language }}</p>
      <p><span>Age Limit:</span> {{ movie.ageLimit }}</p>
    </div>
  </div>
  </body>
</template>

<script>
export default {
  name: "AMovie",
  data() {
    return {
      movie: {
        id: "",
        title: "",
        startTime: "",
        freeSeats: "",
        language: "",
        ageLimit: "",
        genre: "",
      },
    };
  },

  methods: {
    fetchAMovie(id) {
      fetch(`http://localhost:8080/movie/${id}`)
          .then((response) => response.json())
          .then((data) => (this.movie = data))
          .then((data) => console.log(data))
          .catch((err) => console.log(err.message));

    },
  formatStartingTime(startingTime) {
    const date = new Date(startingTime);
    const options = { month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', hour12: false };
    return date.toLocaleDateString('en-GB', options);
  },
  },
    mounted() {
      this.fetchAMovie(this.$route.params.id);
    },

  }

</script>

<style scoped>

body {
  display: flex;
  justify-content: center;
}


.movie-card {

  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  margin: 20px;
  width: 300px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.movie-card h2 {
  margin-top: 0;
}
.movie-card p {
  margin: 5px 0;
}
.movie-details {
  font-size: 14px;
}
.movie-details span {
  font-weight: bold;
}

</style>