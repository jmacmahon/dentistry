package views;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.HealthcarePlanType;
import model.Patient;

public class NewPlan extends ViewComponent {
	private Patient patient;
	public NewPlan(Patient patient) {
		this.patient = patient;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		panel.add(new JLabel("Patient:"));
		panel.add(new JLabel(this.patient.toString()));

		panel.add(new JLabel("Plan:"));
		Vector<HealthcarePlanType> plans = new Vector<>(HealthcarePlanType.getAllHealthcarePlans());
		JComboBox<HealthcarePlanType> planBox = new JComboBox<>(plans);
		panel.add(planBox);

		JButton subscribe = new JButton("Subscribe to selected plan");
		subscribe.addActionListener(e -> {
			Controller.addPlan(this.patient, (HealthcarePlanType)planBox.getSelectedItem());
		});
		panel.add(subscribe);

		return panel;
	}

}
