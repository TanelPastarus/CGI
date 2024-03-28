package com.cgi.backend.Services;

import com.cgi.backend.Models.Movie;
import com.cgi.backend.Models.WatchedMovie;
import com.cgi.backend.Repositories.WatchedMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchedMovieServiceImpl implements WatchedMovieService {

    private final WatchedMovieRepository watchedMovieRepository;

    public WatchedMovieServiceImpl(WatchedMovieRepository watchedMovieRepository) {
        this.watchedMovieRepository = watchedMovieRepository;
    }

    @Override
    public List<WatchedMovie> getAllWatchedMovies() {
        return (List<WatchedMovie>) watchedMovieRepository.findAll();
    }

    @Override
    public void addWatchedMovie(Movie Movie) {
        WatchedMovie watchedMovie = new WatchedMovie();
        watchedMovie.setId(Movie.getId());
        watchedMovie.setTitle(Movie.getTitle());
        watchedMovie.setStartTime(Movie.getStartTime());
        watchedMovie.setLanguage(Movie.getLanguage());
        watchedMovie.setAgeLimit(Movie.getAgeLimit());
        watchedMovie.setGenre(Movie.getGenre());
        watchedMovieRepository.save(watchedMovie);
    }
}
