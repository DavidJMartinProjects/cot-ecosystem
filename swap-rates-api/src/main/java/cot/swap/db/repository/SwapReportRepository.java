package cot.swap.db.repository;

import cot.swap.db.entity.chart.ChartSwapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author davidjmartin
 */
@Repository
public interface SwapReportRepository extends JpaRepository<ChartSwapEntity, Long> {

    List<ChartSwapEntity> findBySymbolContainingIgnoreCase(String symbol);

    List<ChartSwapEntity> findDistinctBySymbolContainingIgnoreCase(String symbol);
//    void findFirstByIdBySymbolAsc();
//
//    public default List<ChartSwapEntity> findOrderedBySeatNumberLimitedTo(int limit) {
//        return entityManager.createQuery("SELECT p FROM ChartSwapEntity p ORDER BY p.id",
//                ChartSwapEntity.class).setMaxResults(limit).getResultList();
//    }

}
