package controller;

import java.util.*;
import java.text.*;

import classes.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class RegisterHandler
{
// REGISTER NEW USER
  public static String registerUser(String user_name, String first_name, String last_name, String password, String password_confirme, String date_of_birth, String address, String email, String phone)
  {
    if(user_name.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || password_confirme.isEmpty() || password.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || date_of_birth.isEmpty())
    {
      return ("Some Fields are empty"); //some string is empty
    }

    StudentUser student;
    TeacherUser teacher;
    AdminUser admin;

    try
    {
      List<Criterion> crit = new ArrayList<Criterion>();
      crit.add(Restrictions.eq("user_name_", user_name));

      student = HibernateSupport.readOneObject(StudentUser.class, crit);
      teacher = HibernateSupport.readOneObject(TeacherUser.class, crit);
      admin = HibernateSupport.readOneObject(AdminUser.class, crit);
    }
    catch (HibernateException e)
    {
      System.out.println("registerUser: " + e);
      return ("Can't load User to check if user_name already taken.");
    }

    if((student != null) || (teacher != null) | (admin != null))
      return ("Username already taken.");

    Date new_date_of_birth;
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
      new_date_of_birth = sdf.parse(date_of_birth);
    }
    catch(ParseException e)
    {
      return ("Wrong Date formate");
    }

    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(email);
    if(!m.matches())
    {
      return ("Invalid mail format");
    }

    if(!(password.equals(password_confirme)))
    {
      return ("Password and Password Confirm is not the same");
    }

    StudentUser new_student = new StudentUser(user_name, first_name, last_name, password, new_date_of_birth, address, email, phone);

    try
    {
      HibernateSupport.beginTransaction();
      HibernateSupport.commit(new_student);
      HibernateSupport.commitTransaction();
    }
    catch (HibernateException e)
    {
      System.out.println("registerUser: " + e);
      return ("Can't save new user to the DB.");
    }

    return("");
  }
}
