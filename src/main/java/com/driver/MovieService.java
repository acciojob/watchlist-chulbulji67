package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    void addMovie(Movie movie){
        movieRepository.getMoviesList().add(movie);
    }

    String addDirector(Director director){
        movieRepository.getDirectorsList().add(director);
        return "Success";
    }

    void addMovieDirectorPair(Movie movie, Director director){
        movieRepository.getMovieDirectorPair().put(movie, director);
    }

    Movie getMovieByName(String movieName){
        for(Movie movie : movieRepository.getMoviesList()){
            System.out.println(movie.getName()+", and "+movieName);
            if(movie.getName().equals(movieName))
                return movie;
        }
        return new Movie();
    }

    Director getDirectorByName(String directorName){
        for(Director director : movieRepository.getDirectorsList()){
            if(director.getName().equals(directorName))
                return director;
        }
        return new Director();
    }

    List<Movie> getMoviesByDirectorName(String name){
        List<Movie> movies = new ArrayList<>();

        HashMap<Movie, Director> map = movieRepository.getMovieDirectorPair();

        for(Movie m : movieRepository.getMovieDirectorPair().keySet()){
            System.out.println(m+" , "+movieRepository.getMovieDirectorPair().get(m));
        }

        for(Movie movie: map.keySet()){
            System.out.println(map.get(movie).getName());
            System.out.println(map.get(movie).getName()+" , " +name);
            if(map.get(movie).getName().equals(name)){
                movies.add(movie);
            }
        }
        return movies;
    }

    List<Movie> findAllMovies(){
        return movieRepository.getMoviesList();
    }

    void deleteDirectorByName(String directorName){
        List<Director> directorsList = movieRepository.getDirectorsList();
        for(int i = 0; i< directorsList.size(); i++){
            if(directorsList.get(i).getName().equals(directorName))
                directorsList.remove(i);
        }
        HashMap<Movie, Director> map = movieRepository.getMovieDirectorPair();
        for(Movie movie : map.keySet()){
            if(map.get(movie).getName().equals(directorName)){
                map.remove(movie);
                movieRepository.getMoviesList().remove(movie);
            }

        }

    }

    void deleteAllDirectors(){
        movieRepository.getDirectorsList().clear();
        movieRepository.getMovieDirectorPair().clear();
    }

}
