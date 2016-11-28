package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class HealthcarePlanType {
	private int id;
	private String name;
	private int maxCheckUps;
	private int maxHygieneVisits;
	private int maxRepairWork;
	private int cost;
	public HealthcarePlanType(int id, String name, int maxCheckUps, int maxHygieneVisits, int maxRepairWork, int cost) {
		super();
		this.id = id;
		this.name = name;
		this.maxCheckUps = maxCheckUps;
		this.maxHygieneVisits = maxHygieneVisits;
		this.maxRepairWork = maxRepairWork;
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getMaxCheckUps() {
		return maxCheckUps;
	}
	public int getMaxHygieneVisits() {
		return maxHygieneVisits;
	}
	public int getMaxRepairWork() {
		return maxRepairWork;
	}
	public int getCost() {
		return cost;
	}
	@Override
	public String toString() {
		return this.getName();
	}

	public static HealthcarePlanType fromResultSet(ResultSet results) {
		try {
			return new HealthcarePlanType(
					results.getInt("plan.id"),
					results.getString("plan.name"),
					results.getInt("plan.checkUps"),
					results.getInt("plan.hygieneVisits"),
					results.getInt("plan.repairWork"),
					results.getInt("plan.cost"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static List<HealthcarePlanType> getAllHealthcarePlans() {
		try {
			ResultSet results = model.db.Queries.getAllHealthcarePlans();
			Vector<HealthcarePlanType> plans = new Vector<>();
			while (results.next()) {
				plans.add(fromResultSet(results));
			}
			return plans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static int add(int patientId, int planId) {
		try {
			ResultSet results = model.db.Queries.addPatientPlan(patientId, planId);
			results.next();
			return results.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
