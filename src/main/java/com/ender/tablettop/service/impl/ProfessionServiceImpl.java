package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.ProfessionService;
import com.ender.tablettop.domain.Profession;
import com.ender.tablettop.repository.ProfessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Profession.
 */
@Service
@Transactional
public class ProfessionServiceImpl implements ProfessionService {

    private final Logger log = LoggerFactory.getLogger(ProfessionServiceImpl.class);

    private final ProfessionRepository professionRepository;

    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    /**
     * Save a profession.
     *
     * @param profession the entity to save
     * @return the persisted entity
     */
    @Override
    public Profession save(Profession profession) {
        log.debug("Request to save Profession : {}", profession);
        return professionRepository.save(profession);
    }

    /**
     * Get all the professions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Profession> findAll() {
        log.debug("Request to get all Professions");
        return professionRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Profession with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Profession> findAllWithEagerRelationships(Pageable pageable) {
        return professionRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one profession by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Profession> findOne(Long id) {
        log.debug("Request to get Profession : {}", id);
        return professionRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the profession by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profession : {}", id);
        professionRepository.deleteById(id);
    }
}
