package com.ender.tablettop.service;

import com.ender.tablettop.domain.Monster;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Monster.
 */
public interface MonsterService {

    /**
     * Save a monster.
     *
     * @param monster the entity to save
     * @return the persisted entity
     */
    Monster save(Monster monster);

    /**
     * Get all the monsters.
     *
     * @return the list of entities
     */
    List<Monster> findAll();


    /**
     * Get the "id" monster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Monster> findOne(Long id);

    /**
     * Delete the "id" monster.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
