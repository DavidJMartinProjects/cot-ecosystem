package com.cot.app.backend.swap;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class SwapsWebScraper {

    private static final String TD_HTML_TAG = "td";
    private static final String TBODY_HTML_TAG = "tbody";
    private static final String SWAP_TABLE_ELEMENT_ID = "tablepress-25";
    private static final String REMOTE_SWAP_SERVICE_URL = "https://www.icmarkets.com/blog/metatrader-4-swaps/";

    private static final int SYMBOL_ELEMENT_INDEX = 0;
    private static final int LONG_SWAP_ELEMENT_INDEX = 1;
    private static final int SHORT_SWAP_ELEMENT_INDEX = 2;

    @Autowired
    private SwapDao swapDao;

    public void retrieveSwapData() {
        Set<SwapDto> swaps = new HashSet<>();
        try {
            Document webPage = Jsoup.connect(REMOTE_SWAP_SERVICE_URL).get();

            Element tbodyElement = webPage
                .getElementById(SWAP_TABLE_ELEMENT_ID)
                .getElementsByTag(TBODY_HTML_TAG).get(SYMBOL_ELEMENT_INDEX);
            Elements rows = tbodyElement.children();

            for (Element row : rows) {
                Elements tdElements = row.getElementsByTag(TD_HTML_TAG);
                String symbol = tdElements.get(SYMBOL_ELEMENT_INDEX).text().replace(",", "");
                double longSwap = Double.parseDouble(tdElements.get(LONG_SWAP_ELEMENT_INDEX).text().replace(",", ""));
                double shortSwap = Double.parseDouble(tdElements.get(SHORT_SWAP_ELEMENT_INDEX).text().replace(",", ""));

                if(!symbol.contains(".")) {
                    SwapDto swapDto = SwapDto.builder()
                        .symbol(symbol)
                        .longSwap(longSwap)
                        .shortSwap(shortSwap)
                        .build();
                    swaps.add(swapDto);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        List swapDtos = Arrays.asList(swaps.toArray());
        swapDao.saveSwaps(swapDtos);
        log.info("saved: {} swaps.", swaps.size());
    }

}
