package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.MonsterService;
import com.ender.tablettop.domain.Monster;
import com.ender.tablettop.repository.MonsterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Monster.
 */
@Service
@Transactional
public class MonsterServiceImpl implements MonsterService {

    private final Logger log = LoggerFactory.getLogger(MonsterServiceImpl.class);

    private final MonsterRepository monsterRepository;

    public MonsterServiceImpl(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    /**
     * Save a monster.
     *
     * @param monster the entity to save
     * @return the persisted entity
     */
    @Override
    public Monster save(Monster monster) {
        log.debug("Request to save Monster : {}", monster);
        return monsterRepository.save(monster);
    }

    /**
     * Get all the monsters.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Monster> findAll() {
        log.debug("Request to get all Monsters");
        return monsterRepository.findAll();
    }


    /**
     * Get one monster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Monster> findOne(Long id) {
        log.debug("Request to get Monster : {}", id);
        return monsterRepository.findById(id);
    }

    /**
     * Delete the monster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Monster : {}", id);
        monsterRepository.deleteById(id);
    }
}
