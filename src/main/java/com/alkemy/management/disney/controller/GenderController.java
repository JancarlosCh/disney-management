package com.alkemy.management.disney.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alkemy.management.disney.dto.FullGenderDTO;
import com.alkemy.management.disney.service.GenderService;

@RestController
@RequestMapping("genres")
public class GenderController {

  @Autowired
  private GenderService genderService;

  @GetMapping
  public List<FullGenderDTO> getGenres() {
    List<FullGenderDTO> genderDTOs = genderService.getGenres();
    return genderDTOs;
  }

  @PostMapping
  public ResponseEntity<FullGenderDTO> saveGender(@RequestBody FullGenderDTO gender){
    FullGenderDTO genderDTO = genderService.saveGender(gender);
    return ResponseEntity.status(HttpStatus.CREATED).body(genderDTO);
  }
}
