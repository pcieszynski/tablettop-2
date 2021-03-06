package com.ender.tablettop.web.rest;

import com.ender.tablettop.TabletTopApp;

import com.ender.tablettop.domain.Npc;
import com.ender.tablettop.repository.NpcRepository;
import com.ender.tablettop.service.NpcService;
import com.ender.tablettop.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static com.ender.tablettop.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NpcResource REST controller.
 *
 * @see NpcResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TabletTopApp.class)
public class NpcResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private NpcRepository npcRepository;

    @Autowired
    private NpcService npcService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNpcMockMvc;

    private Npc npc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NpcResource npcResource = new NpcResource(npcService);
        this.restNpcMockMvc = MockMvcBuilders.standaloneSetup(npcResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Npc createEntity(EntityManager em) {
        Npc npc = new Npc()
            .name(DEFAULT_NAME)
            .message(DEFAULT_MESSAGE);
        return npc;
    }

    @Before
    public void initTest() {
        npc = createEntity(em);
    }

    @Test
    @Transactional
    public void createNpc() throws Exception {
        int databaseSizeBeforeCreate = npcRepository.findAll().size();

        // Create the Npc
        restNpcMockMvc.perform(post("/api/npcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(npc)))
            .andExpect(status().isCreated());

        // Validate the Npc in the database
        List<Npc> npcList = npcRepository.findAll();
        assertThat(npcList).hasSize(databaseSizeBeforeCreate + 1);
        Npc testNpc = npcList.get(npcList.size() - 1);
        assertThat(testNpc.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNpc.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createNpcWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = npcRepository.findAll().size();

        // Create the Npc with an existing ID
        npc.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNpcMockMvc.perform(post("/api/npcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(npc)))
            .andExpect(status().isBadRequest());

        // Validate the Npc in the database
        List<Npc> npcList = npcRepository.findAll();
        assertThat(npcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNpcs() throws Exception {
        // Initialize the database
        npcRepository.saveAndFlush(npc);

        // Get all the npcList
        restNpcMockMvc.perform(get("/api/npcs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(npc.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getNpc() throws Exception {
        // Initialize the database
        npcRepository.saveAndFlush(npc);

        // Get the npc
        restNpcMockMvc.perform(get("/api/npcs/{id}", npc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(npc.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNpc() throws Exception {
        // Get the npc
        restNpcMockMvc.perform(get("/api/npcs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNpc() throws Exception {
        // Initialize the database
        npcService.save(npc);

        int databaseSizeBeforeUpdate = npcRepository.findAll().size();

        // Update the npc
        Npc updatedNpc = npcRepository.findById(npc.getId()).get();
        // Disconnect from session so that the updates on updatedNpc are not directly saved in db
        em.detach(updatedNpc);
        updatedNpc
            .name(UPDATED_NAME)
            .message(UPDATED_MESSAGE);

        restNpcMockMvc.perform(put("/api/npcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNpc)))
            .andExpect(status().isOk());

        // Validate the Npc in the database
        List<Npc> npcList = npcRepository.findAll();
        assertThat(npcList).hasSize(databaseSizeBeforeUpdate);
        Npc testNpc = npcList.get(npcList.size() - 1);
        assertThat(testNpc.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNpc.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingNpc() throws Exception {
        int databaseSizeBeforeUpdate = npcRepository.findAll().size();

        // Create the Npc

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNpcMockMvc.perform(put("/api/npcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(npc)))
            .andExpect(status().isBadRequest());

        // Validate the Npc in the database
        List<Npc> npcList = npcRepository.findAll();
        assertThat(npcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNpc() throws Exception {
        // Initialize the database
        npcService.save(npc);

        int databaseSizeBeforeDelete = npcRepository.findAll().size();

        // Get the npc
        restNpcMockMvc.perform(delete("/api/npcs/{id}", npc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Npc> npcList = npcRepository.findAll();
        assertThat(npcList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Npc.class);
        Npc npc1 = new Npc();
        npc1.setId(1L);
        Npc npc2 = new Npc();
        npc2.setId(npc1.getId());
        assertThat(npc1).isEqualTo(npc2);
        npc2.setId(2L);
        assertThat(npc1).isNotEqualTo(npc2);
        npc1.setId(null);
        assertThat(npc1).isNotEqualTo(npc2);
    }
}
