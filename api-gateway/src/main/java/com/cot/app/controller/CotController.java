package com.cot.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CotController.API_BASE_PATH)
@Slf4j
public class CotController {

    public static final String API_BASE_PATH = "/api";
    public static final String STATUS_PATH = "/status";

    @GetMapping(STATUS_PATH)
    @ResponseStatus(HttpStatus.OK)
    public String getGreeting() {
        log.info("GET request to {}.", STATUS_PATH);
        return "api-gateway api is online.";
    }

}
