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

/**
 * @author DavidJMartin
 */
@RestController
@RequestMapping(API_BASE_PATH)
@Slf4j
@CrossOrigin
public class ReportController {

    public static final String API_BASE_PATH = "/api";
    public static final String REPORTS_URL = "/reports";

    @Autowired
    private ReportService reportService;

    @GetMapping(REPORTS_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<ReportDto> getReports(@RequestParam String symbol) {
        log.info("GET: {}?symbol={}", API_BASE_PATH + REPORTS_URL, symbol);
        return reportService.getReportBySymbol(symbol);
    }

}
