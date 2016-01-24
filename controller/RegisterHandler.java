package controller;

public RegisterHandler
{
// REGISTER NEW USER
  public static String registerUser(String user_name, String first_name, String last_name, String password, String password_confirme, String date_of_birth, String address, String email, String phone)
  {
    if(user_name.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || password_confirme.isEmpty() || password.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || date_of_birth.isEmpty())
    {
      return "Some Fields are empty"; //some string is empty
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
      return ("Cannt load User to check if user_name already taken.";
    }




  }
}
