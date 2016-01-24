package controller;

import java.util.*;
import java.text.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class GetObjects
{
  public static StudentUser getStudentByName(String name)
  {
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", name));

      return HibernateSupport.readOneObject(StudentUser.class, crit);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static List<StudentUser> getAllStudents()
  {
    try
    {
      return HibernateSupport.readMoreObjects(StudentUser.class, null);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static TeacherUser getTeacherByName(String name)
  {
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", name));

      return HibernateSupport.readOneObject(TeacherUser.class, crit);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static List<TeacherUser> getAllTeachers()
  {
    try
    {
      return HibernateSupport.readMoreObjects(TeacherUser.class, null);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static AdminUser getAdminsByName(String name)
  {
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", name));

      return HibernateSupport.readOneObject(AdminUser.class, crit);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static List<AdminUser> getAllAdmins()
  {
    try
    {
      return HibernateSupport.readMoreObjects(AdminUser.class, null);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static Place getPlacebyNr(int nr)
  {
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("roomnr_", nr));

      return HibernateSupport.readOneObject(Place.class, crit);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static List<Place> getAllPlaces()
  {
    try
    {
      return HibernateSupport.readMoreObjects(Place.class, null);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }


  public static Course getCourseByName(String name)
  {
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("name_", name));

      return HibernateSupport.readOneObject(Course.class, crit);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }

  public static List<Course> getAllCourses()
  {
    try
    {
      return HibernateSupport.readMoreObjects(Course.class, null);
    }
    catch (HibernateException e)
    {
      return null;
    }
  }



}
