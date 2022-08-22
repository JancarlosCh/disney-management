package com.alkemy.management.disney.util;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFilter {
  private String title;
  private Set<Long> genres;
  private String order;

  public MovieFilter(String title, Set<Long> genres, String order) {
    this.title = title;
    this.genres = genres;
    this.order = order;
  }

  public boolean isASC() {
    return this.order.compareToIgnoreCase("ASC") == 0;
  }

  public boolean isDESC() {
    return this.order.compareToIgnoreCase("DESC") == 0;
  }

}
