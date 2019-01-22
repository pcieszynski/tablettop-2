package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Event;
import com.ender.tablettop.domain.PlayerMessage;
import com.ender.tablettop.service.EventService;
import com.ender.tablettop.service.GameService;
import com.ender.tablettop.service.PlayerMessageService;
import com.ender.tablettop.web.rest.errors.BadRequestAlertException;
import com.ender.tablettop.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.*;

/**
 * REST controller for managing PlayerMessage.
 */
@RestController
@RequestMapping("/api")
public class PlayerMessageResource {

    private final Logger log = LoggerFactory.getLogger(PlayerMessageResource.class);

    private static final String ENTITY_NAME = "playerMessage";

    private final PlayerMessageService playerMessageService;
    private final EventService eventService;
    private final GameService gameService;

    public PlayerMessageResource(PlayerMessageService playerMessageService, EventService eventService, GameService gameService) {
        this.playerMessageService = playerMessageService;
        this.eventService = eventService;
        this.gameService = gameService;
    }

    /**
     * POST  /player-messages : Create a new playerMessage.
     *
     * @param playerMessage the playerMessage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new playerMessage, or with status 400 (Bad Request) if the playerMessage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/player-messages")
    @Timed
    public ResponseEntity<PlayerMessage> createPlayerMessage(@RequestBody PlayerMessage playerMessage) throws URISyntaxException {
        log.debug("REST request to save PlayerMessage : {}", playerMessage);
        if (playerMessage.getId() != null) {
            throw new BadRequestAlertException("A new playerMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayerMessage result = playerMessageService.save(playerMessage);
        return ResponseEntity.created(new URI("/api/player-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /player-messages : Updates an existing playerMessage.
     *
     * @param playerMessage the playerMessage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated playerMessage,
     * or with status 400 (Bad Request) if the playerMessage is not valid,
     * or with status 500 (Internal Server Error) if the playerMessage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/player-messages")
    @Timed
    public ResponseEntity<PlayerMessage> updatePlayerMessage(@RequestBody PlayerMessage playerMessage) throws URISyntaxException {
        log.debug("REST request to update PlayerMessage : {}", playerMessage);
        if (playerMessage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlayerMessage result = playerMessageService.save(playerMessage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, playerMessage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /player-messages : get all the playerMessages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of playerMessages in body
     */
    @GetMapping("/player-messages")
    @Timed
    public List<PlayerMessage> getAllPlayerMessages() {
        log.debug("REST request to get all PlayerMessages");
        return playerMessageService.findAll();
    }

    /**
     * GET  /player-messages/:id : get the "id" playerMessage.
     *
     * @param id the id of the playerMessage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the playerMessage, or with status 404 (Not Found)
     */
    @GetMapping("/player-messages/{id}")
    @Timed
    public ResponseEntity<PlayerMessage> getPlayerMessage(@PathVariable Long id) {
        log.debug("REST request to get PlayerMessage : {}", id);
        Optional<PlayerMessage> playerMessage = playerMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(playerMessage);
    }

    /**
     * DELETE  /player-messages/:id : delete the "id" playerMessage.
     *
     * @param id the id of the playerMessage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/player-messages/{id}")
    @Timed
    public ResponseEntity<Void> deletePlayerMessage(@PathVariable Long id) {
        log.debug("REST request to delete PlayerMessage : {}", id);
        playerMessageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/player-messages/event/{gameid}")
    @Timed
    public ResponseEntity<PlayerMessage> createPlayerMessageWithEvent(@RequestBody PlayerMessage playerMessage, @PathVariable("gameid") String gameid) throws URISyntaxException {
        log.debug("REST request to save PlayerMessage : {}", playerMessage);
        if (playerMessage.getId() != null) {
            throw new BadRequestAlertException("A new playerMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayerMessage message = createMessageWithEvent(playerMessage,gameid);
        return ResponseEntity.created(new URI("/api/player-messages/" + message.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, message.getId().toString()))
            .body(message);
    }

    private PlayerMessage createMessageWithEvent(PlayerMessage playerMessage, String gameid) {
        PlayerMessage message = playerMessageService.save(playerMessage);
        Event event = new Event();
        Set<PlayerMessage> messges = new HashSet<>();
        messges.add(message);
        message.setEvent(event);
        event.setPlayerMessages(messges);
        event.setGame(gameService.findOne(Long.parseLong(gameid)).orElse(null));
        eventService.save(event);
        playerMessageService.save(playerMessage);
        return message;
    }
}
