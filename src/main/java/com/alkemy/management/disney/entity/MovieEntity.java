package com.alkemy.management.disney.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true where id=?")
@Where(clause = "deleted = false")

@Getter
@Setter
public class MovieEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String image;
  private String title;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "creation_date")
  private LocalDate creationDate;

  private double rating;
  private String history;

  private boolean deleted = Boolean.FALSE;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(
    name = "gender_id",
    insertable = true,
    updatable = true
  )
  private GenderEntity gender;

  @ManyToMany(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })

  @JoinTable(
      name = "movies_characters", 
      joinColumns = @JoinColumn(name = "movie_id"), 
      inverseJoinColumns = @JoinColumn(name = "character_id")
    )
  private Set<CharacterEntity> characters = new HashSet<>();
}
