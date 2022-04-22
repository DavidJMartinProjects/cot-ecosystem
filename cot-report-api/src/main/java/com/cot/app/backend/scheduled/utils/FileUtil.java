package com.cot.app.backend.scheduled.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


@Component
@Slf4j
public class FileUtil {

    public static final String REPORT_DOWNLOAD_LOCATION = "./";
    public static final String REPORT_ZIPPED_FILENAME = "cot_report_yr_%s.zip";

    public String writeToFile(ResponseEntity<byte[]> reportResponse, String cotReportYear) {
        String fileName = String.format(REPORT_ZIPPED_FILENAME, cotReportYear);
        try {
            Files.write(Paths.get(REPORT_DOWNLOAD_LOCATION + fileName), Objects.requireNonNull(reportResponse.getBody()));
        } catch (IOException exception) {
            log.info("Encountered error writing to file: {} {}", exception, exception.getMessage());
        }
        log.info("Created file: {}", fileName);
        return fileName;
    }

}
