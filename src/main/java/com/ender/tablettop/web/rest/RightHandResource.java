package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.RightHand;
import com.ender.tablettop.service.RightHandService;
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
 * REST controller for managing RightHand.
 */
@RestController
@RequestMapping("/api")
public class RightHandResource {

    private final Logger log = LoggerFactory.getLogger(RightHandResource.class);

    private static final String ENTITY_NAME = "rightHand";

    private final RightHandService rightHandService;

    public RightHandResource(RightHandService rightHandService) {
        this.rightHandService = rightHandService;
    }

    /**
     * POST  /right-hands : Create a new rightHand.
     *
     * @param rightHand the rightHand to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rightHand, or with status 400 (Bad Request) if the rightHand has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/right-hands")
    @Timed
    public ResponseEntity<RightHand> createRightHand(@RequestBody RightHand rightHand) throws URISyntaxException {
        log.debug("REST request to save RightHand : {}", rightHand);
        if (rightHand.getId() != null) {
            throw new BadRequestAlertException("A new rightHand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RightHand result = rightHandService.save(rightHand);
        return ResponseEntity.created(new URI("/api/right-hands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /right-hands : Updates an existing rightHand.
     *
     * @param rightHand the rightHand to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rightHand,
     * or with status 400 (Bad Request) if the rightHand is not valid,
     * or with status 500 (Internal Server Error) if the rightHand couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/right-hands")
    @Timed
    public ResponseEntity<RightHand> updateRightHand(@RequestBody RightHand rightHand) throws URISyntaxException {
        log.debug("REST request to update RightHand : {}", rightHand);
        if (rightHand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RightHand result = rightHandService.save(rightHand);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rightHand.getId().toString()))
            .body(result);
    }

    /**
     * GET  /right-hands : get all the rightHands.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of rightHands in body
     */
    @GetMapping("/right-hands")
    @Timed
    public List<RightHand> getAllRightHands(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all RightHands");
        return rightHandService.findAll();
    }

    /**
     * GET  /right-hands/:id : get the "id" rightHand.
     *
     * @param id the id of the rightHand to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rightHand, or with status 404 (Not Found)
     */
    @GetMapping("/right-hands/{id}")
    @Timed
    public ResponseEntity<RightHand> getRightHand(@PathVariable Long id) {
        log.debug("REST request to get RightHand : {}", id);
        Optional<RightHand> rightHand = rightHandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rightHand);
    }

    /**
     * DELETE  /right-hands/:id : delete the "id" rightHand.
     *
     * @param id the id of the rightHand to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/right-hands/{id}")
    @Timed
    public ResponseEntity<Void> deleteRightHand(@PathVariable Long id) {
        log.debug("REST request to delete RightHand : {}", id);
        rightHandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
