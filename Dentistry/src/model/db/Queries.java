package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.Config;

public class Queries {
	public static ResultSet getAllPatients() throws SQLException {
		ResultSet results = Config.db.runQuery("SELECT * FROM patient "
				+ "LEFT OUTER JOIN address "
				+ "ON patient.addressId = address.id;");
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
}
