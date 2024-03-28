package com.cgi.backend.Repositories;

import com.cgi.backend.Models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}
