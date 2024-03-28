package com.cgi.backend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchedMovie {
    @Id
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private String language;
    private String ageLimit;
    private String genre;
}
