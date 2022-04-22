package com.cot.app.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cot.app.backend.db.ReportDao;
import com.cot.app.backend.model.ReportDto;

/**
 * @author DavidJMartin
 */
@Service
public class ReportService {

    @Autowired
    private ReportDao<ReportDto> reportDao;

    public List<ReportDto> getReportBySymbol(String symbol) {
        return reportDao.findAll(symbol);
    }

}
