package com.ender.tablettop.repository;

import com.ender.tablettop.domain.Backpack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Backpack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BackpackRepository extends JpaRepository<Backpack, Long> {

    @Query(value = "select distinct backpack from Backpack backpack left join fetch backpack.legs left join fetch backpack.boots left join fetch backpack.gloves left join fetch backpack.righthands left join fetch backpack.lefthands left join fetch backpack.armours left join fetch backpack.helmets left join fetch backpack.items",
        countQuery = "select count(distinct backpack) from Backpack backpack")
    Page<Backpack> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct backpack from Backpack backpack left join fetch backpack.legs left join fetch backpack.boots left join fetch backpack.gloves left join fetch backpack.righthands left join fetch backpack.lefthands left join fetch backpack.armours left join fetch backpack.helmets left join fetch backpack.items")
    List<Backpack> findAllWithEagerRelationships();

    @Query("select backpack from Backpack backpack left join fetch backpack.legs left join fetch backpack.boots left join fetch backpack.gloves left join fetch backpack.righthands left join fetch backpack.lefthands left join fetch backpack.armours left join fetch backpack.helmets left join fetch backpack.items where backpack.id =:id")
    Optional<Backpack> findOneWithEagerRelationships(@Param("id") Long id);

}
