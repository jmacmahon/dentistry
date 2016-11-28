package views;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.HealthcarePlan;
import model.Patient;
import model.Treatment;
import model.Treatment.TreatmentType;

public class Checkout extends ViewComponent {
	private List<Treatment> standardTreatments;
	private List<Treatment> checkUpTreatments;
	private List<Treatment> hygieneVisitTreatments;
	private List<Treatment> repairWorkTreatments;
	private Patient patient;

	public Checkout(List<Treatment> treatments, Patient patient) {
		super();
		this.patient = patient;
		this.standardTreatments = new Vector<>();
		this.checkUpTreatments = new Vector<>();
		this.hygieneVisitTreatments = new Vector<>();
		this.repairWorkTreatments = new Vector<>();

		HealthcarePlan plan = patient.getPlan();

		// This logic should be in the controller but it's near the deadline
		// and I don't have time to refactor it.
		for (Treatment treatment : treatments) {
			if (treatment.getType() == TreatmentType.CHECK_UP
					&& plan.getUsedCheckUps() < plan.getMaxCheckUps()) {
				checkUpTreatments.add(treatment);
			} else if (treatment.getType() == TreatmentType.HYGIENE_VISIT
					&& plan.getUsedHygieneVisits() < plan.getMaxHygieneVisits()) {
				hygieneVisitTreatments.add(treatment);
			} else if (treatment.getType() == TreatmentType.REPAIR_WORK
					&& plan.getUsedRepairWork() < plan.getMaxRepairWork()) {
				repairWorkTreatments.add(treatment);
			} else {
				standardTreatments.add(treatment);
			}
		}
	}

	private JPanel getTotal() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("Total:"));

		int total = 0;
		for (Treatment treatment : this.standardTreatments) {
			total += treatment.getCost();
		}

		double totalInPounds = total / 100.0;
		panel.add(new JLabel(String.format("£%.2f", totalInPounds)));
		return panel;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Standard price treatments"));
		for (Treatment treatment : this.standardTreatments) {
			panel.add((new TreatmentDetail(treatment, this.patient)).getPanel());
			panel.add(new JSeparator(JSeparator.HORIZONTAL));
		}
		panel.add(new JSeparator(JSeparator.HORIZONTAL));

		panel.add(new JLabel("Treatments included in healthcare plan"));
		for (Treatment treatment : this.checkUpTreatments) {
			panel.add((new TreatmentDetail(treatment, this.patient)).getPanel());
			panel.add(new JSeparator(JSeparator.HORIZONTAL));
		}
		for (Treatment treatment : this.hygieneVisitTreatments) {
			panel.add((new TreatmentDetail(treatment, this.patient)).getPanel());
			panel.add(new JSeparator(JSeparator.HORIZONTAL));
		}
		for (Treatment treatment : this.repairWorkTreatments) {
			panel.add((new TreatmentDetail(treatment, this.patient)).getPanel());
			panel.add(new JSeparator(JSeparator.HORIZONTAL));
		}
		panel.add(new JSeparator(JSeparator.HORIZONTAL));

		panel.add(this.getTotal());
		panel.add((new ReceiptButton()).getPanel());
		return panel;
	}

	private class TreatmentDetail extends ViewComponent {
		private Treatment treatment;
		//		private PatientInterface patient;

		public TreatmentDetail(Treatment treatment, Patient patient) {
			this.treatment = treatment;
			//			this.patient = patient;
		}

		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2));
			panel.add(new JLabel("Treatment name:"));
			panel.add(new JLabel(this.treatment.getName()));

			panel.add(new JLabel("Treatment type:"));
			panel.add(new JLabel(this.treatment.getType().toString()));

			panel.add(new JLabel("Treatment cost:"));
			double costInPounds = this.treatment.getCost() / 100.0;
			panel.add(new JLabel(String.format("£%.2f", costInPounds)));
			return panel;
		}
	}

	private class ReceiptButton extends ViewComponent {
		//		private Receipt receipt;
		//
		//		public ReceiptButton(Receipt receipt) {
		//			this.receipt = receipt;
		//		}

		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			JButton save = new JButton("Save receipt");
			save.addActionListener(e -> {
				// TODO generate + save receipt
				JOptionPane.showMessageDialog(save, "Not yet implemented", "Not yet implemented", JOptionPane.ERROR_MESSAGE);
			});
			panel.add(save);
			return panel;
		}
	}
}
