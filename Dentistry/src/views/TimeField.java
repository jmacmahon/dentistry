package views;

import java.time.LocalTime;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class TimeField extends ViewComponent {
	private static final Integer[] HOUR = IntStream.rangeClosed(0, 24).boxed().toArray(Integer[]::new);
	private static final Integer[] MINUTE = IntStream.rangeClosed(0, 59).boxed().toArray(Integer[]::new);

	private JComboBox<Integer> hour;
	private JComboBox<Integer> minute;

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		this.hour = new JComboBox<>(HOUR);
		this.minute = new JComboBox<>(MINUTE);

		panel.add(hour);
		panel.add(minute);
		return panel;
	}

	public int getHour() {
		return ((Integer)this.hour.getSelectedItem()).intValue();
	}

	public int getMinute() {
		return ((Integer)this.minute.getSelectedItem()).intValue();
	}

	public LocalTime getTime() {
		return LocalTime.of(this.getHour(), this.getMinute());
	}
}
