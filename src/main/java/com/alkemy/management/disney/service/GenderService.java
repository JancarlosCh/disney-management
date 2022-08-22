package com.alkemy.management.disney.service;

import java.util.List;

import com.alkemy.management.disney.dto.FullGenderDTO;

public interface GenderService {

  List<FullGenderDTO> getGenres();

  FullGenderDTO saveGender(FullGenderDTO dto);

}
