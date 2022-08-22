package com.alkemy.management.disney.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.management.disney.dto.BasicCharacterDTO;
import com.alkemy.management.disney.dto.FullCharacterDTO;
import com.alkemy.management.disney.dto.SimpleCharacterDTO;
import com.alkemy.management.disney.entity.CharacterEntity;
import com.alkemy.management.disney.exception.ParamNotFound;
import com.alkemy.management.disney.mapper.BasicCharacterMapper;
import com.alkemy.management.disney.mapper.FullCharacterMapper;
import com.alkemy.management.disney.mapper.SimpleCharacterMapper;
import com.alkemy.management.disney.repository.CharacterRepository;
import com.alkemy.management.disney.service.CharacterService;
import com.alkemy.management.disney.service.implementation.specifications.CharacterSpecification;
import com.alkemy.management.disney.util.CharacterFilter;

@Service
public class CharacterServiceImplementation implements CharacterService {

  @Autowired
  private CharacterSpecification characterSpecification;

  @Autowired
  private SimpleCharacterMapper simpleCharacterMapper;

  @Autowired
  private BasicCharacterMapper basicCharacterMapper;

  @Autowired
  private FullCharacterMapper fullCharacterMapper;

  @Autowired
  private CharacterRepository characterRepository;

  @Override
  public List<FullCharacterDTO> getAllCharacters() {
    List<CharacterEntity> characters = characterRepository.findAll();
    List<FullCharacterDTO> resultDTO = fullCharacterMapper.listCharacterEntities2CharacterDTOs(characters, true);
    return resultDTO;
  }

  @Override
  public List<SimpleCharacterDTO> getCharacterByFilters(String name, Integer age, Double weight, Set<Long> movies) {
    CharacterFilter characterFilter = new CharacterFilter(name, age, weight, movies);
    List<CharacterEntity> characters = characterRepository
        .findAll(characterSpecification.getByFilters(characterFilter));
    List<SimpleCharacterDTO> resultDTO = simpleCharacterMapper.listcharacterEntity2SimpleCharacterDTOS(characters);

    return resultDTO;
  }

  @Override
  public SimpleCharacterDTO findById(Long id) {
    Optional<CharacterEntity> characterEntity = characterRepository.findById(id);

    if (!characterEntity.isPresent()) {
      throw new ParamNotFound("ID incorrecto");
    }

    SimpleCharacterDTO characterDTO = simpleCharacterMapper.characterEntity2SimpleCharacterDTO(characterEntity.get());

    return characterDTO;
  }

  @Transactional
  @Override
  public BasicCharacterDTO saveCharacter(BasicCharacterDTO characterDTO) {
    CharacterEntity characterEntity = basicCharacterMapper.basicCharacterDTO2Entity(characterDTO);
    CharacterEntity savedCharacter = characterRepository.save(characterEntity);
    BasicCharacterDTO resultDto = basicCharacterMapper.characterEntity2BasicCharacterDTO(savedCharacter);

    return resultDto;
  }

  public void deleteCharacterById(Long id) {
    characterRepository.deleteById(id);
  }
}
