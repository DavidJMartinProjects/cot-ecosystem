package com.cot.app.backend;

import com.cot.app.backend.scheduled.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * @author DavidJMartin
 */
@SpringBootApplication
public class BackendApplication {

	@Autowired
	private ScheduledTask scheduledTask;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BackendApplication.class, args);
	}

	 @Bean
	 public CommandLineRunner CommandLineRunnerBean() {
	 	return (args) -> {
	 		scheduledTask.downloadCotReports();
	 	};
	 }

}
