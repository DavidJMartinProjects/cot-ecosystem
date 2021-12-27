package com.cot.app.backend.swap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class SwapDao {

    @Autowired
    private SwapRepository swapRepository;

    @Autowired
    private EntityMapper entityMapper;

    public void saveSwaps(List<SwapDto> swapDtos) {
        List<SwapEntity> swapEntities = entityMapper.toList(swapDtos, SwapEntity.class);
        swapRepository.saveAll(swapEntities);
    }

    public List<SwapDto> fetchPositiveSwaps() {
        List<SwapEntity> shortSwaps = new ArrayList<>();
        shortSwaps = swapRepository.findByShortSwapGreaterThan(0);

        List<SwapEntity> longSwaps = new ArrayList<>();
        longSwaps = swapRepository.findByLongSwapGreaterThan(0);

        Set<SwapEntity> totalPositiveSwaps = new HashSet<SwapEntity>();
        totalPositiveSwaps.addAll(shortSwaps);
        totalPositiveSwaps.addAll(longSwaps);

        return entityMapper.toList(Arrays.asList(totalPositiveSwaps.toArray()), SwapDto.class);
    }

    public List<SwapDto> fetchAllSwaps() {
        return entityMapper.toList(swapRepository.findAll(), SwapDto.class);
    }

    public List<SwapDto> fetchSwapsBySymbol(String symbol) {
        return entityMapper.toList(swapRepository.findBySymbolContainingIgnoreCase(symbol), SwapDto.class);
    }
}
