package com.ender.tablettop.service;

import com.ender.tablettop.domain.Character;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Interface for managing Character.
 */
public interface CharacterService {

    /**
     * Save a character.
     *
     * @param character the entity to save
     * @return the persisted entity
     */
    Character save(Character character);

    /**
     * Get all the characters.
     *
     * @return the list of entities
     */
    List<Character> findAll();
    /**
     * Get all the CharacterDTO where Backpack is null.
     *
     * @return the list of entities
     */
    List<Character> findAllWhereBackpackIsNull();

    /**
     * Get all the Character with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Character> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" character.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Character> findOne(Long id);

    /**
     * Delete the "id" character.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Optional<Character> findByPlayerIdAndGameId(String playerId, String gameId);

    List<Character> findByGameId(String gameId);
}
