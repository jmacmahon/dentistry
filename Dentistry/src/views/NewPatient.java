package views;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewPatient implements ViewComponent {

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
		JTextField postCode = new JTextField();
		panel.add(postCode);

		panel.add(new JPanel());

		JButton save = new JButton("Validate and save");
		save.addActionListener(e -> {
			// TODO
		});
		panel.add(save);

		return panel;
	}

	private static class DateField implements ViewComponent {
		private static final Integer[] DAYS = IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new);
		private static final String[] MONTHS = {"January", "February",
				"March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December"};
		private static final Integer[] YEARS = IntStream.rangeClosed(1900, 2016).boxed().toArray(Integer[]::new);

		private JComboBox<Integer> day;
		private JComboBox<String> month;
		private JComboBox<Integer> year;

		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

			this.day = new JComboBox<>(DAYS);
			this.month = new JComboBox<>(MONTHS);
			this.year = new JComboBox<>(YEARS);

			panel.add(day);
			panel.add(month);
			panel.add(year);
			return panel;
		}

		public int getDay() {
			return ((Integer)this.day.getSelectedItem()).intValue();
		}

		public int getMonth() {
			return this.month.getSelectedIndex() + 1;
		}

		public int getYear() {
			return ((Integer)this.year.getSelectedItem()).intValue();
		}

		public LocalDate getDate() {
			return LocalDate.of(this.getYear(), this.getMonth(), this.getDay());
		}
	}
}
