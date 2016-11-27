package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Config;

public class Database {

	private String url;
	private String user;
	private String pass;
	private String dburl;

	private Connection connection;

	public Database(String url, String user, String pass) {
		this.url = url + Config.DB_PROPERTIES;
		this.user = user;
		this.pass = pass;
		this.dburl = url + Config.DB_NAME + Config.DB_PROPERTIES;
	}

	public ResultSet runQuery(String query) throws SQLException {
		return this.connection.createStatement().executeQuery(query);
	}

	public void init() {
		System.out.println("Connecting to server...");
		try {
			connection = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected...");

			this.create();

		} catch (SQLException e) {
			if (e.getErrorCode() == 1007) {
				System.out.println("Database already exists...");
				try {
					connection.close();
					connection = DriverManager.getConnection(dburl, user, pass);
					System.out.println("Connected to existing database...");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("Could not connect to the database.");
				if (Config.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}

	private void create() throws SQLException {
		Statement statement = connection.createStatement();

		statement.executeUpdate(Config.DB_CREATE);
		System.out.println("Created database: " + Config.DB_NAME + "...");

		connection.close();
		statement.close();

		connection = DriverManager.getConnection(dburl, user, pass);
		statement = connection.createStatement();
		System.out.println("Connected to new database...");

		statement.executeUpdate(Config.DB_CREATE_APPOINTMENTS);
		System.out.println("Added appointments table...");

		if (statement != null) {
			statement.close();
		}
	}
}
