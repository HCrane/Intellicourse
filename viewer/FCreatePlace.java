package viewer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

import controller.*;
import classes.*;

import viewer.FMainMenue;

import org.jdesktop.xswingx.*;

public class FCreatePlace extends JFrame
{
  private JPanel content_panel_;

//LOGIN PANEL
  private JLabel lb_l_room_name_;
  private JLabel lb_l_room_nr_;
  private JLabel lb_l_capacity_;

  private JTextField text_l_name_;
  private JFormattedTextField text_l_room_nr_;
  private JFormattedTextField text_l_capacity_;



  private JButton btn_confirm_creation_;

  public FCreatePlace()
  {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(350, 200);
    setTitle("Intellicourse - Create Room");

    createPanel();
    add(content_panel_);


    Action CreateRoom = new AbstractAction()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {


          try{
            String ret_val;

            int roomnumber = Integer.parseInt(text_l_room_nr_.getText());
            int capacity = Integer.parseInt(text_l_capacity_.getText());
            System.out.println(roomnumber);
            System.out.println(capacity);

            if(roomnumber == 0 || capacity == 0)
            {
              JOptionPane.showMessageDialog(null, "Input invalid!");
              return;

            }

            ret_val = RegisterHandler.registerPlace(text_l_name_.getText(),capacity,roomnumber);

            if (ret_val != null)
            {
              JOptionPane.showMessageDialog(null, ret_val);
              return;
            }
            invalidate();
            validate();

            setVisible(false);
            dispose();
            //TODO Implement

          }catch(NumberFormatException ex){ // handle your exception
             JOptionPane.showMessageDialog(null, "Input invalid!");
          }








        }
    };

    btn_confirm_creation_.addActionListener(CreateRoom);

  }

  private void createPanel()
  {
    content_panel_ = new JPanel(new GridBagLayout());
    GridBagConstraints constraints_ = new GridBagConstraints();




    lb_l_room_name_ = new JLabel("Room Name:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 0;
    content_panel_.add(lb_l_room_name_, constraints_);


    lb_l_room_nr_ = new JLabel("Room Number:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 1;
    content_panel_.add(lb_l_room_nr_, constraints_);

    lb_l_capacity_ = new JLabel("Capacity:");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 2;
    content_panel_.add(lb_l_capacity_, constraints_);


    text_l_name_ = new JTextField(16);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 0;
    constraints_.gridwidth = 3;
    content_panel_.add(text_l_name_, constraints_);


    NumberFormat format = NumberFormat.getInstance();
    format.setGroupingUsed(false);
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setCommitsOnValidEdit(true);



    text_l_room_nr_ = new JFormattedTextField(formatter);
    text_l_room_nr_.setColumns(15);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 1;
    content_panel_.add(text_l_room_nr_, constraints_);


    text_l_capacity_ = new JFormattedTextField(formatter);
    text_l_capacity_.setColumns(15);
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 1;
    constraints_.gridy = 2;
    content_panel_.add(text_l_capacity_, constraints_);



    btn_confirm_creation_ = new JButton("Create Place");
    constraints_.fill = GridBagConstraints.HORIZONTAL;
    constraints_.gridx = 0;
    constraints_.gridy = 3;
    content_panel_.add(btn_confirm_creation_, constraints_);
  }

}
