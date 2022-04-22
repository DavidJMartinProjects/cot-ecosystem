package com.cot.app.backend.scheduled.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.cot.app.backend.scheduled.utils.FileUtil.REPORT_DOWNLOAD_LOCATION;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author DavidJMartin
 */
@Slf4j
public class ZipFileUtil {
    public static void unzip(String fileName) throws IOException {
        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(REPORT_DOWNLOAD_LOCATION + fileName))) {
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
