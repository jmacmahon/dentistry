package views;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.Patient;
import model.Treatment;

public class Checkout extends ViewComponent {
	private List<Treatment> treatments;
	private Patient patient;

	public Checkout(List<Treatment> treatments, Patient patient) {
		this.treatments = treatments;
		this.patient = patient;
	}

	private JPanel getTotal() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("Total:"));

		int total = 0;
		for (Treatment treatment : this.treatments) {
			total += treatment.getCost();
		}

		double totalInPounds = total / 100.0;
		panel.add(new JLabel(String.format("£%.2f", totalInPounds)));
		return panel;
	}

	@Override
	public JPanel getPanel() {
		// TODO subscriptions & plans & stuff

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		for (Treatment treatment : this.treatments) {
			panel.add((new TreatmentDetail(treatment, this.patient)).getPanel());
			panel.add(new JSeparator(JSeparator.HORIZONTAL));
		}
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
			panel.add(new JLabel("not yet implemented"));
			//			panel.add(new JLabel(this.treatment.getType()));

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
