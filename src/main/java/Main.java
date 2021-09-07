
import config.management.ConfigManager;
import retrieval.RetrieveData;


public class Main {

    public static void main(String[] args) {

        RetrieveData writeToFile = new RetrieveData();

        writeToFile.writeAllMoviesToFile();

        RetrieveData movieByTitle = new RetrieveData();

        System.out.println(movieByTitle.getSpecificMovieByTitle(ConfigManager.movieTitleFileLocation(), new String[] {"Avatar",
                "Spider-Man 3", "The Neon Demon", "My Date with Drew", "Harry Potter and the Half-Blood Prince"}));


    }
}
