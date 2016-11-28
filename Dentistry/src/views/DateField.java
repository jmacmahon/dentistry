package views;

import java.time.LocalDate;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DateField extends ViewComponent {
	private static final Integer[] DAYS = IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new);
	private static final String[] MONTHS = {"January", "February",
			"March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December"};
	private final Integer[] YEARS;

	private JComboBox<Integer> day;
	private JComboBox<String> month;
	private JComboBox<Integer> year;

	public DateField() {
		this(1900, 2016);
	}
	public DateField(int fromYear, int toYear) {
		this.YEARS = IntStream.rangeClosed(fromYear, toYear).boxed().toArray(Integer[]::new);
	}

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
