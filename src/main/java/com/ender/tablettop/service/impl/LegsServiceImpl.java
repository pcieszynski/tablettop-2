package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.LegsService;
import com.ender.tablettop.domain.Legs;
import com.ender.tablettop.repository.LegsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Legs.
 */
@Service
@Transactional
public class LegsServiceImpl implements LegsService {

    private final Logger log = LoggerFactory.getLogger(LegsServiceImpl.class);

    private final LegsRepository legsRepository;

    public LegsServiceImpl(LegsRepository legsRepository) {
        this.legsRepository = legsRepository;
    }

    /**
     * Save a legs.
     *
     * @param legs the entity to save
     * @return the persisted entity
     */
    @Override
    public Legs save(Legs legs) {
        log.debug("Request to save Legs : {}", legs);
        return legsRepository.save(legs);
    }

    /**
     * Get all the legs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Legs> findAll() {
        log.debug("Request to get all Legs");
        return legsRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Legs with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Legs> findAllWithEagerRelationships(Pageable pageable) {
        return legsRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one legs by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Legs> findOne(Long id) {
        log.debug("Request to get Legs : {}", id);
        return legsRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the legs by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Legs : {}", id);
        legsRepository.deleteById(id);
    }
}
