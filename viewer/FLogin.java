package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.*;
import classes.*;

import viewer.FMainMenue;

import org.jdesktop.xswingx.*;

public class FLogin extends JFrame
{
  private JPanel content_panel_;
  private JPanel register_panel_;

//LOGIN PANEL
  private JLabel lb_lname_;
  private JLabel lb_lpasswd_;

  private JTextField text_f_name_;
  private JPasswordField pass_f_passwd_;

  private JButton btn_confirm_login_;
  private JButton btn_register_;

// REGISTER PANEL
  private JLabel lb_rname_;
  private JLabel lb_rfirst_name_;
  private JLabel lb_rlast_name_;
  private JLabel lb_date_of_birth_;
  private JLabel lb_adress_;
  private JLabel lb_email_;
  private JLabel lb_phone_;

  private JTextField text_f_rname_;
  private JTextField text_f_rfirst_name_;
  private JTextField text_f_rlast_name_;
  private JTextField text_f_date_of_birth_;
  private JTextField text_f_adress_;
  private JTextField text_f_email_;
  private JTextField text_f_phone_;

  private JLabel lb_rpasswd_;
  private JLabel lb_rpasswd_confirmed_;

  private JPasswordField pass_f_rpasswd_;
  private JPasswordField pass_f_rpasswd_confirmed_;

  private JButton btn_confirm_register_;

  public FLogin()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(450, 300);
    setTitle("Intellicourse - Login");
    HibernateSupport.init();
    createLoginPanel();
    createRegisterPanel();
    add(content_panel_);

    btn_register_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent register)
      {
        setContentPane(register_panel_);
        invalidate();
        validate();
      }
    });

    Action LoginAction = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          boolean sucess_student = false;
          boolean sucess_teacher = false;
          boolean sucess_admin = false;

          StudentUser student = LoginHandler.studentLogin(text_f_name_.getText(),String.valueOf(pass_f_passwd_.getPassword()));
          if(student != null)
          {
            sucess_student = true;
          }

          TeacherUser teacher = LoginHandler.teacherLogin(text_f_name_.getText(),String.valueOf(pass_f_passwd_.getPassword()));
          if(teacher != null)
          {
            sucess_teacher = true;
          }

          AdminUser admin = LoginHandler.adminLogin(text_f_name_.getText(),String.valueOf(pass_f_passwd_.getPassword()));
          if(admin != null)
          {
            sucess_admin = true;
          }

          if (sucess_admin) {
            setVisible(false);
            FMainMenue f_main_menue_ = new FMainMenue(admin);
            f_main_menue_.setVisible(true);
            dispose();
          }else if(sucess_teacher)
          {
            setVisible(false);
            FMainMenue f_main_menue_ = new FMainMenue(teacher);
            f_main_menue_.setVisible(true);
            dispose();
          }else if(sucess_student)
          {
            setVisible(false);
            FMainMenue f_main_menue_ = new FMainMenue(student);
            f_main_menue_.setVisible(true);
            dispose();
          }else {
              JOptionPane.showMessageDialog(null, "Username or Password incorrect!");
          }
        }
    };

    btn_confirm_login_.addActionListener(LoginAction);
    text_f_name_.addActionListener(LoginAction);
    pass_f_passwd_.addActionListener(LoginAction);

    btn_confirm_register_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent register_confirmed)
      {
         String ret_val;

         ret_val = RegisterHandler.registerUser(text_f_rname_.getText(),
                                                text_f_rfirst_name_.getText(),
                                                text_f_rlast_name_.getText(),
                                                String.valueOf(pass_f_rpasswd_.getPassword()),
                                                String.valueOf(pass_f_rpasswd_confirmed_.getPassword()),
                                                text_f_date_of_birth_.getText(),
                                                text_f_adress_.getText(),
                                                text_f_email_.getText(),
                                                text_f_phone_.getText());

         if (ret_val != null)
         {
           JOptionPane.showMessageDialog(null, ret_val);
         }else
         {
           setContentPane(content_panel_);
           invalidate();
           validate();
         }


      }
    });

  }

  private void createLoginPanel()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();
    //TODO + add IntelliCourse Logo

    lb_lname_ = new JLabel("Username:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    content_panel_.add(lb_lname_, constraints_);

    lb_lpasswd_ = new JLabel("Password:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    content_panel_.add(lb_lpasswd_, constraints_);

    text_f_name_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    content_panel_.add(text_f_name_, constraints_);

    pass_f_passwd_ = new JPasswordField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 1;
    content_panel_.add(pass_f_passwd_, constraints_);

    btn_register_ = new JButton("Register");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    content_panel_.add(btn_register_, constraints_);

    btn_confirm_login_ = new JButton("Login");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    content_panel_.add(btn_confirm_login_, constraints_);
  }

  private void createRegisterPanel()
  {
    register_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    lb_rname_ = new JLabel("Username:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    register_panel_.add(lb_rname_, constraints_);

    lb_rfirst_name_ = new JLabel("First Name:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    register_panel_.add(lb_rfirst_name_, constraints_);

    lb_rlast_name_ = new JLabel("Last Name:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    register_panel_.add(lb_rlast_name_, constraints_);

    lb_date_of_birth_ = new JLabel("Date of Birth:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 3;
    register_panel_.add(lb_date_of_birth_, constraints_);

    lb_adress_ = new JLabel("Adress:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 4;
    register_panel_.add(lb_adress_, constraints_);

    lb_email_ = new JLabel("E-Mail:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 5;
    register_panel_.add(lb_email_, constraints_);

    lb_phone_ = new JLabel("Phone Number:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 6;
    register_panel_.add(lb_phone_, constraints_);

    lb_rpasswd_ = new JLabel("Password");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 7;
    register_panel_.add(lb_rpasswd_, constraints_);

    lb_rpasswd_confirmed_ = new JLabel("Confirm Password:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 8;
    register_panel_.add(lb_rpasswd_confirmed_, constraints_);

    text_f_rname_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    PromptSupport.setPrompt("Username for System", text_f_rname_);
    register_panel_.add(text_f_rname_, constraints_);

    text_f_rfirst_name_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 1;
    register_panel_.add(text_f_rfirst_name_, constraints_);

    text_f_rlast_name_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    register_panel_.add(text_f_rlast_name_, constraints_);

    text_f_date_of_birth_ = new JTextField(10);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 3;
    PromptSupport.setPrompt("DD.MM.YYYY", text_f_date_of_birth_);
    register_panel_.add(text_f_date_of_birth_, constraints_);

    text_f_adress_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 4;
    register_panel_.add(text_f_adress_, constraints_);

    text_f_email_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 5;
    PromptSupport.setPrompt("something@somthing.com", text_f_email_);
    register_panel_.add(text_f_email_, constraints_);

    text_f_phone_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 6;
    PromptSupport.setPrompt("+43 660 123456", text_f_phone_);
    register_panel_.add(text_f_phone_, constraints_);

    pass_f_rpasswd_ = new JPasswordField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 7;
    register_panel_.add(pass_f_rpasswd_, constraints_);

    pass_f_rpasswd_confirmed_ = new JPasswordField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 8;
    register_panel_.add(pass_f_rpasswd_confirmed_, constraints_);

    btn_confirm_register_ = new JButton("Register");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 9;
    constraints_.weightx = 0.0;
    constraints_.gridwidth = 2;
    register_panel_.add(btn_confirm_register_, constraints_);

  }
}
