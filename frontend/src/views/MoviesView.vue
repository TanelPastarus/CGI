<template>
    <div>
      <label for="startTime">Start Time:</label>
      <select id="startTime" v-model="selectedStartTime">
      <option v-for="time in timeSlots" :key="time">{{ time }}</option>
    </select>
    <label for="genre">Genre:</label>
    <select id ="genre" v-model="selectedGenre">
      <option>All</option>
      <option v-for="genre in genres" :key="genre">{{ genre }}</option>
    </select>
    <label for="ageLimit">Age limit:</label>
    <select id ="ageLimit" v-model="selectedAgeLimit">
      <option>All</option>
      <option v-for="age in ageLimits" :key="age">{{ age }}</option>
    </select>
    <div class="item" v-for="movie in filteredMovies" :key="movie.id">
      <router-link :to="'/amovie/' + movie.id">
        <div class="title"> {{ movie.title }}  </div> <br />
        <span class="body"> {{ movie.genre }} </span> <br />
      </router-link>
    </div>
  </div>
</template>

<script>

export default {
  name: "MoviesView",
  components: {

  },
  data: function () {
    return {
      movies: [],
      selectedGenre: 'All',
      selectedStartTime: 'All',
      timeSlots: ['All','09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00'],
      selectedAgeLimit: 'All',
      genres: ['Action','Fantasy','Drama','Crime', 'Comedy', 'Family', 'Horror', 'Sci-Fi'], // Update with your genres
      ageLimits: ['G', 'PG', 'PG-13', 'R', 'NC-17']
    }
  },

  // Filter movies based on selected genre, age limit and start time
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

.title {
  text-align: left;
  font-weight: bold;
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

select {
  margin: 10px;
  padding: 10px;
  font-size: 16px;
}

.router-link {
  text-decoration: none;
  color: black;
}

</style>