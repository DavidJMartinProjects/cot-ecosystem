package com.cot.app.backend.scheduled.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Component
@Slf4j
public class FileUtil {

    public static final String REPORT_DOWNLOAD_LOCATION = "./downloaded-reports";
    public static final String REPORT_ZIP_FILENAME = "cot_report_yr_%s.zip";

    public String writeToFile(ResponseEntity<byte[]> reportResponse, String cotReportYear) {
        String fileName = String.format(REPORT_ZIP_FILENAME, cotReportYear);
        try {
            Files.write(Paths.get(fileName), Objects.requireNonNull(reportResponse.getBody()));
        } catch (IOException exception) {
            log.info("Encountered error writing to file: {} {}", exception, exception.getMessage());
        }
        log.info("Saved file to disk: {}", fileName);
        return fileName;
    }

    public void unzip(String fileName, String cotReportYear) {
        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(fileName))) {
            Path path = Paths.get(REPORT_DOWNLOAD_LOCATION + "/"+ cotReportYear);
            for (ZipEntry entry; (entry = inputStream.getNextEntry()) != null; ) {
                Path resolvedPath = path.resolve(entry.getName());
                if (!entry.isDirectory()) {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(inputStream, resolvedPath, REPLACE_EXISTING);
                } else {
                    Files.createDirectories(resolvedPath);
                }
            }
            log.info("Completed unzipping file.");
        } catch (Exception exception) {
            log.info("Encountered error unzipping file: {} {}", exception, exception.getMessage());
        }
    }

}
