package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.MonsterTypeService;
import com.ender.tablettop.domain.MonsterType;
import com.ender.tablettop.repository.MonsterTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing MonsterType.
 */
@Service
@Transactional
public class MonsterTypeServiceImpl implements MonsterTypeService {

    private final Logger log = LoggerFactory.getLogger(MonsterTypeServiceImpl.class);

    private final MonsterTypeRepository monsterTypeRepository;

    public MonsterTypeServiceImpl(MonsterTypeRepository monsterTypeRepository) {
        this.monsterTypeRepository = monsterTypeRepository;
    }

    /**
     * Save a monsterType.
     *
     * @param monsterType the entity to save
     * @return the persisted entity
     */
    @Override
    public MonsterType save(MonsterType monsterType) {
        log.debug("Request to save MonsterType : {}", monsterType);
        return monsterTypeRepository.save(monsterType);
    }

    /**
     * Get all the monsterTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MonsterType> findAll() {
        log.debug("Request to get all MonsterTypes");
        return monsterTypeRepository.findAll();
    }


    /**
     * Get one monsterType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MonsterType> findOne(Long id) {
        log.debug("Request to get MonsterType : {}", id);
        return monsterTypeRepository.findById(id);
    }

    /**
     * Delete the monsterType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonsterType : {}", id);
        monsterTypeRepository.deleteById(id);
    }
}
