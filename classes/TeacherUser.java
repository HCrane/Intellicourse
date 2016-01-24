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
public class TeacherUser extends StdUser
{
  @OneToMany(fetch = FetchType.EAGER, mappedBy="lecturer_")
  private Set<Course> teaching_courses_;

  @OneToMany(fetch = FetchType.EAGER, mappedBy="lecturerExam_")
  private Set<Exam> teaching_exams_;

  //DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public TeacherUser(){}

  public TeacherUser(String user_name, String fist_name, String last_name, String password, Date dob, String address, String email, String phone)
  {
    this.user_name_ = user_name;
    this.first_name_ = fist_name;
    this.last_name_ = last_name;
    this.password_ = password;
    this.date_of_birth_ = dob;
    this.address_ = address;
    this.email_ = email;
    this.phone_ = phone;
    this.registraded_courses_ = new HashSet<Course>(0);
    this.registraded_exams_ = new HashSet<Exam>(0);
    this.teaching_courses_ = new HashSet<Course>(0);
    this.teaching_exams_ = new HashSet<Exam>(0);
  }

  public final boolean addTeachingCourse(Course course)
  {
    this.teaching_courses_.add(course);
    return true;
  }

  public final boolean removeTeachingCourse(Course course)
  {
    for(Iterator<Course> it = this.teaching_courses_.iterator(); it.hasNext();)
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

  public final boolean addTeachingExam(Exam exam)
  {
    this.teaching_exams_.add(exam);
    return true;
  }

  public final boolean removeTeachingExam(Exam exam)
  {
    for(Iterator<Exam> it = this.teaching_exams_.iterator(); it.hasNext();)
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

}
