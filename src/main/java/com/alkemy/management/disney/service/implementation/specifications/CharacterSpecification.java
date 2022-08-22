package com.alkemy.management.disney.service.implementation.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.management.disney.entity.CharacterEntity;
import com.alkemy.management.disney.entity.MovieEntity;
import com.alkemy.management.disney.util.CharacterFilter;

@Component
public class CharacterSpecification {

  // casi todo viene de javax.persistence.criteria
  public Specification<CharacterEntity> getByFilters(CharacterFilter urlParameters) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      // filtra por el nombre de personajes
      if (StringUtils.hasLength(urlParameters.getName())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get(
                    "name")),
                "%" + urlParameters.getName().toLowerCase() + "%"
            ));
      }

      // filtra por la edad de los personajes
      if (urlParameters.getAge() != null) {
        predicates.add(
          criteriaBuilder.equal(root.get("age"), urlParameters.getAge())
        );
      }

      // filtra por el peso de los personajes
      if (urlParameters.getWeight() != null) {
        predicates.add(
          criteriaBuilder.equal(root.get("weight"), urlParameters.getWeight())
        );
      }

      if (!CollectionUtils.isEmpty(urlParameters.getMovies())) {
        Join<CharacterEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
        Expression<String> moviesId = join.get("id");
        predicates.add(moviesId.in(urlParameters.getMovies()));
      }

      query.distinct(true);

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
