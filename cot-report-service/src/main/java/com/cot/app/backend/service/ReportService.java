package com.cot.app.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cot.app.backend.db.DbOperation;
import com.cot.app.backend.model.ReportDto;

/**
 * @author DavidJMartin
 */
@Service
public class ReportService {

    @Autowired
    private DbOperation<ReportDto> dbOperation;

    public List<ReportDto> getReportsByInstrument(String instrument) {
        return dbOperation.findAll(instrument);
    }

}
