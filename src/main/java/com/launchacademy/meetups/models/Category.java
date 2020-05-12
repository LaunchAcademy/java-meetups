package com.launchacademy.meetups.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="categories")
@Getter
@Setter
public class Category {
  @Id
  @SequenceGenerator(name="category_generator", sequenceName="categories_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="category_generator")
  @Column(name="id", nullable=false, unique=true)
  private Long id;

  @Column
  @NotEmpty
  private String name;

  @OneToMany(mappedBy = "category")
  @JsonIgnore
  private Set<Event> events;
}
