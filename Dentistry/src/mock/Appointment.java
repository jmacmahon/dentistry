package mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import main.AppointmentInterface;
import main.PatientInterface;
import main.TreatmentInterface;

public class Appointment implements AppointmentInterface {
	private static final int[] t1 = {1, 2};
	private static final int[] t2 = {0};
	private static final int[] t3 = {0, 2, 3};

	public static final Appointment[] MOCK_DATA = {
			new Appointment(LocalDateTime.of(2016, 11, 21, 13, 0), 20, "Joe Bloggs", "Dentist", t1),
			new Appointment(LocalDateTime.of(2016, 11, 21, 13, 20), 20, "Laura Phipps", "Dentist", t1),
			new Appointment(LocalDateTime.of(2016, 11, 22, 9, 30), 20, "Eliza Juniper", "Hygienist", t2),
			new Appointment(LocalDateTime.of(2016, 11, 22, 15, 0), 45, "Mary O'Kane", "Dentist", t3),
			new Appointment(LocalDateTime.of(2016, 11, 23, 10, 20), 30, "Brad Chapman", "Hygienist", t1),
	};

	private LocalDateTime startTime;
	private int duration;
	private String patientName;
	private String partnerName;
	private Vector<TreatmentInterface> treatments;

	public Appointment(LocalDateTime startTime, int duration, String patientName, String partnerName, int[] treatments) {
		this.startTime = startTime;
		this.duration = duration;
		this.patientName = patientName;
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
		return this.patientName;
	}

	@Override
	public String getID() {
		return this.startTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")) + " " + this.patientName + " with " + this.partnerName;
	}

	@Override
	public PatientInterface getPatient() {
		return new PatientInterface() {
		};
	}
}
