package mock;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import model.Partner;
import model.Patient;
import model.Treatment;

public class Appointment extends model.Appointment {
	private static final int[] t1 = {1, 2};
	private static final int[] t2 = {0};
	private static final int[] t3 = {0, 2, 3};

	public static final Appointment[] MOCK_DATA = {
			Appointment.withTreatmentIndices(LocalDateTime.of(2016, 11, 21, 13, 0), 20, mock.Patient.MOCK_DATA[0], "Dentist", t1),
			Appointment.withTreatmentIndices(LocalDateTime.of(2016, 11, 21, 13, 20), 20, mock.Patient.MOCK_DATA[1], "Dentist", t1),
			Appointment.withTreatmentIndices(LocalDateTime.of(2016, 11, 22, 9, 30), 20, mock.Patient.MOCK_DATA[2], "Hygienist", t2),
			Appointment.withTreatmentIndices(LocalDateTime.of(2016, 11, 22, 15, 0), 45, mock.Patient.MOCK_DATA[3], "Dentist", t3),
			Appointment.withTreatmentIndices(LocalDateTime.of(2016, 11, 23, 10, 20), 30, mock.Patient.MOCK_DATA[4], "Hygienist", t1),
	};

	public static Set<Appointment> deleted = new HashSet<>();

	public static List<Appointment> getAppointments() {
		Vector<Appointment> appointments = new Vector<>();
		for (Appointment appointment : MOCK_DATA) {
			if (!deleted.contains(appointment)) {
				appointments.add(appointment);
			}
		}
		return appointments;
	}

	public static Appointment withTreatmentIndices(LocalDateTime startTime, int duration, Patient patient, String partnerName,
			int[] treatmentsIndices) {
		// TODO This is useless now we don't specify treatments in the constructor
		Vector<Treatment> treatments = new Vector<>();
		for (int treatmentIndex : treatmentsIndices) {
			treatments.add(mock.Treatment.MOCK_DATA[treatmentIndex]);
		}
		return new Appointment(-1, startTime, duration, patient, partnerName);
	}

	public Appointment(int id, LocalDateTime startTime, int duration, Patient patient, String partnerName) {
		super(id, startTime, duration, patient, new Partner(0, "A", partnerName, "Mx"));
	}
}
