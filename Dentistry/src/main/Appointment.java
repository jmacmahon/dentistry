package main;

import java.time.LocalDateTime;

public interface Appointment {
	public LocalDateTime getStartTime();
	
	public LocalDateTime getEndTime();
	
	public int getDurationMinutes();
}
