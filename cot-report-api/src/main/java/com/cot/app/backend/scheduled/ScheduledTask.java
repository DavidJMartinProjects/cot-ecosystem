package com.cot.app.backend.scheduled;

import com.cot.app.backend.scheduled.utils.DataUtil;
import com.cot.app.backend.scheduled.utils.ExcelFileUtils;
import com.cot.app.backend.scheduled.utils.ZipFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author DavidJMartin
 */
@Service
@Slf4j
public class ScheduledTask {

    public static final String LAST_YEARS_REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/dea_fut_xls_2021.zip";
    public static final String THIS_YEARS_REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/dea_fut_xls_2022.zip";

    @Autowired
    private ExcelFileUtils excelFileUtils;

    @Autowired
    private ReportDownloader reportDownloader;

    @Autowired
    private DataUtil dataUtil;

    public void downloadCotReports() {
        try {
            downloadAndSaveReport(THIS_YEARS_REPORT_DOWNLOAD_URL);
            downloadAndSaveReport(LAST_YEARS_REPORT_DOWNLOAD_URL);
        } catch (Exception exception) {
            log.error("failed to download report. Error: {}", exception.getMessage());
        }
    }

    private void downloadAndSaveReport(String reportUrl) throws IOException {
        log.info("retrieving latest cot report");
        reportDownloader.downloadFile(reportUrl);
        ZipFileUtils.unzip();
        excelFileUtils.saveReportToDb();
        dataUtil.process();
        log.info("cot-report retrieved successfully.");
    }

}
