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

    public void saveChartData(String timeStamp, List<SwapDto> swapDtos) {
        List<SwapEntity> swapEntities = entityMapper.toList(swapDtos, SwapEntity.class);
        List<ChartSwapEntity> swaps = swapEntities.stream().map(
            swapEntity -> ChartSwapEntity.builder()
                .symbol(swapEntity.getSymbol())
                .timeStamp(timeStamp)
                .longSwap(String.valueOf(swapEntity.getLongSwap()))
                .shortSwap(String.valueOf(swapEntity.getShortSwap()))
                .build()
        )
        .collect(Collectors.toList());
        swapReportRepository.saveAllAndFlush(swaps);
    }

    public List<SwapReportDto> fetchSwapChartDataBySymbol(String symbol) {
        return entityMapper.toList(
            swapReportRepository.findDistinctBySymbolContainingIgnoreCase(symbol), SwapReportDto.class);
    }

}
