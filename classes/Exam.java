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
public class Exam extends Happening
{
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="exam_student", joinColumns=@JoinColumn(name="HAPPENING_ID"), inverseJoinColumns=@JoinColumn(name="STD_USER_ID"))
  private Set<StdUser> registraded_user_exam_;

  @ManyToOne(fetch = FetchType.EAGER)
  private TeacherUser lecturerExam_;

// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Exam(){}

  public Exam(String name, String description)
  {
    this.name_ = name;
    this.description_ = description;
    this.registraded_user_exam_ = new HashSet<StdUser>(0);
  }

  public final boolean addUserExam(StdUser user)
  {
    this.registraded_user_exam_.add(user);
    return true;
  }

  public final boolean removeUserExam(StdUser user)
  {
    for(Iterator<StdUser> it = this.registraded_user_exam_.iterator(); it.hasNext();)
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
  public final Set<StdUser> getRegistradedUserExam()
  {
    return registraded_user_exam_;
  }
  public final void setRegistradedUserExam(Set<StdUser> registraded_user_exam)
  {
    this.registraded_user_exam_ = registraded_user_exam;
  }
//------------------------------------------------------------------------------

// GETTER SETTER LECTURER
//------------------------------------------------------------------------------
  public final TeacherUser getLecturerExam()
  {
    return lecturerExam_;
  }
  public final void setLecturerExam(TeacherUser lecturerExam)
  {
    this.lecturerExam_ = lecturerExam;
  }
//------------------------------------------------------------------------------



}
