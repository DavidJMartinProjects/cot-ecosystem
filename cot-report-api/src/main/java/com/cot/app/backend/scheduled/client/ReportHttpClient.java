package com.cot.app.backend.scheduled.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class ReportHttpClient {

    @Autowired
    private RestTemplate restTemplate;
    
    public ResponseEntity<byte[]> downloadReport(String reportUrl) {
        ResponseEntity<byte[]> response = null;
        HttpEntity<String> entity = buildRequestHeader();
        try {
            log.info("Executing GET request to: {}", reportUrl);
            response = restTemplate.exchange(reportUrl, HttpMethod.GET, entity, byte[].class);
        } catch (HttpClientErrorException exception) {
            log.info("Encountered http-client while retrieving report: {} {}", exception, exception.getMessage());
        } catch (Exception exception) {
            log.info("Encountered error while retrieving report: {} {}", exception, exception.getMessage());
        }
        log.info("Response status code: {}.", response.getStatusCode());
        return response;
    }

    private HttpEntity<String> buildRequestHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>(headers);
    }

}


