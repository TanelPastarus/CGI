package com.cgi.backend.Controller;

import com.cgi.backend.Models.Movie;
import com.cgi.backend.Models.WatchedMovie;
import com.cgi.backend.Services.MovieService;
import com.cgi.backend.Services.WatchedMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;
    private final WatchedMovieService watchedMovieService;


    public MovieController(MovieService movieService, WatchedMovieService watchedMovieService) {
        this.movieService = movieService;
        this.watchedMovieService = watchedMovieService;
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        if (movieService.getMovieById(id).isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(movieService.getMovieById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/watchedMovies")
    public ResponseEntity<List<WatchedMovie>> getWatchedMovies() {
        return new ResponseEntity<>(watchedMovieService.getAllWatchedMovies(), HttpStatus.OK);
    }

    @PostMapping("/watchedMovies/add")
    public ResponseEntity<WatchedMovie> addWatchedMovie(@RequestBody Movie Movie) {
        if (Movie == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            watchedMovieService.addWatchedMovie(Movie);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


}
