package model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

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
		return new Vector<AppointmentInterface>(Arrays.asList(Appointment.MOCK_DATA));
	}

	public static List<AppointmentInterface> getAllAppointments() {
		return new Vector<AppointmentInterface>(Arrays.asList(Appointment.MOCK_DATA));
	}
}
