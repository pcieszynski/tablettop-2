package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.GamemasterService;
import com.ender.tablettop.domain.Gamemaster;
import com.ender.tablettop.repository.GamemasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Gamemaster.
 */
@Service
@Transactional
public class GamemasterServiceImpl implements GamemasterService {

    private final Logger log = LoggerFactory.getLogger(GamemasterServiceImpl.class);

    private final GamemasterRepository gamemasterRepository;

    public GamemasterServiceImpl(GamemasterRepository gamemasterRepository) {
        this.gamemasterRepository = gamemasterRepository;
    }

    /**
     * Save a gamemaster.
     *
     * @param gamemaster the entity to save
     * @return the persisted entity
     */
    @Override
    public Gamemaster save(Gamemaster gamemaster) {
        log.debug("Request to save Gamemaster : {}", gamemaster);
        return gamemasterRepository.save(gamemaster);
    }

    /**
     * Get all the gamemasters.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Gamemaster> findAll() {
        log.debug("Request to get all Gamemasters");
        return gamemasterRepository.findAll();
    }


    /**
     * Get one gamemaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Gamemaster> findOne(Long id) {
        log.debug("Request to get Gamemaster : {}", id);
        return gamemasterRepository.findById(id);
    }

    /**
     * Delete the gamemaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gamemaster : {}", id);
        gamemasterRepository.deleteById(id);
    }
}
