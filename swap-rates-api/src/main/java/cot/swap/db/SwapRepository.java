package cot.swap.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author davidjmartin
 */
@Repository
public interface SwapRepository extends JpaRepository<SwapEntity, Long> {
    List<SwapEntity> findBySymbolContainingIgnoreCase(String symbol);
    List<SwapEntity> findByShortSwapGreaterThan(double num);
    List<SwapEntity> findByLongSwapGreaterThan(double num);
    List<SwapEntity> findBySymbolContainingIgnoreCaseAndShortSwapGreaterThan(String symbol, double num);
    List<SwapEntity> findBySymbolContainingIgnoreCaseAndLongSwapGreaterThan(String symbol, double num);
}
