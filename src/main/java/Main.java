import com.fasterxml.jackson.databind.ObjectMapper;
import config.management.ConfigManager;

import csv.data.FormattedMovieDto;
import csv.data.Movie;
import csv.data.MovieDto;
import csv.data.MovieGenerator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Application Start");
        String fileName = ConfigManager.imdbFileLocation();
        MovieGenerator movieGenerator = new MovieGenerator();

        List<MovieDto> movies = movieGenerator.generate(fileName);
        List<FormattedMovieDto> formattedMovies = new ArrayList<>();

        Movie transformer = new Movie();
        movies.forEach(m -> formattedMovies.add(transformer.transform(m)));
        System.out.println(movies.size());
        System.out.println(formattedMovies.size());

        File outputFile = new File(ConfigManager.outputFileLocation());
        FileWriter writer;

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(formattedMovies.get(3831).getId());

        try {
            writer = new FileWriter(outputFile);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(formattedMovies);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Application End");

    }
}
