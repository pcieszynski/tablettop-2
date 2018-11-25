package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.BootsService;
import com.ender.tablettop.domain.Boots;
import com.ender.tablettop.repository.BootsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Boots.
 */
@Service
@Transactional
public class BootsServiceImpl implements BootsService {

    private final Logger log = LoggerFactory.getLogger(BootsServiceImpl.class);

    private final BootsRepository bootsRepository;

    public BootsServiceImpl(BootsRepository bootsRepository) {
        this.bootsRepository = bootsRepository;
    }

    /**
     * Save a boots.
     *
     * @param boots the entity to save
     * @return the persisted entity
     */
    @Override
    public Boots save(Boots boots) {
        log.debug("Request to save Boots : {}", boots);
        return bootsRepository.save(boots);
    }

    /**
     * Get all the boots.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Boots> findAll() {
        log.debug("Request to get all Boots");
        return bootsRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Boots with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Boots> findAllWithEagerRelationships(Pageable pageable) {
        return bootsRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one boots by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Boots> findOne(Long id) {
        log.debug("Request to get Boots : {}", id);
        return bootsRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the boots by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Boots : {}", id);
        bootsRepository.deleteById(id);
    }
}
