package com.cot.app.backend.scheduled;

import com.cot.app.backend.scheduled.service.AnalysisService;
import com.cot.app.backend.scheduled.service.DownloadService;
import com.cot.app.backend.scheduled.utils.ExcelFileUtil;
import com.cot.app.backend.scheduled.utils.FileUtil;
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

    private final static String YR_2021 = "2021";
    private final static String YR_2022 = "2022";

    private String[] cotReportYears = { YR_2022, YR_2021 };

    public static final String REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/dea_fut_xls_%s.zip";

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ExcelFileUtil excelFileUtil;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private AnalysisService analysisService;

    public void downloadCotReports() {
        try {
            for (String cotReportYear : cotReportYears) {
                String url = String.format(REPORT_DOWNLOAD_URL, cotReportYear);
                downloadAndSaveReport(String.format(REPORT_DOWNLOAD_URL, cotReportYear), cotReportYear);
            }
        } catch (Exception exception) {
            log.error("failed to download report. Error: {}", exception.getMessage());
        }
        log.info("--- Scheduled task complete. ---");
    }

    private void downloadAndSaveReport(String reportUrl, String cotReportYear) throws IOException {
        log.info("Retrieving report Year: {}", cotReportYear);
        String fileName = downloadService.retrieveReport(reportUrl, cotReportYear);
        fileUtil.unzip(fileName);
        excelFileUtil.saveReportToDb();
        analysisService.calcWeeklyChange();
        log.info("Success: Report saved.");
    }

}
