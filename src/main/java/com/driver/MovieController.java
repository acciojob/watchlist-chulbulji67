package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;

//    Add a movie: POST /movies/add-movie
//    Pass the Movie object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovie

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity("Success", HttpStatus.OK);
    }


//    Add a director: POST /movies/add-director
//    Pass the Director object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addDirector

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity("Success", HttpStatus.OK);
    }


//    Pair an existing movie and director: PUT /movies/add-movie-director-pair
//    Pass movie name and director name as request parameters
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovieDirectorPair

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
        System.out.println("Movie Stirng name is "+movieName);
        System.out.println("Deirctor Stirng name is "+directorName);
    Movie movie = movieService.getMovieByName(movieName);
        System.out.println("Movie is "+movie);
    Director director = movieService.getDirectorByName(directorName);
        System.out.println("Director is "+director);
    movieService.addMovieDirectorPair(movie, director);
    return  new ResponseEntity("Success", HttpStatus.CREATED);
    }

//
//    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    Pass movie name as path parameter
//    Return Movie object wrapped in a ResponseEntity object
//    Controller Name - getMovieByName

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){

        Movie movie = movieService.getMovieByName(name);
        if(movie.getName() == null)
        return new ResponseEntity<>("No any Books",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }

//    Get Director by director name: GET /movies/get-director-by-name/{name}
//    Pass director name as path parameter
//    Return Director object wrapped in a ResponseEntity object
//    Controller Name - getDirectorByName

    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
       Director director = movieService.getDirectorByName(name);
       if(director.getName() == null)
           return new ResponseEntity("No Director Available", HttpStatus.BAD_REQUEST);
       return new ResponseEntity(director, HttpStatus.OK);
    }


//    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//    Pass director name as path parameter
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - getMoviesByDirectorName

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){
        return new ResponseEntity(movieService.getMoviesByDirectorName(director), HttpStatus.OK);
    }

//    Get List of all movies added: GET /movies/get-all-movies
//    No params or body required
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - findAllMovies

     @GetMapping("/get-all-movies")
     public ResponseEntity findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
     }

//    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName

    public @DeleteMapping("/delete-director-by-name")
      ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        movieService.deleteDirectorByName(directorName);
            return new ResponseEntity("Success", HttpStatus.OK);
      }

//    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
//            (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)


        @DeleteMapping("/delete-all-directors")
        public ResponseEntity deleteAllDirectors(){
            movieService.deleteAllDirectors();
            return new ResponseEntity("Success", HttpStatus.OK);
        }

}
