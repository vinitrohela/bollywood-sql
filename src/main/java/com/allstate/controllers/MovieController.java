package com.allstate.controllers;

import com.allstate.entities.Movie;
import com.allstate.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    private MovieService service;

    @Autowired
    public void setService(MovieService service) {
        this.service = service;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public Movie create(@RequestBody Movie movie){
        return this.service.create(movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie findById(@PathVariable int id){
        return this.service.findById(id);
    }


    //list all movies
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Iterable<Movie> findAll(){
        return this.service.findAll();
    }

    //find Movie By title
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Movie DeleteById(@RequestParam Map<String, String> query){
        return this.service.findByTitle(query.get("title"));
    }



    //delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void DeleteById(@PathVariable int id){
        this.service.delete(id);
    }

    //update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Movie create(@PathVariable int id,@RequestBody Movie movie){
        return this.service.update(id,movie);
    }
}
