package classes;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
public class StudentUser extends StdUser
{
  //DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public StudentUser(){}
    
  public StudentUser(String user_name, String fist_name, String last_name, String password, String address, String email, String phone)
  {
    this.user_name_ = user_name;
    this.first_name_ = fist_name;
    this.last_name_ = last_name;
    this.password_ = password;
    this.address_ = address;
    this.email_ = email;
    this.phone_ = phone;
    this.registraded_courses_ = new HashSet<Course>(0);
  }
}