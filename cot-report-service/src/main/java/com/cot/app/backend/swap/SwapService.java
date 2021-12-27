package com.cot.app.backend.swap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davidjmartin
 */
@Service
public class SwapService {

    private static final String TD_HTML_TAG = "td";
    private static final String TBODY_HTML_TAG = "tbody";
    private static final String SWAP_TABLE_ELEMENT_ID = "tablepress-25";
    private static final String REMOTE_SWAP_SERVICE_URL = "https://www.icmarkets.com/blog/metatrader-4-swaps/";

    @Autowired
    private SwapDao swapDao;

    public List<SwapDto> retrieveSwapData() {
        List<SwapDto> covidDataList = new ArrayList<>();

        try {
            Document webPage = Jsoup
                .connect(REMOTE_SWAP_SERVICE_URL)
                .get();
            Element tbody = webPage
                .getElementById(SWAP_TABLE_ELEMENT_ID)
                .getElementsByTag(TBODY_HTML_TAG).get(0);
            Elements rows = tbody.children();

            for (Element row : rows) {
                Elements tds = row.getElementsByTag(TD_HTML_TAG);
                String symbol = tds.get(0).text().replace(",", "");
                String longSwap = tds.get(1).text().replace(",", "");
                String shortSwap = tds.get(2).text().replace(",", "");

                SwapDto swapDto = SwapDto.builder()
                    .symbol(symbol)
                    .longSwap(longSwap)
                    .shortSwap(shortSwap)
                    .build();
                covidDataList.add(swapDto);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        swapDao.saveSwaps(covidDataList);
        return covidDataList;
    }

}

