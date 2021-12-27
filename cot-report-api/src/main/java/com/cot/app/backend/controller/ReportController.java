package com.cot.app.backend.controller;

import com.cot.app.backend.model.ReportDto;
import com.cot.app.backend.service.ReportService;
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

import static com.cot.app.backend.controller.ReportController.API_BASE_PATH;
import static com.cot.app.backend.controller.ReportController.REPORTS_URL;

/**
 * @author DavidJMartin
 */
@RestController
@RequestMapping(API_BASE_PATH + REPORTS_URL)
@Slf4j
@CrossOrigin
public class ReportController {

    public static final String API_BASE_PATH = "/api";
    public static final String REPORTS_URL = "/reports";

    private static final String STATUS_URL = "/status";
    public static final String COT_PATH = "/cot";

    @Autowired
    private ReportService reportService;

    @GetMapping(STATUS_URL)
    @ResponseStatus(HttpStatus.OK)
    public String greeting() {
        return "cot report service is online.";
    }

    @GetMapping(COT_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<ReportDto> getReports(@RequestParam String symbol) {
        log.info("GET: {}?symbol={}", API_BASE_PATH + REPORTS_URL + COT_PATH, symbol);
        return reportService.getReportsByInstrument(symbol);
    }

}
