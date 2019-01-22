package com.ender.tablettop.repository;

import com.ender.tablettop.domain.Event;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByGameId(Long gameId);
}
