package com.cot.app.backend.scheduled.utils;

import com.cot.app.backend.db.ReportDao;
import com.cot.app.backend.db.dao.repository.ReportRepository;
import com.cot.app.backend.model.ReportDto;
import com.cot.app.backend.model.enums.Symbols;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
@Slf4j
public class DataUtil {

    @Autowired
    private ReportDao<ReportDto> reportDao;

    @Autowired
    private ReportRepository reportRepository;

    private DecimalFormat df = new DecimalFormat("0.#");

    public void process() {
        List<ReportDto> reportDtos;
        for (Symbols symbol : Symbols.values()) {
            reportDtos = reportDao.findAll(symbol.name());

            for (int index = reportDtos.size()-2; index > 0; index --) {
                String longChange = df.format(Double.parseDouble(reportDtos.get(index - 1).getPercentageLong())
                        - Double.parseDouble(reportDtos.get(index).getPercentageLong()));

                String shortChange = df.format(Double.parseDouble(reportDtos.get(index - 1).getPercentageShort())
                        - Double.parseDouble(reportDtos.get(index).getPercentageShort()));

                reportDtos.get(index - 1).setPercentageLongChange(longChange);
                reportDtos.get(index - 1).setPercentageShortChange(shortChange);

                double netChange = reportDtos.get(index - 1).getNetPositions()
                        - reportDtos.get(index).getNetPositions();

                reportDtos.get(index - 1).setNetPositionsChange(netChange);
            }
            reportDao.save(reportDtos);

        }
    }
}
