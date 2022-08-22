package com.alkemy.management.disney.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullMovieDTO {
  private Long id;
  private String image;
  private String title;
  private LocalDate creationDate;
  private double rating;
  private String history;
  private FullGenderDTO gender;
  private Set<FullCharacterDTO> characters;
}
