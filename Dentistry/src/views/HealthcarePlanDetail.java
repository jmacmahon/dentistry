package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.HealthcarePlan;
import model.Patient;

public class HealthcarePlanDetail extends ViewComponent {
	private Patient patient;
	private JPanel wrapper;

	public HealthcarePlanDetail(Patient patient) {
		super();
		this.patient = patient;
	}

	@Override
	public void refresh() {
		this.wrapper.removeAll();
		this.wrapper.add(this.getInner());
		this.wrapper.revalidate();
		this.wrapper.repaint();
	}

	private JPanel getInner() {
		HealthcarePlan plan = this.patient.getPlan();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		panel.add(new JLabel("Patient name:"));
		panel.add(new JLabel(this.patient.getTitle() + " "
				+ this.patient.getSurname() + ", "
				+ this.patient.getForename()));

		panel.add(new JLabel("Plan name:"));
		panel.add(new JLabel(plan.getName()));

		if (!plan.getName().equals("No plan")) {
			panel.add(new JLabel("Used check-ups:"));
			panel.add(new JLabel(plan.getUsedCheckUps() + "/" + plan.getMaxCheckUps()));

			panel.add(new JLabel("Used hygiene visits:"));
			panel.add(new JLabel(plan.getUsedHygieneVisits() + "/" + plan.getMaxHygieneVisits()));

			panel.add(new JLabel("Used repair work:"));
			panel.add(new JLabel(plan.getUsedRepairWork() + "/" + plan.getMaxRepairWork()));
		} else {
			JButton newPlan = new JButton("Subscribe to a plan");
			newPlan.addActionListener(e -> {
				ViewComponent.spawnInFrame(new NewPlan(this.patient), "New plan");
			});
			panel.add(newPlan);
		}
		return panel;
	}

	@Override
	public JPanel getPanel() {
		this.wrapper = new JPanel();
		this.wrapper.add(this.getInner());
		return this.wrapper;
	}
}
