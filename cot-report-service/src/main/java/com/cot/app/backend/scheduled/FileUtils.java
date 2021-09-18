package com.cot.app.backend.scheduled;

import static com.cot.app.backend.scheduled.ReportDownloader.REPORT_DOWNLOAD_LOCATION;
import static com.cot.app.backend.scheduled.ReportDownloader.REPORT_ZIPPED_FILENAME;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class FileUtils {

    private static final String REPORT_ZIP_FILE = REPORT_DOWNLOAD_LOCATION + REPORT_ZIPPED_FILENAME;

    private FileUtils() {}

    public static void unzip() throws IOException {
        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(REPORT_ZIP_FILE))) {
            Path path = Paths.get(REPORT_DOWNLOAD_LOCATION);
            for (ZipEntry entry; (entry = inputStream.getNextEntry()) != null; ) {
                Path resolvedPath = path.resolve(entry.getName());
                if (!entry.isDirectory()) {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(inputStream, resolvedPath, REPLACE_EXISTING);
                } else {
                    Files.createDirectories(resolvedPath);
                }
            }
            log.info("unzip operation completed successfully.");
        }
    }
}
