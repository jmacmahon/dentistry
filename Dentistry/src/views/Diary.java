package views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import main.AppointmentInterface;
import main.Config;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Vector;

public class Diary implements ViewComponent {
	private HashMap<DayOfWeek, Day> days;

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

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		for (DayOfWeek dayOfWeek : Config.WORKING_WEEK_DAYS) {
			panel.add(this.days.get(dayOfWeek).getPanel());
		}
		return panel;
	}

	private class Day implements ViewComponent {
		private Vector<AppointmentInterface> appointments;
		private LocalDate date;

		public Day(Vector<AppointmentInterface> appointments, LocalDate date) {
			this.appointments = appointments;
			this.date = date;
		}

		public void addAppointment(AppointmentInterface appointment) {
			this.appointments.add(appointment);
		}

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
	
	private class AppointmentComponent implements ViewComponent {
		private AppointmentInterface appointment;

		public AppointmentComponent(AppointmentInterface appointment) {
			this.appointment = appointment;
		}

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

	private class AppointmentButtons implements ViewComponent {
		private AppointmentInterface appointment;

		public AppointmentButtons(AppointmentInterface appointment) {
			this.appointment = appointment;
		}

		public JPanel getPanel() {
			JPanel panel = new JPanel();
			JButton delete = new JButton("Delete");
			delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(
							panel,
							"Are you sure you want to delete this appointment?",
							"Confirm deletion",
							JOptionPane.YES_NO_OPTION
					);
					if (confirm == 0) {
						// Notify the controller
						System.out.println("Deleting appointment with id: " + appointment.getID());
					}
				}
			});
			panel.add(delete);
			return panel;
		}
	}
}
