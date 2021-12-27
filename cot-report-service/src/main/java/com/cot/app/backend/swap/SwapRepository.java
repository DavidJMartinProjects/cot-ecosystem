package com.cot.app.backend.swap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author davidjmartin
 */
@Repository
public interface SwapRepository extends JpaRepository<SwapEntity, Long> {
    public List<SwapEntity> findBySymbolContainingIgnoreCase(String symbol);
    public List<SwapEntity> findByShortSwapGreaterThan(double num);
    public List<SwapEntity> findByLongSwapGreaterThan(double num);
}
