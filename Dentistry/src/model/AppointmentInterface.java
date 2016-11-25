package model;

import java.time.LocalDateTime;
import java.util.List;

import mock.Appointment;

public interface AppointmentInterface {
	public LocalDateTime getStartTime();

	public LocalDateTime getEndTime();

	public int getDurationMinutes();

	public String getPatientName();

	public String getPartnerName();

	public String getID();

	public List<TreatmentInterface> getTreatments();

	public PatientInterface getPatient();

	public static List<AppointmentInterface> getAppointments(LocalDateTime from, LocalDateTime to) {
		return Appointment.getAppointments();
	}

	public static List<AppointmentInterface> getAllAppointments() {
		return Appointment.getAppointments();
	}

	public static void delete(AppointmentInterface appointment) {
		Appointment.deleted.add(appointment);
	}
}
