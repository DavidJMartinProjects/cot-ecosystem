package com.cot.app.backend.swap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author davidjmartin
 */
@Repository
public interface SwapRepository extends JpaRepository<SwapEntity, Long> {
}
