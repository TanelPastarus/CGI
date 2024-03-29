package com.cgi.backend.Services;

import com.cgi.backend.Models.Movie;
import com.cgi.backend.Repositories.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;

    // Constructor to populate the database with some movies
    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
        int[][] array1 = populateRandomScreening();
        int[][] array2 = populateRandomScreening();
        int[][] array3 = populateRandomScreening();
        int[][] array4 = populateRandomScreening();
        int[][] array5 = populateRandomScreening();
        int[][] array6 = populateRandomScreening();
        int[][] array7 = populateRandomScreening();
        int[][] array8 = populateRandomScreening();
        int[][] array9 = populateRandomScreening();
        int[][] array10 = populateRandomScreening();

        movieRepo.save(new Movie(1L, "The Matrix", LocalDateTime.parse("2024-10-10T10:00:00"), howManyFreeSeats(array1), "English", "PG-13", "Action", setArray(array1)));
        movieRepo.save(new Movie(2L, "The Matrix Reloaded", LocalDateTime.parse("2024-10-10T12:00:00"), howManyFreeSeats(array2), "Estonian", "R", "Action", setArray(array2)));
        movieRepo.save(new Movie(3L, "The Matrix Revolutions", LocalDateTime.parse("2024-10-10T14:00:00"), howManyFreeSeats(array3), "Russian", "R", "Action", setArray(array3)));
        movieRepo.save(new Movie(4L, "The Lord of the Rings: The Fellowship of the Ring", LocalDateTime.parse("2024-10-10T16:00:00"), howManyFreeSeats(array4), "English", "PG-13", "Fantasy", setArray(array4)));
        movieRepo.save(new Movie(5L, "The Lord of the Rings: The Two Towers", LocalDateTime.parse("2024-10-10T18:00:00"), howManyFreeSeats(array5), "English", "PG-13", "Fantasy", setArray(array5)));
        movieRepo.save(new Movie(6L, "The Lord of the Rings: The Return of the King", LocalDateTime.parse("2024-10-10T20:00:00"), howManyFreeSeats(array6), "Russian", "PG-13", "Fantasy", setArray(array6)));
        movieRepo.save(new Movie(7L, "The Shawshank Redemption", LocalDateTime.parse("2024-10-10T22:00:00"), howManyFreeSeats(array7), "English", "R", "Drama", setArray(array7)));
        movieRepo.save(new Movie(8L, "The Godfather", LocalDateTime.parse("2024-10-11T10:00:00"), howManyFreeSeats(array8), "English", "NC-17", "Crime", setArray(array8)));
        movieRepo.save(new Movie(9L, "The Dark Knight", LocalDateTime.parse("2024-10-11T12:00:00"), howManyFreeSeats(array9), "English", "PG-13", "Action", setArray(array9)));
        movieRepo.save(new Movie(10L, "Kung Fu Panda 4", LocalDateTime.parse("2024-10-11T14:00:00"), howManyFreeSeats(array10), "Estonian", "G", "Family", setArray(array10)));
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepo.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepo.findById(id);
    }

    // Generate a random 10x10 array with 0s and 1s (0 = free seat, 1 = taken seat)
    public int[][] populateRandomScreening() {
        int[][] array = new int[10][10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(2);
            }
        }
        return array;
    }

    // Count how many free seats are in the screening
    public int howManyFreeSeats(int[][] array) {
        int freeSeats = 0;
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    freeSeats += 1;
                }
            }
        }
        return freeSeats;
    }

    // Convert a 2D array to a JSON string
    public String setArray(int[][] array) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
