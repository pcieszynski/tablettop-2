package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.PlayerMessageService;
import com.ender.tablettop.domain.PlayerMessage;
import com.ender.tablettop.repository.PlayerMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PlayerMessage.
 */
@Service
@Transactional
public class PlayerMessageServiceImpl implements PlayerMessageService {

    private final Logger log = LoggerFactory.getLogger(PlayerMessageServiceImpl.class);

    private final PlayerMessageRepository playerMessageRepository;

    public PlayerMessageServiceImpl(PlayerMessageRepository playerMessageRepository) {
        this.playerMessageRepository = playerMessageRepository;
    }

    /**
     * Save a playerMessage.
     *
     * @param playerMessage the entity to save
     * @return the persisted entity
     */
    @Override
    public PlayerMessage save(PlayerMessage playerMessage) {
        log.debug("Request to save PlayerMessage : {}", playerMessage);
        return playerMessageRepository.save(playerMessage);
    }

    /**
     * Get all the playerMessages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PlayerMessage> findAll() {
        log.debug("Request to get all PlayerMessages");
        return playerMessageRepository.findAll();
    }


    /**
     * Get one playerMessage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlayerMessage> findOne(Long id) {
        log.debug("Request to get PlayerMessage : {}", id);
        return playerMessageRepository.findById(id);
    }

    /**
     * Delete the playerMessage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlayerMessage : {}", id);
        playerMessageRepository.deleteById(id);
    }
}
