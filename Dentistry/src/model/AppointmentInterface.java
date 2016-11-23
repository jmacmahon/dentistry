package model;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentInterface {
	public LocalDateTime getStartTime();

	public LocalDateTime getEndTime();

	public int getDurationMinutes();

	public String getPatientName();

	public String getPartnerName();

	public String getID();

	public List<TreatmentInterface> getTreatments();

	public PatientInterface getPatient();
}
