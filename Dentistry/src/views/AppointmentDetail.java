package views;

import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import controller.Controller;
import model.Appointment;

public class AppointmentDetail extends ViewComponent {
	private Appointment appointment;

	public AppointmentDetail(Appointment appointment) {
		super();
		this.appointment = appointment;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	private JPanel getPatientDetails() {
		JPanel patientDetails = new JPanel();
		patientDetails.setLayout(new GridLayout(0, 2));
		patientDetails.add(new JLabel("Patient"));
		patientDetails.add((new PatientDetail(this.appointment.getPatient())).getPanel());

		return patientDetails;
	}

	private JPanel getPartnerDetails() {
		JPanel partnerDetails = new JPanel();
		partnerDetails.setLayout(new GridLayout(0, 2));
		partnerDetails.add(new JLabel("Partner"));
		partnerDetails.add(new JLabel(this.appointment.getPartnerName()));

		return partnerDetails;
	}

	private JPanel getDateTimeDetails() {
		JPanel dateTimeDetails = new JPanel();
		dateTimeDetails.setLayout(new GridLayout(0, 2));
		dateTimeDetails.add(new JLabel("Date & Time"));
		dateTimeDetails.add(new JLabel(this.appointment.getStartTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm (EEE)"))));

		return dateTimeDetails;
	}

	private JPanel getDurationDetails() {
		JPanel partnerDetails = new JPanel();
		partnerDetails.setLayout(new GridLayout(0, 2));
		partnerDetails.add(new JLabel("Duration"));
		partnerDetails.add(new JLabel(Integer.toString(this.appointment.getDurationMinutes()) + "m"));

		return partnerDetails;
	}

	private JPanel getDetails() {
		JPanel innerDetails = new JPanel();
		innerDetails.setLayout(new BoxLayout(innerDetails, BoxLayout.PAGE_AXIS));
		innerDetails.add(this.getPatientDetails());
		innerDetails.add(new JSeparator(JSeparator.HORIZONTAL));

		innerDetails.add(this.getPartnerDetails());
		innerDetails.add(new JSeparator(JSeparator.HORIZONTAL));

		innerDetails.add(this.getDateTimeDetails());
		innerDetails.add(new JSeparator(JSeparator.HORIZONTAL));

		innerDetails.add(this.getDurationDetails());
		innerDetails.add(new JSeparator(JSeparator.HORIZONTAL));
		return innerDetails;
	}

	private JPanel getButtons() {
		JButton delete = new JButton("Delete");

		// TODO disabled if appt not marked as completed
		JButton checkOut = new JButton("Check out");

		delete.addActionListener(e -> {
			int confirmation = JOptionPane.showConfirmDialog(
					delete,
					"Are you sure you want to delete this appointment?",
					"Confirm delete",
					JOptionPane.YES_NO_OPTION
					);
			if (confirmation == 0) {
				Controller.deleteAppointment(appointment);
			}
		});

		checkOut.addActionListener(e -> {
			ViewComponent.spawnInFrame(new Checkout(appointment.getTreatments(), appointment.getPatient()), "Checkout");
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
