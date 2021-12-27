package com.cot.app.backend.scheduled;

import com.cot.app.backend.scheduled.utils.DataUtil;
import com.cot.app.backend.scheduled.utils.ExcelFileUtils;
import com.cot.app.backend.scheduled.utils.ZipFileUtils;
import com.cot.app.backend.swap.SwapsWebScraper;
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

    @Autowired
    private ExcelFileUtils excelFileUtils;

    @Autowired
    private ReportDownloader reportDownloader;

    @Autowired
    private DataUtil dataUtil;

    @Autowired
    private SwapsWebScraper swapsWebScraper;

    public void importReport() throws IOException {
        log.info("retrieving latest cot report");
        reportDownloader.downloadFile();

        log.info("unzipping cot report data");
        ZipFileUtils.unzip();

        log.info("saving Report To Db");
        excelFileUtils.saveReportToDb();


        log.info("processing data...");
        dataUtil.process();

//        log.info("cleaning work directory files.");
//        org.apache.commons.io.FileUtils.cleanDirectory(new File(REPORT_DOWNLOAD_LOCATION));
        log.info("cot-report retrieved successfully.");

        log.info("retrieving latest swap data.");
        swapsWebScraper.scrape();
        log.info("swap data retrieved successfully.");
    }

}
