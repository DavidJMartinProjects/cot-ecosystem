package com.cot.app.backend.db;

import com.cot.app.backend.model.SupportedSymbol;

import java.util.List;

/**
 * details supported db operations.
 *
 * @param <T> the entity type
 *
 * @author davidjmartin
 */
public interface DbOperation<T> {

    /**
     * Save a list of entities.
     *
     * @param entities the list of entities
     *
     * @return the saved entites
     */
    List<T> save(List<T> entities);

    /**
     * Find all entity.
     *
     * @return the list of found entity
     */
    List<T> findAll();

    /**
     * Find by filter.
     *
     * @return the list of found entity
     */
    List<T> findAll(String filter);

    /**
     * Delete all records.
     *
     * @return the list of found entity
     */
    void deleteAll();

    /**
     * Delete by Symbol.
     *
     * @return the list of found entity
     */
    void deleteBySymbol(SupportedSymbol symbol);

}