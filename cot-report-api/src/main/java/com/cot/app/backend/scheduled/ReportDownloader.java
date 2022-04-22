package com.cot.app.backend.scheduled;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class ReportDownloader {

    public static final String REPORT_DOWNLOAD_LOCATION = "./";
    public static final String REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/dea_fut_xls_2021.zip";

    public static final String REPORT_ZIPPED_FILENAME = "cot_report_yr_%s.zip";
    public static final String REPORT_UNZIPPED_FILENAME = "annual.xls";

    @Autowired
    private RestTemplate restTemplate;
    
    public String downloadFile(String reportUrl, String cotReportYear) {
        try {
            // ToDo: implement restTemplateFacade here
            log.info("making GET request to retrieve latest COT report.");
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(reportUrl, HttpMethod.GET, entity, byte[].class);
            log.info("success: downloaded.");

            String fileName = String.format(REPORT_ZIPPED_FILENAME, cotReportYear);
            Files.write(Paths.get(REPORT_DOWNLOAD_LOCATION + fileName), Objects.requireNonNull(response.getBody()));
            log.info("fileName: {}", fileName);
            return fileName;
        } catch (Exception e) {
            log.info("encountered error downloading cot report file.", e.getMessage(), e);
            e.printStackTrace();
        }
        return "";
    }

}

