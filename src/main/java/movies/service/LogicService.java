package movies.service;

import movies.domain.Movie;
import movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogicService
{
    @Autowired
    MovieRepository movieRepository;

    public void getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();

        movieList.forEach( movie -> {
            System.out.println( movie.getTitle() );
        });
    }
}
