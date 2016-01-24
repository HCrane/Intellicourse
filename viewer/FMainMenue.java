package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

import controller.*;
import classes.*;

class UpcomingEventsElement implements Comparable<UpcomingEventsElement>{

  private String name_;
  private Date start_time_;
  private Date end_time_;

  @Override
  public int compareTo(UpcomingEventsElement o) {
    return getStartTime().compareTo(o.getStartTime());
  }

  public Date getStartTime() {
    return start_time_;
  }

  public void setStartTime(Date datetime) {
    this.start_time_ = datetime;
  }


    public Date getEndTime() {
      return end_time_;
    }

    public void setEndTime(Date datetime) {
      this.end_time_ = datetime;
    }

    public final String getName()
    {
      return name_;
    }
    public final void setName(String name)
    {
      this.name_ = name;
    }

}


public class FMainMenue extends JFrame
{
  private JPanel content_panel_;

  private JLabel lb_header_info_;
  private JLabel lb_header_actions_;
  private JLabel lb_t_name_;
  private JLabel lb_t_user_type;
  private JLabel lb_name_;
  private JLabel lb_user_type_;
  private JButton btn_logout_;
  private JButton btn_update_user_;

  private JButton btn_manage_lv_;
  private JButton btn_manage_events_;
  private JButton btn_calendar_;
  private JButton btn_manage_users_;
  private JButton btn_manage_places_;

  private JLabel lb_upcoming_events_;
  private JTable jt_upcoming_events_;
  private DefaultTableModel jtm_upcoming_events_;
  private JScrollPane sp_upcoming_events_;
  private Timer jt_timer_;


  private StudentUser student_ = null;
  private TeacherUser teacher_ = null;
  private AdminUser admin_ = null;

  private void init()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
  }



  private void createContent()
  {
    //MAIN content_panel_
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    lb_header_info_ = new JLabel("User Info:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0,0,15,0);
    Font font = lb_header_info_.getFont();
    Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    lb_header_info_.setFont(boldFont);


    content_panel_.add(lb_header_info_, constraints_);

    lb_name_ = new JLabel("Name:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    constraints_.insets = new Insets(0,0,0,0);
    content_panel_.add(lb_name_, constraints_);

    lb_user_type_ = new JLabel("User-Type:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    content_panel_.add(lb_user_type_, constraints_);

    lb_t_name_ = new JLabel((student_ != null)?student_.getName() :((teacher_!= null)?teacher_.getName() :admin_.getName() ));
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 1;
    content_panel_.add(lb_t_name_, constraints_);

    lb_t_user_type = new JLabel((student_ != null)?"Student":((teacher_!= null)?"Teacher":"Admin"));
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    content_panel_.add(lb_t_user_type, constraints_);

    btn_update_user_ = new JButton("Update User-Data");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 6;
    content_panel_.add(btn_update_user_, constraints_);

    btn_update_user_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent update_user)
      {

      }

    });

    btn_logout_ = new JButton("Logout");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 6;
    content_panel_.add(btn_logout_, constraints_);

    btn_logout_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent logout)
      {
       setVisible(false);
      HibernateSupport.deinit();
       FLogin f_logout_ = new FLogin();

       f_logout_.setVisible(true);
       dispose();
     }

    });


    lb_header_actions_ = new JLabel("User Actions:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 0;
    constraints_.insets = new Insets(0,100,15,0);
    lb_header_actions_.setFont(boldFont);
    content_panel_.add(lb_header_actions_, constraints_);

    btn_manage_lv_ = new JButton("Manage LV");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 1;
    constraints_.insets = new Insets(0,100,0,0);

    content_panel_.add(btn_manage_lv_, constraints_);

    btn_manage_lv_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent manage_lv)
      {
        if (student_ !=null) {
          FManageLv f_manage_lv_ = new FManageLv(student_);
          f_manage_lv_.setVisible(true);
        }
        else if (teacher_ != null) {
          FManageLv f_manage_lv_ = new FManageLv(teacher_);
          f_manage_lv_.setVisible(true);
        }
        else{
          FManageLv f_manage_lv_ = new FManageLv(admin_);
          f_manage_lv_.setVisible(true);
        }
      }
    });

    btn_manage_lv_ = new JButton("Manage Events");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 3;
    content_panel_.add(btn_manage_lv_, constraints_);

    btn_manage_lv_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent manage_events)
      {
        if (admin_ != null) {
          FManageEvent f_manage_event_ = new FManageEvent(admin_);
          f_manage_event_.setVisible(true);
        }else if(teacher_ != null)
        {
          FManageEvent f_manage_event_ = new FManageEvent(admin_);
          f_manage_event_.setVisible(true);
        }else if(student_ != null)
        {
          FManageEvent f_manage_event_ = new FManageEvent(student_);
          f_manage_event_.setVisible(true);
        }
      }

    });


    btn_calendar_ = new JButton("Calendar");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 4;
    content_panel_.add(btn_calendar_, constraints_);

    btn_calendar_.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent logout)
      {

      }

    });

    if (admin_ != null) {

      btn_manage_users_ = new JButton("Manage Users");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 5;
      content_panel_.add(btn_manage_users_, constraints_);

      btn_manage_users_.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent manage_users)
        {
          if (admin_ !=null) {
            FManageUser f_manage_users_ = new FManageUser(admin_);
            f_manage_users_.setVisible(true);
          }
        }

      });

      btn_manage_places_ = new JButton("Manage Places");
      constraints_.fill = GridBagConstraints.HORIZONTAL;
      constraints_.gridx = 2;
      constraints_.gridy = 6;
      content_panel_.add(btn_manage_places_, constraints_);

      btn_manage_places_.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent action_manage_places_)
        {
          if (admin_ !=null) {
            FManagePlace f_manage_places_ = new FManagePlace(admin_);
            f_manage_places_.setVisible(true);
          }
        }

      });

    }




    //upcoming events example



    lb_upcoming_events_ = new JLabel("upcoming events");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 7;
    constraints_.insets = new Insets(30,0,0,0);
    lb_header_info_.setFont(boldFont);
    content_panel_.add(lb_upcoming_events_, constraints_);


    jt_upcoming_events_ = new JTable();
    jtm_upcoming_events_ = (DefaultTableModel) jt_upcoming_events_.getModel();
    jtm_upcoming_events_.addColumn("Date");
    jtm_upcoming_events_.addColumn("Name");
    jtm_upcoming_events_.addColumn("starttime");
    jtm_upcoming_events_.addColumn("endtime");
    jt_timer_ = new Timer(0, new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent e) {
          fillTable();
       }
    });

    jt_timer_.setDelay(3000); // delay for 3 seconds
    jt_timer_.start();

    sp_upcoming_events_ = new JScrollPane(jt_upcoming_events_);
    sp_upcoming_events_ = new JScrollPane(jt_upcoming_events_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 8;
    constraints_.gridwidth = 3;
    constraints_.ipady = 100;
    constraints_.insets = new Insets(0,0,0,0);

    content_panel_.add(sp_upcoming_events_, constraints_);

    add(content_panel_);

  }

  private void fillTable()
  {
    int index = jt_upcoming_events_.getSelectedRow();
    List<Course> courses_student_subsscribed_to = new ArrayList<Course>();;
    List<UpcomingEventsElement> upcomming_events = new ArrayList<UpcomingEventsElement>();;
    if (student_ !=null) {
      courses_student_subsscribed_to = new ArrayList<>(student_.getCourses());
    }
    else if (teacher_ != null) {
      courses_student_subsscribed_to = new ArrayList<>(teacher_.getCourses());
    }
    else{
      courses_student_subsscribed_to = new ArrayList<>(admin_.getCourses());
    }
    jtm_upcoming_events_.setRowCount(0);

    for (Course course : courses_student_subsscribed_to) {
      for (Time time : course.getTimes()) {
        UpcomingEventsElement n = new UpcomingEventsElement();
        n.setName(course.getName());
        n.setStartTime(time.getStartTime());
        n.setEndTime(time.getEndTime());
        upcomming_events.add(n);
      }
    }
    Collections.sort(upcomming_events);
    DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    DateFormat time = new SimpleDateFormat("HH:mm");
    for (UpcomingEventsElement element : upcomming_events)
    {
        String[] data = new String[4];
        data[0] = date.format(element.getStartTime());
        data[1] = element.getName();
        data[2] = time.format(element.getStartTime());
        data[3] = time.format(element.getEndTime());
        jtm_upcoming_events_.addRow(data);
    }


    jtm_upcoming_events_.fireTableDataChanged();

    if (index < jt_upcoming_events_.getRowCount() && index != -1) {
      jt_upcoming_events_.setRowSelectionInterval(index, index);
    }

  }


  public FMainMenue(StudentUser student)
  {
    init();
    setTitle("IntelliCourse - Student");
    student_ = student;
    createContent();
  }
  public FMainMenue(TeacherUser teacher)
  {
    init();
    setTitle("IntelliCourse - Teacher");
    student_ = null;
    teacher_ = teacher;
    admin_ = null;
    createContent();
  }
  public FMainMenue(AdminUser admin)
  {
    init();
    setTitle("IntelliCourse - Admin");
    student_ = null;
    teacher_ = null;
    admin_ = admin;
    createContent();
  }
}
