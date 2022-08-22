package com.alkemy.management.disney.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.management.disney.dto.SimpleMovieDTO;
import com.alkemy.management.disney.entity.MovieEntity;

@Component
public class SimpleMovieMapper {
  public SimpleMovieDTO movieEntity2SimpleMovieDTO(MovieEntity entity) {
    SimpleMovieDTO movieDTO = new SimpleMovieDTO();

    movieDTO.setImage(entity.getImage());
    movieDTO.setTitle(entity.getTitle());
    movieDTO.setCreationDate(entity.getCreationDate());

    return movieDTO;
  }

  public List<SimpleMovieDTO> listMovieEntities2SimpleMovieDTOs(Collection<MovieEntity> entities) {
    List<SimpleMovieDTO> DTOs = new ArrayList<>();

    for (MovieEntity movie : entities) {
      DTOs.add(movieEntity2SimpleMovieDTO(movie));
    }

    return DTOs;
  }
}
