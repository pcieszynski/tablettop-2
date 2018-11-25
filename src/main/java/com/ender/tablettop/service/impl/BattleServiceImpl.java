package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.BattleService;
import com.ender.tablettop.domain.Battle;
import com.ender.tablettop.repository.BattleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Battle.
 */
@Service
@Transactional
public class BattleServiceImpl implements BattleService {

    private final Logger log = LoggerFactory.getLogger(BattleServiceImpl.class);

    private final BattleRepository battleRepository;

    public BattleServiceImpl(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    /**
     * Save a battle.
     *
     * @param battle the entity to save
     * @return the persisted entity
     */
    @Override
    public Battle save(Battle battle) {
        log.debug("Request to save Battle : {}", battle);
        return battleRepository.save(battle);
    }

    /**
     * Get all the battles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Battle> findAll() {
        log.debug("Request to get all Battles");
        return battleRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Battle with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Battle> findAllWithEagerRelationships(Pageable pageable) {
        return battleRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one battle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Battle> findOne(Long id) {
        log.debug("Request to get Battle : {}", id);
        return battleRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the battle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Battle : {}", id);
        battleRepository.deleteById(id);
    }
}
