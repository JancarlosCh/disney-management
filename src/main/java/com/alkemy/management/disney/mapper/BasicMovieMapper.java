package com.alkemy.management.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.management.disney.dto.BasicMovieDTO;
import com.alkemy.management.disney.entity.MovieEntity;

@Component
public class BasicMovieMapper {

  @Autowired
  FullGenderMapper fullGenderMapper;

  // Convierte un DTO básico de película a una entidad pélicula
  public MovieEntity basicMovieDTO2Entity(BasicMovieDTO dto) {

    MovieEntity movieEntity = new MovieEntity();

    movieEntity.setImage(dto.getImage());
    movieEntity.setTitle(dto.getTitle());
    movieEntity.setCreationDate(dto.getCreationDate());
    movieEntity.setRating(dto.getRating());
    movieEntity.setHistory(dto.getHistory());
    movieEntity.setGender(fullGenderMapper.genderDTO2Entity(dto.getGender()));

    return movieEntity;
  }

  // Convierte una entidad película a un DTO básico de película
  public BasicMovieDTO movieEntity2BasicMovieDTO(MovieEntity entity) {

    BasicMovieDTO basicMovieDTO = new BasicMovieDTO();

    basicMovieDTO.setId(entity.getId());
    basicMovieDTO.setImage(entity.getImage());
    basicMovieDTO.setTitle(entity.getTitle());
    basicMovieDTO.setCreationDate(entity.getCreationDate());
    basicMovieDTO.setRating(entity.getRating());
    basicMovieDTO.setHistory(entity.getHistory());
    basicMovieDTO.setGender(fullGenderMapper.genderEntity2DTO(entity.getGender()));

    return basicMovieDTO;
  }

  // Convierte una lista de DTOs básicos de película a una lista de entidades
  public List<MovieEntity> listBasicMovieDTOs2Entities(List<BasicMovieDTO> DTOs) {

    List<MovieEntity> entities = new ArrayList<>();

    for (BasicMovieDTO basicMovieDTO : DTOs) {
      entities.add(basicMovieDTO2Entity(basicMovieDTO));
    }

    return entities;
  }

  // Convierte una lista de entidades de películas a una lista de DTOs básicos
  public List<BasicMovieDTO> listMovieEntities2BasicMovieDTOs(List<MovieEntity> entities) {

    List<BasicMovieDTO> DTOs = new ArrayList<>();

    for (MovieEntity movieEntity : entities) {
      DTOs.add(movieEntity2BasicMovieDTO(movieEntity));
    }
    
    return DTOs;
  }
}
