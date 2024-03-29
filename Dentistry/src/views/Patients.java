package views;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Patient;

public class Patients extends ViewComponent {
	private List<Patient> patients;
	private JPanel patientList;

	public static Patients auto() {
		List<Patient> patients = Patient.getAllPatients();
		return new Patients(patients);
	}

	public Patients(List<Patient> patients) {
		super();
		this.patients = patients;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add((new Buttons()).getPanel());
		panel.add(new SearchText().getPanel());
		panel.add(new SearchButton().getPanel());
		this.patientList = new JPanel();
		this.patientList.setLayout(new BoxLayout(this.patientList, BoxLayout.PAGE_AXIS));
		refreshPatientList(this.patients);

		JScrollPane patientListScroll = new JScrollPane(this.patientList);
		patientListScroll.setPreferredSize(this.patientList.getPreferredSize());

		panel.add(patientListScroll);
		return panel;
	}

	private void refreshPatientList(List<Patient> patients) {
		this.patientList.removeAll();
		for (Patient patient : patients) {
			// TODO maybe scrolling?
			this.patientList.add(new JSeparator(JSeparator.HORIZONTAL));
			this.patientList.add((new PatientDetail(patient)).getPanel());
		}
		this.patientList.revalidate();
		this.patientList.repaint();
	}

	private class Buttons extends ViewComponent {
		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
			JButton newPatient = new JButton("Register new patient");
			newPatient.addActionListener(e -> {
				ViewComponent.spawnInFrame(new NewPatient(), "New Patient");
			});
			panel.add(newPatient);
			return panel;
		}
	}

	private JTextField forenameField;
	private JTextField surnameField;
	private JTextField houseNumberField;
	private JTextField postcodeField;

	private class SearchText extends ViewComponent {
		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0,2));

			forenameField = new JTextField();
			surnameField = new JTextField();
			houseNumberField = new JTextField();
			postcodeField = new JTextField();

			panel.add(new JLabel("Forename"));
			panel.add(forenameField);

			panel.add(new JLabel("Surname"));
			panel.add(surnameField);

			panel.add(new JLabel("House Number: "));
			panel.add(houseNumberField);

			panel.add(new JLabel("Postcode: "));
			panel.add(postcodeField);

			return panel;
		}
	}

	private class SearchButton extends ViewComponent {
		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			JButton search = new JButton("Search patients");

			search.addActionListener(e -> {
				if (houseNumberField != null && postcodeField != null) {
					String houseNumberText = houseNumberField.getText();
					String postcodeText = postcodeField.getText();
					String forenameText = forenameField.getText();
					String surnameText = surnameField.getText();
					Integer houseNumber;
					try {
						houseNumber = Integer.parseInt(houseNumberText);
					} catch (NumberFormatException exc) {
						houseNumber = null;
					}
					List<Patient> filteredPatients = Patient.search(forenameText, surnameText, houseNumber, postcodeText);
					refreshPatientList(filteredPatients);
				}
			});

			panel.add(search);
			return panel;
		}
	}
}
