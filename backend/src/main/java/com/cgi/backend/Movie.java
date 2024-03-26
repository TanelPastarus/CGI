package com.cgi.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {


    @Id
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private int freeSeats;
    private String language;
    private String ageLimit;
    private String genre;
    private String jsonSeats;

}
