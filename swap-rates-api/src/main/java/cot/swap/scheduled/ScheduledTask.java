package cot.swap.scheduled;

import cot.swap.db.SwapDao;
import cot.swap.db.SwapReportDao;
import cot.swap.model.SwapDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DavidJMartin
 */
@Service
@Slf4j
public class ScheduledTask {

    @Autowired
    private SwapsWebScraper swapsWebScraper;

    @Autowired
    private SwapDao swapDao;

    @Autowired
    private SwapReportDao swapReportDao;

    public void fetchAndSaveSwaps() throws IOException {
        log.info("=============================================");
        log.info("Execution TimeStamp: " + LocalDateTime.now());
        log.info("=============================================");
        log.info("Executing scheduled task: retrieve swap data.");
        List<SwapDto> swapDtos = swapsWebScraper.scrape();
        log.info("Status: Swap data retrieved successfully.");
        log.info("Status: Swap data saved successfully.");
        log.info("=============================================");
        swapDao.saveSwaps(swapDtos);
        swapReportDao.saveSwapsReport(LocalDateTime.now(), swapDtos);
    }

}
