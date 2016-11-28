package views;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import main.Config;
import model.Appointment;

public class DiaryDay extends ViewComponent {
	private HashMap<DayOfWeek, Day> days;

	public static DiaryDay single(int partnerId) {
		LocalDate thisDay = LocalDate.now();
		System.out.println(thisDay);
		LocalDate nextDay = LocalDate.now().plusDays(1);
		System.out.println(nextDay);
		return new DiaryDay(
				Appointment.getPartnerAppointments(
						thisDay.atStartOfDay(),
						nextDay.atStartOfDay(),
						partnerId),
				thisDay);
	}



	public DiaryDay(Iterable<Appointment> appointments, LocalDate weekStartDate) {
		super();
		// We shouldn't need to do this but I'll leave it just in case
		// startDate = startDate.with(TemporalAdjusters.previousOrSame(Config.WORKING_WEEK[0]));
		LocalDate weekEndDate = weekStartDate.plusDays(1);
		this.days = new HashMap<DayOfWeek, Day>();

		// Populate the hashmap with empty days
		for (DayOfWeek dayOfWeek : Config.WORKING_WEEK_DAYS) {
			Day day = new Day(
					new Vector<Appointment>(),
					weekStartDate.with(TemporalAdjusters.nextOrSame(dayOfWeek))
					);
			this.days.put(dayOfWeek, day);
		}

		// Sort the appointments into days
		for (Appointment appointment : appointments) {
			LocalDateTime startTime = appointment.getStartTime();
			DayOfWeek apptDay = startTime.getDayOfWeek();
			if (startTime.isAfter(weekStartDate.atTime(0, 0))
					&& startTime.isBefore(weekEndDate.atTime(23, 59))) {
				if (!this.days.containsKey(appointment.getStartTime().getDayOfWeek())) {
					this.days.put(apptDay, new Day(new Vector<Appointment>(), startTime.toLocalDate()));
				}
				this.days.get(apptDay).addAppointment(appointment);
			}
		}
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(this.days.get(LocalDate.now().getDayOfWeek()).getPanel());
		return panel;
	}

	private class Day extends ViewComponent {
		private Vector<Appointment> appointments;
		private LocalDate date;

		public Day(Vector<Appointment> appointments, LocalDate date) {
			this.appointments = appointments;
			this.date = date;
		}

		public void addAppointment(Appointment appointment) {
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
			for (Appointment appointment : appointments) {
				panel.add((new AppointmentComponent(appointment)).getPanel());
			}
			return panel;
		}
	}

	private class AppointmentComponent extends ViewComponent {
		private Appointment appointment;

		public AppointmentComponent(Appointment appointment) {
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
			panel.add(new JLabel("Partner: " + appointment.getPartner().toString()));
			panel.add((new AppointmentButtons(this.appointment)).getPanel());
			panel.add(new JSeparator(SwingConstants.HORIZONTAL));
			return panel;
		}
	}

	private class AppointmentButtons extends ViewComponent {
		private Appointment appointment;

		public AppointmentButtons(Appointment appointment) {
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
