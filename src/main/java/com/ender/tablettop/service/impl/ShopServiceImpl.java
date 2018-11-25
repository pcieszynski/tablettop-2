package com.ender.tablettop.service.impl;

import com.ender.tablettop.service.ShopService;
import com.ender.tablettop.domain.Shop;
import com.ender.tablettop.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Shop.
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private final Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    /**
     * Save a shop.
     *
     * @param shop the entity to save
     * @return the persisted entity
     */
    @Override
    public Shop save(Shop shop) {
        log.debug("Request to save Shop : {}", shop);
        return shopRepository.save(shop);
    }

    /**
     * Get all the shops.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Shop> findAll() {
        log.debug("Request to get all Shops");
        return shopRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Shop with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Shop> findAllWithEagerRelationships(Pageable pageable) {
        return shopRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one shop by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Shop> findOne(Long id) {
        log.debug("Request to get Shop : {}", id);
        return shopRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the shop by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Shop : {}", id);
        shopRepository.deleteById(id);
    }
}
