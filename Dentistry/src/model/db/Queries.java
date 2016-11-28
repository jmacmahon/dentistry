package model.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

	public static ResultSet getAllPartners() throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM partner;");
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

	public static ResultSet getPartnerAppointments(LocalDateTime from, LocalDateTime to, int partnerId) throws SQLException {
		String escapedFrom = from.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"));
		String escapedTo = to.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"));
		ResultSet results = Config.db.runQuery("SELECT * FROM appointment"
				+ " LEFT OUTER JOIN patient ON appointment.patientId = patient.id"
				+ " LEFT OUTER JOIN partner ON appointment.partnerId = partner.id"
				+ " WHERE appointment.date >= '" + escapedFrom + "'"
				+ " AND appointment.date <= '" + escapedTo + "'"
				+ " AND appointment.partnerId = " + Integer.toString(partnerId) + ";");
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

	public static ResultSet getPlanForPatient(int patientId) throws SQLException {
		return Config.db.runQuery("SELECT * FROM patientPlan"
				+ " LEFT OUTER JOIN patient ON patientPlan.patientId = patient.id"
				+ " LEFT OUTER JOIN plan ON patientPlan.planId = plan.id"
				+ " WHERE patientPlan.patientId = " + Integer.toString(patientId) + ";");
	}

	public static ResultSet addAddress(int houseNumber, String streetName,
			String districtName, String cityName, String postcode) throws SQLException {
		String query = "INSERT INTO address (houseNumber, streetName,"
				+ " districtName, cityName, postCode) VALUES ( (?), (?), (?), (?), (?) );";
		PreparedStatement statement = Config.db.getPreparedStatement(query);
		statement.setInt(1, houseNumber);
		statement.setString(2, streetName);
		statement.setString(3, districtName);
		statement.setString(4, cityName);
		statement.setString(5, postcode);
		statement.executeUpdate();
		return Config.db.runQuery("SELECT id FROM address ORDER BY id DESC LIMIT 1;");
	}

	public static ResultSet addPatient(String title, String forename, String surname,
			String contact, LocalDate dateOfBirth, int addressId) throws SQLException {
		String query = "INSERT INTO patient (title, forename, surname, contactNumber,"
				+ " dateOfBirth, addressId) VALUES ( (?), (?), (?), (?), (?), (?) );";
		PreparedStatement statement = Config.db.getPreparedStatement(query);
		statement.setString(1, title);
		statement.setString(2, forename);
		statement.setString(3, surname);
		statement.setString(4, contact);
		statement.setDate(5, Date.valueOf(dateOfBirth));
		statement.setInt(6, addressId);
		statement.executeUpdate();
		return Config.db.runQuery("SELECT id FROM patient ORDER BY id DESC LIMIT 1;");
	}

	public static ResultSet addAppointment(int patientId, int partnerId, LocalDateTime date, int duration) throws SQLException {
		String query = "INSERT INTO appointment (patientId, partnerId, date, duration) VALUES"
				+ " (" + Integer.toString(patientId)
				+ ", " + Integer.toString(partnerId)
				+ ", '" + date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")) + "'"
				+ ", " + Integer.toString(duration) + ");";
		Config.db.runQuery(query);
		return Config.db.runQuery("SELECT id FROM appointment ORDER BY id DESC LIMIT 1;");
	}

	public static void deleteAppointment(int appointmentId) throws SQLException {
		Config.db.runQuery("DELETE FROM appointment WHERE appointment.id = " + Integer.toString(appointmentId) + ";");
	}
}
