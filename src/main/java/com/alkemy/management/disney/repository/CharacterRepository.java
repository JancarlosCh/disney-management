package com.alkemy.management.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.management.disney.entity.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

  List<CharacterEntity> findAll(Specification<CharacterEntity> specification);

}
