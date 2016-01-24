package controller;

import java.io.*;
import java.util.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class LoginHandler
{
  public static StudentUser studentLogin(String user_name, String password)
  {
    StudentUser student;
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", user_name));

      student = HibernateSupport.readOneObject(StudentUser.class, crit);
    }
    catch (HibernateException e)
    {
      System.out.println("studentLogin: " + e);
      return null;
    }

    if(student == null)
      return null;

    if(!student.getPassword().equals(password))
      return null;

    return student;
  }

  public static TeacherUser teacherLogin(String user_name, String password)
  {
    TeacherUser teacher;
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", user_name));

      teacher = HibernateSupport.readOneObject(TeacherUser.class, crit);
    }
    catch (HibernateException e)
    {
      System.out.println("teacherLogin: " + e);
      return null;
    }

    if(teacher == null)
      return null;

    if(!teacher.getPassword().equals(password))
      return null;

    return teacher;
  }

  public static AdminUser adminLogin(String user_name, String password)
  {
    AdminUser admin;
    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", user_name));

      admin = HibernateSupport.readOneObject(AdminUser.class, crit);
    }
    catch (HibernateException e)
    {
      System.out.println("adminLogin: " + e);
      return null;
    }

    if(admin == null)
      return null;

    if(!admin.getPassword().equals(password))
      return null;

    return admin;
  }
}
