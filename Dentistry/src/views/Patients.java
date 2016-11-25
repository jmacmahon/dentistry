package views;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.PatientInterface;

public class Patients extends ViewComponent {
		
	
	
	private List<PatientInterface> patients;

	public static Patients auto() {
		List<PatientInterface> patients = PatientInterface.getAllPatients();
		return new Patients(patients);
	}

	public Patients(List<PatientInterface> patients) {
		this.patients = patients;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add((new Buttons()).getPanel());
		panel.add(new SearchText().getPanel());
		panel.add(new SearchButton().getPanel());
		for (PatientInterface patient : this.patients) {
			// TODO maybe scrolling?
			panel.add(new JSeparator(JSeparator.HORIZONTAL));
			panel.add((new PatientDetail(patient)).getPanel());
		}
		return panel;
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
	
	private class SearchText extends ViewComponent {
		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0,2));
			
			JTextField houseNumber = new JTextField("House Number");
			JTextField postcode = new JTextField("Postcode");
			
			panel.add(new JLabel("House Number: "));
			panel.add(houseNumber);
			
			panel.add(new JLabel("Postcode: "));
			panel.add(postcode);
			
			return panel;
			
		}
	}
	
	private class SearchButton extends ViewComponent {
		
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			
			JButton search = new JButton("Search patients");
		
			panel.add(search);
			
			return panel;
		}
	
	}
}
