package cot.swap.scheduled;

import cot.swap.db.SwapDao;
import cot.swap.model.SwapDto;
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
public class SwapsWebScraper {

    private static final String TD_TAG = "td";
    private static final String TBODY_TAG = "tbody";
    private static final String SWAP_TABLE_ID = "tablepress-25";
    private static final String REMOTE_SWAP_SERVICE_URL = "https://www.icmarkets.com/blog/metatrader-4-swaps/";

    private static final int SYMBOL_INDEX = 0;
    private static final int LONG_SWAP_INDEX = 1;
    private static final int SHORT_SWAP_INDEX = 2;

    @Autowired
    private SwapDao swapDao;

    public void scrape() {
        Set<SwapDto> swaps = new HashSet<>();
        try {
            Document webPage = Jsoup.connect(REMOTE_SWAP_SERVICE_URL).get();            
            Element tbody = webPage.getElementById(SWAP_TABLE_ID)
                .getElementsByTag(TBODY_TAG)
                .get(SYMBOL_INDEX);
            Elements tableRows = tbody.children();

            for (Element tableRow : tableRows) {
                Elements tableData = tableRow.getElementsByTag(TD_TAG);
                String symbol = getTextByIndex(tableData, SYMBOL_INDEX);
                String longSwap = getTextByIndex(tableData, LONG_SWAP_INDEX);
                String shortSwap = getTextByIndex(tableData, SHORT_SWAP_INDEX);

                if(!symbol.contains(".")) {
                    swaps.add(buildSwapDto(symbol, longSwap, shortSwap));
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
    }

    private String getTextByIndex(Elements elements, int elementId) {
        return elements.get(elementId).text().replace(",", "");
    }

    private SwapDto buildSwapDto(String symbol, String longSwap, String shortSwap) {
        return SwapDto.builder()
            .symbol(symbol)
            .longSwap(Double.parseDouble(longSwap))
            .shortSwap(Double.parseDouble(shortSwap))
            .build();
    }

}
