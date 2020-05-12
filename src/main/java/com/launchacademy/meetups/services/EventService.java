package com.launchacademy.meetups.services;

import com.launchacademy.meetups.dtos.EventDTO;
import com.launchacademy.meetups.mappers.EventMapper;
import com.launchacademy.meetups.models.Event;
import com.launchacademy.meetups.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
  @Autowired
  private EventRepository eventRepo;

  @Autowired
  private EventMapper eventMapper;

  public Page<EventDTO> findAll(Pageable pageable) {
    Page<Event> eventPage = eventRepo.findAll(pageable);
    return new PageImpl<EventDTO>(
        eventMapper.eventListToEventDTOList(eventPage.getContent()),
        pageable,
        eventPage.getTotalElements()
    );
  }
}
