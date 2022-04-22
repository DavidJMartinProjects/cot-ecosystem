package com.cot.app.backend.scheduled.service;

import com.cot.app.backend.db.ReportDao;
import com.cot.app.backend.model.ReportDto;
import com.cot.app.backend.model.enums.Symbols;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
@Slf4j
public class AnalysisService {

    @Autowired
    private ReportDao<ReportDto> reportDao;

    private final DecimalFormat df = new DecimalFormat("0.#");

    public void calcWeeklyChange() {
        List<ReportDto> reportDtos;
        for (Symbols symbol : Symbols.values()) {
            reportDtos = reportDao.findAll(symbol.name());
            for (int index = reportDtos.size()-2; index > 0; index --) {
                calculatePositionChanges(reportDtos, index);
                calculateNetChange(reportDtos, index);
            }
            reportDao.save(reportDtos);
        }
    }

    private void calculateNetChange(List<ReportDto> reportDtos, int index) {
        // calculate and set net-change
        double netChange = reportDtos.get(index - 1).getNetPositions() - reportDtos.get(index).getNetPositions();
        reportDtos.get(index - 1).setNetPositionsChange(netChange);
    }

    private void calculatePositionChanges(List<ReportDto> reportDtos, int index) {
        // calculate change from previous week
        String longChange = df.format(Double.parseDouble(reportDtos.get(index - 1).getPercentageLong())
                - Double.parseDouble(reportDtos.get(index).getPercentageLong()));
        String shortChange = df.format(Double.parseDouble(reportDtos.get(index - 1).getPercentageShort())
                - Double.parseDouble(reportDtos.get(index).getPercentageShort()));

        // set change results
        reportDtos.get(index - 1).setPercentageLongChange(longChange);
        reportDtos.get(index - 1).setPercentageShortChange(shortChange);
    }

}
