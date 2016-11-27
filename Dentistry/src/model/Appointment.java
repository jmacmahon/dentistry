package model;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Appointment {
	private LocalDateTime startTime;
	private int duration;
	private Patient patient;
	private String partnerName;
	private int id;
	private List<Treatment> treatments;

	public Appointment(LocalDateTime startTime, int duration, Patient patient, String partnerName, int id,
			List<Treatment> treatments) {
		super();
		this.startTime = startTime;
		this.duration = duration;
		this.patient = patient;
		this.partnerName = partnerName;
		this.id = id;
		this.treatments = treatments;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public int getDuration() {
		return duration;
	}
	public Patient getPatient() {
		return patient;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public int getId() {
		return id;
	}
	public List<Treatment> getTreatments() {
		return treatments;
	}

	public abstract LocalDateTime getEndTime();
	public abstract String getPatientName();

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
