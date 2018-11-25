package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.RightHandService;
import com.ender.tablettop.domain.RightHand;
import com.ender.tablettop.repository.RightHandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing RightHand.
 */
@Service
@Transactional
public class RightHandServiceImpl implements RightHandService {

    private final Logger log = LoggerFactory.getLogger(RightHandServiceImpl.class);

    private final RightHandRepository rightHandRepository;

    public RightHandServiceImpl(RightHandRepository rightHandRepository) {
        this.rightHandRepository = rightHandRepository;
    }

    /**
     * Save a rightHand.
     *
     * @param rightHand the entity to save
     * @return the persisted entity
     */
    @Override
    public RightHand save(RightHand rightHand) {
        log.debug("Request to save RightHand : {}", rightHand);
        return rightHandRepository.save(rightHand);
    }

    /**
     * Get all the rightHands.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RightHand> findAll() {
        log.debug("Request to get all RightHands");
        return rightHandRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the RightHand with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RightHand> findAllWithEagerRelationships(Pageable pageable) {
        return rightHandRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one rightHand by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RightHand> findOne(Long id) {
        log.debug("Request to get RightHand : {}", id);
        return rightHandRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the rightHand by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RightHand : {}", id);
        rightHandRepository.deleteById(id);
    }
}
