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
public class Happening
{
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  @Column(name = "HAPPENING_ID", unique = true, nullable = false)
  protected int id_;

  @OneToMany(fetch = FetchType.EAGER, mappedBy="happening_")
  private Set<Time> times_;

  @OneToOne
  protected Place room_;

  protected String name_;
  protected String description_;


// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Happening(){}

  public final boolean addTime(Time time)
  {
    this.times_.add(time);
    return true;
  }

  public final boolean removeTime(Time time)
  {
    for(Iterator<Time> it = this.times_.iterator(); it.hasNext();)
    {
      Time time_it = it.next();
      if(time_it.getID() == time.getID())
      {
        it.remove();
        return true;
      }
    }
    return false;
  }

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

// GETTER SETTER TIMES_
//------------------------------------------------------------------------------
    public final Set<Time> getTimes()
    {
      return times_;
    }
    public final void setTimes(Set<Time> times)
    {
      this.times_ = times;
    }
//------------------------------------------------------------------------------
}
