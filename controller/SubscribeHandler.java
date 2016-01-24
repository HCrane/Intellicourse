package controller;

import java.io.*;
import java.util.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class SubscribeHandler
{
  public static String regiserForCourse(StdUser user, Course course)
  {
    if((user == null) || (course == null))
    {
      return "User or Course is not selected.";
    }
    user.addCourse(course);
    course.addUser(user);
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(user);
      HibernateSupport.commit(course);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("registerUserForCourse: " + e);
      return ("Can't subscribe");
    }
    return null;
  }

  public static String unregisterOfCourse(StdUser user, Course course)
  {
    if((user == null) || (course == null))
    {
      return "User or Course is not selected.";
    }

    Set<StdUser>students = course.getStudents();
    boolean is_in_set = false;
    for(Iterator<StudentUser> it = students.iterator(); it.hasNext();)
    {
      StudentUser it_student = it.next();
      if(it_student.getID() == student.getID())
      {
        is_in_set = true;
        break;
      }
    }
    if(!is_in_set)
      return "The Student is not in this Course.";

    user.removeCourse(course);
    course.removeUser(user);
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(user);
      HibernateSupport.commit(course);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("unregisterOfCourse: " + e);
      return ("Can't unsubscribe from Course.");
    }
    return null;
  }

}
