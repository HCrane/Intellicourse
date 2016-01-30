package controller;

import java.io.*;
import java.util.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class DeleteHandler
{
  public static String deleteCoursed(Course course)
  {
    if(course == null)
      return "No Course selected.";

    Course check_course = GetObjects.getCourseByName(course.getName());
    if(check_course == null)
      return "Course not existing.";

    TeacherUser teacher = (TeacherUser)course.getLecturer();
    Set<StdUser> course_user_set = course.getRegistradedUser();

    teacher.removeTeachingCourse(course);
    for(StdUser user: course_user_set)
    {
      user.removeCourse(course);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(user);
        HibernateSupport.commitTransaction();
      }
      catch(HibernateException e)
      {
        System.out.println("Can't update the User!");
        System.out.println(e);

        return "Can't update the User!";
      }
    }

    Set<Time>times_set = course.getTimes();
    course.setTimes(new HashSet<Time>(0));
    for(Time time: times_set)
    {
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.deleteObject(time);
        HibernateSupport.commitTransaction();
      }
      catch(HibernateException e)
      {
        System.out.println("Can't delete the Time!");
        System.out.println(e);

        return "Can't delete the Time!";
      }
    }

    course.setLecturer(null);
    course.setRegistradedUser(new HashSet<StdUser>(0));
    course.setRoom(null);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(teacher);
      HibernateSupport.deleteObject(course);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't delete the Courses!");
      System.out.println(e);
      return "Can't delete the Courses!";
    }
    return null;
  }
}
