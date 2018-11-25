package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.HelmetService;
import com.ender.tablettop.domain.Helmet;
import com.ender.tablettop.repository.HelmetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Helmet.
 */
@Service
@Transactional
public class HelmetServiceImpl implements HelmetService {

    private final Logger log = LoggerFactory.getLogger(HelmetServiceImpl.class);

    private final HelmetRepository helmetRepository;

    public HelmetServiceImpl(HelmetRepository helmetRepository) {
        this.helmetRepository = helmetRepository;
    }

    /**
     * Save a helmet.
     *
     * @param helmet the entity to save
     * @return the persisted entity
     */
    @Override
    public Helmet save(Helmet helmet) {
        log.debug("Request to save Helmet : {}", helmet);
        return helmetRepository.save(helmet);
    }

    /**
     * Get all the helmets.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Helmet> findAll() {
        log.debug("Request to get all Helmets");
        return helmetRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Helmet with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Helmet> findAllWithEagerRelationships(Pageable pageable) {
        return helmetRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one helmet by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Helmet> findOne(Long id) {
        log.debug("Request to get Helmet : {}", id);
        return helmetRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the helmet by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Helmet : {}", id);
        helmetRepository.deleteById(id);
    }
}
