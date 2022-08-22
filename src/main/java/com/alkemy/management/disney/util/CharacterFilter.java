package com.alkemy.management.disney.util;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFilter {
  private String name;
  private Integer age;
  private Double weight;
  private Set<Long> movies;

  public CharacterFilter(String name, Integer age, Double weight, Set<Long> movies){
    this.name = name;
    this.age = age;
    this.movies = movies;
    this.weight = weight;
  }
}
