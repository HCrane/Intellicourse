package controller;

import java.io.*;
import java.util.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class AuthorizeHandler
{
  public static String userToStudent(TeacherUser user)
  {
    if(user == null)
      return "No User selected.";

    StudentUser new_student = new StudentUser(user.getUserName(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), user.getEmail(), user.getPhone());

    new_student.setRegistradedCoures(user.getRegistradedCourse());
    Set<Course> course_set = user.getRegistradedCourse();
    user.setRegistradedCoures(new HashSet<Course>(0));
    for(Course course: course_set)
    {
      course.removeUser(user);
      course.addUser(new_student);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToStudent: " + e);
        return ("Can't update the Course to the DB.");
      }
    }

    course_set = user.getTeachingCourses();
    for(Course course: course_set)
    {
      course.setLecturer(null);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToStudent: " + e);
        return ("Can't update the Course to the DB.");
      }
    }
    user.setTeachingCourses(new HashSet<Course>(0));
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(new_student);
      HibernateSupport.deleteObject(user);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("userToStudent: " + e);
      return ("Can't update the Users to the DB.");
    }
    return null;
  }


}
