package retrieval;

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

public class RetrieveData {
    private static final Logger logger = LogManager.getLogger(RetrieveData.class);
    private static final File csvFile = new File(ConfigManager.imdbFileLocation());
    private static final MovieGenerator movieGenerator = new MovieGenerator();
    private static final  List<MovieDto> movies = movieGenerator.generate(csvFile);
    private static final List<FormattedMovieDto> formattedMovies = new ArrayList<>();
    private static final Movie transformer = new Movie();
    private static File outputFile;
    private static final ObjectMapper mapper = new ObjectMapper();


    public List<MovieDto> writeMoviesToFile() {

        movies.forEach(m -> formattedMovies.add(transformer.transform(m)));

        outputFile = new File(ConfigManager.outputFileLocation());
        FileWriter writer;

        try {
            writer = new FileWriter(outputFile);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(formattedMovies);
            writer.write(json);
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        logger.info("Application End");
        return movies;

    }

    public List<FormattedMovieDto> getSpecificMovieByTitle(String[] movieTitles) {

        movies.forEach(m -> formattedMovies.add(transformer.transform(m)));

        outputFile = new File(ConfigManager.movieTitleFileLocation());
        FileWriter writer;

        List<FormattedMovieDto> specificMovie = new ArrayList<>();

        List<String> title = new ArrayList<>();

        for (FormattedMovieDto formattedMovie : formattedMovies) {

            for (String movieTitle : movieTitles
            ) {
                if (formattedMovie.getTitle().equalsIgnoreCase(movieTitle) && !title.contains(movieTitle)) {
                    specificMovie.add(formattedMovie);
                    title.add(movieTitle);
                    try {
                        writer = new FileWriter(outputFile);
                        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(specificMovie);
                        writer.write(json);
                        writer.close();

                    } catch (
                            IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            logger.info("Application End");



        }

        return specificMovie;




    }
}
