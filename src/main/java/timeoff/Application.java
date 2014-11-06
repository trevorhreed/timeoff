package timeoff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		UserRepo userRepo = ctx.getBean(UserRepo.class);
		
		userRepo.save(new User("adam",		"Adam",		"Smith",		"a"));
		userRepo.save(new User("amy",			"Amy",		"Jones",		"a"));
		userRepo.save(new User("aaron",		"Aaron",	"Larson",		"a"));
		userRepo.save(new User("brooke",	"Brooke",	"Turner",		"b"));
		userRepo.save(new User("brent",		"Brent",	"Rodgers",	"b"));
		userRepo.save(new User("beth",		"Beth",		"Cook",			"b"));

		RequestRepo requestRepo = ctx.getBean(RequestRepo.class);
		requestRepo.save(new Request("amy", 1415238488070L, 1415238488070L, "Vacation", 1));
	}
	
}