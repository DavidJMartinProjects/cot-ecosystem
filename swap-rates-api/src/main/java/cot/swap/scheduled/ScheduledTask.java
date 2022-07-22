package cot.swap.scheduled;

import cot.swap.db.SwapDao;
import cot.swap.db.SwapReportDao;
import cot.swap.model.SwapDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

    // 0 0 6,19 * * * = 6:00 AM and 7:00 PM every day.
    @Scheduled(cron="0 0 8,20 * * * ")
    public void fetchAndSaveSwaps() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);

        log.info("=============================================");
        log.info("Execution TimeStamp: " + dateTime);
        log.info("=============================================");
        log.info("Executing scheduled task: retrieve swap data.");
        List<SwapDto> swapDtos = swapsWebScraper.scrape();
        log.info("Status: Swap data retrieved successfully.");

        log.info("Status: Saving data.");
        //  save swap dashboard data
        swapDao.deleteAllSwaps();
        swapDao.saveSwaps(swapDtos);

        //  save swap chart data
        swapReportDao.saveChartData(dateTime, swapDtos);
        log.info("Status: Swap data saved successfully.");
        log.info("=============================================");
    }

}
