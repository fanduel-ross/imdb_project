package csv.data;

public class Movie {
    public FormattedMovieDto transform(MovieDto movieDto) {
        String[] actors = new String[] {movieDto.getActor1(), movieDto.getActor2(), movieDto.getActor3()};
        return new FormattedMovieDto(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getScore(),
                movieDto.getYear(),
                movieDto.getDuration(),
                movieDto.getRating(),
                movieDto.getBudget(),
                movieDto.getGenres(),
                movieDto.getGross(),
                movieDto.getDirector(),
                actors,
                movieDto.getLanguage(),
                movieDto.getCountry());
    }
}
