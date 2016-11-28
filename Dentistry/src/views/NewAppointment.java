package views;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import controller.DataException;
import model.Partner;
import model.Patient;

public class NewAppointment extends ViewComponent {

	@Override
	public JPanel getPanel() {
		List<Partner> partners = Partner.getAllPartners();
		List<Patient> patients = Patient.getAllPatients();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		panel.add(new JLabel("Patient:"));
		JComboBox<Patient> patientBox = new JComboBox<Patient>(new Vector<>(patients));
		panel.add(patientBox);

		panel.add(new JLabel("Partner:"));
		JComboBox<Partner> partnerBox = new JComboBox<Partner>(new Vector<>(partners));
		panel.add(partnerBox);

		panel.add(new JLabel("Date:"));
		DateField date = new DateField(2015, 2017);
		panel.add(date.getPanel());

		panel.add(new JLabel("Time:"));
		TimeField time = new TimeField();
		panel.add(time.getPanel());

		panel.add(new JLabel("Duration:"));
		Vector<Integer> durationOptions = new Vector<>();
		for (int i = 5; i <= 120; i += 5) {
			durationOptions.add(i);
		}
		JComboBox<Integer> durationBox = new JComboBox<>(durationOptions);
		panel.add(durationBox);

		JButton book = new JButton("Book appointment");
		book.addActionListener(e -> {
			try {
				Controller.newAppointment((Patient)patientBox.getSelectedItem(), (Partner)partnerBox.getSelectedItem(),
						date.getDate(), time.getTime(), (Integer)durationBox.getSelectedItem());
			} catch (DataException exc) {
				JOptionPane.showMessageDialog(book, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(book);

		return panel;
	}
}
