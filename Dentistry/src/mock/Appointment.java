package mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.AppointmentInterface;

public class Appointment implements AppointmentInterface {
	public static final Appointment[] MOCK_DATA = {
		new Appointment(LocalDateTime.of(2016, 11, 21, 13, 0), 20, "Joe Bloggs", "Dentist"),
		new Appointment(LocalDateTime.of(2016, 11, 21, 13, 20), 20, "Laura Phipps", "Dentist"),
		new Appointment(LocalDateTime.of(2016, 11, 22, 9, 30), 20, "Eliza Juniper", "Hygienist"),
		new Appointment(LocalDateTime.of(2016, 11, 22, 15, 0), 45, "Mary O'Kane", "Dentist"),
		new Appointment(LocalDateTime.of(2016, 11, 23, 10, 20), 30, "Brad Chapman", "Hygienist"),
	};

	private LocalDateTime startTime;
	private int duration;
	private String patientName;
	private String partnerName;

	public Appointment(LocalDateTime startTime, int duration, String patientName, String partnerName) {
		this.startTime = startTime;
		this.duration = duration;
		this.patientName = patientName;
		this.partnerName = partnerName;
	}

	@Override
	public LocalDateTime getStartTime() {
		return startTime;
	}

	@Override
	public LocalDateTime getEndTime() {
		return startTime.plusMinutes(this.duration);
	}

	@Override
	public int getDurationMinutes() {
		return this.duration;
	}

	@Override
	public String getPartnerName() {
		return this.partnerName;
	}

	@Override
	public String getPatientName() {
		return this.patientName;
	}

	@Override
	public String getID() {
		return this.startTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")) + " " + this.patientName + " with " + this.partnerName;
	}
}
