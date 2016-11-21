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
//			DayOfWeek.SATURDAY,
//			DayOfWeek.SUNDAY,
		};
}
