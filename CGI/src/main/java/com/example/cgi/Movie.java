package com.example.cgi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Movie {
    @Id
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private int freeSeats;
    private int screen;
    private String language;

    public Movie(Long id, String title, LocalDateTime startTime, int freeSeats, int screen, String language) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.freeSeats = freeSeats;
        this.screen = screen;
        this.language = language;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public int getScreen() {
        return screen;
    }

    public String getLanguage() {
        return language;
    }
}
