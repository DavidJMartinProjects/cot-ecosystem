package com.cot.app.backend.swap;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class SwapService {

//    private static final String TD_HTML_TAG = "td";
//    private static final String TBODY_HTML_TAG = "tbody";
//    private static final String SWAP_TABLE_ELEMENT_ID = "tablepress-25";
//    private static final String REMOTE_SWAP_SERVICE_URL = "https://www.icmarkets.com/blog/metatrader-4-swaps/";
//
    @Autowired
    private SwapDao swapDao;
//
//    public void retrieveSwapData() {
//        Set<SwapDto> swaps = new HashSet<>();
//
//        try {
//            Document webPage = Jsoup
//                .connect(REMOTE_SWAP_SERVICE_URL)
//                .get();
//
//            Element tbodyElement = webPage
//                .getElementById(SWAP_TABLE_ELEMENT_ID)
//                .getElementsByTag(TBODY_HTML_TAG).get(0);
//            Elements rows = tbodyElement.children();
//
//            for (Element row : rows) {
//                Elements tdElements = row.getElementsByTag(TD_HTML_TAG);
//
//                String symbol = tdElements.get(0).text().replace(",", "");
//                double longSwap = Double.parseDouble(tdElements.get(1).text().replace(",", ""));
//                double shortSwap = Double.parseDouble(tdElements.get(2).text().replace(",", ""));
//
//                if(!symbol.contains(".")) {
//                    SwapDto swapDto = SwapDto.builder()
//                        .symbol(symbol)
//                        .longSwap(longSwap)
//                        .shortSwap(shortSwap)
//                        .build();
//                    swaps.add(swapDto);
//                }
//            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            throw exception;
//        }
//        log.info("SwapList.size(): {}", swaps.size());
//        List swapDtos = Arrays.asList(swaps.toArray());
//        swapDao.saveSwaps(swapDtos);
//        log.info("saved: {} swaps.", swaps.size());
//    }

    public List<SwapDto> fetchAllSwaps() {
        return swapDao.fetchAllSwaps();
    }

    public List<SwapDto> fetchPositiveSwaps() {
        return swapDao.fetchPositiveSwaps();
    }

    public List<SwapDto> fetchSwapsBySymbol(String symbol) {
        return swapDao.fetchSwapsBySymbol(symbol);
    }
}

