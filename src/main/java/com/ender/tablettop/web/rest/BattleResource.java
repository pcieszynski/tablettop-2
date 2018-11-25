package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Battle;
import com.ender.tablettop.service.BattleService;
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
 * REST controller for managing Battle.
 */
@RestController
@RequestMapping("/api")
public class BattleResource {

    private final Logger log = LoggerFactory.getLogger(BattleResource.class);

    private static final String ENTITY_NAME = "battle";

    private final BattleService battleService;

    public BattleResource(BattleService battleService) {
        this.battleService = battleService;
    }

    /**
     * POST  /battles : Create a new battle.
     *
     * @param battle the battle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new battle, or with status 400 (Bad Request) if the battle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/battles")
    @Timed
    public ResponseEntity<Battle> createBattle(@RequestBody Battle battle) throws URISyntaxException {
        log.debug("REST request to save Battle : {}", battle);
        if (battle.getId() != null) {
            throw new BadRequestAlertException("A new battle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Battle result = battleService.save(battle);
        return ResponseEntity.created(new URI("/api/battles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /battles : Updates an existing battle.
     *
     * @param battle the battle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated battle,
     * or with status 400 (Bad Request) if the battle is not valid,
     * or with status 500 (Internal Server Error) if the battle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/battles")
    @Timed
    public ResponseEntity<Battle> updateBattle(@RequestBody Battle battle) throws URISyntaxException {
        log.debug("REST request to update Battle : {}", battle);
        if (battle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Battle result = battleService.save(battle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, battle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /battles : get all the battles.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of battles in body
     */
    @GetMapping("/battles")
    @Timed
    public List<Battle> getAllBattles(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Battles");
        return battleService.findAll();
    }

    /**
     * GET  /battles/:id : get the "id" battle.
     *
     * @param id the id of the battle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the battle, or with status 404 (Not Found)
     */
    @GetMapping("/battles/{id}")
    @Timed
    public ResponseEntity<Battle> getBattle(@PathVariable Long id) {
        log.debug("REST request to get Battle : {}", id);
        Optional<Battle> battle = battleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(battle);
    }

    /**
     * DELETE  /battles/:id : delete the "id" battle.
     *
     * @param id the id of the battle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/battles/{id}")
    @Timed
    public ResponseEntity<Void> deleteBattle(@PathVariable Long id) {
        log.debug("REST request to delete Battle : {}", id);
        battleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
