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

import com.alkemy.management.disney.entity.GenderEntity;
import com.alkemy.management.disney.entity.MovieEntity;
import com.alkemy.management.disney.util.MovieFilter;

@Component
public class MovieSpecification {

  // Este método devuelve una especificación para las consultas de la entidad de
  // peliculas
  // En esta especificación se definen las busquedas por query params
  // Lo que permite consultas dinamicas con query params combinados
  public Specification<MovieEntity> getByFilters(MovieFilter urlParameters) {

    // El retorno viene de la expresión lamba (root, query, cirteriaBuilder) -> {}
    return (root, query, criteriaBuilder) -> {

      // Aquí se almacenarán los predicados construidos con criteriaBuilder
      List<Predicate> predicates = new ArrayList<>();

      // StringUtils ayuda a verificar que el query param haya recibido texto
      if (StringUtils.hasLength(urlParameters.getTitle())) {
        predicates.add(

            // criteriaBuilder permite realizar la consulta haciendo uso de like en sql
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + urlParameters.getTitle().toLowerCase() + "%"));
      }

      // Si se envían ids de géneros
      if (!CollectionUtils.isEmpty(urlParameters.getGenres())) {
        // Hacer join entre la tabla de peliculas y la tabla de géneros
        Join<MovieEntity, GenderEntity> join = root.join("gender", JoinType.INNER);

        // Crear una expresión para obtener mediante el join todos los id de la tabla
        // joineada
        Expression<String> idGenres = join.get("id");

        // buscar todos los géneros que se pasaron en el parámetro en idGenres
        predicates.add(idGenres.in(urlParameters.getGenres()));
      }

      // eliminar los registros repetidos
      query.distinct(true);

      // ordenar de forma ASC o DESC
      String orderByField = "creationDate";
      query.orderBy(
          urlParameters.isASC() ? criteriaBuilder.asc(root.get(orderByField))
              : criteriaBuilder.desc(root.get(orderByField))
      );

      // Retorno de los criterios
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
