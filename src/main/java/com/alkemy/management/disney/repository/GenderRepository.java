package com.alkemy.management.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.management.disney.entity.GenderEntity;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {

}
