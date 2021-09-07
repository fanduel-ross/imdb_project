package retrieval;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.management.ConfigManager;
import csv.data.FormattedMovieDto;
import csv.data.Movie;
import csv.data.MovieDto;
import csv.data.MovieGenerator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveData {
    private static final List<FormattedMovieDto> movieList = new ArrayList<>();
    private static final File csvFile = new File(ConfigManager.imdbFileLocation());
    private static final MovieGenerator movieGenerator = new MovieGenerator();
    private static final List<MovieDto> movies = movieGenerator.generate(csvFile);
    private static final List<FormattedMovieDto> formattedMovies = new ArrayList<>();
    private static final Movie transformer = new Movie();

    public void csvManager(String fileLocation, boolean isNewList) {
        ObjectMapper mapper = new ObjectMapper();
        File outputFile = new File(fileLocation);
        FileWriter writer;

        if (isNewList) {
            try {
                writer = new FileWriter(outputFile);
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(movieList);
                writer.write(json);
                writer.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                writer = new FileWriter(outputFile);
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(formattedMovies);
                writer.write(json);
                writer.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }


    }


    public List<MovieDto> writeAllMoviesToFile() {
        movies.forEach(m -> formattedMovies.add(transformer.transform(m)));
        csvManager(ConfigManager.outputFileLocation(), false);

        return movies;

    }

    public List<FormattedMovieDto> getSpecificMovieByTitle(String fileLocation, String[] movieTitles) {

        movies.forEach(m -> formattedMovies.add(transformer.transform(m)));


        List<String> title = new ArrayList<>();

        for (FormattedMovieDto formattedMovie : formattedMovies) {

            for (String movieTitle : movieTitles
            ) {
                if (formattedMovie.getTitle().equalsIgnoreCase(movieTitle) && !title.contains(movieTitle)) {
                    movieList.add(formattedMovie);
                    title.add(movieTitle);
                    csvManager(fileLocation, true);
                }

            }


        }


        return movieList;


    }
}

