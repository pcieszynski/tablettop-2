package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.GlovesService;
import com.ender.tablettop.domain.Gloves;
import com.ender.tablettop.repository.GlovesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Gloves.
 */
@Service
@Transactional
public class GlovesServiceImpl implements GlovesService {

    private final Logger log = LoggerFactory.getLogger(GlovesServiceImpl.class);

    private final GlovesRepository glovesRepository;

    public GlovesServiceImpl(GlovesRepository glovesRepository) {
        this.glovesRepository = glovesRepository;
    }

    /**
     * Save a gloves.
     *
     * @param gloves the entity to save
     * @return the persisted entity
     */
    @Override
    public Gloves save(Gloves gloves) {
        log.debug("Request to save Gloves : {}", gloves);
        return glovesRepository.save(gloves);
    }

    /**
     * Get all the gloves.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Gloves> findAll() {
        log.debug("Request to get all Gloves");
        return glovesRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Gloves with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Gloves> findAllWithEagerRelationships(Pageable pageable) {
        return glovesRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one gloves by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Gloves> findOne(Long id) {
        log.debug("Request to get Gloves : {}", id);
        return glovesRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the gloves by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gloves : {}", id);
        glovesRepository.deleteById(id);
    }
}
