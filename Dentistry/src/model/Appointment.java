package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import main.Config;

public class Appointment {
	private LocalDateTime startTime;
	private int duration;
	private Patient patient;
	private Partner partner;
	private int id;

	public Appointment(int id, LocalDateTime startTime, int duration, Patient patient, Partner partner) {
		super();
		this.startTime = startTime;
		this.duration = duration;
		this.patient = patient;
		this.partner = partner;
		this.id = id;
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
	public Partner getPartner() {
		return partner;
	}
	public int getId() {
		return id;
	}
	public List<Treatment> getTreatments() {
		if (Config.MOCK) {
			return new Vector<>();
		} else {
			try {
				ResultSet results = model.db.Queries.getTreatmentsForAppointment(this.getId());
				Vector<Treatment> treatments = new Vector<>();
				while (results.next()) {
					treatments.add(Treatment.fromResultSet(results, results.getInt("treatmentId")));
				}
				return treatments;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public LocalDateTime getEndTime() {
		return this.getStartTime().plusMinutes(this.getDuration());
	}
	public String getPatientName() {
		return this.getPatient().getSurname() + ", " + this.getPatient().getForename();
	}

	@SuppressWarnings("unchecked")
	public static List<Appointment> getAppointments(LocalDateTime from, LocalDateTime to) {
		if (Config.MOCK) {
			return (List<Appointment>)(List<?>) mock.Appointment.getAppointments();
		} else {
			try {
				ResultSet results = model.db.Queries.getAppointments(from, to);
				Vector<Appointment> appointments = new Vector<>();
				while (results.next()) {
					appointments.add(fromResultSet(results));
				}
				return appointments;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

	public static void delete(Appointment appointment) {
		if (Config.MOCK) {
			mock.Appointment.deleted.add((mock.Appointment) appointment);
		} else {
			try {
				model.db.Queries.deleteAppointment(appointment.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Appointment fromResultSet(ResultSet result) {
		try {
			return new Appointment(
					result.getInt("appointment.id"),
					result.getTimestamp("appointment.date").toLocalDateTime(),
					result.getInt("appointment.duration"),
					Patient.fromResultSet(result, result.getInt("appointment.patientId")),
					Partner.fromResultSet(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static int add(int patientId, int partnerId, LocalDateTime date, int duration) {
		try {
			ResultSet result = model.db.Queries.addAppointment(patientId, partnerId, date, duration);
			result.next();
			return result.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
