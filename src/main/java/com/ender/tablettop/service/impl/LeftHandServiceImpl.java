package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.LeftHandService;
import com.ender.tablettop.domain.LeftHand;
import com.ender.tablettop.repository.LeftHandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LeftHand.
 */
@Service
@Transactional
public class LeftHandServiceImpl implements LeftHandService {

    private final Logger log = LoggerFactory.getLogger(LeftHandServiceImpl.class);

    private final LeftHandRepository leftHandRepository;

    public LeftHandServiceImpl(LeftHandRepository leftHandRepository) {
        this.leftHandRepository = leftHandRepository;
    }

    /**
     * Save a leftHand.
     *
     * @param leftHand the entity to save
     * @return the persisted entity
     */
    @Override
    public LeftHand save(LeftHand leftHand) {
        log.debug("Request to save LeftHand : {}", leftHand);
        return leftHandRepository.save(leftHand);
    }

    /**
     * Get all the leftHands.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LeftHand> findAll() {
        log.debug("Request to get all LeftHands");
        return leftHandRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the LeftHand with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<LeftHand> findAllWithEagerRelationships(Pageable pageable) {
        return leftHandRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one leftHand by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LeftHand> findOne(Long id) {
        log.debug("Request to get LeftHand : {}", id);
        return leftHandRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the leftHand by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LeftHand : {}", id);
        leftHandRepository.deleteById(id);
    }
}
