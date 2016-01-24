package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import org.jdesktop.xswingx.*;
import java.util.List;
import java.util.Date;

import controller.*;
import classes.*;
import model.*;

import viewer.FMainMenue;

public class FCreateEvent extends JFrame
{
  private JPanel content_panel_;


  private JLabel lb_event_name_;
  private JLabel lb_event_max_students_;
  private JLabel lb_event_discription_;

  private JTextField text_event_name_;
  private JFormattedTextField text_event_max_students_;
  private JTextField text_event_description_;

  private JButton btn_confirm_creation_;
  private JButton btn_back_;

  private void createPanel()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    lb_event_name_ = new JLabel("Name:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    content_panel_.add(lb_event_name_, constraints_);

    lb_event_max_students_ = new JLabel("Max Students:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    content_panel_.add(lb_event_max_students_, constraints_);

    lb_event_discription_ = new JLabel("Description:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    content_panel_.add(lb_event_discription_, constraints_);

    text_event_name_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    constraints_.gridwidth = 3;
    content_panel_.add(text_event_name_, constraints_);

    NumberFormat format = NumberFormat.getInstance();
    format.setGroupingUsed(false);
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setCommitsOnValidEdit(true);

    text_event_max_students_ = new JFormattedTextField(formatter);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    content_panel_.add(text_event_max_students_, constraints_);

    text_event_description_ = new JTextField(256);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 3;
    constraints_.gridheight = 3;
    constraints_.gridwidth = 3;
    content_panel_.add(text_event_description_, constraints_);

    btn_confirm_creation_ = new JButton("Create Event");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 6;
    content_panel_.add(btn_confirm_creation_, constraints_);

    btn_back_ = new JButton("Back");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 3;
    constraints_.gridy = 6;
    content_panel_.add(btn_back_, constraints_);
  }
  public FCreateEvent()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(300, 200);
    setTitle("Intellicourse - Create Event");

    createPanel();
    add(content_panel_);
  }

}
