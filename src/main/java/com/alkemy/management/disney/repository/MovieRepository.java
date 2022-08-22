package com.alkemy.management.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.management.disney.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

  List<MovieEntity> findAll(Specification<MovieEntity> specification);

}
