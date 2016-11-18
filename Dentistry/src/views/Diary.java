package views;

import javax.swing.JPanel;

import main.Appointment;
import main.Config;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Vector;

public class Diary implements View {
	private JPanel[] panels;

	public Diary(Iterable<Appointment> appointments, LocalDate weekStartDate) {
		// We shouldn't need to do this but I'll leave it just in case
//		startDate = startDate.with(TemporalAdjusters.previousOrSame(Config.WORKING_WEEK_START));
		LocalDate weekEndDate = weekStartDate.plusDays(Config.WORKING_WEEK_DAYS);
		HashMap<DayOfWeek, Vector<Appointment>> apptsByDay = new HashMap<DayOfWeek, Vector<Appointment>>();
		
		// Sort the appointments into days
		for (Appointment appointment : appointments) {
			LocalDateTime startTime = appointment.getStartTime();
			DayOfWeek apptDay = startTime.getDayOfWeek();
			if (startTime.isAfter(weekStartDate.atTime(0, 0))
					&& startTime.isBefore(weekEndDate.atTime(23, 59))) {
				if (!apptsByDay.containsKey(appointment.getStartTime().getDayOfWeek())) {
					apptsByDay.put(apptDay, new Vector<Appointment>());
				}
				apptsByDay.get(apptDay).add(appointment);
			}
		}
		
		this.panels = new JPanel[Config.WORKING_WEEK_DAYS];
		// Populate with empty JPanels -- cannot do this in a for-each loop
		for (int i = 0; i < this.panels.length; i++) {
			this.panels[i] = new JPanel();
		}

		LocalDate iter = weekStartDate;
		for (JPanel dayPanel : this.panels) {
			dayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));
			dayPanel.add(new JLabel(iter.format(DateTimeFormatter.ofPattern("EEEE"))));
			dayPanel.add(new JLabel(iter.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));
			iter = iter.plusDays(1);	
		}
	}

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		for (JPanel dayPanel : this.panels) {
			panel.add(dayPanel);
		}
		return panel;
	}
	
	private class Day implements View {
		public Day(Vector<Appointment> appointments, LocalDate date) {
		
		}
		
		public JPanel getPanel() {
			return null;
		}
	}
}
