package com.example.cgi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class MovieController {
    private final MovieRepository movieRepo;

    public MovieController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
        movieRepo.save(new Movie(1L, "Kung Fu Panda 4", LocalDateTime.parse("2024-02-03T00:00:00"), 100, 3, "Eesti"));
    }
    @GetMapping("/")
    public String index(Model model) {
        List<Movie> movieList = (List<Movie>) movieRepo.findAll();
        model.addAttribute("movies", movieList.isEmpty() ? Collections.EMPTY_LIST : movieList);
        return "index";
    }
}
