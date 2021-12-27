package com.cot.app.backend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cot.app.backend.scheduled.ScheduledTask;

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
	 		scheduledTask.importReport();
	 	};
	 }

}
