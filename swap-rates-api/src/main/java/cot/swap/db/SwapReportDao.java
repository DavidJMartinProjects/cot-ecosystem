package cot.swap.db;

import cot.swap.db.entity.EntityMapper;
import cot.swap.db.entity.SwapEntity;
import cot.swap.db.entity.chart.ChartSwapEntity;
import cot.swap.db.repository.SwapReportRepository;
import cot.swap.model.SwapDto;
import cot.swap.model.SwapReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author davidjmartin
 */
@Service
public class SwapReportDao {

    @Autowired
    private SwapReportRepository swapReportRepository;

    @Autowired
    private EntityMapper entityMapper;

    public void saveSwapsReport(LocalDateTime timeStamp, List<SwapDto> swapDtos) {
        List<SwapEntity> swapEntities = entityMapper.toList(swapDtos, SwapEntity.class);
        List<ChartSwapEntity> swaps = swapEntities.stream().map(
            swapEntity -> {
                return ChartSwapEntity.builder()
                    .symbol(swapEntity.getSymbol())
                    .timeStamp(timeStamp.toLocalTime().toString())
                    .longSwap(String.valueOf(swapEntity.getLongSwap()))
                    .shortSwap(String.valueOf(swapEntity.getShortSwap()))
                    .build();
            })
        .collect(Collectors.toList());
        swapReportRepository.saveAllAndFlush(swaps);
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public List<SwapReportDto> fetchAllSwapReports() {
        return entityMapper.toList(swapReportRepository.findAll(), SwapReportDto.class);
    }

    public List<SwapReportDto> fetchSwapChartDataBySymbol(String symbol) {
        return entityMapper.toList(
                swapReportRepository.findDistinctBySymbolContainingIgnoreCase(symbol), SwapReportDto.class);
    }

}
