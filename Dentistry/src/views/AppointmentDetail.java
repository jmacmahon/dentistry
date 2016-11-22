package views;

import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.AppointmentInterface;

public class AppointmentDetail implements ViewComponent {
	AppointmentInterface appointment;

	public AppointmentDetail(AppointmentInterface appointment) {
		this.appointment = appointment;
	}

	private JPanel getDetails() {
		JPanel innerDetails = new JPanel();
		innerDetails.setLayout(new GridLayout(0, 2));
		innerDetails.add(new JLabel("Patient"));
		innerDetails.add(new JLabel(appointment.getPatientName()));

		innerDetails.add(new JLabel("Partner"));
		innerDetails.add(new JLabel(appointment.getPartnerName()));

		innerDetails.add(new JLabel("Date & Time"));
		innerDetails.add(new JLabel(appointment.getStartTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm (EEE)"))));

		innerDetails.add(new JLabel("Duration"));
		innerDetails.add(new JLabel(Integer.toString(appointment.getDurationMinutes()) + "m"));
		return innerDetails;
	}

	private JPanel getButtons() {
		JButton delete = new JButton("Delete");
		JButton checkOut = new JButton("Check out");

		delete.addActionListener(e -> {
			int confirmation = JOptionPane.showConfirmDialog(
					delete,
					"Are you sure you want to delete this appointment?",
					"Confirm delete",
					JOptionPane.YES_NO_OPTION
					);
			if (confirmation == 0) {
				System.out.println("Delete appointment: " + appointment.toString());
			}
		});

		checkOut.addActionListener(e -> {
			// Go to payment view
		});

		JPanel buttons = new JPanel();
		buttons.add(delete);
		buttons.add(checkOut);
		return buttons;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(this.getDetails());
		panel.add(this.getButtons());
		return panel;
	}

}
