package com.alkemy.management.disney.service;

import java.util.List;
import java.util.Set;

import com.alkemy.management.disney.dto.BasicCharacterDTO;
import com.alkemy.management.disney.dto.FullCharacterDTO;
import com.alkemy.management.disney.dto.SimpleCharacterDTO;

public interface CharacterService {

  List<FullCharacterDTO> getAllCharacters();

  List<SimpleCharacterDTO> getCharacterByFilters(String name, Integer age, Double weight, Set<Long> movies);

  SimpleCharacterDTO findById(Long id);

  BasicCharacterDTO saveCharacter(BasicCharacterDTO characterDTO);

  void deleteCharacterById(Long id);
}
