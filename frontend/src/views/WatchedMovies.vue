<template>
    <div>
    <div class="item" v-for="movie in filteredMovies" :key="movie.id">
        <div class="title"> {{ movie.title }}  </div> <br />
        <span class="body"> {{ movie.genre }} </span> <br />
    </div>
  </div>
</template>

<script>

export default {
  name: "WatchedMovies",
  components: {

  },
  data: function () {
    return {
      movies: [],
      selectedGenre: 'All',
      selectedStartTime: 'All',
      timeSlots: ['All','09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00'],
      selectedAgeLimit: 'All',
      genres: ['Action','Fantasy','Drama','Crime', 'Comedy', 'Family', 'Horror', 'Sci-Fi'], 
      ageLimits: ['G', 'PG', 'PG-13', 'R', 'NC-17']
    }
  },
  computed: {
    filteredMovies() {
      return this.movies.filter(movie => 
        (this.selectedGenre === 'All' || movie.genre === this.selectedGenre) &&
        (this.selectedAgeLimit === 'All'|| movie.ageLimit === this.selectedAgeLimit) &&
        (this.selectedStartTime === 'All' || new Date(movie.startTime).getHours() >= this.selectedStartTime.split(':')[0])
      );
    }

  },
  methods: {
    fetchMovies () {
      fetch("http://localhost:8080/watchedMovies")
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

.title {
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
  transition: 0.3s;
  width: 65%;
  height: auto;
  box-sizing: border-box;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
}

.item:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}



button {
  background: rgb(97, 85, 85);
  margin: 20px 20px 20px 20px;
  color: white;
}

select {
  margin: 10px;
  padding: 10px;
  font-size: 16px;
}

.router-link {
  text-decoration: none;
  color: black;
}

.row {
  display: flex;
}
.box {
  width: 20px;
  height: 20px;
  border: 1px solid black;
}
.box.colored {
  background-color: blue;
}

</style>