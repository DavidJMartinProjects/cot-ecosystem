package cot.swap;

import cot.swap.scheduled.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author davidjmartin
 */
@SpringBootApplication
public class SwapServiceApplication {

	@Autowired
	private ScheduledTask scheduledTask;

	public static void main(String[] args) {
		SpringApplication.run(SwapServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			scheduledTask.importReport();
		};
	}

}
