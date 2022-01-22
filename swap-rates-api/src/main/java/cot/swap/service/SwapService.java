package cot.swap.service;

import cot.swap.model.SwapDto;

import java.util.List;

/**
 * @author davidjmartin
 */
public interface SwapService {
    List<SwapDto> fetchAllSwaps();
    List<SwapDto> fetchPositiveSwaps();
    List<SwapDto> fetchSwapsBySymbol(String symbol);
    List<SwapDto> fetchSwapsBySymbol(String symbol, boolean filterPositiveSwaps);
}
