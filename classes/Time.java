package classes;

import java.util.List;
import java.util.Set;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
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
public class Time
{
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  @Column(name = "TIME_ID", unique = true, nullable = false)
  private int id_;

  @ManyToOne(fetch = FetchType.EAGER)
  private Happening happening_;

  private Date start_time_;
  private Date end_time_;

// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Time(){}

  public Time(Date start_time, Date end_time)
  {
    this.start_time_ = start_time;
    this.end_time_ = end_time;
  }

// GETTER ID_
//------------------------------------------------------------------------------
    public final int getID()
    {
      return id_;
    }

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

// GETTER SETTER HAPPENING_
//------------------------------------------------------------------------------
    public final Happening getHappening()
    {
      return happening_;
    }
    public final void setHappening(Happening happening)
    {
      this.happening_ = happening;
    }
//------------------------------------------------------------------------------
}
