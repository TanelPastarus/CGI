package com.cgi.backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
}
