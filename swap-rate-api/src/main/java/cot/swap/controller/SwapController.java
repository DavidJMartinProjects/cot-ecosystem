package cot.swap.controller;

import cot.swap.model.SwapDto;
import cot.swap.service.SwapServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cot.swap.controller.SwapController.*;

/**
 * @author davidjmartin
 */
@RestController
@RequestMapping(API_BASE_PATH + REPORTS_URL)
@Slf4j
@CrossOrigin
public class SwapController {

    public static final String API_BASE_PATH = "/api";
    public static final String REPORTS_URL = "/reports";

    private static final String SWAP_URL = "/swaps";
    private static final String POSITIVE_SWAP_URL = "/positive";

    private static final String SYMBOL_DEFAULT_VALUE = "all";

    @Autowired
    private SwapServiceImpl swapService;

    @GetMapping(SWAP_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapDto> getSwapsBySymbol(@RequestParam(defaultValue = SYMBOL_DEFAULT_VALUE) String symbol) {
        log.info("GET: {}?symbol={}", API_BASE_PATH + REPORTS_URL + SWAP_URL, symbol);
        if(SYMBOL_DEFAULT_VALUE.equalsIgnoreCase(symbol)) {
            return swapService.fetchAllSwaps();
        }
        return swapService.fetchSwapsBySymbol(symbol);
    }

    @GetMapping(SWAP_URL + POSITIVE_SWAP_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapDto> getPositiveSwaps() {
        log.info("GET: {}", API_BASE_PATH + REPORTS_URL + SWAP_URL + POSITIVE_SWAP_URL);
        return swapService.fetchPositiveSwaps();
    }

}
