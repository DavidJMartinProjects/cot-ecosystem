package cot.swap.service;

import cot.swap.db.SwapReportDao;
import cot.swap.model.SwapReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwapReportService {

    @Autowired
    SwapReportDao swapReportDao;

    public List<SwapReportDto> getChartDataForSymbol(String symbol) {
        return swapReportDao.fetchSwapChartDataBySymbol(symbol);
    }
}
