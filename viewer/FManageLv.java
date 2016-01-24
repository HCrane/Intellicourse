package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controller.*;
import classes.*;
import model.*;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Timer;

import org.jdesktop.xswingx.*;

public class FManageLv extends JFrame {

  private JPanel content_panel_;
  private JButton btn_s_lv_;

  private JTable jt_current_lvs_;
  private DefaultTableModel jtm_current_lvs_;
  private Timer jt_timer_;
  private JScrollPane sp_current_lvs_;

  private JButton btn_add_curriculum_;
  private JButton btn_del_curriculum_;

  private JButton btn_add_system_;
  private JButton btn_del_system_;

  private JButton btn_manage_time_;

  private StudentUser student_ = null;
  private TeacherUser teacher_ = null;
  private AdminUser admin_ = null;

  private List<Course> courses_student_subsscribed_to_;

  private void init() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(550, 400);
  }

  private void createLvInterface() {

    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    btn_s_lv_ = new JButton("Join new LV");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0, 0, 0, 10);
    content_panel_.add(btn_s_lv_, constraints_);

    btn_s_lv_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent search_system_course) {
        if (student_ !=null) {
          FAddCourseCV f_add_course_ = new FAddCourseCV(student_);
          f_add_course_.setVisible(true);
        }
        else if (teacher_ != null) {
          FAddCourseCV f_add_course_ = new FAddCourseCV(teacher_);
          f_add_course_.setVisible(true);
        }
        else{
          FAddCourseCV f_add_course_ = new FAddCourseCV(admin_);
          f_add_course_.setVisible(true);
        }

      }

    });
/*
    btn_add_curriculum_ = new JButton("ADD LV to Curr.");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 2;
    constraints_.insets = new Insets(0, 0, 0, 10);
    content_panel_.add(btn_add_curriculum_, constraints_);*/

    btn_del_curriculum_ = new JButton("DELETE LV from Curr.");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 2;
    constraints_.insets = new Insets(0, 0, 10, 10);
    content_panel_.add(btn_del_curriculum_, constraints_);

    btn_del_curriculum_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent search_system_course) {
        int index = jt_current_lvs_.getSelectedRow();

        if (index < courses_student_subsscribed_to_.size() && index >= 0) {
          Course unsubscribe_from = courses_student_subsscribed_to_.get(index);
          boolean success = false;
          if (student_ !=null) {
            success = RegisterForCourseHandler.unregisterStudent(student_,unsubscribe_from) == null;
          }
          else if (teacher_ != null) {
            success = RegisterForCourseHandler.unregisterTeacher(teacher_,unsubscribe_from) == null;
          }
          else{
            success = RegisterForCourseHandler.unregisterAdmin(admin_,unsubscribe_from) == null;
          }

          if(success)
          {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Successfully subscribed from ");
            stringBuilder.append(unsubscribe_from.getName());

            JOptionPane.showMessageDialog(null, stringBuilder.toString());
            fillTable();
          }
        }

      }

    });

    if (student_ == null) {
      btn_add_system_ = new JButton("ADD LV to System.");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 4;
      constraints_.insets = new Insets(0, 0, 0, 10);
      content_panel_.add(btn_add_system_, constraints_);

      btn_add_system_.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent add_system) {
          FCreateLV f_create_course_ = new FCreateLV();
          f_create_course_.setVisible(true);
        }

      });

      btn_del_system_ = new JButton("DELETE LV from System.");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 5;
      constraints_.insets = new Insets(0, 0, 0, 10);
      content_panel_.add(btn_del_system_, constraints_);

      btn_del_system_.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent del_system) {


        }

      });

			btn_manage_time_ = new JButton("Manage Time for LV");
	    constraints_.fill = GridBagConstraints.HORIZONTAL;
	    constraints_.gridx = 2;
	    constraints_.gridy = 6;
	    constraints_.insets = new Insets(0, 0, 10, 10);
	    content_panel_.add(btn_manage_time_, constraints_);

      btn_manage_time_.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent del_system) {
          int index = jt_current_lvs_.getSelectedRow();

          if (index < courses_student_subsscribed_to_.size() && index >= 0)
          {
            FManageTime f_manage_time = new FManageTime(courses_student_subsscribed_to_.get(index));
            f_manage_time.setVisible(true);

          }
        }

      });

    }


    jt_current_lvs_ = new JTable();
    jtm_current_lvs_ = (DefaultTableModel) jt_current_lvs_.getModel();
    jtm_current_lvs_.addColumn("ID");
    jtm_current_lvs_.addColumn("Name");
    jt_timer_ = new Timer(0, new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent e) {
          fillTable();
       }
    });

    jt_timer_.setDelay(300); // delay for .3 seconds
    jt_timer_.start();

    sp_current_lvs_ = new JScrollPane(jt_current_lvs_);
    sp_current_lvs_ = new JScrollPane(jt_current_lvs_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    constraints_.gridwidth = 2;
    constraints_.gridheight = 4;
    constraints_.ipadx = 340;
    constraints_.ipady = 260;
    constraints_.insets = new Insets(0, 0, 0, 10);

    content_panel_.add(sp_current_lvs_, constraints_);
    add(content_panel_);
  }

  private void fillTable()
  {
    int index = jt_current_lvs_.getSelectedRow();

    if (student_ !=null) {
      courses_student_subsscribed_to_ = new ArrayList<>(student_.getCourses());
    }
    else if (teacher_ != null) {
      courses_student_subsscribed_to_ = new ArrayList<>(teacher_.getCourses());
      for (Course course : GetCourse.getCourseAll()) {
          if (course.getLecturer().getID() == teacher_.getID()) {
            courses_student_subsscribed_to_.add(course);
          }
      }
    }
    else{
      courses_student_subsscribed_to_ = new ArrayList<>(admin_.getCourses());
      for (Course course : GetCourse.getCourseAll()) {
          if (course.getLecturer().getID() == admin_.getID()) {
            courses_student_subsscribed_to_.add(course);
          }
      }
    }
    jtm_current_lvs_.setRowCount(0);

    for (Course course : courses_student_subsscribed_to_) {
        String[] data = new String[2];
        data[0] = String.valueOf(course.getID());
        data[1] = course.getName();
        jtm_current_lvs_.addRow(data);
    }

    jtm_current_lvs_.fireTableDataChanged();

    if (index < jt_current_lvs_.getRowCount() && index != -1) {
      jt_current_lvs_.setRowSelectionInterval(index, index);
    }

  }



  public FManageLv(StudentUser student) {
    init();
    setTitle("IntelliCourse - Manage LV's - Student");
    student_ = student;
    createLvInterface();
  }

  public FManageLv(TeacherUser teacher) {
    init();
    setTitle("IntelliCourse - Manage LV's - Teacher");
    teacher_ = teacher;
    createLvInterface();
  }

  public FManageLv(AdminUser admin) {
    init();
    setTitle("IntelliCourse - Manage LV's - Admin");
    admin_ = admin;
    createLvInterface();
  }
}
