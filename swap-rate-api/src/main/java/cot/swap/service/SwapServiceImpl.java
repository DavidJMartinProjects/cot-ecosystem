package cot.swap.service;

import cot.swap.db.SwapDao;
import cot.swap.model.SwapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author davidjmartin
 */
@Service
public class SwapServiceImpl implements SwapService {

    @Autowired
    private SwapDao swapDao;

    @Override
    public List<SwapDto> fetchAllSwaps() {
        return swapDao.fetchAllSwaps();
    }

    @Override
    public List<SwapDto> fetchPositiveSwaps() {
        return swapDao.fetchPositiveSwaps();
    }

    @Override
    public List<SwapDto> fetchSwapsBySymbol(String symbol) {
        return swapDao.fetchSwapsBySymbol(symbol);
    }

}

