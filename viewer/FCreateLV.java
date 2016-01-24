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

import viewer.FMainMenue;



public class FCreateLV extends JFrame
{
  private JPanel content_panel_;


  private JLabel lb_l_name_;
  private JLabel lb_l_teacher_;
  private JLabel lb_l_max_students_;
  private JLabel lb_l_description_;

  private JTextField text_l_name_;
  private JFormattedTextField text_l_max_students_;
  private JComboBox<TeacherUser> cb_teacher;
  private List<TeacherUser> teachers_;
  private JTextField text_l_description_;

  private JRadioButton jr_attendees_yes_;
  private JRadioButton jr_attendees_no_;
  private ButtonGroup bg_attendees_;

  private JButton btn_confirm_creation_;

  public FCreateLV()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(300, 200);
    setTitle("Intellicourse - Create Course");

    createPanel();
    add(content_panel_);


    Action CreateCourse = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

          try{
            String ret_val;

            int max_students = Integer.parseInt(text_l_max_students_.getText());

            if(max_students == 0 )
            {
              JOptionPane.showMessageDialog(null, "Input invalid!");
              return;

            }

            if (cb_teacher.getSelectedItem() == null) {
              JOptionPane.showMessageDialog(null, "No Teacher selected, so you can't create a LV!");
              return;
            }

            boolean attendees = jr_attendees_yes_.isSelected();


            //c style cast here because we know that the object can only be a TeacherUser
            ret_val = RegisterHandler.registerCourse(text_l_name_.getText(),text_l_description_.getText(),max_students, (TeacherUser)cb_teacher.getSelectedItem(),attendees);

            if (ret_val != null)
            {
              JOptionPane.showMessageDialog(null, ret_val);
              return;
            }
            invalidate();
            validate();

            setVisible(false);
            dispose();

          }catch(NumberFormatException ex){ // handle your exception
             JOptionPane.showMessageDialog(null, "Input invalid!");
          }


        }
    };

    btn_confirm_creation_.addActionListener(CreateCourse);

  }

  private void createPanel()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();

    lb_l_name_ = new JLabel("Name:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    content_panel_.add(lb_l_name_, constraints_);

    lb_l_teacher_ = new JLabel("Teacher:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    content_panel_.add(lb_l_teacher_, constraints_);

    lb_l_max_students_ = new JLabel("Max Students:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    content_panel_.add(lb_l_max_students_, constraints_);

    lb_l_description_ = new JLabel("Description:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 3;
    content_panel_.add(lb_l_description_, constraints_);

    lb_l_description_ = new JLabel("Attendees:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 4;
    content_panel_.add(lb_l_description_, constraints_);

    text_l_name_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    constraints_.gridwidth = 3;
    content_panel_.add(text_l_name_, constraints_);


    teachers_ = GetUser.getAllTeacher();
    cb_teacher = new JComboBox<TeacherUser>();
    for (TeacherUser teacher : teachers_) {
      cb_teacher.addItem(teacher);
    }


    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 1;
    constraints_.gridwidth = 3;
    content_panel_.add(cb_teacher, constraints_);



    NumberFormat format = NumberFormat.getInstance();
    format.setGroupingUsed(false);
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);

    formatter.setCommitsOnValidEdit(true);


    text_l_max_students_ = new JFormattedTextField(formatter);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    constraints_.gridwidth = 3;
    content_panel_.add(text_l_max_students_, constraints_);

    text_l_description_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 3;
    constraints_.gridwidth = 3;
    content_panel_.add(text_l_description_, constraints_);

    jr_attendees_yes_ = new JRadioButton("Yes",true);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 4;
    content_panel_.add(jr_attendees_yes_, constraints_);

    jr_attendees_no_ = new JRadioButton("No");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 5;
    content_panel_.add(jr_attendees_no_, constraints_);


    bg_attendees_ =  new ButtonGroup();
    bg_attendees_.add(jr_attendees_no_);
    bg_attendees_.add(jr_attendees_yes_);



    btn_confirm_creation_ = new JButton("Create Course");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 6;
    content_panel_.add(btn_confirm_creation_, constraints_);
  }

}
