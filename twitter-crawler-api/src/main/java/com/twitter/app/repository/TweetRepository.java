package com.twitter.app.repository;

import com.twitter.app.model.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author davidjmartin
 */
@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
}
