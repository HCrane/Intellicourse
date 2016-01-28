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

  public static String userToStudent(AdminUser user)
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

  public static String userToTeacher(StudentUser user)
  {
    if(user == null)
      return "No User selected.";

    TeacherUser new_teacher = new TeacherUser(user.getUserName(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), user.getEmail(), user.getPhone());

    new_teacher.setRegistradedCoures(user.getRegistradedCourse());
    Set<Course> course_set = user.getRegistradedCourse();
    user.setRegistradedCoures(new HashSet<Course>(0));
    for(Course course: course_set)
    {
      course.removeUser(user);
      course.addUser(new_teacher);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToTeacher: " + e);
        return ("Can't update the Course to the DB.");
      }
    }
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(new_teacher);
      HibernateSupport.deleteObject(user);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("userToTeacher: " + e);
      return ("Can't update the Users to the DB.");
    }
    return null;
  }

  public static String userToTeacher(AdminUser user)
  {
    if(user == null)
      return "No User selected.";

    TeacherUser new_teacher = new TeacherUser(user.getUserName(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), user.getEmail(), user.getPhone());

    new_teacher.setRegistradedCoures(user.getRegistradedCourse());
    Set<Course> course_set = user.getRegistradedCourse();
    user.setRegistradedCoures(new HashSet<Course>(0));
    for(Course course: course_set)
    {
      course.removeUser(user);
      course.addUser(new_teacher);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToTeacher: " + e);
        return ("Can't update the Course to the DB.");
      }
    }

    course_set = user.getTeachingCourses();
    for(Course course: course_set)
    {
      course.setLecturer(new_teacher);
      new_teacher.addTeachingCourse(course);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToTeacher: " + e);
        return ("Can't update the Course to the DB.");
      }
    }
    user.setTeachingCourses(new HashSet<Course>(0));
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(new_teacher);
      HibernateSupport.deleteObject(user);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("userToTeacher: " + e);
      return ("Can't update the Users to the DB.");
    }
    return null;
  }

  public static String userToAdmin(StudentUser user)
  {
    if(user == null)
      return "No User selected.";

    AdminUser new_admin = new AdminUser(user.getUserName(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), user.getEmail(), user.getPhone());

    new_admin.setRegistradedCoures(user.getRegistradedCourse());
    Set<Course> course_set = user.getRegistradedCourse();
    user.setRegistradedCoures(new HashSet<Course>(0));
    for(Course course: course_set)
    {
      course.removeUser(user);
      course.addUser(new_admin);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToAdmin: " + e);
        return ("Can't update the Course to the DB.");
      }
    }
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(new_admin);
      HibernateSupport.deleteObject(user);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("userToAdmin: " + e);
      return ("Can't update the Users to the DB.");
    }
    return null;
  }

  public static String userToAdmin(TeacherUser user)
  {
    if(user == null)
      return "No User selected.";

    AdminUser new_admin = new AdminUser(user.getUserName(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), user.getEmail(), user.getPhone());

    new_admin.setRegistradedCoures(user.getRegistradedCourse());
    Set<Course> course_set = user.getRegistradedCourse();
    user.setRegistradedCoures(new HashSet<Course>(0));
    for(Course course: course_set)
    {
      course.removeUser(user);
      course.addUser(new_admin);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToTeacher: " + e);
        return ("Can't update the Course to the DB.");
      }
    }

    course_set = user.getTeachingCourses();
    for(Course course: course_set)
    {
      course.setLecturer(new_admin);
      new_admin.addTeachingCourse(course);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(course);
        HibernateSupport.commitTransaction();
      }
      catch (HibernateException e)
      {
        System.out.println("userToTeacher: " + e);
        return ("Can't update the Course to the DB.");
      }
    }
    user.setTeachingCourses(new HashSet<Course>(0));
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(new_admin);
      HibernateSupport.deleteObject(user);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("userToTeacher: " + e);
      return ("Can't update the Users to the DB.");
    }
    return null;
  }

}
