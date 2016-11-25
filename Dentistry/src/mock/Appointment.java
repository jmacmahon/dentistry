package mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import model.AppointmentInterface;
import model.PatientInterface;
import model.TreatmentInterface;

public class Appointment implements AppointmentInterface {
	private static final int[] t1 = {1, 2};
	private static final int[] t2 = {0};
	private static final int[] t3 = {0, 2, 3};

	public static final Appointment[] MOCK_DATA = {
			new Appointment(LocalDateTime.of(2016, 11, 21, 13, 0), 20, Patient.MOCK_DATA[0], "Dentist", t1),
			new Appointment(LocalDateTime.of(2016, 11, 21, 13, 20), 20, Patient.MOCK_DATA[1], "Dentist", t1),
			new Appointment(LocalDateTime.of(2016, 11, 22, 9, 30), 20, Patient.MOCK_DATA[2], "Hygienist", t2),
			new Appointment(LocalDateTime.of(2016, 11, 22, 15, 0), 45, Patient.MOCK_DATA[3], "Dentist", t3),
			new Appointment(LocalDateTime.of(2016, 11, 23, 10, 20), 30, Patient.MOCK_DATA[4], "Hygienist", t1),
	};

	public static Set<AppointmentInterface> deleted = new HashSet<>();

	public static List<AppointmentInterface> getAppointments() {
		Vector<AppointmentInterface> appointments = new Vector<>();
		for (Appointment appointment : MOCK_DATA) {
			if (!deleted.contains(appointment)) {
				appointments.add(appointment);
			}
		}
		return appointments;
	}

	private LocalDateTime startTime;
	private int duration;
	private PatientInterface patient;
	private String partnerName;
	private Vector<TreatmentInterface> treatments;

	public Appointment(LocalDateTime startTime, int duration, PatientInterface patient, String partnerName, int[] treatments) {
		this.startTime = startTime;
		this.duration = duration;
		this.patient = patient;
		this.partnerName = partnerName;
		this.treatments = new Vector<>();
		for (int treatment : treatments) {
			this.treatments.add(Treatment.MOCK_DATA[treatment]);
		}
	}

	@Override
	public Vector<TreatmentInterface> getTreatments() {
		return this.treatments;
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
		return this.patient.getSurname() + ", " + this.patient.getForename();
	}

	@Override
	public String getID() {
		return this.startTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")) + " " + this.getPatientName() + " with " + this.partnerName;
	}

	@Override
	public PatientInterface getPatient() {
		return this.patient;
	}
}
