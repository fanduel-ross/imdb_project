package config.management;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties config;

    static {
        config = new Properties();
        try {
            config.load(new FileReader("src/main/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String imdbFileLocation(){
        return config.getProperty("imdb_file_location");
    }

    public static String outputFileLocation(){
        return config.getProperty("output_file_location");
    }

    public static String movieTitleFileLocation(){
        return config.getProperty("movie_title_file_location");
    }

    public static String testFileLocation(){
        return config.getProperty("test_file_location");
    }
}
