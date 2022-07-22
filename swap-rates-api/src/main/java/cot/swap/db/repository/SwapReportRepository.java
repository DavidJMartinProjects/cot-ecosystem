package cot.swap.db.repository;

import cot.swap.db.entity.chart.ChartSwapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author davidjmartin
 */
@Repository
public interface SwapReportRepository extends JpaRepository<ChartSwapEntity, Long> {

    List<ChartSwapEntity> findDistinctBySymbolContainingIgnoreCase(String symbol);

}
