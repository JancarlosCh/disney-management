package com.alkemy.management.disney.mapper;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.alkemy.management.disney.entity.GenderEntity;
import com.alkemy.management.disney.dto.FullGenderDTO;

@Component
public class FullGenderMapper {

  // Convierte un DTO detallado de género de pelicula a una entidad
  public GenderEntity genderDTO2Entity(FullGenderDTO dto) {
    GenderEntity genderEntity = new GenderEntity();

    genderEntity.setName(dto.getName());
    genderEntity.setImage(dto.getImage());

    return genderEntity;
  }

  // Convierte una entidad género de pelicula a un DTO detallado
  public FullGenderDTO genderEntity2DTO(GenderEntity entity) {
    FullGenderDTO genderDTO = new FullGenderDTO();

    genderDTO.setId(entity.getId());
    genderDTO.setName(entity.getName());
    genderDTO.setImage(entity.getImage());

    return genderDTO;
  }

  // Convierte una lista de entidades de géneros de peliculas lista de DTOs detallados
  public List<FullGenderDTO> listGenderEntityToListGenderDTO(List<GenderEntity> entities) {
    List<FullGenderDTO> listDTOs = new ArrayList<>();

    for (GenderEntity genderEntity : entities) {
      listDTOs.add(genderEntity2DTO(genderEntity));
    }
    return listDTOs;
  }

  // Convierte una lista DTOs detallados de géneros de peliculas lista de entidades
  public List<GenderEntity> listGenderDTOs2GenderEntities(List<FullGenderDTO> DTOs) {
    List<GenderEntity> entities = new ArrayList<>();

    for (FullGenderDTO genderDTO : DTOs) {
      entities.add(genderDTO2Entity(genderDTO));
    }
    return entities;
  }
}
