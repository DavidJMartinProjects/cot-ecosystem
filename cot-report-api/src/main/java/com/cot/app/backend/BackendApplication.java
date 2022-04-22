package com.cot.app.backend;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DavidJMartin
 */
@SpringBootApplication
@Slf4j
public class BackendApplication {
	@Autowired
	private RuntimeService runtimeService;

	private static final String REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/dea_fut_xls_%s.zip";

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	public void startProcess() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("years" , Arrays.asList("2022", "2021"));
		variables.put("reportUrl", REPORT_DOWNLOAD_URL);

		log.info("Calling workflow...");
		runtimeService.startProcessInstanceByKey("downloadReports", variables);
	}

}
