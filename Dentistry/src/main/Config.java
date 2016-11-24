package main;

import java.time.DayOfWeek;

public final class Config {
	public static final int WORKING_HOURS_START = 9;
	public static final int WORKING_HOURS_END = 17;
	public static final DayOfWeek[] WORKING_WEEK_DAYS = {
			DayOfWeek.MONDAY,
			DayOfWeek.TUESDAY,
			DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY,
			//DayOfWeek.SATURDAY,
			//DayOfWeek.SUNDAY,
	};
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DB_USER = "root";
	public static final String DB_PASS = "dentistry";
	public static final String DB_NAME = "dentist_db";
	public static final String DB_PROPERTIES = "?useSSL=false";
	
	public static final String DB_CREATE = "CREATE DATABASE " + Config.DB_NAME;
	
	public static final String DB_CREATE_APPOINTMENTS = 
			"CREATE TABLE appointments " +
            "(id INTEGER  NOT NULL AUTO_INCREMENT, " +
            " patientName VARCHAR(255), " + 
            " partnerName VARCHAR(255), " + 
            " startTime datetime, " +
            " duration INTEGER, " +
            " treatments VARCHAR(255), " +
            " PRIMARY KEY ( id ))";
}
