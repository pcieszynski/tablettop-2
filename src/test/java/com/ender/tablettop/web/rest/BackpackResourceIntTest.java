package com.ender.tablettop.web.rest;

import com.ender.tablettop.TabletTopApp;

import com.ender.tablettop.domain.Backpack;
import com.ender.tablettop.repository.BackpackRepository;
import com.ender.tablettop.service.BackpackService;
import com.ender.tablettop.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.ender.tablettop.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BackpackResource REST controller.
 *
 * @see BackpackResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TabletTopApp.class)
public class BackpackResourceIntTest {

    @Autowired
    private BackpackRepository backpackRepository;

    @Mock
    private BackpackRepository backpackRepositoryMock;

    @Mock
    private BackpackService backpackServiceMock;

    @Autowired
    private BackpackService backpackService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBackpackMockMvc;

    private Backpack backpack;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BackpackResource backpackResource = new BackpackResource(backpackService);
        this.restBackpackMockMvc = MockMvcBuilders.standaloneSetup(backpackResource)
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
    public static Backpack createEntity(EntityManager em) {
        Backpack backpack = new Backpack();
        return backpack;
    }

    @Before
    public void initTest() {
        backpack = createEntity(em);
    }

    @Test
    @Transactional
    public void createBackpack() throws Exception {
        int databaseSizeBeforeCreate = backpackRepository.findAll().size();

        // Create the Backpack
        restBackpackMockMvc.perform(post("/api/backpacks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(backpack)))
            .andExpect(status().isCreated());

        // Validate the Backpack in the database
        List<Backpack> backpackList = backpackRepository.findAll();
        assertThat(backpackList).hasSize(databaseSizeBeforeCreate + 1);
        Backpack testBackpack = backpackList.get(backpackList.size() - 1);
    }

    @Test
    @Transactional
    public void createBackpackWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = backpackRepository.findAll().size();

        // Create the Backpack with an existing ID
        backpack.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBackpackMockMvc.perform(post("/api/backpacks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(backpack)))
            .andExpect(status().isBadRequest());

        // Validate the Backpack in the database
        List<Backpack> backpackList = backpackRepository.findAll();
        assertThat(backpackList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBackpacks() throws Exception {
        // Initialize the database
        backpackRepository.saveAndFlush(backpack);

        // Get all the backpackList
        restBackpackMockMvc.perform(get("/api/backpacks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(backpack.getId().intValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllBackpacksWithEagerRelationshipsIsEnabled() throws Exception {
        BackpackResource backpackResource = new BackpackResource(backpackServiceMock);
        when(backpackServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restBackpackMockMvc = MockMvcBuilders.standaloneSetup(backpackResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBackpackMockMvc.perform(get("/api/backpacks?eagerload=true"))
        .andExpect(status().isOk());

        verify(backpackServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllBackpacksWithEagerRelationshipsIsNotEnabled() throws Exception {
        BackpackResource backpackResource = new BackpackResource(backpackServiceMock);
            when(backpackServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restBackpackMockMvc = MockMvcBuilders.standaloneSetup(backpackResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBackpackMockMvc.perform(get("/api/backpacks?eagerload=true"))
        .andExpect(status().isOk());

            verify(backpackServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getBackpack() throws Exception {
        // Initialize the database
        backpackRepository.saveAndFlush(backpack);

        // Get the backpack
        restBackpackMockMvc.perform(get("/api/backpacks/{id}", backpack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(backpack.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBackpack() throws Exception {
        // Get the backpack
        restBackpackMockMvc.perform(get("/api/backpacks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBackpack() throws Exception {
        // Initialize the database
        backpackService.save(backpack);

        int databaseSizeBeforeUpdate = backpackRepository.findAll().size();

        // Update the backpack
        Backpack updatedBackpack = backpackRepository.findById(backpack.getId()).get();
        // Disconnect from session so that the updates on updatedBackpack are not directly saved in db
        em.detach(updatedBackpack);

        restBackpackMockMvc.perform(put("/api/backpacks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBackpack)))
            .andExpect(status().isOk());

        // Validate the Backpack in the database
        List<Backpack> backpackList = backpackRepository.findAll();
        assertThat(backpackList).hasSize(databaseSizeBeforeUpdate);
        Backpack testBackpack = backpackList.get(backpackList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingBackpack() throws Exception {
        int databaseSizeBeforeUpdate = backpackRepository.findAll().size();

        // Create the Backpack

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBackpackMockMvc.perform(put("/api/backpacks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(backpack)))
            .andExpect(status().isBadRequest());

        // Validate the Backpack in the database
        List<Backpack> backpackList = backpackRepository.findAll();
        assertThat(backpackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBackpack() throws Exception {
        // Initialize the database
        backpackService.save(backpack);

        int databaseSizeBeforeDelete = backpackRepository.findAll().size();

        // Get the backpack
        restBackpackMockMvc.perform(delete("/api/backpacks/{id}", backpack.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Backpack> backpackList = backpackRepository.findAll();
        assertThat(backpackList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Backpack.class);
        Backpack backpack1 = new Backpack();
        backpack1.setId(1L);
        Backpack backpack2 = new Backpack();
        backpack2.setId(backpack1.getId());
        assertThat(backpack1).isEqualTo(backpack2);
        backpack2.setId(2L);
        assertThat(backpack1).isNotEqualTo(backpack2);
        backpack1.setId(null);
        assertThat(backpack1).isNotEqualTo(backpack2);
    }
}
