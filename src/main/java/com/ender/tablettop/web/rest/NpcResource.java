package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Npc;
import com.ender.tablettop.service.NpcService;
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
 * REST controller for managing Npc.
 */
@RestController
@RequestMapping("/api")
public class NpcResource {

    private final Logger log = LoggerFactory.getLogger(NpcResource.class);

    private static final String ENTITY_NAME = "npc";

    private final NpcService npcService;

    public NpcResource(NpcService npcService) {
        this.npcService = npcService;
    }

    /**
     * POST  /npcs : Create a new npc.
     *
     * @param npc the npc to create
     * @return the ResponseEntity with status 201 (Created) and with body the new npc, or with status 400 (Bad Request) if the npc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/npcs")
    @Timed
    public ResponseEntity<Npc> createNpc(@RequestBody Npc npc) throws URISyntaxException {
        log.debug("REST request to save Npc : {}", npc);
        if (npc.getId() != null) {
            throw new BadRequestAlertException("A new npc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Npc result = npcService.save(npc);
        return ResponseEntity.created(new URI("/api/npcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /npcs : Updates an existing npc.
     *
     * @param npc the npc to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated npc,
     * or with status 400 (Bad Request) if the npc is not valid,
     * or with status 500 (Internal Server Error) if the npc couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/npcs")
    @Timed
    public ResponseEntity<Npc> updateNpc(@RequestBody Npc npc) throws URISyntaxException {
        log.debug("REST request to update Npc : {}", npc);
        if (npc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Npc result = npcService.save(npc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, npc.getId().toString()))
            .body(result);
    }

    /**
     * GET  /npcs : get all the npcs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of npcs in body
     */
    @GetMapping("/npcs")
    @Timed
    public List<Npc> getAllNpcs() {
        log.debug("REST request to get all Npcs");
        return npcService.findAll();
    }

    /**
     * GET  /npcs/:id : get the "id" npc.
     *
     * @param id the id of the npc to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the npc, or with status 404 (Not Found)
     */
    @GetMapping("/npcs/{id}")
    @Timed
    public ResponseEntity<Npc> getNpc(@PathVariable Long id) {
        log.debug("REST request to get Npc : {}", id);
        Optional<Npc> npc = npcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(npc);
    }

    /**
     * DELETE  /npcs/:id : delete the "id" npc.
     *
     * @param id the id of the npc to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/npcs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNpc(@PathVariable Long id) {
        log.debug("REST request to delete Npc : {}", id);
        npcService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
