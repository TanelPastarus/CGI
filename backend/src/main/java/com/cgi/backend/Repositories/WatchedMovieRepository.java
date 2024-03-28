package com.cgi.backend.Repositories;

import com.cgi.backend.Models.WatchedMovie;
import org.springframework.data.repository.CrudRepository;

public interface WatchedMovieRepository extends CrudRepository<WatchedMovie, Long> {
}
