package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private IMovieRepository repository;

    @Autowired
    public void setRepository(IMovieRepository repository) {
        this.repository = repository;
    }

    public Movie create(Movie m){
        m.setId(m.getId());
        System.out.println("id............................"+m.getId());
        return this.repository.save(m);
    }

    public Movie findById(int id){
        return this.repository.findOne(id);
    }

    public Iterable<Movie> findAll(){
        return this.repository.findAll();
    }

    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    public void delete(int id){
        this.repository.delete(id);
    }

    public Movie update(int id, Movie movie){
        Movie old = this.repository.findOne(id);
        old.setTitle(movie.getTitle());
        old.setWatched(movie.isWatched());
        old.setRating(movie.getRating());
        old.setMovieLength(movie.getMovieLength());
        return this.repository.save(old);
    }
}
