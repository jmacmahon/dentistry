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
	private String partnerName;
	private int id;

	public Appointment(int id, LocalDateTime startTime, int duration, Patient patient, String partnerName) {
		super();
		this.startTime = startTime;
		this.duration = duration;
		this.patient = patient;
		this.partnerName = partnerName;
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
	public String getPartnerName() {
		return partnerName;
	}
	public int getId() {
		return id;
	}
	public List<Treatment> getTreatments() {
		return null;
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

	@SuppressWarnings("unchecked")
	public static List<Appointment> getAllAppointments() {
		return (List<Appointment>)(List<?>) mock.Appointment.getAppointments();
	}

	public static void delete(Appointment appointment) {
		mock.Appointment.deleted.add((mock.Appointment) appointment);
	}

	public static Appointment fromResultSet(ResultSet result) {
		try {
			return new Appointment(
					result.getInt("appointment.id"),
					result.getTimestamp("appointment.date").toLocalDateTime(),
					result.getInt("appointment.duration"),
					Patient.fromResultSet(result),
					result.getString("partner.surname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
