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

      <div class="seat-selection">
        <label for="numSeats">Number of seats:</label>
        <input id="numSeats" v-model.number="numSeats" type="number" min="1">
        <button @click="chooseSeats">Choose seats</button>
        <button @click="addMovie">Save Movie</button>
      </div>

      <div class="big-box">
        <div  v-for="(row, rowIndex) in parsedArray" :key="rowIndex" class="row">
          <div v-for="(value, colIndex) in row" :key="colIndex" :class="{ 'small-box': true, 'colored': value === 1, 'colored2': value === 2}"
          @click="toggleSeat(rowIndex, colIndex)">
          </div>
        </div>
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
        jsonSeats:""
      },
      parsedArray: [],
      numSeats: 1,
      seatt: [],
    };
  },

  watch: {
  'movie.jsonSeats': function(newVal) {
    if (newVal) {
      this.parsedArray = JSON.parse(newVal);
    }
  },
},

  methods: {
    fetchAMovie(id) {
      fetch(`http://localhost:8080/movie/${id}`)
          .then((response) => response.json())
          .then((data) => (this.movie = data))
          .then((data) => console.log(data))
          .catch((err) => console.log(err.message));

    },
    chooseSeats() {
      const seats = this.findSeats(this.numSeats);
      console.log(seats);
      if (seats) {
        console.log(seats);
      } else {
        alert("No available seats");
      }
    },

    toggleSeat(rowIndex, colIndex) {
      let selectedSeats = this.parsedArray.flat().filter(value => value === 2).length;
  
      if (this.parsedArray[rowIndex][colIndex] === 1) 
        return;
      
      if (selectedSeats === this.numSeats){
        this.parsedArray[this.seatt[0][0]][this.seatt[0][1]] = 0;
        this.seatt.splice(0, 1);
        selectedSeats = this.parsedArray.flat().filter(value => value === 2).length;
      }

      if (selectedSeats < this.numSeats){
      this.parsedArray[rowIndex][colIndex] = this.parsedArray[rowIndex][colIndex] === 0 ? 2 : 0;
      this.seatt.push([rowIndex, colIndex]);
      }

      else {      
        this.parsedArray[rowIndex][colIndex] = this.parsedArray[rowIndex][colIndex] = 0
      }
  },
    

  formatStartingTime(startingTime) {
    const date = new Date(startingTime);
    const options = { month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', hour12: false };
    return date.toLocaleDateString('en-GB', options);
  },

  addMovie() {
      var data = {
        "id": this.movie.id,
        "title": this.movie.title,
        "startTime": this.movie.startTime,
        "language": this.movie.language,
        "ageLimit": this.movie.ageLimit,
        "genre": this.movie.genre,
      };
      fetch("http://localhost:8080/watchedMovies/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
        console.log("error");
      });
    },

  findSeats(n) {
  let closestSeats = null;
  let minDistance = Infinity;
  console.log(this.parsedArray);

  for (let row = 0; row < this.parsedArray.length; row++) {
    for (let col = 0; col <= this.parsedArray[row].length - n; col++) {
      let seats = [];
      for (let k = 0; k < n; k++) {
        if (this.parsedArray[row][col + k] === 0) {
          seats.push([row, col + k]);
        } else {
          seats = [];
          break;
        }
      }

      if (seats.length === n) {
        const center = [Math.floor(this.parsedArray.length / 2), Math.floor(this.parsedArray[0].length / 2)];
        const distance = seats.reduce((acc, [row, col]) => acc + Math.abs(center[0] - row) + Math.abs(center[1] - col), 0);
        if (distance < minDistance) {
          minDistance = distance;
          closestSeats = seats;
        }
      }
    }
  }

  if (closestSeats) {
    for (const [row, col] of closestSeats) {
      this.parsedArray[row][col] = 2;
    }
    for (const [row, col] of this.seatt) {
      this.parsedArray[row][col] = 0;
    }
  }

  
  this.seatt = closestSeats;
  return closestSeats;
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
  justify-content: center;
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

.big-box {
  padding: 20px;
  grid-gap: 1px; 
  align-items: center; 
  justify-content: center;

}

.small-box {
  width: 25px; 
  height: 25px; 
  border: 1px solid #000;
  margin: 1px;
  
}
.row {
  justify-content: center;
  display: flex;
}

.colored {
  background-color: red; 
}

.colored2 {
  background-color: yellow; 
}

</style>