package controller;

import java.util.*;
import java.text.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class UpdateObjects
{
  public static String updateCourse(Course course, String name, String desc, TeacherUser lecturer, Date start_time, Date end_time)
  {
    if(name != null)
    {
      course.setName(name);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("updateCourse: " + e);
        return ("Can't update the Course to the DB.");
      }
    }

    if(desc != null)
    {
      course.setDescription(desc);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("updateCourse: " + e);
        return ("Can't update the Course to the DB.");
      }
    }

    if(lecturer != null)
    {
      TeacherUser old_lecturer = (TeacherUser)course.getLecturer();
      course.setLecturer(lecturer);
      old_lecturer.removeTeachingCourse(course);
      lecturer.addTeachingCourse(course);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(old_lecturer);
        HibernateSupport.commit(lecturer);
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("updateCourse: " + e);
        return ("Can't update the Course to the DB.");
      }
    }

    if((start_time != null) && (end_time != null))
    {
      List<Course> course_list = GetObjects.getAllCourses();
      if(course_list == null)
      {
        return "Can't get all Courses.";
      }
      for(Course course_it: course_list)
      {
        if((course_it.getRoom().getID() == course.getRoom().getID()))
        {
          Set<Time> time_course = course_it.getTimes();
          for(Time time_it: time_course)
          {
            if((time_it.getStartTime().before(start_time)) && (time_it.getEndTime().after(start_time)))
            {
              return "Room is already boocked at this time.";
            }
            else if (time_it.getStartTime().after(start_time)&& time_it.getStartTime().before(end_time))
            {
              return "Room is already boocked at this time.";
            }
          }
        }
      }

      Time time = new Time(start_time, end_time);
      course.addTime(time);
      time.setHappening(course);

      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commit(time);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("updateCourse: " + e);
        return ("Can't update the Course to the DB.");
      }

    }
    return null;
  }
}
