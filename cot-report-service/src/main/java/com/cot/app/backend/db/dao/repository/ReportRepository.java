package com.cot.app.backend.db.dao.repository;

import com.cot.app.backend.db.dao.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author davidjmartin
 */
@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    List<ReportEntity> findByInstrument(String instrument);
    void deleteByInstrument(String name);
}
