package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.*;
import classes.*;

import org.jdesktop.xswingx.*;

public class FManageEvent extends JFrame
{

  private JPanel content_panel_;
  private JTextField text_f_sevent_;
  private JButton btn_s_event_;

  private JTable jt_current_events_;
  private JScrollPane sp_current_events_;

  private JButton btn_more_info_;
  private JButton btn_create_event_;
  private JButton btn_del_event_;

  private StudentUser student_ = null;
  private TeacherUser teacher_ = null;
  private AdminUser admin_ = null;

  private void init()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(550, 400);
  }

  private void createEventInterface()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    text_f_sevent_ = new JTextField(32);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    constraints_.gridwidth = 2;
    constraints_.insets = new Insets(0,0,0,10);
    constraints_.anchor = GridBagConstraints.FIRST_LINE_START;
    PromptSupport.setPrompt("DISABLED", text_f_sevent_);
    content_panel_.add(text_f_sevent_, constraints_);

    btn_s_event_ = new JButton("Search & Join");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0,0,10,10);
    content_panel_.add(btn_s_event_, constraints_);

    btn_s_event_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent search_system)
      {
        FAddCourseCV f_add_course_ = new FAddCourseCV();
        f_add_course_.setLocationRelativeTo(null);
        f_add_course_.setVisible(true);
      }

    });

      btn_create_event_ = new JButton("CREATE Event");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 2;
      constraints_.insets = new Insets(0,0,0,10);
      content_panel_.add(btn_create_event_, constraints_);

      btn_create_event_.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent add_system_event)
        {
          FCreateEvent f_create_event_ = new FCreateEvent();
          f_create_event_.setLocationRelativeTo(null);
          f_create_event_.setVisible(true);
        }

      });

      btn_del_event_ = new JButton("DELETE Event");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 3;
      constraints_.insets = new Insets(0,0,0,10);
      content_panel_.add(btn_del_event_, constraints_);

      btn_del_event_.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent del_event)
        {

        }

      });

      btn_more_info_ = new JButton(" More Info");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 4;
      constraints_.insets = new Insets(0,0,0,10);
      content_panel_.add(btn_more_info_, constraints_);

      btn_more_info_.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent more_info)
        {

        }

      });
      String[] columnNames = {"ID",
                          "Name"
                        };
      Object[][] data = {
          {"123.456", "Analysis T1",},
          {"123.456", "Analysis T1",},
          {"123.456", "Analysis T1",},
          {"123.456", "Analysis T1",},
          {"123.456", "Analysis T1",}
      };

    jt_current_events_ = new JTable(data, columnNames);
    sp_current_events_ = new JScrollPane(jt_current_events_);
    sp_current_events_ = new JScrollPane(jt_current_events_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    constraints_.gridwidth = 2;
    constraints_.gridheight = 4;
    constraints_.ipadx = 340;
    constraints_.ipady = 260;
    constraints_.insets = new Insets(0,0,10,10);

    content_panel_.add(sp_current_events_, constraints_);
    add(content_panel_);
}




  public FManageEvent(StudentUser student)
  {
    init();
    setTitle("IntelliCourse - Manage Events");
    student_ = student;
    createEventInterface();
  }

  public FManageEvent(TeacherUser teacher)
  {
    init();
    setTitle("IntelliCourse - Manage Events");
    teacher_ = teacher;
    createEventInterface();
  }

  public FManageEvent(AdminUser admin)
  {
    init();
    setTitle("IntelliCourse - Manage Events");
    admin_ = admin;
    createEventInterface();
  }
}
