package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.BackpackService;
import com.ender.tablettop.domain.Backpack;
import com.ender.tablettop.repository.BackpackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Backpack.
 */
@Service
@Transactional
public class BackpackServiceImpl implements BackpackService {

    private final Logger log = LoggerFactory.getLogger(BackpackServiceImpl.class);

    private final BackpackRepository backpackRepository;

    public BackpackServiceImpl(BackpackRepository backpackRepository) {
        this.backpackRepository = backpackRepository;
    }

    /**
     * Save a backpack.
     *
     * @param backpack the entity to save
     * @return the persisted entity
     */
    @Override
    public Backpack save(Backpack backpack) {
        log.debug("Request to save Backpack : {}", backpack);
        return backpackRepository.save(backpack);
    }

    /**
     * Get all the backpacks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Backpack> findAll() {
        log.debug("Request to get all Backpacks");
        return backpackRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Backpack with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Backpack> findAllWithEagerRelationships(Pageable pageable) {
        return backpackRepository.findAllWithEagerRelationships(pageable);
    }


    /**
     * Get one backpack by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Backpack> findOne(Long id) {
        log.debug("Request to get Backpack : {}", id);
        return backpackRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the backpack by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Backpack : {}", id);
        backpackRepository.deleteById(id);
    }

    @Override
    public Optional<Backpack> findByCharacterId(Long characterId) {
        return backpackRepository.findByCharacterId(characterId);
    }


}
