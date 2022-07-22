package cot.swap.db;

import cot.swap.db.entity.EntityMapper;
import cot.swap.db.entity.SwapEntity;
import cot.swap.db.repository.SwapRepository;
import cot.swap.model.SwapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author davidjmartin
 */
@Service
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
        Set<SwapEntity> totalPositiveSwaps = new HashSet<SwapEntity>();
        totalPositiveSwaps.addAll(swapRepository.findByShortSwapGreaterThan(0));
        totalPositiveSwaps.addAll(swapRepository.findByLongSwapGreaterThan(0));
        return entityMapper.toList(Arrays.asList(totalPositiveSwaps.toArray()), SwapDto.class);
    }

    public List<SwapDto> fetchAllSwaps() {
        return entityMapper.toList(swapRepository.findAll(), SwapDto.class);
    }

    public List<SwapDto> fetchSwapsBySymbol(String symbol) {
        return entityMapper.toList(swapRepository.findBySymbolContainingIgnoreCase(symbol), SwapDto.class);
    }

    public List<SwapDto> fetchSwapsBySymbol(String symbol, boolean filterPositiveSwaps) {
        if(filterPositiveSwaps) {
            Set<SwapEntity> totalPositiveSwaps = new HashSet<SwapEntity>();
            totalPositiveSwaps.addAll(swapRepository.findBySymbolContainingIgnoreCaseAndShortSwapGreaterThan(symbol,0));
            totalPositiveSwaps.addAll(swapRepository.findBySymbolContainingIgnoreCaseAndLongSwapGreaterThan(symbol, 0));
            return entityMapper.toList(Arrays.asList(totalPositiveSwaps.toArray()), SwapDto.class);
        }
        return entityMapper.toList(swapRepository.findBySymbolContainingIgnoreCase(symbol), SwapDto.class);
    }

}
