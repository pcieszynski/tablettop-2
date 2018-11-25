package com.ender.tablettop.service;

import com.ender.tablettop.domain.Shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Shop.
 */
public interface ShopService {

    /**
     * Save a shop.
     *
     * @param shop the entity to save
     * @return the persisted entity
     */
    Shop save(Shop shop);

    /**
     * Get all the shops.
     *
     * @return the list of entities
     */
    List<Shop> findAll();

    /**
     * Get all the Shop with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Shop> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" shop.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Shop> findOne(Long id);

    /**
     * Delete the "id" shop.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
