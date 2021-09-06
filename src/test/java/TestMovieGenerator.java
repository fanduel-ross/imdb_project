

import config.management.ConfigManager;
import csv.data.MovieDto;
import csv.data.MovieGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMovieGenerator {
    private static List<MovieDto> movieDtos;
    private static MovieDto firstMovie;
    private static MovieDto lastMovie;

    @BeforeAll
    private static void setup(){
        File testFile = new File(ConfigManager.imdbFileLocation());
        MovieGenerator moviesGenerator = new MovieGenerator();
        movieDtos = moviesGenerator.generate(testFile);
        firstMovie = movieDtos.get(0);
        lastMovie = movieDtos.get(3831);
    }

    @Test
    public void generatesCorrectNumber(){
        assertEquals( 3832, movieDtos.size());
    }

    @Test
    public void generatesCorrectTitle(){
        assertEquals( "Avatar", firstMovie.getTitle());
        assertEquals( "My Date with Drew", lastMovie.getTitle());
    }

    @Test
    public void generatesCorrectScore(){
        assertEquals( 7.9, firstMovie.getScore());
    }

    @Test
    public void generatesCorrectYear(){
        assertEquals( 2009, firstMovie.getYear());
    }

    @Test
    public void generatesCorrectRating(){
        assertEquals( "PG-13", firstMovie.getRating());
    }

    @Test
    public void generatesCorrectGenres(){
        String[] expected = {"Action", "Adventure", "Fantasy", "Sci-Fi"};
        assertArrayEquals( expected, firstMovie.getGenres());
    }


}

