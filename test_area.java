import java.util.*;
import java.text.*;

import classes.*;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;

public class test_area
{
  public static void main(String[] args)
  {
    createUserCourse();
    createTeacherCourse();
    //deleteCourse();
  }

  public static void createUserCourse()
  {
    String fname = "abc";
    String nname = "def";
    String uname = "coffee1";
    String passwd = "123";
    String addr = "trololol 234";
    String mail = "trolololol@rofl.lol";
    String phone = "+06648607978";
    StudentUser student = new StudentUser(uname, fname, nname, passwd, addr, mail, phone);
    
    HibernateSupport.beginTransaction();
    HibernateSupport.commit(student);
    HibernateSupport.commitTransaction();
    
    String cname = "DRECKS OOAD";
    String cdescription = "Unser Tutor is so richtig scheiße";
    int creg_limit = 250;
    boolean atendees = true;
    
    Course course = new Course(cname, cdescription, creg_limit, atendees);
    
    String c2name = "DRECKS OOAD2";
    String c2description = "Unser Tutor is so richtig scheiße";
    int c2reg_limit = 250;
    boolean atendees2 = true;
    
    Course course2 = new Course(c2name, c2description, c2reg_limit, atendees2);
    
    HibernateSupport.beginTransaction();
    HibernateSupport.commit(course);
    HibernateSupport.commitTransaction();
    
    
    HibernateSupport.beginTransaction();
    HibernateSupport.commit(course2);
    HibernateSupport.commitTransaction();
    
    
    course.addUser(student);
    student.addCourse(course);
    
    course2.addUser(student);
    student.addCourse(course2);
    
    HibernateSupport.beginTransaction();
    HibernateSupport.commit(student);
    HibernateSupport.commit(course);
    HibernateSupport.commitTransaction();
  }

  public static void createTeacherCourse()
  {
    String fname = "TEA";
    String nname = "CHER";
    String uname = "coffee2";
    String passwd = "123";
    String addr = "trololol 234";
    String mail = "trolololol@rofl.lol";
    String phone = "+06648607978";
    TeacherUser teacher = new TeacherUser(uname, fname, nname, passwd, addr, mail, phone);
    
    HibernateSupport.beginTransaction();
    HibernateSupport.commit(teacher);
    HibernateSupport.commitTransaction();
    
    List<Criterion> crit = new ArrayList<Criterion>();
    crit.add(Restrictions.eq("name_", "DRECKS OOAD"));
    Course course = HibernateSupport.readOneObject(Course.class, crit);
    
    course.setLecturer(teacher);
    teacher.addTeachingCourse(course);
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(course);
      HibernateSupport.commit(teacher);
      HibernateSupport.commitTransaction();      
    }
    catch(HibernateException e)
    {
      System.out.println("SHIIIIIT!");
    }
    
  }

  public static void deleteCourse()
  {
    List<Criterion> crit = new ArrayList<Criterion>();
    crit.add(Restrictions.eq("name_", "DRECKS OOAD"));
    Course course = HibernateSupport.readOneObject(Course.class, crit);
    
    TeacherUser teacher = course.getLecturer();
    teacher.removeTeachingCourse(course);
    course.setLecturer(null);
    
    Set<StdUser> user_set = course.getRegistradedUser();
    for(Iterator<StdUser> it = user_set.iterator(); it.hasNext();)
    {
      StdUser it_user = it.next();
      it_user.removeCourse(course);
      try
      {  
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(it_user);
        HibernateSupport.commitTransaction();  
      }
      catch(HibernateException e)
      {
        System.out.println("geht nix user speichern!");
      }
    }
    course.setRegistradedUser(new HashSet<StdUser>(0));
    
    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(teacher);
      HibernateSupport.commitTransaction();  
    }
    catch(HibernateException e)
    {
      System.out.println("geht nix lehrer speichern!");
    }

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.deleteObject(course);
      HibernateSupport.commitTransaction();      
    }
    catch(HibernateException e)
    {
      System.out.println("geht nix kurs löschen!");
    }
    
    
    
  }

}