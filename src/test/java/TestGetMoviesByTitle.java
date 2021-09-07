import config.management.ConfigManager;
import csv.data.FormattedMovieDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrieval.RetrieveData;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetMoviesByTitle {
        private static final RetrieveData retrieveData = new RetrieveData();
        private static FormattedMovieDto movie;

        @BeforeAll
        private static void setup(){
            List<FormattedMovieDto> movieDtos = retrieveData.getSpecificMovieByTitle(ConfigManager.testFileLocation(), new String[]{"Spider-Man 3"});
            movie = movieDtos.get(0);
            System.out.println(movie);
        }

        @Test
        public void generatesCorrectId(){
            assertEquals( 6, movie.getId());
        }

        @Test
        public void generatesCorrectTitle() {
            assertEquals("Spider-Man 3", movie.getTitle());
        }

        @Test
        public void generatesCorrectRating(){
            assertEquals( 6.2, movie.getImdbRating());
    }
        @Test
        public void generatesCorrectYear(){
            assertEquals( 2007, movie.getYear());
        }
}
