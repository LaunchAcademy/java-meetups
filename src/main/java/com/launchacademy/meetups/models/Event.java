package com.launchacademy.meetups.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="events")
@ToString
public class Event {
  @Id
  @SequenceGenerator(name="event_generator", sequenceName="events_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="event_generator")
  @Column(name="id", nullable=false, unique=true)
  @Getter
  @Setter
  private Long id;

  @Column
  @Getter
  @Setter
  @NotBlank
  private String name;

  @Column(name="hosting_organization")
  @Getter
  @Setter
  private String hostingOrganization;

  @ManyToOne
  @JoinColumn(name="category_id")
  @Getter
  @Setter
  private Category category;
}
