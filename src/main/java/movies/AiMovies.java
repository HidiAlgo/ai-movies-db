package movies;

import movies.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AiMovies {

	@Autowired
	LogicService logicService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AiMovies.class, args);
//		ApplicationContext context = SpringApplication.run(AiMovies.class, args);
		LogicService service = context.getBean(LogicService.class); // ✅ use Spring context
//
		service.getAllMovies();
	}

}
