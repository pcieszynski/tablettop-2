package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.ArmourService;
import com.ender.tablettop.domain.Armour;
import com.ender.tablettop.repository.ArmourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Armour.
 */
@Service
@Transactional
public class ArmourServiceImpl implements ArmourService {

    private final Logger log = LoggerFactory.getLogger(ArmourServiceImpl.class);

    private final ArmourRepository armourRepository;

    public ArmourServiceImpl(ArmourRepository armourRepository) {
        this.armourRepository = armourRepository;
    }

    /**
     * Save a armour.
     *
     * @param armour the entity to save
     * @return the persisted entity
     */
    @Override
    public Armour save(Armour armour) {
        log.debug("Request to save Armour : {}", armour);
        return armourRepository.save(armour);
    }

    /**
     * Get all the armours.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Armour> findAll() {
        log.debug("Request to get all Armours");
        return armourRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Armour with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Armour> findAllWithEagerRelationships(Pageable pageable) {
        return armourRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one armour by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Armour> findOne(Long id) {
        log.debug("Request to get Armour : {}", id);
        return armourRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the armour by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Armour : {}", id);
        armourRepository.deleteById(id);
    }
}
