package com.launchacademy.meetups.controllers.api.v1;

import com.launchacademy.meetups.dtos.EventDTO;
import com.launchacademy.meetups.mappers.EventMapper;
import com.launchacademy.meetups.models.Event;
import com.launchacademy.meetups.repositories.EventRepository;
import com.launchacademy.meetups.services.EventService;
import java.util.Set;
import javax.naming.Binding;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/events")
public class EventsController {
  @Autowired
  private EventRepository eventRepo;

  @Autowired
  private EventMapper eventMapper;

  @Autowired
  private EventService eventService;

  private class InvalidEventException extends RuntimeException {};
  @ControllerAdvice
  private class InvalidContractorAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidEventException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidContractor(InvalidEventException ic) {
      return "";
    }
  }

  @PostMapping
  public Event createEvent(@RequestBody @Valid EventDTO eventDTO, BindingResult bindingResult) {
    Event event = eventMapper.eventDTOToEvent(eventDTO);
    System.out.println(event);
    System.out.print(bindingResult.hasErrors());
    if(bindingResult.hasErrors()) {
      throw new InvalidEventException();
    }
    else {
      return eventRepo.save(event);
    }
  }

//  @GetMapping
//  public Page<Event> getList(Pageable pageable) {
//    return eventRepo.findAll(pageable);
//  }
  public Page<EventDTO> getList(Pageable pageable) {
    return eventService.findAll(pageable);
  }
}
