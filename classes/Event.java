package classes;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
public class Event extends Happening
{
// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Event(){}

  public Event(String name, String description)
  {
    this.name_ = name;
    this.description_ = description;
  }
}
