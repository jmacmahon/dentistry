package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Treatment {
	private int id;
	private CachedTreatment cached;
	public Treatment(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}

	private CachedTreatment getCached() {
		if (this.cached == null) {
			try {
				ResultSet results = model.db.Queries.getTreatment(this.id);
				results.next();
				Treatment shouldBeCached = Treatment.fromResultSet(results, this.id);
				if (shouldBeCached instanceof CachedTreatment) {
					this.cached = (CachedTreatment) shouldBeCached;
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

	public String getName() {
		return this.getCached().getName();
	}
	public TreatmentType getType() {
		return this.getCached().getType();
	}
	public int getCost() {
		return this.getCached().getCost();
	}

	public static enum TreatmentType {
		HYGIENE_VISIT,
		CHECK_UP,
		REPAIR_WORK,
	}

	private static TreatmentType parseType(String typeStr) {
		switch(typeStr) {
		case "hygieneVisit":
			return TreatmentType.HYGIENE_VISIT;
		case "checkUp":
			return TreatmentType.CHECK_UP;
		case "repairWork":
			return TreatmentType.REPAIR_WORK;
		}
		// TODO make this a better exception
		throw new RuntimeException();
	}

	public static Treatment fromResultSet(ResultSet results, int id) {
		try {
			return new CachedTreatment(
					results.getInt("treatment.id"),
					results.getString("treatment.name"),
					parseType(results.getString("treatment.type")),
					results.getInt("treatment.cost"));
		} catch (SQLException e) {
			return new Treatment(id);
		}
	}
}
