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
public class Course extends Happening
{
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="course_student", joinColumns=@JoinColumn(name="HAPPENING_ID"), inverseJoinColumns=@JoinColumn(name="STD_USER_ID"))
  private Set<StdUser> registraded_user_;

  @ManyToOne(fetch = FetchType.EAGER)
  private StdUser lecturer_;

  private boolean antendees_;

// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Course(){}

  public Course(String name, String description, boolean antendees)
  {
    this.name_ = name;
    this.description_ = description;
    this.antendees_ = antendees;
    this.registraded_user_ = new HashSet<StdUser>(0);
  }

  public final boolean addUser(StdUser user)
  {
    this.registraded_user_.add(user);
    return true;
  }

  public final boolean removeUser(StdUser user)
  {
    for(Iterator<StdUser> it = this.registraded_user_.iterator(); it.hasNext();)
    {
      StdUser user_it = it.next();
      if(user_it.getID() == user.getID())
      {
        it.remove();
        return true;
      }
    }
    return false;
  }

// GETTER SETTER REGISTRADED_USER
//------------------------------------------------------------------------------
  public final Set<StdUser> getRegistradedUser()
  {
    return registraded_user_;
  }
  public final void setRegistradedUser(Set<StdUser> registraded_user)
  {
    this.registraded_user_ = registraded_user;
  }
//------------------------------------------------------------------------------

// GETTER SETTER LECTURER
//------------------------------------------------------------------------------
  public final StdUser getLecturer()
  {
    return lecturer_;
  }
  public final void setLecturer(StdUser lecturer)
  {
    this.lecturer_ = lecturer;
  }
//------------------------------------------------------------------------------

}
