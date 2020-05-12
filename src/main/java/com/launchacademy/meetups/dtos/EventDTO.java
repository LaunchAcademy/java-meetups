package com.launchacademy.meetups.dtos;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EventDTO {
  private Long id;

  @NotBlank
  private String name;
  private String hostingOrganization;
  private Long categoryId;
}
