package com.cot.app.backend.swap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cot.app.backend.controller.ReportController.API_BASE_PATH;
import static com.cot.app.backend.controller.ReportController.REPORTS_URL;

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

    public static final String SWAP_URL = "/swaps";
    public static final String POSITIVE_SWAP_URL = "/positive";

    @Autowired
    private SwapService swapService;

    @GetMapping(SWAP_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapDto> greeting() {
        return swapService.fetchAllSwaps();
    }

    @GetMapping(SWAP_URL + POSITIVE_SWAP_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<SwapDto> getPositiveSwaps() {
        return swapService.fetchPositiveSwaps();
    }

}
