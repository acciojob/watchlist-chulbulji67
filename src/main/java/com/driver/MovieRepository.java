package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    private List<Movie> moviesList;
    private List<Director> directorsList;
    private HashMap<Movie, Director> movieDirectorPair;

    public MovieRepository() {
        moviesList = new ArrayList<>();
        directorsList = new ArrayList<>();
        movieDirectorPair = new HashMap<Movie, Director>();
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Director> getDirectorsList() {
        return directorsList;
    }

    public void setDirectorsList(List<Director> directorsList) {
        this.directorsList = directorsList;
    }

    public HashMap<Movie, Director> getMovieDirectorPair() {
        return movieDirectorPair;
    }

    public void setMovieDirectorPair(HashMap<Movie, Director> movieDirectorPair) {
        this.movieDirectorPair = movieDirectorPair;
    }
}
