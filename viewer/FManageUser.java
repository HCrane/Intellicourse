package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import classes.*;

import java.util.Set;
import java.util.List;


import org.jdesktop.xswingx.*;

public class FManageUser extends JFrame {

  private JPanel content_panel_;
	private JTextField text_f_suser_;
	private JButton btn_s_user_;

	private JTable jt_search_output_users_;
  private DefaultTableModel jtm_search_output_users_;
	private JScrollPane sp_search_output_users_;

	private JButton btn_make_student_;
	private JButton btn_make_teacher_;
	private JButton btn_make_admin_;

	private StudentUser student_ = null;
	private TeacherUser teacher_ = null;
	private AdminUser admin_ = null;

  private List<StudentUser> students_;
  private List<TeacherUser> teachers_;
  private List<AdminUser> admins_;

  private void init()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(550, 400);
  }

  private void createManageUserInterface()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    text_f_suser_ = new JTextField(32);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    constraints_.gridwidth = 2;
    constraints_.insets = new Insets(0,0,0,10);
    constraints_.anchor = GridBagConstraints.FIRST_LINE_START;
    PromptSupport.setPrompt("Search for User", text_f_suser_);
    content_panel_.add(text_f_suser_, constraints_);

    btn_s_user_ = new JButton("Search for User");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0,0,10,10);
    content_panel_.add(btn_s_user_, constraints_);

    btn_s_user_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent search_system_user)
      {

      }
    });

    btn_make_student_ = new JButton("Make Student");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 2;
    constraints_.insets = new Insets(0,0,0,10);
    content_panel_.add(btn_make_student_, constraints_);
    btn_make_student_.setEnabled(false);
    btn_make_student_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent change_user_student)
      {
        /*
        int index = jt_search_output_users_.getSelectedRow();
        int studentcount = students_.size();
        int teachercount = teachers_.size();
        int admincount = admins_.size();

        String selectedType = (String)jtm_search_output_users_.getValueAt(index, 1);

        if (selectedType == "Student") {
          //never called!
        }else if(selectedType == "Teacher")
        {
          TeacherUser to_del = teachers_.get(index+studentcount);
          String ret_val = authorizeUser.teacher_to_student(to_del, new StudentUser());
          if (ret_val != null) {
            JOptionPane.showMessageDialog(null, ret_val);

          }
        }else if(selectedType == "Admin")
        {
          AdminUser to_del = admins_.get(index+studentcount+teachercount);
          if (admin_.getName() == to_del.getName()) {
            JOptionPane.showMessageDialog(null, "You can't delete yourself!");
          }
          String ret_val = authorizeUser.admin_to_student(to_del, new StudentUser());
          if (ret_val != null) {
            JOptionPane.showMessageDialog(null, ret_val);

          }

        }else
        {
          JOptionPane.showMessageDialog(null, "Error!");

        }

        fillTable();*/
      }
    });

    btn_make_teacher_ = new JButton("Make Teacher");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 3;
    constraints_.insets = new Insets(0,0,0,10);
    content_panel_.add(btn_make_teacher_, constraints_);
    btn_make_teacher_.setEnabled(false);
    btn_make_teacher_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent change_user_teacher)
      {
/*
        int index = jt_search_output_users_.getSelectedRow();
        int studentcount = students_.size();
        int teachercount = teachers_.size();
        int admincount = admins_.size();

        String selectedType = (String)jtm_search_output_users_.getValueAt(index, 1);

        if (selectedType == "Student") {
          StudentUser to_del = students_.get(index);
          String ret_val = authorizeUser.student_to_admin(to_del, new AdminUser());
          if (ret_val != null) {
            JOptionPane.showMessageDialog(null, ret_val);

          }
        }else if(selectedType == "Teacher")
        {
          TeacherUser to_del = teachers_.get(index+studentcount);
          String ret_val = authorizeUser.teacher_to_admin(to_del, new AdminUser());
          if (ret_val != null) {
            JOptionPane.showMessageDialog(null, ret_val);

          }
        }else if(selectedType == "Admin")
        {
          //never called
        }else
        {
          JOptionPane.showMessageDialog(null, "Error!");

        }
        fillTable();*/
      }
    });

    btn_make_admin_ = new JButton("Make Admin");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 4;
    constraints_.insets = new Insets(0,0,0,10);
    content_panel_.add(btn_make_admin_, constraints_);
    btn_make_admin_.setEnabled(false);
    btn_make_admin_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent change_user_admin)
      {
        /*
        int index = jt_search_output_users_.getSelectedRow();
        int studentcount = students_.size();
        int teachercount = teachers_.size();
        int admincount = admins_.size();

        String selectedType = (String)jtm_search_output_users_.getValueAt(index, 1);

        if (selectedType == "Student") {
          StudentUser to_del = students_.get(index);
          String ret_val = authorizeUser.student_to_teacher(to_del, new TeacherUser());
          if (ret_val != null) {
            JOptionPane.showMessageDialog(null, ret_val);

          }
        }else if(selectedType == "Teacher")
        {
          //never called!
        }else if(selectedType == "Admin")
        {
          AdminUser to_del = admins_.get(index+studentcount+teachercount);
          if (admin_.getName() == to_del.getName()) {
            JOptionPane.showMessageDialog(null, "You can't delete yourself!");
          }
          String ret_val = authorizeUser.admin_to_teacher(to_del, new TeacherUser());
          if (ret_val != null) {
            JOptionPane.showMessageDialog(null, ret_val);

          }
        }else
        {
          JOptionPane.showMessageDialog(null, "Error!");

        }
        fillTable();*/
      }
    });


    jt_search_output_users_ = new JTable();
    jtm_search_output_users_ = (DefaultTableModel) jt_search_output_users_.getModel();
    jtm_search_output_users_.addColumn("ID");
    jtm_search_output_users_.addColumn("Type");
    jtm_search_output_users_.addColumn("Username");
    jtm_search_output_users_.addColumn("Lastname");
    jtm_search_output_users_.addColumn("Firstname");
    sp_search_output_users_ = new JScrollPane(jt_search_output_users_);
    sp_search_output_users_ = new JScrollPane(jt_search_output_users_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    fillTable();
    jt_search_output_users_.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {

            int index = jt_search_output_users_.getSelectedRow();
            int studentcount = students_.size();
            int teachercount = teachers_.size();
            int admincount = admins_.size();

            String selectedType = (String)jtm_search_output_users_.getValueAt(index, 1);

            if (selectedType == "Student") {
              btn_make_teacher_.setEnabled(true);
              btn_make_admin_.setEnabled(true);
              btn_make_student_.setEnabled(false);

            }else if(selectedType == "Teacher")
            {
                btn_make_teacher_.setEnabled(false);
                btn_make_admin_.setEnabled(true);
                btn_make_student_.setEnabled(true);

            }else if(selectedType == "Admin")
            {
                btn_make_teacher_.setEnabled(true);
                btn_make_admin_.setEnabled(false);
                btn_make_student_.setEnabled(true);

            }else
            {
                btn_make_teacher_.setEnabled(false);
                btn_make_admin_.setEnabled(false);
                btn_make_student_.setEnabled(false);
            }




        }
    });

    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    constraints_.gridwidth = 2;
    constraints_.gridheight = 4;
    constraints_.ipadx = 340;
    constraints_.ipady = 260;
    constraints_.insets = new Insets(0,0,10,10);

    content_panel_.add(sp_search_output_users_, constraints_);
    add(content_panel_);
  }
  private void fillTable()
  {
    students_ = GetObjects.getAllStudents();
    teachers_ = GetObjects.getAllTeachers();
    admins_   = GetObjects.getAllAdmins();
    jtm_search_output_users_.setRowCount(0);
    int index = 0;

    for (StudentUser student : students_) {
      String[] data = new String[5];
      data[0] = String.valueOf(student.getID());
      data[1] = "Student";
      data[2] = student.getName();
      data[3] = student.getFirstName();
      data[4] = student.getLasttName();
      jtm_search_output_users_.addRow(data);
    }

    for (TeacherUser teacher : teachers_) {
      String[] data = new String[5];
      data[0] = String.valueOf(teacher.getID());
      data[1] = "Teacher";
      data[2] = teacher.getName();
      data[3] = teacher.getFirstName();
      data[4] = teacher.getLasttName();
      jtm_search_output_users_.addRow(data);
    }

    for (AdminUser admin : admins_) {
      String[] data = new String[5];
      data[0] = String.valueOf(admin.getID());
      data[1] = "Admin";
      data[2] = admin.getName();
      data[3] = admin.getFirstName();
      data[4] = admin.getLasttName();
      jtm_search_output_users_.addRow(data);
    }

    jtm_search_output_users_.fireTableDataChanged();

  }


  public FManageUser(AdminUser admin)
  {
    init();
    setTitle("IntelliCourse - Manage Users");
    admin_ = admin;
    createManageUserInterface();
  }
}
