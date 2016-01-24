package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.*;
import classes.*;
import model.*;
import java.util.List;
import java.util.Set;

import org.jdesktop.xswingx.*;


public class FAddCourseCV extends JFrame
{
  private JPanel content_panel_;

  private JTable jt_search_lv_;
  private JScrollPane sp_search_lv_;

  private JButton btn_add_lv_cv_;
  private JButton btn_back_;

  private List<Course> courses_;

  private StudentUser student_ = null;
  private TeacherUser teacher_ = null;
  private AdminUser admin_ = null;

  private void init()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(510, 415);
    setTitle("Add LV to CV");
  }
  private void createAddLvInterface()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    btn_add_lv_cv_ = new JButton("Add to CV");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0, 0, 0, 10);
    content_panel_.add(btn_add_lv_cv_, constraints_);

    btn_add_lv_cv_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent add_lv_cv
      )
      {
        int selectedRowIndex = jt_search_lv_.getSelectedRow();
        if(selectedRowIndex > courses_.size() || selectedRowIndex < 0)
        {
          JOptionPane.showMessageDialog(null, "Sorry, something went wrong!");
          return;
        }
        boolean found = false;

        Set<Course> courses_student_subsscribed_to;

        if (student_ !=null) {
          courses_student_subsscribed_to = student_.getCourses();
        }
        else if (teacher_ != null) {
          courses_student_subsscribed_to = teacher_.getCourses();
        }
        else{
          courses_student_subsscribed_to = admin_.getCourses();
        }

        for (Course s_course : courses_student_subsscribed_to) {
          if (s_course.getID() == courses_.get(selectedRowIndex).getID()) {
            found = true;
          }
        }

        if(found)
        {
          JOptionPane.showMessageDialog(null, "You are already subscribed to this course!");
          return;
        }

        if (student_ !=null) {
          if(RegisterForCourseHandler.registerStudent(student_,courses_.get(selectedRowIndex)) == null)
          {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Successfully subscribed in ");
            stringBuilder.append(courses_.get(selectedRowIndex).getName());

            JOptionPane.showMessageDialog(null, stringBuilder.toString());

          }
        }
        else if (teacher_ != null) {
          if(RegisterForCourseHandler.registerTeacher(teacher_,courses_.get(selectedRowIndex)) == null)
          {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Successfully subscribed in ");
            stringBuilder.append(courses_.get(selectedRowIndex).getName());

            JOptionPane.showMessageDialog(null, stringBuilder.toString());

          }
        }
        else{
          if(RegisterForCourseHandler.registerAdmin(admin_,courses_.get(selectedRowIndex)) == null)
          {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Successfully subscribed in ");
            stringBuilder.append(courses_.get(selectedRowIndex).getName());

            JOptionPane.showMessageDialog(null, stringBuilder.toString());

          }

        }







      }


    });

    btn_back_ = new JButton("Back");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0, 0, 0, 10);
    content_panel_.add(btn_back_, constraints_);

    btn_back_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent back)
      {
        dispose();
      }

    });

    //example data to show
    String[] columnNames = {
      "Course N#",
      "Name",
    };

    courses_ = GetCourse.getCourseAll();

    Object[][] data_ = new Object[courses_.size()][2];


    for (int i = 0; i < courses_.size(); i++) {
			data_[i][0] = courses_.get(i).getID();
      data_[i][1] = courses_.get(i).getName();
		}

    jt_search_lv_ = new JTable(data_, columnNames);
    sp_search_lv_ = new JScrollPane(jt_search_lv_);
    sp_search_lv_ = new JScrollPane(jt_search_lv_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    constraints_.gridwidth = 2;
    constraints_.ipady = 300;
    constraints_.ipadx = 450;

 content_panel_.add(sp_search_lv_, constraints_);
  }
  public FAddCourseCV()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    init();
    createAddLvInterface();
    add(content_panel_);
  }


  public FAddCourseCV(StudentUser student)
  {
    init();
    setTitle("IntelliCourse - Subscribe to LV - Student");
    student_ = student;
    createAddLvInterface();
    add(content_panel_);
  }

  public FAddCourseCV(TeacherUser teacher)
  {
    init();
    setTitle("IntelliCourse - Subscribe to LV - Teacher");
    teacher_ = teacher;
    createAddLvInterface();
    add(content_panel_);
  }

  public FAddCourseCV(AdminUser admin)
  {
    init();
    setTitle("IntelliCourse - Subscribe to LV - Admin");
    admin_ = admin;
    createAddLvInterface();
    add(content_panel_);
  }
}
