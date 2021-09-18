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
//    public static final String REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/fut_fin_xls_2021.zip";

    public static final String REPORT_ZIPPED_FILENAME = "dea_fut_xls_2021.zip";
    public static final String REPORT_UNZIPPED_FILENAME = "annual.xls";

    @Autowired
    private RestTemplate restTemplate;

    public void downloadFile() {
        try {
            log.info("making GET request to retrieve latest COT report.");
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(REPORT_DOWNLOAD_URL, HttpMethod.GET, entity, byte[].class);
            log.info("success: downloaded.");

            Files.write(Paths.get(REPORT_DOWNLOAD_LOCATION + REPORT_ZIPPED_FILENAME), Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {

            log.info("encountered error downloading cot report file.", e.getMessage(), e);
            e.printStackTrace();
        }
    }

}

