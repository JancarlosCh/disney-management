package com.alkemy.management.disney.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.management.disney.dto.FullMovieDTO;
import com.alkemy.management.disney.dto.SimpleMovieDTO;
import com.alkemy.management.disney.entity.MovieEntity;
import com.alkemy.management.disney.exception.ParamNotFound;
import com.alkemy.management.disney.mapper.FullMovieMapper;
import com.alkemy.management.disney.mapper.SimpleMovieMapper;
import com.alkemy.management.disney.repository.MovieRepository;
import com.alkemy.management.disney.service.MovieService;
import com.alkemy.management.disney.service.implementation.specifications.MovieSpecification;
import com.alkemy.management.disney.util.MovieFilter;

@Service
public class MovieServiceImplementation implements MovieService {

  @Autowired
  private FullMovieMapper fullMovieMapper;

  @Autowired
  private SimpleMovieMapper simpleMovieMapper;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private MovieSpecification movieSpecification;

  @Override
  public List<FullMovieDTO> getAllMovies() {
    List<MovieEntity> movies = movieRepository.findAll();
    List<FullMovieDTO> resultDTO = fullMovieMapper.listMovieEntities2DTOs(movies, true);
    return resultDTO;
  }

  @Override
  public List<SimpleMovieDTO> getMovieByFilters(String title, Set<Long> genres, String order) {
    MovieFilter filtersDTO = new MovieFilter(title, genres, order);
    List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
    List<SimpleMovieDTO> resultDTOs = simpleMovieMapper.listMovieEntities2SimpleMovieDTOs(entities);
    return resultDTOs;
  }

  @Override
  public FullMovieDTO getMovieById(Long id) {

    if (!movieRepository.existsById(id)) {
      throw new ParamNotFound("No se encontró una pelicula o una serie con la ID enviada.");
    }

    MovieEntity movieEntity = movieRepository.findById(id).get();
    FullMovieDTO resultDTO = fullMovieMapper.movieEntity2DTO(movieEntity, true);

    return resultDTO;
  }

  @Transactional
  @Override
  public FullMovieDTO saveMovie(FullMovieDTO movieDTO) {
    MovieEntity movieEntity = fullMovieMapper.movieDTO2Entity(movieDTO);
    MovieEntity savedMovie = movieRepository.save(movieEntity);
    FullMovieDTO resultDTO = fullMovieMapper.movieEntity2DTO(savedMovie, true);
    return resultDTO;
  }

  @Override
  public void deleteMovieById(Long id) {
    movieRepository.deleteById(id);
  }
}
