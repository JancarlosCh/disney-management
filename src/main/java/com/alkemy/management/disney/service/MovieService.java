package com.alkemy.management.disney.service;

import java.util.List;
import java.util.Set;

import com.alkemy.management.disney.dto.FullMovieDTO;
import com.alkemy.management.disney.dto.SimpleMovieDTO;

public interface MovieService {

  List<FullMovieDTO> getAllMovies();
  
  List<SimpleMovieDTO> getMovieByFilters(String title, Set<Long> genres, String order);

  FullMovieDTO getMovieById(Long id);

  FullMovieDTO saveMovie(FullMovieDTO movieDTO);

  void deleteMovieById(Long movieId);
}
