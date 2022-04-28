package com.cot.app.backend;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DavidJMartin
 */
@SpringBootApplication
@Slf4j
public class BackendApplication {
	@Autowired
	private RuntimeService runtimeService;

	@Value( "${cot-report.url}" )
	private String url;

	@Value( "${cot-report.years}" )
	private List<String> years;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	public void startProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("years" , years);
		variables.put("reportUrl", url);

		log.info("Calling workflow...");
		runtimeService.startProcessInstanceByKey("downloadReports", variables);
	}

}
