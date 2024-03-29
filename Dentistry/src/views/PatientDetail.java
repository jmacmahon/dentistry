package views;

import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Address;
import model.Patient;

public class PatientDetail extends ViewComponent {
	private Patient patient;

	public PatientDetail(Patient patient) {
		super();
		this.patient = patient;
	}

	private JPanel getLeftPanel() {
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(0, 2));
		leftPanel.add(new JLabel("Title:"));
		leftPanel.add(new JLabel(this.patient.getTitle()));

		leftPanel.add(new JLabel("Name:"));
		leftPanel.add(new JLabel(this.patient.toString()));

		leftPanel.add(new JLabel("DOB:"));
		leftPanel.add(new JLabel(this.patient.getDateOfBirth().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))));

		leftPanel.add(new JLabel("Contact #:"));
		leftPanel.add(new JLabel(this.patient.getContactNumber()));

		return leftPanel;
	}

	private JPanel getRightPanel() {
		JPanel rightPanel = new JPanel();
		rightPanel.add(new JLabel("Address:"));
		Address address = this.patient.getAddress();
		rightPanel.add(new JLabel(
				"<html>" + address.getHouseNumber() + " " + address.getStreetName()
				+ "<br />" + address.getDistrictName()
				+ "<br />" + address.getCityName()
				+ "<br />" + address.getPostcode()
				+ "</html>"));
		return rightPanel;
	}

	private JPanel getButtons() {
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

		JButton healthcarePlan = new JButton("Healthcare plan details");
		healthcarePlan.addActionListener(e -> {
			ViewComponent.spawnInFrame(new HealthcarePlanDetail(patient), "Healthcare Plan");
		});
		buttons.add(healthcarePlan);

		return buttons;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 3));

		panel.add(this.getLeftPanel());
		panel.add(this.getRightPanel());
		panel.add(this.getButtons());
		return panel;
	}
}