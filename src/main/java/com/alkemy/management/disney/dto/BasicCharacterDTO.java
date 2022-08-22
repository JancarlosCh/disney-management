package com.alkemy.management.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicCharacterDTO {
  private Long id;
  private String image;
  private String name;
  private int age;
  private double weight;
  private String history;
}
