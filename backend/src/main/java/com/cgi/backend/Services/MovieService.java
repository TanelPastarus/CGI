package com.cgi.backend.Services;

import com.cgi.backend.Models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface MovieService {
    List<Movie> getAllMovies();
    Optional<Movie> getMovieById(Long id);
}
