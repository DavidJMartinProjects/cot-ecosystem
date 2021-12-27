package com.cot.app.backend.scheduled;

import com.cot.app.backend.swap.SwapDto;
import com.cot.app.backend.swap.SwapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    private DataProcessor dataProcessor;

    @Autowired
    private SwapService swapService;

    public void importReport() throws IOException {
        log.info("retrieving latest cot report");
        reportDownloader.downloadFile();

        log.info("unzipping cot report data");
        FileUtils.unzip();

        log.info("saving Report To Db");
        excelFileUtils.saveReportToDb();

        log.info("processing data...");
        dataProcessor.processData();

//        log.info("cleaning work directory files.");
//        org.apache.commons.io.FileUtils.cleanDirectory(new File(REPORT_DOWNLOAD_LOCATION));
        log.info("cot-report retrieved successfully.");

        log.info("retrieving latest swap data.");
        List<SwapDto> swaps = swapService.retrieveSwapData();
        log.info("swap data retrieved successfully.");
    }

}
