package classes;

import java.util.List;
import java.util.Set;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Happening
{
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  @Column(name = "HAPPENING_ID", unique = true, nullable = false)
  protected int id_;

  @OneToOne
  protected Place room_;

  protected String name_;
  protected String description_;

  protected Date start_time_;
  protected Date end_time_;


// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Happening(){}

// GETTER ID_
//------------------------------------------------------------------------------
    public final int getID()
    {
      return id_;
    }
//------------------------------------------------------------------------------

// GETTER SETTER NAME_
//------------------------------------------------------------------------------
    public final String getName()
    {
      return name_;
    }
    public final void setName(String name)
    {
      this.name_ = name;
    }
//------------------------------------------------------------------------------

// GETTER SETTER DESCRIPTION_
//------------------------------------------------------------------------------
    public final String getDescription()
    {
      return description_;
    }
    public final void setDescription(String description)
    {
      this.description_ = description;
    }
//------------------------------------------------------------------------------

// GETTER SETTER ROOM
//------------------------------------------------------------------------------
    public final Place getRoom()
    {
      return room_;
    }
    public final void setRoom(Place room)
    {
      this.room_ = room;
    }
//------------------------------------------------------------------------------

// GETTER SETTER START_TIME_
//------------------------------------------------------------------------------
    public final Date getStartTime()
    {
      return start_time_;
    }
    public final void setStartTime(Date start_time)
    {
      this.start_time_ = start_time;
    }
//------------------------------------------------------------------------------

// GETTER SETTER END_TIME_
//------------------------------------------------------------------------------
    public final Date getEndTime()
    {
      return end_time_;
    }
    public final void setEndTime(Date end_time)
    {
      this.end_time_ = end_time;
    }
//------------------------------------------------------------------------------
}
