package com.cgi.backend.Services;

import com.cgi.backend.Models.Movie;
import com.cgi.backend.Models.WatchedMovie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchedMovieService {
    List<WatchedMovie> getAllWatchedMovies();
    void addWatchedMovie(Movie Movie);
}
