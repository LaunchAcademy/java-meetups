package com.launchacademy.meetups.repositories;

import com.launchacademy.meetups.models.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
}
