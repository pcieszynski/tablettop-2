package com.ender.tablettop.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ender.tablettop.domain.Character;
import com.ender.tablettop.service.CharacterService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Character.
 */
@RestController
@RequestMapping("/api")
public class CharacterResource {

    private final Logger log = LoggerFactory.getLogger(CharacterResource.class);

    private static final String ENTITY_NAME = "character";

    private final CharacterService characterService;

    public CharacterResource(CharacterService characterService) {
        this.characterService = characterService;
    }

    /**
     * POST  /characters : Create a new character.
     *
     * @param character the character to create
     * @return the ResponseEntity with status 201 (Created) and with body the new character, or with status 400 (Bad Request) if the character has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/characters")
    @Timed
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) throws URISyntaxException {
        log.debug("REST request to save Character : {}", character);
        if (character.getId() != null) {
            throw new BadRequestAlertException("A new character cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Character result = characterService.save(character);
        return ResponseEntity.created(new URI("/api/characters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /characters : Updates an existing character.
     *
     * @param character the character to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated character,
     * or with status 400 (Bad Request) if the character is not valid,
     * or with status 500 (Internal Server Error) if the character couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/characters")
    @Timed
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character) throws URISyntaxException {
        log.debug("REST request to update Character : {}", character);
        if (character.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Character result = characterService.save(character);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, character.getId().toString()))
            .body(result);
    }

    /**
     * GET  /characters : get all the characters.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of characters in body
     */
    @GetMapping("/characters")
    @Timed
    public List<Character> getAllCharacters(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("backpack-is-null".equals(filter)) {
            log.debug("REST request to get all Characters where backpack is null");
            return characterService.findAllWhereBackpackIsNull();
        }
        log.debug("REST request to get all Characters");
        return characterService.findAll();
    }

    /**
     * GET  /characters/:id : get the "id" character.
     *
     * @param id the id of the character to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the character, or with status 404 (Not Found)
     */
    @GetMapping("/characters/{id}")
    @Timed
    public ResponseEntity<Character> getCharacter(@PathVariable Long id) {
        log.debug("REST request to get Character : {}", id);
        Optional<Character> character = characterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(character);
    }

    /**
     * DELETE  /characters/:id : delete the "id" character.
     *
     * @param id the id of the character to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/characters/{id}")
    @Timed
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        log.debug("REST request to delete Character : {}", id);
        characterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("characters/player/{username}/game/{gameId}")
    @Timed
    public ResponseEntity<Character> getCharacterByPlayerIdAndGameId(@PathVariable String username, @PathVariable String gameId){
        Optional<Character> character = characterService.findByPlayerIdAndGameId(username,gameId);
        return ResponseUtil.wrapOrNotFound(character);
    }
}
