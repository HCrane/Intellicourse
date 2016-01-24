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

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Timer;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FManageTime extends JFrame{

  private JPanel content_panel_;

  private JTable jt_current_tv_times_;
  private DefaultTableModel jtm_current_lv_times_;
  private Timer jt_timer_;
  private JScrollPane sp_current_lv_times_;

  private JButton btn_add_time_;
  private JButton btn_del_time_;

  private Course course_ = null;

  private void init(){
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(550, 400);
  }

  private void createTimeInterface(){
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    btn_add_time_ = new JButton("Add Time for LV");
    constraints_.gridx = 2;
    constraints_.gridy = 1;
    constraints_.insets = new Insets(0, 0, 0, 10);
    content_panel_.add(btn_add_time_, constraints_);

    btn_add_time_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent add_time_to_lv) {
        FCreateTime f_create_time = new FCreateTime(course_);
        f_create_time.setVisible(true);

      }

    });

    btn_del_time_ = new JButton("Delete Time for LV");
    constraints_.gridx = 2;
    constraints_.gridy = 2;
    constraints_.insets = new Insets(0, 0, 0, 10);
    content_panel_.add(btn_del_time_, constraints_);

    btn_del_time_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent del_time_to_lv) {
      }

    });

    jt_current_tv_times_ = new JTable();
    jtm_current_lv_times_ = (DefaultTableModel) jt_current_tv_times_.getModel();
    jtm_current_lv_times_.addColumn("Date");
    jtm_current_lv_times_.addColumn("Start-Time");
    jtm_current_lv_times_.addColumn("End-Time");
    jt_timer_ = new Timer(0, new ActionListener() {

       @Override
       public void actionPerformed(ActionEvent e) {
          fillTable();
       }
    });

    jt_timer_.setDelay(300); // delay for .3 seconds
    jt_timer_.start();

    sp_current_lv_times_ = new JScrollPane(jt_current_tv_times_);
    sp_current_lv_times_ = new JScrollPane(jt_current_tv_times_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    constraints_.gridwidth = 2;
    constraints_.gridheight = 4;
    constraints_.ipadx = 340;
    constraints_.ipady = 260;
    constraints_.insets = new Insets(0, 0, 0, 10);

    content_panel_.add(sp_current_lv_times_, constraints_);
    add(content_panel_);

  }
  private void fillTable(){
    int index = jt_current_tv_times_.getSelectedRow();
    Set<Time> times = course_.getTimes();
    jtm_current_lv_times_.setRowCount(0);


    DateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
    DateFormat timeF = new SimpleDateFormat("HH:mm");

    for (Time time : times) {

        String[] data = new String[4];
        data[0] = dateF.format(time.getStartTime());
        data[1] = timeF.format(time.getStartTime());
        data[2] = timeF.format(time.getEndTime());
        jtm_current_lv_times_.addRow(data);
    }

    if (index < jt_current_tv_times_.getRowCount() && index != -1) {
      jt_current_tv_times_.setRowSelectionInterval(index, index);
    }

  }

  public FManageTime(Course course) {
    init();
    setTitle("IntelliCourse - Manage LV -- Times - Admin");
    course_ = course;
    createTimeInterface();
  }
}
