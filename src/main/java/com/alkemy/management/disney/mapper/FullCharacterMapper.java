package com.alkemy.management.disney.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.alkemy.management.disney.dto.BasicCharacterDTO;
import com.alkemy.management.disney.dto.FullCharacterDTO;
import com.alkemy.management.disney.entity.CharacterEntity;

@Component
public class FullCharacterMapper {
  @Autowired
  private FullMovieMapper movieMapper;

  // Convierte un DTO detallado de personaje a una entidad personaje
  public CharacterEntity characterDTO2Entity(FullCharacterDTO dto) {

    CharacterEntity characterEntity = new CharacterEntity();

    characterEntity.setImage(dto.getImage());
    characterEntity.setName(dto.getName());
    characterEntity.setAge(dto.getAge());
    characterEntity.setWeight((Double) dto.getWeight());
    characterEntity.setHistory(dto.getHistory());
  
    return characterEntity;
  }

  // Convierte una entidad personaje a un DTO detallado de personaje
  public FullCharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {

    FullCharacterDTO characterDTO = new FullCharacterDTO();

    characterDTO.setId(entity.getId());
    characterDTO.setImage(entity.getImage());
    characterDTO.setName(entity.getName());
    characterDTO.setAge(entity.getAge());
    characterDTO.setWeight(entity.getWeight());
    characterDTO.setHistory(entity.getHistory());
    characterDTO.setMovies(new ArrayList<>());

    if (loadMovies) {
      characterDTO.setMovies(movieMapper.listMovieEntities2DTOs(entity.getMovies(), false));
    }

    return characterDTO;
  }

  // Convierte una lista de DTOs detallados de personajes a una lista entidades
  public List<CharacterEntity> listCharacterDTOs2CharacterEntities(List<FullCharacterDTO> DTOs) {

    List<CharacterEntity> entitites = new ArrayList<>();

    for (FullCharacterDTO characterDTO : DTOs) {
      entitites.add(characterDTO2Entity(characterDTO));
    }
    return entitites;
  }

  // Convierte una lista de entidades de personajes a una lista de DTOs detallados
  public List<FullCharacterDTO> listCharacterEntities2CharacterDTOs(List<CharacterEntity> entitites, boolean loadMovies) {
    
    List<FullCharacterDTO> DTOs = new ArrayList<>();

    for (CharacterEntity characterEntity : entitites) {
      DTOs.add(characterEntity2DTO(characterEntity, loadMovies));
    }
    return DTOs;
  }

  // Convierte un hash set de DTOs detallados de personajes a un hash set de entidades
  public Set<CharacterEntity> hashSetCharacterDTOs2CharacterEntities(Set<FullCharacterDTO> DTOs) {
    
    Set<CharacterEntity> entitites = new HashSet<>();

    for (FullCharacterDTO characterDTO : DTOs) {
      entitites.add(characterDTO2Entity(characterDTO));
    }
    return entitites;
  }

  // Convierte un hash set de entidades de personajes a un hash set de DTOs detallados
  public Set<FullCharacterDTO> hashSetCharacterEntities2CharacterDTOs(Set<CharacterEntity> entities, boolean loadMovies) {

    Set<FullCharacterDTO> DTOs = new HashSet<>();

    for (CharacterEntity characterEntity : entities) {
      DTOs.add(characterEntity2DTO(characterEntity, loadMovies));
    }
    return DTOs;
  }
}
