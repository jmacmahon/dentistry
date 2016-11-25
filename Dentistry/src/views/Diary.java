package views;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Vector;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import main.Config;
import model.AppointmentInterface;

public class Diary extends ViewComponent {
	private HashMap<DayOfWeek, Day> days;

	public static Diary auto() {
		LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate nextMonday = thisMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		return new Diary(
				AppointmentInterface.getAppointments(
						thisMonday.atStartOfDay(),
						nextMonday.atStartOfDay()),
				thisMonday);
	}

	public Diary(Iterable<AppointmentInterface> appointments, LocalDate weekStartDate) {
		// We shouldn't need to do this but I'll leave it just in case
		// startDate = startDate.with(TemporalAdjusters.previousOrSame(Config.WORKING_WEEK[0]));
		LocalDate weekEndDate = weekStartDate.plusDays(Config.WORKING_WEEK_DAYS.length);
		this.days = new HashMap<DayOfWeek, Day>();

		// Populate the hashmap with empty days
		for (DayOfWeek dayOfWeek : Config.WORKING_WEEK_DAYS) {
			Day day = new Day(
					new Vector<AppointmentInterface>(),
					weekStartDate.with(TemporalAdjusters.nextOrSame(dayOfWeek))
					);
			this.days.put(dayOfWeek, day);
		}

		// Sort the appointments into days
		for (AppointmentInterface appointment : appointments) {
			LocalDateTime startTime = appointment.getStartTime();
			DayOfWeek apptDay = startTime.getDayOfWeek();
			if (startTime.isAfter(weekStartDate.atTime(0, 0))
					&& startTime.isBefore(weekEndDate.atTime(23, 59))) {
				if (!this.days.containsKey(appointment.getStartTime().getDayOfWeek())) {
					this.days.put(apptDay, new Day(new Vector<AppointmentInterface>(), startTime.toLocalDate()));
				}
				this.days.get(apptDay).addAppointment(appointment);
			}
		}
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		for (DayOfWeek dayOfWeek : Config.WORKING_WEEK_DAYS) {
			panel.add(this.days.get(dayOfWeek).getPanel());
		}
		return panel;
	}

	private class Day extends ViewComponent {
		private Vector<AppointmentInterface> appointments;
		private LocalDate date;

		public Day(Vector<AppointmentInterface> appointments, LocalDate date) {
			this.appointments = appointments;
			this.date = date;
		}

		public void addAppointment(AppointmentInterface appointment) {
			this.appointments.add(appointment);
		}

		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(new JLabel(this.date.format(DateTimeFormatter.ofPattern("EEEE"))));
			panel.add(new JLabel(this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));
			panel.add(new JSeparator(SwingConstants.HORIZONTAL));
			for (AppointmentInterface appointment : appointments) {
				panel.add((new AppointmentComponent(appointment)).getPanel());
			}
			return panel;
		}
	}

	private class AppointmentComponent extends ViewComponent {
		private AppointmentInterface appointment;

		public AppointmentComponent(AppointmentInterface appointment) {
			this.appointment = appointment;
		}

		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(new JLabel(
					this.appointment.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"))
					+ " — "
					+ this.appointment.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"))
					));
			panel.add(new JLabel("Patient: " + this.appointment.getPatientName()));
			panel.add(new JLabel("Partner: " + appointment.getPartnerName()));
			panel.add((new AppointmentButtons(this.appointment)).getPanel());
			panel.add(new JSeparator(SwingConstants.HORIZONTAL));
			return panel;
		}
	}

	private class AppointmentButtons extends ViewComponent {
		private AppointmentInterface appointment;

		public AppointmentButtons(AppointmentInterface appointment) {
			this.appointment = appointment;
		}

		@Override
		public JPanel getPanel() {
			JPanel panel = new JPanel();
			JButton details = new JButton("Details");
			details.addActionListener(e -> {
				ViewComponent.spawnInFrame(new AppointmentDetail(appointment), "Appointment");
			});
			panel.add(details);
			return panel;
		}
	}
}
