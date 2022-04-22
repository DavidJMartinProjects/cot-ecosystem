package com.cot.app.backend.scheduled;

import com.cot.app.backend.scheduled.service.AnalysisService;
import com.cot.app.backend.scheduled.service.DownloadService;
import com.cot.app.backend.scheduled.utils.ExcelUtil;
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

    private final String[] cotReportYears = { YR_2022, YR_2021 };

    public static final String REPORT_DOWNLOAD_URL = "https://www.cftc.gov/files/dea/history/dea_fut_xls_%s.zip";

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ExcelUtil spreadsheetUtil;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private AnalysisService analysisService;

    public void downloadCotReports() {
        for (String cotReportYear : cotReportYears) {
            String url = String.format(REPORT_DOWNLOAD_URL, cotReportYear);
            downloadReport(url, cotReportYear);
        }
        log.info("--- Scheduled task complete. ---");
    }

    private void downloadReport(String reportUrl, String cotReportYear) {
        // download report
        log.info("Retrieving report Year: {}", cotReportYear);
        String fileName = downloadService.downloadReport(reportUrl, cotReportYear);

        // save file
        fileUtil.unzip(fileName, cotReportYear);
        spreadsheetUtil.processSheet(cotReportYear);

        // perform calculations and save
        analysisService.calcWeeklyChange();
        log.info("Success: Report saved.");
    }

}
