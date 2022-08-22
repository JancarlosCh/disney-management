package com.alkemy.management.disney.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.management.disney.dto.FullMovieDTO;
import com.alkemy.management.disney.dto.SimpleMovieDTO;
import com.alkemy.management.disney.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping(value = "/all")
  public ResponseEntity<List<FullMovieDTO>> getAllMovies() {
    List<FullMovieDTO> movies = movieService.getAllMovies();
    return ResponseEntity.status(HttpStatus.OK).body(movies);
  }

  @GetMapping
  public ResponseEntity<List<SimpleMovieDTO>> getMovieByFilters(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) Set<Long> genres,
      @RequestParam(required = false, defaultValue = "ASC") String order

  ) {
    List<SimpleMovieDTO> movies = movieService.getMovieByFilters(title, genres, order);
    return ResponseEntity.status(HttpStatus.OK).body(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FullMovieDTO> getMovieById(@PathVariable("id") Long id) {
    FullMovieDTO movieDTO = movieService.getMovieById(id);
    return ResponseEntity.status(HttpStatus.OK).body(movieDTO);
  }

  @PostMapping
  public ResponseEntity<FullMovieDTO> saveMovie(@RequestBody FullMovieDTO movie) {
    FullMovieDTO movieDTO = movieService.saveMovie(movie);
    return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovieById(@PathVariable("id") Long id) {
    movieService.deleteMovieById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
