package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Backpack;
import com.ender.tablettop.service.BackpackService;
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
 * REST controller for managing Backpack.
 */
@RestController
@RequestMapping("/api")
public class BackpackResource {

    private final Logger log = LoggerFactory.getLogger(BackpackResource.class);

    private static final String ENTITY_NAME = "backpack";

    private final BackpackService backpackService;

    public BackpackResource(BackpackService backpackService) {
        this.backpackService = backpackService;
    }

    /**
     * POST  /backpacks : Create a new backpack.
     *
     * @param backpack the backpack to create
     * @return the ResponseEntity with status 201 (Created) and with body the new backpack, or with status 400 (Bad Request) if the backpack has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/backpacks")
    @Timed
    public ResponseEntity<Backpack> createBackpack(@RequestBody Backpack backpack) throws URISyntaxException {
        log.debug("REST request to save Backpack : {}", backpack);
        if (backpack.getId() != null) {
            throw new BadRequestAlertException("A new backpack cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Backpack result = backpackService.save(backpack);
        return ResponseEntity.created(new URI("/api/backpacks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /backpacks : Updates an existing backpack.
     *
     * @param backpack the backpack to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated backpack,
     * or with status 400 (Bad Request) if the backpack is not valid,
     * or with status 500 (Internal Server Error) if the backpack couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/backpacks")
    @Timed
    public ResponseEntity<Backpack> updateBackpack(@RequestBody Backpack backpack) throws URISyntaxException {
        log.debug("REST request to update Backpack : {}", backpack);
        if (backpack.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Backpack result = backpackService.save(backpack);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, backpack.getId().toString()))
            .body(result);
    }

    /**
     * GET  /backpacks : get all the backpacks.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of backpacks in body
     */
    @GetMapping("/backpacks")
    @Timed
    public List<Backpack> getAllBackpacks(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Backpacks");
        return backpackService.findAll();
    }

    /**
     * GET  /backpacks/:id : get the "id" backpack.
     *
     * @param id the id of the backpack to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the backpack, or with status 404 (Not Found)
     */
    @GetMapping("/backpacks/{id}")
    @Timed
    public ResponseEntity<Backpack> getBackpack(@PathVariable Long id) {
        log.debug("REST request to get Backpack : {}", id);
        Optional<Backpack> backpack = backpackService.findOne(id);
        return ResponseUtil.wrapOrNotFound(backpack);
    }

    /**
     * DELETE  /backpacks/:id : delete the "id" backpack.
     *
     * @param id the id of the backpack to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/backpacks/{id}")
    @Timed
    public ResponseEntity<Void> deleteBackpack(@PathVariable Long id) {
        log.debug("REST request to delete Backpack : {}", id);
        backpackService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
