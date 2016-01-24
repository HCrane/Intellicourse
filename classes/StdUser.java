package classes;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StdUser
{
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "STD_USER_ID", unique = true, nullable = false)
  protected int id_;

  @ManyToMany(mappedBy="registraded_user_", fetch = FetchType.EAGER)
  protected Set<Course> registraded_courses_;

  @ManyToMany(mappedBy="registraded_user_exam_", fetch = FetchType.EAGER)
  protected Set<Exam> registraded_exams_;

  protected String first_name_;
  protected String last_name_;
  protected String user_name_; //name_ is always the USERNAME
  protected String password_;
  protected String address_;
  protected String email_;
  protected String phone_;

  //DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public StdUser(){}

  public final boolean addCourse(Course course)
  {
    registraded_courses_.add(course);
    return true;
  }

  public final boolean removeCourse(Course course)
  {
    for(Iterator<Course> it = registraded_courses_.iterator(); it.hasNext();)
    {
      Course course_it = it.next();
      if(course_it.getID() == course.getID())
      {
        it.remove();
        return true;
      }
    }
    return false;
  }

  public final boolean addExam(Exam exam)
  {
    registraded_exams_.add(exam);
    return true;
  }

  public final boolean removeExam(Exam exam)
  {
    for(Iterator<Exam> it = registraded_exams_.iterator(); it.hasNext();)
    {
      Exam exam_it = it.next();
      if(exam_it.getID() == exam.getID())
      {
        it.remove();
        return true;
      }
    }
    return false;
  }
  // GETTER SETTER NAME_
  //------------------------------------------------------------------------------
    public final String getUserName()
    {
      return user_name_;
    }
    public final void setUserName(String user_name)
    {
      this.user_name_ = user_name;
    }
  //------------------------------------------------------------------------------

  // GETTER SETTER FIRST_NAME_
  //------------------------------------------------------------------------------
    public final String getFirstName()
    {
      return first_name_;
    }
    public final void setFirstName(String first_name)
    {
      this.first_name_ = first_name;
    }
  //------------------------------------------------------------------------------


  // GETTER SETTER LAST_NAME_
  //------------------------------------------------------------------------------
    public final String getLasttName()
    {
      return last_name_;
    }
    public final void setLastName(String last_name)
    {
      this.last_name_ = last_name;
    }
  //------------------------------------------------------------------------------


  // GETTER SETTER PASSWORD_
  //------------------------------------------------------------------------------
    public final String getPassword()
    {
      return password_;
    }
    public final void setPassword(String password)
    {
      this.password_ = password;
    }
  //------------------------------------------------------------------------------

  // GETTER SETTER address_
  //------------------------------------------------------------------------------
    public final String getAddress()
    {
      return address_;
    }
    public final void setAddress(String address)
    {
      this.address_ = address;
    }
  //------------------------------------------------------------------------------

  // GETTER SETTER email_
  //------------------------------------------------------------------------------
    public final String getEmail()
    {
      return email_;
    }
    public final void setEmail(String email)
    {
      this.email_ = email;
    }
  //------------------------------------------------------------------------------

  // GETTER SETTER phone_
  //------------------------------------------------------------------------------
    public final String getPhone()
    {
      return phone_;
    }
    public final void setPhone(String phone)
    {
      this.phone_ = phone;
    }
  //------------------------------------------------------------------------------

  // GETTER ID_
  //------------------------------------------------------------------------------
    public final int getID()
    {
      return id_;
    }
  //------------------------------------------------------------------------------

}
