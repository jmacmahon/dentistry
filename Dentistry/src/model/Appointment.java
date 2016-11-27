package model;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Appointment {
	public abstract LocalDateTime getStartTime();

	public abstract LocalDateTime getEndTime();

	public abstract int getDurationMinutes();

	public abstract String getPatientName();

	public abstract String getPartnerName();

	public abstract String getID();

	public abstract List<Treatment> getTreatments();

	public abstract Patient getPatient();

	@SuppressWarnings("unchecked")
	public static List<Appointment> getAppointments(LocalDateTime from, LocalDateTime to) {
		return (List<Appointment>)(List<?>) mock.Appointment.getAppointments();
	}

	@SuppressWarnings("unchecked")
	public static List<Appointment> getAllAppointments() {
		return (List<Appointment>)(List<?>) mock.Appointment.getAppointments();
	}

	public static void delete(Appointment appointment) {
		mock.Appointment.deleted.add((mock.Appointment) appointment);
	}
}
