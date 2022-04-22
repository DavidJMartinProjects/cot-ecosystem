package com.cot.app.backend.scheduled;

import com.cot.app.backend.scheduled.service.ReportDownloadService;
import com.cot.app.backend.scheduled.utils.DataUtil;
import com.cot.app.backend.scheduled.utils.ExcelFileUtil;
import com.cot.app.backend.scheduled.utils.ZipFileUtil;
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
    private ExcelFileUtil excelFileUtil;

    @Autowired
    private ReportDownloadService reportDownloadService;

    @Autowired
    private DataUtil dataUtil;

    public void downloadCotReports() {
        try {
            for (String cotReportYear : cotReportYears) {
                String url = String.format(REPORT_DOWNLOAD_URL, cotReportYear);
                downloadAndSaveReport(url, cotReportYear);
            }
        } catch (Exception exception) {
            log.error("failed to download report. Error: {}", exception.getMessage());
        }
    }

    private void downloadAndSaveReport(String reportUrl, String cotReportYear) throws IOException {
        log.info("retrieving report yr: {}, url: {}", cotReportYear, reportUrl);
        String fileName = reportDownloadService.retrieveReport(reportUrl, cotReportYear);
        ZipFileUtil.unzip(fileName);
        excelFileUtil.saveReportToDb();
        dataUtil.process();
        log.info("cot-report retrieved successfully.");
    }

}
