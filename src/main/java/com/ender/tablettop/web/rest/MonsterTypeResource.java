package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.MonsterType;
import com.ender.tablettop.service.MonsterTypeService;
import com.ender.tablettop.web.rest.errors.BadRequestAlertException;
import com.ender.tablettop.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MonsterType.
 */
@RestController
@RequestMapping("/api")
public class MonsterTypeResource {

    private final Logger log = LoggerFactory.getLogger(MonsterTypeResource.class);

    private static final String ENTITY_NAME = "monsterType";

    private final MonsterTypeService monsterTypeService;

    public MonsterTypeResource(MonsterTypeService monsterTypeService) {
        this.monsterTypeService = monsterTypeService;
    }

    /**
     * POST  /monster-types : Create a new monsterType.
     *
     * @param monsterType the monsterType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monsterType, or with status 400 (Bad Request) if the monsterType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monster-types")
    @Timed
    public ResponseEntity<MonsterType> createMonsterType(@RequestBody MonsterType monsterType) throws URISyntaxException {
        log.debug("REST request to save MonsterType : {}", monsterType);
        if (monsterType.getId() != null) {
            throw new BadRequestAlertException("A new monsterType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonsterType result = monsterTypeService.save(monsterType);
        return ResponseEntity.created(new URI("/api/monster-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monster-types : Updates an existing monsterType.
     *
     * @param monsterType the monsterType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monsterType,
     * or with status 400 (Bad Request) if the monsterType is not valid,
     * or with status 500 (Internal Server Error) if the monsterType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monster-types")
    @Timed
    public ResponseEntity<MonsterType> updateMonsterType(@RequestBody MonsterType monsterType) throws URISyntaxException {
        log.debug("REST request to update MonsterType : {}", monsterType);
        if (monsterType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonsterType result = monsterTypeService.save(monsterType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monsterType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monster-types : get all the monsterTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of monsterTypes in body
     */
    @GetMapping("/monster-types")
    @Timed
    public List<MonsterType> getAllMonsterTypes() {
        log.debug("REST request to get all MonsterTypes");
        return monsterTypeService.findAll();
    }

    /**
     * GET  /monster-types/:id : get the "id" monsterType.
     *
     * @param id the id of the monsterType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monsterType, or with status 404 (Not Found)
     */
    @GetMapping("/monster-types/{id}")
    @Timed
    public ResponseEntity<MonsterType> getMonsterType(@PathVariable Long id) {
        log.debug("REST request to get MonsterType : {}", id);
        Optional<MonsterType> monsterType = monsterTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monsterType);
    }

    /**
     * DELETE  /monster-types/:id : delete the "id" monsterType.
     *
     * @param id the id of the monsterType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monster-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonsterType(@PathVariable Long id) {
        log.debug("REST request to delete MonsterType : {}", id);
        monsterTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
