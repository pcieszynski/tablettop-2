package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.NpcService;
import com.ender.tablettop.domain.Npc;
import com.ender.tablettop.repository.NpcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Npc.
 */
@Service
@Transactional
public class NpcServiceImpl implements NpcService {

    private final Logger log = LoggerFactory.getLogger(NpcServiceImpl.class);

    private final NpcRepository npcRepository;

    public NpcServiceImpl(NpcRepository npcRepository) {
        this.npcRepository = npcRepository;
    }

    /**
     * Save a npc.
     *
     * @param npc the entity to save
     * @return the persisted entity
     */
    @Override
    public Npc save(Npc npc) {
        log.debug("Request to save Npc : {}", npc);
        return npcRepository.save(npc);
    }

    /**
     * Get all the npcs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Npc> findAll() {
        log.debug("Request to get all Npcs");
        return npcRepository.findAll();
    }


    /**
     * Get one npc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Npc> findOne(Long id) {
        log.debug("Request to get Npc : {}", id);
        return npcRepository.findById(id);
    }

    /**
     * Delete the npc by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Npc : {}", id);
        npcRepository.deleteById(id);
    }
}
