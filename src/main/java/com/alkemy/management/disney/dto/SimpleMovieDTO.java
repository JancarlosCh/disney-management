package com.alkemy.management.disney.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleMovieDTO {
  private String image;
  private String title;
  private LocalDate creationDate;
}
