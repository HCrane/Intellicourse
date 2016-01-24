package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import java.util.Calendar;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import controller.*;
import classes.*;
import model.*;

import viewer.FMainMenue;

import java.util.List;

import org.jdesktop.xswingx.*;

public class FCreateTime extends JFrame
{
  private JPanel content_panel_;

  private JLabel lb_l_date_;
  private JLabel lb_l_starttime_;
  private JLabel lb_l_endtime_;
  private JLabel lb_l_place_;

  //private JTextField text_l_date_;
  private JDatePicker date_picker_;
  private JTextField text_l_starttime_;
  private JTextField text_l_endtime_;

  private JComboBox<Place> cb_place_;
  private List<Place> places_;

  private JButton btn_confirm_creation_;
  private Happening happening_;

  public FCreateTime(Happening happening)
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(300, 170);
    setTitle("Intellicourse - Create Time");

    createPanel();
    add(content_panel_);

    happening_ = happening;

    Action CreateTime = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {




          Date start_time;
          Date end_time;
          Date selected_date;

          int start_time_hours;
          int start_time_minutes;

          int end_time_hours;
          int end_time_minutes;
          try
          {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            start_time = sdf.parse(text_l_starttime_.getText());
            end_time = sdf.parse(text_l_endtime_.getText());
            selected_date = ((Calendar)date_picker_.getModel().getValue()).getTime();

            start_time_hours = start_time.getHours();
            start_time_minutes = start_time.getMinutes();

            end_time_hours = end_time.getHours();
            end_time_minutes = end_time.getMinutes();

            start_time = selected_date;
            end_time = selected_date;

            start_time.setHours(start_time_hours);
            start_time.setMinutes(end_time_minutes);

            end_time.setHours(end_time_hours);
            end_time.setMinutes(end_time_minutes);

            System.out.println(start_time);

          }
          catch(ParseException ex)
          {
            JOptionPane.showMessageDialog(null, "Wrong Date formate");
            return;
          }


          String ret_val = RegisterHandler.registerTime(happening_,start_time, end_time,(Place)cb_place_.getSelectedItem());

          if (ret_val != null)
          {
            JOptionPane.showMessageDialog(null, ret_val);
            return;
          }
          invalidate();
          validate();

          setVisible(false);
          dispose();





        }
    };

    btn_confirm_creation_.addActionListener(CreateTime);

  }

  private void createPanel()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    lb_l_date_ = new JLabel("Date:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    content_panel_.add(lb_l_date_, constraints_);

    lb_l_starttime_ = new JLabel("Start-Time:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    content_panel_.add(lb_l_starttime_, constraints_);

    lb_l_endtime_ = new JLabel("End-Time:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    content_panel_.add(lb_l_endtime_, constraints_);

    lb_l_place_ = new JLabel("Place:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 3;
    content_panel_.add(lb_l_place_, constraints_);



    /*text_l_date_ = new JTextField(10);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    constraints_.gridwidth = 3;
    PromptSupport.setPrompt("DD.MM.YYYY", text_l_date_);
    content_panel_.add(text_l_date_, constraints_);*/

    date_picker_ = new JDateComponentFactory().createJDatePicker();
    date_picker_.setTextEditable(true);
		date_picker_.setShowYearButtons(true);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 2;
    constraints_.gridy = 0;
    constraints_.ipadx = 100;
    content_panel_.add((JComponent)date_picker_, constraints_);

    text_l_starttime_ = new JTextField(10);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 1;
    constraints_.gridwidth = 3;
    PromptSupport.setPrompt("hh:mm", text_l_starttime_);
    content_panel_.add(text_l_starttime_, constraints_);

    text_l_endtime_ = new JTextField(10);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    constraints_.gridwidth = 3;
    PromptSupport.setPrompt("hh:mm", text_l_endtime_);
    content_panel_.add(text_l_endtime_, constraints_);

    places_ = GetPlace.getPlacesAll();
    cb_place_ = new JComboBox<Place>();
    for (Place place : places_) {
      cb_place_.addItem(place);
    }
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 3;
    constraints_.gridwidth = 3;
    content_panel_.add(cb_place_, constraints_);


    btn_confirm_creation_ = new JButton("Create Time");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 4;
    content_panel_.add(btn_confirm_creation_, constraints_);
  }

}
