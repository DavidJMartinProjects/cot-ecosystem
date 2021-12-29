package cot.swap.scheduled;

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
    private SwapsWebScraper swapsWebScraper;

    public void importReport() throws IOException {
        log.info("Executing scheduled task: retrieve swap data.");
        swapsWebScraper.scrape();
        log.info("swap data retrieved successfully.");
    }

}
