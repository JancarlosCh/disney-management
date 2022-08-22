package com.alkemy.management.disney.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.management.disney.dto.BasicCharacterDTO;
import com.alkemy.management.disney.dto.FullCharacterDTO;
import com.alkemy.management.disney.dto.SimpleCharacterDTO;
import com.alkemy.management.disney.service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

  @Autowired
  private CharacterService characterService;

  @GetMapping(value = "/all")
  public ResponseEntity<List<FullCharacterDTO>> getCharacters() {
    List<FullCharacterDTO> characterDTOs = characterService.getAllCharacters();
    return ResponseEntity.status(HttpStatus.OK).body(characterDTOs);
  }

  @GetMapping
  public ResponseEntity<List<SimpleCharacterDTO>> getCharacterByFilters(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) Double weight,
      @RequestParam(required = false) Set<Long> movies) {

    List<SimpleCharacterDTO> characterDTOs = characterService.getCharacterByFilters(name, age, weight, movies);

    return ResponseEntity.status(HttpStatus.OK).body(characterDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SimpleCharacterDTO> findCharacterById(@PathVariable Long id) {
    SimpleCharacterDTO characterDTO = characterService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(characterDTO);
  }

  @PostMapping
  public ResponseEntity<BasicCharacterDTO> saveCharacter(@RequestBody BasicCharacterDTO character) {
    BasicCharacterDTO characterDTO = characterService.saveCharacter(character);
    return ResponseEntity.status(HttpStatus.CREATED).body(characterDTO);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCharacterById(@PathVariable("id") Long id) {
    characterService.deleteCharacterById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
