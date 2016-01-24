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
    HibernateSupport.init();
    createUsers();
    createEvent();
    createCourses();
    createPlace();
    addPlaceToCourse();
    setLecturerOfCourse();
    subscribeForCourse();
    deleteCourse();
    createExam();
    setLecturerOfExam();
    subscribeForExam();
    deleteExam();
    HibernateSupport.deinit();
    System.exit(0);
  }

  public static void createUsers()
  {
    String fnameS = "abc";
    String nnameS = "def";
    String unameS = "coffee1";
    String passwdS = "123";
    String addrS = "trololol 234";
    String mailS = "trolololol@rofl.lol";
    String phoneS = "+06648607978";
    StudentUser student = new StudentUser(unameS, fnameS, nnameS, passwdS, addrS, mailS, phoneS);

    String fnameT = "abc";
    String nnameT = "def";
    String unameT = "coffee2";
    String passwdT = "123";
    String addrT = "trololol 234";
    String mailT = "trolololol@rofl.lol";
    String phoneT = "+06648607978";
    TeacherUser teacher = new TeacherUser(unameT, fnameT, nnameT, passwdT, addrT, mailT, phoneT);

    String fnameT2 = "abc";
    String nnameT2 = "def";
    String unameT2 = "coffee3";
    String passwdT2 = "123";
    String addrT2 = "trololol 234";
    String mailT2 = "trolololol@rofl.lol";
    String phoneT2 = "+06648607978";
    TeacherUser teacher2 = new TeacherUser(unameT2, fnameT2, nnameT2, passwdT2, addrT2, mailT2, phoneT2);

    String fnameA1 = "abc";
    String nnameA1 = "def";
    String unameA1 = "coffee4";
    String passwdA1 = "123";
    String addrA1 = "trololol 234";
    String mailA1 = "trolololol@rofl.lol";
    String phoneA1 = "+06648607978";
    AdminUser admin1 = new AdminUser(unameA1, fnameA1, nnameA1, passwdA1, addrA1, mailA1, phoneA1);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(student);
      HibernateSupport.commit(teacher);
      HibernateSupport.commit(teacher2);
      HibernateSupport.commit(admin1);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't create the Users!");
      System.out.println(e);
      return;
    }
  }

  public static void createEvent()
  {
    String nameE1 = "Feier 1";
    String descE1 = "Abschlussfeier1";

    String nameE2 = "Feier 2";
    String descE2 = "Abschlussfeier2";

    Event event1 = new Event(nameE1, descE1);
    Event event2 = new Event(nameE2, descE2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(event1);
      HibernateSupport.commit(event2);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't create the Events!");
      System.out.println(e);
      return;
    }

  }

  public static void createCourses()
  {
    String nameC1 = "OOAD";
    String descC1 = "some infos";
    int reg_limitC1 = 250;
    boolean atendeesC1 = true;

    Course course1 = new Course(nameC1, descC1, reg_limitC1, atendeesC1);

    String nameC2 = "LC";
    String descC2 = "some infos";
    int reg_limitC2 = 250;
    boolean atendeesC2 = true;

    Course course2 = new Course(nameC2, descC2, reg_limitC2, atendeesC2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(course1);
      HibernateSupport.commit(course2);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't create the Courses!");
      System.out.println(e);
      return;
    }

  }

  public static void createPlace()
  {
    String name1 = "HSi13";
    int room_cp1 = 300;
    int room_nr1 = 152;

    Place room1 = new Place(name1, room_cp1, room_nr1);

    String name2 = "HSi23";
    int room_cp2 = 300;
    int room_nr2 = 252;

    Place room2 = new Place(name2, room_cp2, room_nr2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(room1);
      HibernateSupport.commit(room2);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't create the Room!");
      System.out.println(e);
      return;
    }
  }

  public static void addPlaceToCourse()
  {
    Course course1;
    Course course2;
    Place room1;
    Place room2;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "OOAD"));
      course1 = HibernateSupport.readOneObject(Course.class, crit1);

      List<Criterion> crit2 = new ArrayList<Criterion>();
      crit2.add(Restrictions.eq("roomnr_", 152));
      room1 = HibernateSupport.readOneObject(Place.class, crit2);

      List<Criterion> crit3 = new ArrayList<Criterion>();
      crit3.add(Restrictions.eq("name_", "LC"));
      course2 = HibernateSupport.readOneObject(Course.class, crit3);

      List<Criterion> crit4 = new ArrayList<Criterion>();
      crit4.add(Restrictions.eq("roomnr_", 252));
      room2 = HibernateSupport.readOneObject(Place.class, crit4);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    course1.setRoom(room1);
    course2.setRoom(room2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(course1);
      HibernateSupport.commit(course2);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't set room of the Course!");
      System.out.println(e);
      return;
    }

  }

  public static void setLecturerOfCourse()
  {
    Course course1;
    Course course2;
    TeacherUser teacher;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "OOAD"));
      course1 = HibernateSupport.readOneObject(Course.class, crit1);

      List<Criterion> crit2 = new ArrayList<Criterion>();
      crit2.add(Restrictions.eq("name_", "LC"));
      course2 = HibernateSupport.readOneObject(Course.class, crit2);

      List<Criterion> crit3 = new ArrayList<Criterion>();
      crit3.add(Restrictions.eq("user_name_", "coffee2"));
      teacher = HibernateSupport.readOneObject(TeacherUser.class, crit3);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    course1.setLecturer(teacher);
    course2.setLecturer(teacher);
    teacher.addTeachingCourse(course1);
    teacher.addTeachingCourse(course2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(course1);
      HibernateSupport.commit(course2);
      HibernateSupport.commit(teacher);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't set lecturer of the Courses!");
      System.out.println(e);
      return;
    }
  }

  public static void subscribeForCourse()
  {
    Course course1;
    Course course2;
    StudentUser student;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "OOAD"));
      course1 = HibernateSupport.readOneObject(Course.class, crit1);

      List<Criterion> crit2 = new ArrayList<Criterion>();
      crit2.add(Restrictions.eq("name_", "LC"));
      course2 = HibernateSupport.readOneObject(Course.class, crit2);

      List<Criterion> crit3 = new ArrayList<Criterion>();
      crit3.add(Restrictions.eq("user_name_", "coffee1"));
      student = HibernateSupport.readOneObject(StudentUser.class, crit3);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    course1.addUser(student);
    course2.addUser(student);
    student.addCourse(course1);
    student.addCourse(course2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(course1);
      HibernateSupport.commit(course2);
      HibernateSupport.commit(student);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't subscribe the Courses!");
      System.out.println(e);
      return;
    }
  }

  public static void deleteCourse()
  {
    Course course;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "OOAD"));
      course = HibernateSupport.readOneObject(Course.class, crit1);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    TeacherUser teacher = course.getLecturer();
    Set<StdUser> course_user_set = course.getRegistradedUser();

    teacher.removeTeachingCourse(course);
    for(Iterator<StdUser> it = course_user_set.iterator(); it.hasNext();)
    {
      StdUser user_it = it.next();
      user_it.removeCourse(course);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(user_it);
        HibernateSupport.commitTransaction();
      }
      catch(HibernateException e)
      {
        System.out.println("Can't update the User!");
        System.out.println(e);
        return;
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
      System.out.println("Can't subscribe the Courses!");
      System.out.println(e);
      return;
    }

  }

  public static void createExam()
  {
    String nameE1 = "SNP";
    String descE1 = "some extra infos";
    int reg_limitE1 = 150;

    String nameE2 = "LP";
    String descE2 = "some extra infos";
    int reg_limitE2 = 150;

    Exam exam1 = new Exam(nameE1, descE1, reg_limitE1);
    Exam exam2 = new Exam(nameE2, descE2, reg_limitE2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(exam1);
      HibernateSupport.commit(exam2);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't create the Exames!");
      System.out.println(e);
      return;
    }

  }

  public static void setLecturerOfExam()
  {
    Exam exam1;
    Exam exam2;

    TeacherUser teacher1;
    TeacherUser teacher2;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "SNP"));
      exam1 = HibernateSupport.readOneObject(Exam.class, crit1);

      List<Criterion> crit2 = new ArrayList<Criterion>();
      crit2.add(Restrictions.eq("name_", "LP"));
      exam2 = HibernateSupport.readOneObject(Exam.class, crit2);

      List<Criterion> crit3 = new ArrayList<Criterion>();
      crit3.add(Restrictions.eq("user_name_", "coffee2"));
      teacher1 = HibernateSupport.readOneObject(TeacherUser.class, crit3);

      List<Criterion> crit4 = new ArrayList<Criterion>();
      crit4.add(Restrictions.eq("user_name_", "coffee3"));
      teacher2 = HibernateSupport.readOneObject(TeacherUser.class, crit4);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    exam1.setLecturerExam(teacher2);
    teacher2.addTeachingExam(exam1);
    exam2.setLecturerExam(teacher1);
    teacher1.addTeachingExam(exam2);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(exam1);
      HibernateSupport.commit(exam2);
      HibernateSupport.commit(teacher1);
      HibernateSupport.commit(teacher2);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't save the teachers of Exames!");
      System.out.println(e);
      return;
    }
  }

  public static void subscribeForExam()
  {
    Exam exam1;
    Exam exam2;

    StudentUser student;
    TeacherUser teacher;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "SNP"));
      exam1 = HibernateSupport.readOneObject(Exam.class, crit1);

      List<Criterion> crit2 = new ArrayList<Criterion>();
      crit2.add(Restrictions.eq("name_", "LP"));
      exam2 = HibernateSupport.readOneObject(Exam.class, crit2);

      List<Criterion> crit3 = new ArrayList<Criterion>();
      crit3.add(Restrictions.eq("user_name_", "coffee1"));
      student = HibernateSupport.readOneObject(StudentUser.class, crit3);

      List<Criterion> crit4 = new ArrayList<Criterion>();
      crit4.add(Restrictions.eq("user_name_", "coffee3"));
      teacher = HibernateSupport.readOneObject(TeacherUser.class, crit4);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    student.addExam(exam2);
    exam2.addUserExam(student);

    teacher.addExam(exam1);
    exam1.addUserExam(teacher);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(exam1);
      HibernateSupport.commit(exam2);
      HibernateSupport.commit(teacher);
      HibernateSupport.commit(student);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't subscribe to the Exames!");
      System.out.println(e);
      return;
    }
  }

  public static void deleteExam()
  {
    Exam exam;
    try
    {
      List<Criterion> crit1 = new ArrayList<Criterion>();
      crit1.add(Restrictions.eq("name_", "SNP"));
      exam = HibernateSupport.readOneObject(Exam.class, crit1);
    }
    catch(HibernateException e)
    {
      System.out.println("Can't get the Data!");
      System.out.println(e);
      return;
    }

    TeacherUser teacher = exam.getLecturerExam();
    Set<StdUser> exam_user_set = exam.getRegistradedUserExam();

    teacher.removeTeachingExam(exam);
    for(Iterator<StdUser> it = exam_user_set.iterator(); it.hasNext();)
    {
      StdUser user_it = it.next();
      user_it.removeExam(exam);
      try
      {
        HibernateSupport.beginTransaction();
        HibernateSupport.commit(user_it);
        HibernateSupport.commitTransaction();
      }
      catch(HibernateException e)
      {
        System.out.println("Can't update the User!");
        System.out.println(e);
        return;
      }
    }
    exam.setLecturerExam(null);
    exam.setRegistradedUserExam(new HashSet<StdUser>(0));
    exam.setRoom(null);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(teacher);
      HibernateSupport.deleteObject(exam);
      HibernateSupport.commitTransaction();
    }
    catch(HibernateException e)
    {
      System.out.println("Can't subscribe the Courses!");
      System.out.println(e);
      return;
    }
  }

}
