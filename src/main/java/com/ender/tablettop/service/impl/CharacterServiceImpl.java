package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.CharacterService;
import com.ender.tablettop.domain.Character;
import com.ender.tablettop.repository.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Character.
 */
@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private final Logger log = LoggerFactory.getLogger(CharacterServiceImpl.class);

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    /**
     * Save a character.
     *
     * @param character the entity to save
     * @return the persisted entity
     */
    @Override
    public Character save(Character character) {
        log.debug("Request to save Character : {}", character);
        return characterRepository.save(character);
    }

    /**
     * Get all the characters.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Character> findAll() {
        log.debug("Request to get all Characters");
        return characterRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Character with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Character> findAllWithEagerRelationships(Pageable pageable) {
        return characterRepository.findAllWithEagerRelationships(pageable);
    }


    /**
     * get all the characters where Backpack is null.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Character> findAllWhereBackpackIsNull() {
        log.debug("Request to get all characters where Backpack is null");
        return StreamSupport
            .stream(characterRepository.findAll().spliterator(), false)
            .filter(character -> character.getBackpack() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one character by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Character> findOne(Long id) {
        log.debug("Request to get Character : {}", id);
        return characterRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the character by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Character : {}", id);
        characterRepository.deleteById(id);
    }

    @Override
    public Optional<Character> findByPlayerIdAndGameId(String playerId, String gameId) {
        return characterRepository.findByPlayerIdAndGameId(playerId, Long.parseLong(gameId));
    }

    @Override
    public List<Character> findByGameId(String gameId) {
        return characterRepository.findByGameId(Long.parseLong(gameId));
    }
}
