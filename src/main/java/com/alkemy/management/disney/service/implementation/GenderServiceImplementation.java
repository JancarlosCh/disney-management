package com.alkemy.management.disney.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.management.disney.dto.FullGenderDTO;
import com.alkemy.management.disney.entity.GenderEntity;
import com.alkemy.management.disney.mapper.FullGenderMapper;
import com.alkemy.management.disney.repository.GenderRepository;
import com.alkemy.management.disney.service.GenderService;

@Service
public class GenderServiceImplementation implements GenderService {

  @Autowired
  private FullGenderMapper fullGenderMapper;

  @Autowired
  private GenderRepository genderRepository;

  @Override
  public List<FullGenderDTO> getGenres() {
    List<GenderEntity> genres = genderRepository.findAll();
    List<FullGenderDTO> resultDTO = fullGenderMapper.listGenderEntityToListGenderDTO(genres);
    return resultDTO;
  }

  @Override
  @Transactional
  public FullGenderDTO saveGender(FullGenderDTO dto) {
    GenderEntity gender = fullGenderMapper.genderDTO2Entity(dto);
    GenderEntity savedGender = genderRepository.save(gender);
    FullGenderDTO resultDTO = fullGenderMapper.genderEntity2DTO(savedGender);
    return resultDTO;
  }
}
