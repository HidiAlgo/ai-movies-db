package movies.client;

import jakarta.websocket.server.PathParam;
import movies.domain.Movie;
import movies.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data/movies")
public class RestClient
{
    @Autowired
    LogicService logicService;

    @GetMapping("/all")
    public List<Movie> getAllMovies()
    {
        return logicService.getAllMovies();
    }

    @GetMapping("/director/{name}")
    public List<Movie> getAllMoviesByDirector(@PathParam( "name" ) String name )
    {
        return logicService.getAllMoviesByDirector( name );
    }
}
