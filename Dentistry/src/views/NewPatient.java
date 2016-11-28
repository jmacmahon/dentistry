package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class NewPatient extends ViewComponent {
	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		panel.add(new JLabel("Title:"));
		JTextField title = new JTextField();
		panel.add(title);

		panel.add(new JLabel("Forename:"));
		JTextField forename = new JTextField();
		panel.add(forename);

		panel.add(new JLabel("Surname:"));
		JTextField surname = new JTextField();
		panel.add(surname);

		panel.add(new JLabel("Contact #:"));
		JTextField contactNumber = new JTextField();
		panel.add(contactNumber);

		panel.add(new JLabel("DOB:"));
		DateField dateOfBirth = new DateField();
		panel.add(dateOfBirth.getPanel());

		panel.add(new JLabel("House number:"));
		JTextField houseNumber = new JTextField();
		panel.add(houseNumber);

		panel.add(new JLabel("Street name:"));
		JTextField streetName = new JTextField();
		panel.add(streetName);

		panel.add(new JLabel("District name:"));
		JTextField districtName = new JTextField();
		panel.add(districtName);

		panel.add(new JLabel("City name:"));
		JTextField cityName = new JTextField();
		panel.add(cityName);

		panel.add(new JLabel("Postcode:"));
		JTextField postcode = new JTextField();
		panel.add(postcode);

		panel.add(new JPanel());

		JButton save = new JButton("Validate and save");
		save.addActionListener(e -> {
			int houseNumberInt;
			try {
				houseNumberInt = Integer.parseInt(houseNumber.getText());
			} catch (NumberFormatException exc) {
				JOptionPane.showMessageDialog(panel, "Invalid house number", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Controller.newPatient(title.getText(), forename.getText(), surname.getText(),
					contactNumber.getText(), dateOfBirth.getDate(),
					houseNumberInt, streetName.getText(), districtName.getText(),
					cityName.getText(), postcode.getText());
		});
		panel.add(save);

		return panel;
	}
}
