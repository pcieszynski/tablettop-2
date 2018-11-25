package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Gamemaster;
import com.ender.tablettop.service.GamemasterService;
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
 * REST controller for managing Gamemaster.
 */
@RestController
@RequestMapping("/api")
public class GamemasterResource {

    private final Logger log = LoggerFactory.getLogger(GamemasterResource.class);

    private static final String ENTITY_NAME = "gamemaster";

    private final GamemasterService gamemasterService;

    public GamemasterResource(GamemasterService gamemasterService) {
        this.gamemasterService = gamemasterService;
    }

    /**
     * POST  /gamemasters : Create a new gamemaster.
     *
     * @param gamemaster the gamemaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gamemaster, or with status 400 (Bad Request) if the gamemaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gamemasters")
    @Timed
    public ResponseEntity<Gamemaster> createGamemaster(@RequestBody Gamemaster gamemaster) throws URISyntaxException {
        log.debug("REST request to save Gamemaster : {}", gamemaster);
        if (gamemaster.getId() != null) {
            throw new BadRequestAlertException("A new gamemaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gamemaster result = gamemasterService.save(gamemaster);
        return ResponseEntity.created(new URI("/api/gamemasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gamemasters : Updates an existing gamemaster.
     *
     * @param gamemaster the gamemaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gamemaster,
     * or with status 400 (Bad Request) if the gamemaster is not valid,
     * or with status 500 (Internal Server Error) if the gamemaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gamemasters")
    @Timed
    public ResponseEntity<Gamemaster> updateGamemaster(@RequestBody Gamemaster gamemaster) throws URISyntaxException {
        log.debug("REST request to update Gamemaster : {}", gamemaster);
        if (gamemaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gamemaster result = gamemasterService.save(gamemaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gamemaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gamemasters : get all the gamemasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of gamemasters in body
     */
    @GetMapping("/gamemasters")
    @Timed
    public List<Gamemaster> getAllGamemasters() {
        log.debug("REST request to get all Gamemasters");
        return gamemasterService.findAll();
    }

    /**
     * GET  /gamemasters/:id : get the "id" gamemaster.
     *
     * @param id the id of the gamemaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gamemaster, or with status 404 (Not Found)
     */
    @GetMapping("/gamemasters/{id}")
    @Timed
    public ResponseEntity<Gamemaster> getGamemaster(@PathVariable Long id) {
        log.debug("REST request to get Gamemaster : {}", id);
        Optional<Gamemaster> gamemaster = gamemasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gamemaster);
    }

    /**
     * DELETE  /gamemasters/:id : delete the "id" gamemaster.
     *
     * @param id the id of the gamemaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gamemasters/{id}")
    @Timed
    public ResponseEntity<Void> deleteGamemaster(@PathVariable Long id) {
        log.debug("REST request to delete Gamemaster : {}", id);
        gamemasterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
