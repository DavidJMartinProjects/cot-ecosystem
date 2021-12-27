package com.cot.app.backend.swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author davidjmartin
 */
@Service
public class SwapDao {

    @Autowired
    private SwapRepository swapRepository;

    @Autowired
    private EntityMapper entityMapper;

    public List<SwapDto> saveSwaps(List<SwapDto> swapDtos) {
        List<SwapEntity> swapEntities = entityMapper.toList(swapDtos, SwapEntity.class);
        return entityMapper.toList(swapRepository.saveAll(swapEntities), SwapDto.class);
    }

}
