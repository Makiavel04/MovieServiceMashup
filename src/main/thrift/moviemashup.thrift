namespace java moviemashup.thrift

typedef i16 short

struct MovieDto {
    1: string title,
    2: short year,
    3: string visualisationDate,
    4: short points
}


exception ServiceMovieNotFoundException {
    1: string message
}
service MovieService {
    void addMovie(1: MovieDto  movie),
    MovieDto findMovieByTitle(1: string title) throws (1: ServiceMovieNotFoundException e),
    list<MovieDto> findMoviesByYear(1: short year)
}

