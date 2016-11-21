package main;

import java.time.LocalDateTime;

public interface AppointmentInterface {
	public LocalDateTime getStartTime();

	public LocalDateTime getEndTime();

	public int getDurationMinutes();

	public String getPatientName();

	public String getPartnerName();

	public String getID();
}
