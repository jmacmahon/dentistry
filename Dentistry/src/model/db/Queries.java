package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.Config;

public class Queries {
	public static ResultSet getAllPatients() throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM patient"
				+ " LEFT OUTER JOIN address "
				+ " ON patient.addressId = address.id;");
		return results;
	}

	public static ResultSet getAppointments(LocalDateTime from, LocalDateTime to) throws SQLException {
		String escapedFrom = from.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"));
		String escapedTo = to.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"));
		ResultSet results = Config.db.runQuery("SELECT * FROM appointment"
				+ " LEFT OUTER JOIN patient ON appointment.patientId = patient.id"
				+ " LEFT OUTER JOIN partner ON appointment.partnerId = partner.id"
				+ " WHERE appointment.date > '" + escapedFrom + "'"
				+ " AND appointment.date < '" + escapedTo + "';");
		return results;
	}

	public static ResultSet getAddress(int id) throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM address WHERE address.id = "
				+ Integer.toString(id) + ";");
		return results;
	}

	public static ResultSet getPatient(int id) throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM patient WHERE patient.id = "
				+ Integer.toString(id) + ";");
		return results;
	}

	public static ResultSet getTreatment(int id) throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM treatment WHERE treatment.id = "
				+ Integer.toString(id) + ";");
		return results;
	}

	public static ResultSet getTreatmentsForAppointment(int appointmentId) throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM appointmentTreatments"
				+ " LEFT OUTER JOIN appointment ON appointmentTreatments.appointmentId = appointment.id"
				+ " LEFT OUTER JOIN treatment ON appointmentTreatments.treatmentId = treatment.id"
				+ " WHERE appointmentTreatments.appointmentId = " + Integer.toString(appointmentId) + ";");
		return results;
	}

	public static ResultSet searchPatients(String forename, String surname, Integer houseNumber, String postcode) throws SQLException {
		String forenameClause, surnameClause, houseNumberClause, postcodeClause;
		int forenameIndex = 0;
		int surnameIndex = 0;
		int postcodeIndex = 0;
		int index = 1;
		if (!forename.equals("")) {
			forenameIndex = index++;
			forenameClause = "patient.forename = (?)";
		} else {
			forenameClause = "true";
		}
		if (!surname.equals("")) {
			surnameIndex = index++;
			surnameClause = "patient.surname = (?)";
		} else {
			surnameClause = "true";
		}
		if (houseNumber != null) {
			houseNumberClause = "address.houseNumber = " + houseNumber.toString();
		} else {
			houseNumberClause = "true";
		}
		if (!postcode.equals("")) {
			postcodeIndex = index++;
			postcodeClause = "address.postcode = (?)";
		} else {
			postcodeClause = "true";
		}
		String query = "SELECT * FROM patient"
				+ " LEFT OUTER JOIN address"
				+ " ON patient.addressId = address.id"
				+ " WHERE " + postcodeClause
				+ " AND " + forenameClause
				+ " AND " + surnameClause
				+ " AND " + houseNumberClause + ";";
		PreparedStatement statement = Config.db.getPreparedStatement(query);
		if (forenameIndex != 0) {
			statement.setString(forenameIndex, forename);
		}
		if (surnameIndex != 0) {
			statement.setString(surnameIndex, surname);
		}
		if (postcodeIndex != 0) {
			statement.setString(postcodeIndex, postcode);
		}
		return statement.executeQuery();
	}
}
