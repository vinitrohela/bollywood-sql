package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {
    @Autowired
    private MovieService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Matrix");
        Movie after = this.service.create(before);
        assertEquals(2, after.getId());
        assertEquals("The Matrix", after.getTitle());
    }

    @Test
    public void shouldNotCreateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        before.setTitle("test");
        Movie after = this.service.create(before);
        assertEquals(2, after.getId());

    }

    @Test
    public void shouldFindMovieById() throws Exception {
        Movie m = this.service.findById(1);
        assertNotNull(m);
    }

    @Test
    public void shouldNotFindMovieByIdBadId() throws Exception {
        Movie m = this.service.findById(5);
        assertNull(m);
    }

    @Test
    public void shouldFindAllMovies() throws Exception {
        ArrayList<Movie> movies = (ArrayList<Movie>) this.service.findAll();
        assertEquals(1, movies.size());
    }
    @Test
    public void shouldFindMovieByTitle() throws Exception {
        Movie m = this.service.findByTitle("The Avengers");
        assertNotNull(m);
    }

    @Test
    public void shouldNotFindMovieByIdBadTitle() throws Exception {
        Movie m = this.service.findByTitle("Bad Title");
        assertNull(m);
    }

    @Test
    public void shouldDeleteMovie() throws Exception {
        this.service.delete(1);
        Movie m = this.service.findById(1);
        assertNull(m);
    }

    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void shouldNotDeleteMovieBadId() throws Exception {
        this.service.delete(5);
    }

    @Test
    public void shouldUpdateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Avengers II");
        Movie after = this.service.update(1, before);
        assertEquals(1, after.getVersion());
        assertEquals("The Avengers II", after.getTitle());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotUpdateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        before.setTitle(null);
        Movie after = this.service.update(1, before);
    }
}