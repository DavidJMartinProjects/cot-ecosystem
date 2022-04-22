package com.cot.app.backend.scheduled;

import com.cot.app.backend.scheduled.service.AnalysisService;
import com.cot.app.backend.scheduled.service.DownloadService;
import com.cot.app.backend.scheduled.utils.ExcelUtil;
import com.cot.app.backend.scheduled.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cot.app.backend.scheduled.utils.FileUtil.REPORT_ZIP_FILENAME;

/**
 * @author DavidJMartin
 */
@Service
@Slf4j
public class ScheduledTask implements JavaDelegate {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ExcelUtil spreadSheetUtil;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("delegate executing: {}", delegateExecution.getVariable("years"));
        try {
            String reportUrl = (String) delegateExecution.getVariable("reportUrl");
            String cotReportYear = (String) delegateExecution.getVariable("year");
            String url = String.format(reportUrl, cotReportYear);

            downloadReport(url, cotReportYear);
        } catch (ProcessEngineException exception) {
            log.info("ProcessEngineException: {}", exception.getMessage());
        }
    }
    private void downloadReport(String reportUrl, String cotReportYear) {
        // download report
        log.info("Retrieving report Year: {}", cotReportYear);
        downloadService.downloadReport(reportUrl, cotReportYear);

        String fileName = String.format(REPORT_ZIP_FILENAME, cotReportYear);
        fileUtil.unzip(fileName, cotReportYear);

        // save file
        spreadSheetUtil.processSheet(cotReportYear);

        // perform calculations and save
        analysisService.calcWeeklyChange();
        log.info("Success: Report saved.");
    }

}
