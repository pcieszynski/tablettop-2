package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Legs;
import com.ender.tablettop.service.LegsService;
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
 * REST controller for managing Legs.
 */
@RestController
@RequestMapping("/api")
public class LegsResource {

    private final Logger log = LoggerFactory.getLogger(LegsResource.class);

    private static final String ENTITY_NAME = "legs";

    private final LegsService legsService;

    public LegsResource(LegsService legsService) {
        this.legsService = legsService;
    }

    /**
     * POST  /legs : Create a new legs.
     *
     * @param legs the legs to create
     * @return the ResponseEntity with status 201 (Created) and with body the new legs, or with status 400 (Bad Request) if the legs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/legs")
    @Timed
    public ResponseEntity<Legs> createLegs(@RequestBody Legs legs) throws URISyntaxException {
        log.debug("REST request to save Legs : {}", legs);
        if (legs.getId() != null) {
            throw new BadRequestAlertException("A new legs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Legs result = legsService.save(legs);
        return ResponseEntity.created(new URI("/api/legs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /legs : Updates an existing legs.
     *
     * @param legs the legs to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated legs,
     * or with status 400 (Bad Request) if the legs is not valid,
     * or with status 500 (Internal Server Error) if the legs couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/legs")
    @Timed
    public ResponseEntity<Legs> updateLegs(@RequestBody Legs legs) throws URISyntaxException {
        log.debug("REST request to update Legs : {}", legs);
        if (legs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Legs result = legsService.save(legs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, legs.getId().toString()))
            .body(result);
    }

    /**
     * GET  /legs : get all the legs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of legs in body
     */
    @GetMapping("/legs")
    @Timed
    public List<Legs> getAllLegs(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Legs");
        return legsService.findAll();
    }

    /**
     * GET  /legs/:id : get the "id" legs.
     *
     * @param id the id of the legs to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the legs, or with status 404 (Not Found)
     */
    @GetMapping("/legs/{id}")
    @Timed
    public ResponseEntity<Legs> getLegs(@PathVariable Long id) {
        log.debug("REST request to get Legs : {}", id);
        Optional<Legs> legs = legsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(legs);
    }

    /**
     * DELETE  /legs/:id : delete the "id" legs.
     *
     * @param id the id of the legs to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/legs/{id}")
    @Timed
    public ResponseEntity<Void> deleteLegs(@PathVariable Long id) {
        log.debug("REST request to delete Legs : {}", id);
        legsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
