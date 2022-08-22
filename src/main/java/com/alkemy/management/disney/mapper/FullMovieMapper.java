package com.alkemy.management.disney.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.management.disney.dto.FullGenderDTO;
import com.alkemy.management.disney.dto.FullMovieDTO;
import com.alkemy.management.disney.entity.MovieEntity;

@Component
public class FullMovieMapper {
  @Autowired
  private FullGenderMapper fullGenderMapper;

  @Autowired
  private FullCharacterMapper characterMapper;

  // Convierte un DTO detallado de película a una entidad película
  public MovieEntity movieDTO2Entity(FullMovieDTO dto) {

    MovieEntity movieEntity = new MovieEntity();

    movieEntity.setImage(dto.getImage());
    movieEntity.setTitle(dto.getTitle());
    movieEntity.setCreationDate(dto.getCreationDate());
    movieEntity.setRating(dto.getRating());
    movieEntity.setHistory(dto.getHistory());
    movieEntity.setGender(fullGenderMapper.genderDTO2Entity(dto.getGender()));

    movieEntity.setCharacters(characterMapper.hashSetCharacterDTOs2CharacterEntities(dto.getCharacters()));

    return movieEntity;
  }

  // Convierte una entidad película a un DTO detallado de película
  public FullMovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {

    FullMovieDTO movieDTO = new FullMovieDTO();

    movieDTO.setId(entity.getId());
    movieDTO.setImage(entity.getImage());
    movieDTO.setTitle(entity.getTitle());
    movieDTO.setCreationDate(entity.getCreationDate());
    movieDTO.setRating(entity.getRating());
    movieDTO.setHistory(entity.getHistory());
    
    FullGenderDTO genderDTO = fullGenderMapper.genderEntity2DTO(entity.getGender());
    movieDTO.setGender(genderDTO);
    
    movieDTO.setCharacters(new HashSet<>());
    
    if (loadCharacters) {
      movieDTO.setCharacters(characterMapper.hashSetCharacterEntities2CharacterDTOs(entity.getCharacters(), false));
    }

    return movieDTO;
  }

  // Convierte una lista de DTOs detallados de peliculas a una lista entidades
  public List<MovieEntity> listMovieDTOs2Entities(List<FullMovieDTO> DTOs) {
    List<MovieEntity> entities = new ArrayList<>();

    for (FullMovieDTO movieDTO : DTOs) {
      entities.add(movieDTO2Entity(movieDTO));
    }
    return entities;
  }

  // Convierte una lista entidades de peliculas a una lista de DTOs detallados
  public List<FullMovieDTO> listMovieEntities2DTOs(List<MovieEntity> entities, boolean loadCharacters) {
    List<FullMovieDTO> DTOs = new ArrayList<>();

    for (MovieEntity movieEntity : entities) {
      DTOs.add(movieEntity2DTO(movieEntity, loadCharacters));
    }

    return DTOs;
  }
}
