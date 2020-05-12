package com.launchacademy.meetups.mappers;

import com.launchacademy.meetups.dtos.EventDTO;
import com.launchacademy.meetups.models.Category;
import com.launchacademy.meetups.models.Event;
import com.launchacademy.meetups.repositories.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<EventDTO> eventListToEventDTOList(List<Event> events) {
    List<EventDTO> dtoList = new ArrayList<EventDTO>();
    for(Event event : events) {
      dtoList.add(this.eventToEventDTO(event));
    }

    return dtoList;
  }

  public EventDTO eventToEventDTO(Event event) {
    EventDTO dto = new EventDTO();
    BeanUtils.copyProperties(event, dto);
    Category category = event.getCategory();
    if(category != null) {
      dto.setCategoryId(category.getId());
    }

    return dto;
  }

  public Event eventDTOToEvent(EventDTO dto) {
    Event newEvent = new Event();
    BeanUtils.copyProperties(dto, newEvent);
    if(dto.getCategoryId() != null) {
      Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
      if(category.isPresent()) {
        newEvent.setCategory(category.get());
      }
    }

    return newEvent;
  }
}
