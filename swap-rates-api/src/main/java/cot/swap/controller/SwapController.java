package cot.swap.controller;

import cot.swap.model.SwapDto;
import cot.swap.model.SwapReportDto;
import cot.swap.service.SwapReportService;
import cot.swap.service.SwapService;
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

import static cot.swap.controller.SwapController.API_BASE_PATH;

/**
 * @author davidjmartin
 */
@RestController
@RequestMapping(API_BASE_PATH)
@Slf4j
@CrossOrigin
public class SwapController {

    public static final String API_BASE_PATH = "/api";
    public static final String FILTER_POSITIVE_SWAPS_DEFAULT_VALUE = "false";

    private static final String SWAP_URL = "/swaps";
    private static final String POSITIVE_SWAP_URL = "/positive";
    private static final String GET_ALL_CHARTS_URL = "/charts";
    private static final String SYMBOL_DEFAULT_VALUE = "all";

    private final SwapService swapService;
    private final SwapReportService swapReportService;
    
    @Autowired
    public SwapController(SwapService swapService, SwapReportService swapReportService) {
        this.swapService = swapService;
        this.swapReportService = swapReportService;
    }

    @GetMapping(SWAP_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapDto> getSwapsBySymbol(
        @RequestParam(defaultValue = SYMBOL_DEFAULT_VALUE) String symbol,
        @RequestParam(defaultValue = FILTER_POSITIVE_SWAPS_DEFAULT_VALUE) String filterPositiveSwaps
        ) {
        log.info("GET: {}?symbol={}", API_BASE_PATH + SWAP_URL, symbol);
        if(SYMBOL_DEFAULT_VALUE.equalsIgnoreCase(symbol)) {
            return swapService.fetchAllSwaps();
        }
        return swapService.fetchSwapsBySymbol(symbol, Boolean.valueOf(filterPositiveSwaps));
    }

    @GetMapping(SWAP_URL + POSITIVE_SWAP_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapDto> getPositiveSwaps() {
        log.info("GET: {}", API_BASE_PATH + SWAP_URL + POSITIVE_SWAP_URL);
        return swapService.fetchPositiveSwaps();
    }

    @GetMapping(SWAP_URL + GET_ALL_CHARTS_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapReportDto> getChartDataForSymbol(
        @RequestParam(defaultValue = SYMBOL_DEFAULT_VALUE) String symbol
    ) {
        log.info("{}", swapReportService.getChartDataForSymbol(symbol));
        return swapReportService.getChartDataForSymbol(symbol);
    }

}
