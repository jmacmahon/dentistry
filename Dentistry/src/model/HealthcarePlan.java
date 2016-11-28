package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthcarePlan {
	private int patientId;
	private CachedHealthcarePlan cached;

	public HealthcarePlan(int patientId) {
		this.patientId = patientId;
	}
	public int getPatientId() {
		return this.patientId;
	}

	public int getMaxCheckUps() {
		return this.getCached().getMaxCheckUps();
	}
	public int getMaxHygieneVisits() {
		return this.getCached().getMaxHygieneVisits();
	}
	public int getMaxRepairWork() {
		return this.getCached().getMaxRepairWork();
	}
	public int getUsedCheckUps() {
		return this.getCached().getUsedCheckUps();
	}
	public int getUsedHygieneVisits() {
		return this.getCached().getUsedHygieneVisits();
	}
	public int getUsedRepairWork() {
		return this.getCached().getUsedRepairWork();
	}
	public String getName() {
		return this.getCached().getName();
	}
	public int getCost() {
		return this.getCached().getCost();
	}

	private CachedHealthcarePlan getCached() {
		if (this.cached == null) {
			try {
				ResultSet results = model.db.Queries.getPlanForPatient(this.patientId);
				if (!results.next()) {
					return CachedHealthcarePlan.getNoPlan(this.patientId);
				}
				HealthcarePlan shouldBeCached = HealthcarePlan.fromResultSet(results, this.patientId);
				if (shouldBeCached instanceof CachedHealthcarePlan) {
					this.cached = (CachedHealthcarePlan) shouldBeCached;
				} else {
					// something went wrong -- throw an exception
					// TODO make this a non-runtime exception
					throw new RuntimeException();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return this.cached;
	}

	private static HealthcarePlan fromResultSet(ResultSet results, int patientId) {
		try {
			return new CachedHealthcarePlan(
					results.getInt("patientPlan.patientId"),
					results.getInt("plan.checkUps"),
					results.getInt("plan.hygieneVisits"),
					results.getInt("plan.repairWork"),
					results.getInt("patientPlan.usedCheckUps"),
					results.getInt("patientPlan.usedHygieneVisits"),
					results.getInt("patientPlan.usedRepairWork"),
					results.getString("plan.name"),
					results.getInt("plan.cost"));
		} catch (SQLException e) {
			return new HealthcarePlan(patientId);
		}
	}
}
