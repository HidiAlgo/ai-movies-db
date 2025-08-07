package movies.client;

import movies.domain.Movie;
import movies.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestClient
{
    @Autowired
    LogicService logicService;

    @GetMapping("/get-all-movies")
    public List<Movie> getAllMovies()
    {
        return logicService.getAllMovies();
    }
}
