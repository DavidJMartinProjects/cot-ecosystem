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

    @Autowired
    private ExcelFileUtils excelFileUtils;

    @Autowired
    private ReportDownloader reportDownloader;

    @Autowired
    private DataUtil dataUtil;

    public void importReport() throws IOException {
        log.info("retrieving latest cot report");
        reportDownloader.downloadFile();
        ZipFileUtils.unzip();
        excelFileUtils.saveReportToDb();
        dataUtil.process();
//        log.info("cleaning work directory files.");
//        org.apache.commons.io.FileUtils.cleanDirectory(new File(REPORT_DOWNLOAD_LOCATION));
        log.info("cot-report retrieved successfully.");
    }

}
