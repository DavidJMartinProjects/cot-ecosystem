package com.cot.app.backend.scheduled;

import com.cot.app.backend.db.DbOperation;
import com.cot.app.backend.db.dao.entity.ReportEntity;
import com.cot.app.backend.db.dao.repository.ReportRepository;
import com.cot.app.backend.model.ReportDto;
import com.cot.app.backend.model.SupportedSymbol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DataProcessor {

    @Autowired
    private DbOperation<ReportDto> dbOperation;

    @Autowired
    private ReportRepository reportRepository;

    private DecimalFormat df = new DecimalFormat("0.00##");

    public void processData() {
        List<ReportDto> reportDtos;
        for (SupportedSymbol symbol : SupportedSymbol.values()) {
            reportDtos = dbOperation.findAll(symbol.name());

            for (int index = reportDtos.size()-2; index > 0; index --) {
                String longChange = df.format(Double.parseDouble(reportDtos.get(index - 1).getPercentageLong())
                        - Double.parseDouble(reportDtos.get(index).getPercentageLong()));
                log.info("longChange: {}", longChange);

                String shortChange = df.format(Double.parseDouble(reportDtos.get(index - 1).getPercentageShort())
                        - Double.parseDouble(reportDtos.get(index).getPercentageShort()));
                log.info("shortChange: {}", shortChange);

                reportDtos.get(index - 1).setPercentageLongChange(longChange);
                reportDtos.get(index - 1).setPercentageShortChange(shortChange);
            }
            dbOperation.save(reportDtos);

        }
    }
}
