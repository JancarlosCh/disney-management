package com.alkemy.management.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullCharacterDTO {
  private Long id;
  private String image;
  private String name;
  private int age;
  private double weight;
  private String history;
  private List<FullMovieDTO> movies;
}
