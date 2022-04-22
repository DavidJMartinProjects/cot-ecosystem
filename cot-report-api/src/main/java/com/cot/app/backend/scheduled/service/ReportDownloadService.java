package com.cot.app.backend.scheduled.service;

import com.cot.app.backend.scheduled.client.ReportHttpClient;
import com.cot.app.backend.scheduled.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class ReportDownloadService {

    @Autowired
    private ReportHttpClient httpClient;

    @Autowired
    private FileUtil fileUtil;
    private ResponseEntity<byte[]> report;

    public String retrieveReport(String reportUrl, String cotReportYear) {
        report = httpClient.downloadReport(reportUrl);
        return fileUtil.writeToFile(report, cotReportYear);
    }

}

