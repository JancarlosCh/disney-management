package com.alkemy.management.disney.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true where id=?")
@Where(clause = "deleted = false")

@Getter
@Setter
public class CharacterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String image;
  private String name;
  private int age;
  private Double weight;
  private String history;
  private boolean deleted = Boolean.FALSE;

  @ManyToMany(mappedBy = "characters", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  private List<MovieEntity> movies = new ArrayList<>();
}
