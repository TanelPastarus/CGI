package com.cgi.backend;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepo;

    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepo.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepo.findById(id).get();
    }
}
