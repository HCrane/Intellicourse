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

import org.jdesktop.xswingx.*;

public class FManagePlace extends JFrame {

	private JPanel content_panel_;

	private JTable jt_current_places_;
	private DefaultTableModel jtm_current_places_;
	private Timer jt_timer_;
	private JScrollPane sp_current_places_;

	private JButton btn_del_place_;
	private JButton btn_add_place_;

	private AdminUser admin_ = null;


	private void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550, 400);
	}

	private void createManagePlaceInterface() {

		content_panel_ = new JPanel(new GridBagLayout());
		GridBagConstraints constraints_ = new GridBagConstraints();


		btn_del_place_ = new JButton("DELETE Place");
		constraints_.fill = GridBagConstraints.HORIZONTAL;
		constraints_.gridx = 2;
		constraints_.gridy = 2;
		constraints_.insets = new Insets(0, 0, 10, 10);
		content_panel_.add(btn_del_place_, constraints_);

		btn_del_place_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        int index = jt_current_places_.getSelectedRow();
        List<Place> places_in_system = GetPlace.getPlacesAll();
        if (index < places_in_system.size() && index >= 0) {
          Place place_to_delete = places_in_system.get(index);
          //place_to_delete.deleteFromDB();
          //TODO: delete Place
        }

			}

		});

    btn_add_place_ = new JButton("CREATE Place");
		constraints_.fill = GridBagConstraints.HORIZONTAL;
		constraints_.gridx = 2;
		constraints_.gridy = 3;
		constraints_.insets = new Insets(0, 0, 10, 10);
		content_panel_.add(btn_add_place_, constraints_);

		btn_add_place_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        FCreatePlace f_create_place = new FCreatePlace();
        f_create_place.setVisible(true);
			}

		});



		jt_current_places_ = new JTable();
		jtm_current_places_ = (DefaultTableModel) jt_current_places_.getModel();
		jtm_current_places_.addColumn("Room Nr");
    jtm_current_places_.addColumn("Capacity");
	  jt_timer_ = new Timer(0, new ActionListener() {

		   @Override
		   public void actionPerformed(ActionEvent e) {
		      fillTable();
		   }
		});

		jt_timer_.setDelay(300); // delay for .3 seconds
		jt_timer_.start();

		sp_current_places_ = new JScrollPane(jt_current_places_);
		sp_current_places_ = new JScrollPane(jt_current_places_, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		constraints_.fill = GridBagConstraints.HORIZONTAL;
		constraints_.gridx = 0;
		constraints_.gridy = 2;
		constraints_.gridwidth = 2;
		constraints_.gridheight = 4;
		constraints_.ipadx = 340;
		constraints_.ipady = 260;
		constraints_.insets = new Insets(0, 0, 0, 10);

		content_panel_.add(sp_current_places_, constraints_);
		add(content_panel_);
	}

	private void fillTable()
  {

		int index = jt_current_places_.getSelectedRow();
    List<Place> places_in_system = GetPlace.getPlacesAll();
    jtm_current_places_.setRowCount(0);
    for (Place place : places_in_system) {
        String[] data = new String[3];
        data[0] = String.valueOf(place.getRoomNr());
        data[1] = String.valueOf(place.getCapacity());
        jtm_current_places_.addRow(data);
    }
    jtm_current_places_.fireTableDataChanged();
    if (index < jt_current_places_.getRowCount() && index != -1) {
      jt_current_places_.setRowSelectionInterval(index, index);
    }


  }


	public FManagePlace(AdminUser admin) {
		init();
		setTitle("IntelliCourse - Manage Places's - Admin");
		admin_ = admin;
		createManagePlaceInterface();
	}
}
