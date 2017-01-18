package com.allstate.controllers;

import com.allstate.entities.Movie;
import com.allstate.services.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieService service;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movie = new Movie();
        movie.setId(3);
        movie.setVersion(0);
        movie.setTitle("Ddlj");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception {
        given(this.service.create(Mockito.any(Movie.class)))
                .willReturn(movie);

        MockHttpServletRequestBuilder request = post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Ddlj\"}");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("Ddlj")));
    }

    @Test
    public void shouldNotCreateMovieBadUrl() throws Exception {
        MockHttpServletRequestBuilder request = post("/moviezz");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFindMovieById() throws Exception {
        given(this.service.findById(3))
                .willReturn(movie);

        MockHttpServletRequestBuilder request = get("/movies/3");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("Ddlj")));
    }

    @Test
    public void shouldNotFindMovieByIdDoesNotExist() throws Exception {
        MockHttpServletRequestBuilder request = get("/movies/7");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("")));
    }

    @Test
    public void shouldFindAllMovies() throws Exception {
        Movie m1 = new Movie(); m1.setTitle("Finding Nemo");
        Movie m2 = new Movie(); m2.setTitle("Finding Dory");
        List<Movie> movies = new ArrayList<>();
        movies.add(m1);
        movies.add(m2);

        given(this.service.findAll())
                .willReturn((Iterable<Movie>) movies);

        MockHttpServletRequestBuilder request = get("/movies");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldFindMovieByTitle() throws Exception {
        given(this.service.findByTitle("Ddlj"))
                .willReturn(movie);

        MockHttpServletRequestBuilder request = get("/movies/search?title=Ddlj");


        System.out.println("movie title--------------------------------"+movie.getTitle());
        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is("Ddlj")));
    }

    @Test
    public void shouldNotFindMovieByTitleDoesNotExist() throws Exception {
        MockHttpServletRequestBuilder request = get("/movies/search?title=Wrong");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("")));
    }

    @Test
    public void shouldDeleteMovie() throws Exception {
        MockHttpServletRequestBuilder request = delete("/movies/7");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteMovieBadUrl() throws Exception {
        MockHttpServletRequestBuilder request = delete("/movies/wrong/7");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldUpdateMovie() throws Exception {
        movie.setVersion(1);

        given(this.service.update(anyInt(), Mockito.any(Movie.class)))
                .willReturn(movie);

        MockHttpServletRequestBuilder request = put("/movies/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Ddlj\"}");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version", is(1)));
    }
}