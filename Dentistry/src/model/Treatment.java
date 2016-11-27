package model;

public abstract class Treatment {
	public static enum TreatmentType {
		HYGIENE_VISIT,
		CHECK_UP,
		REPAIR_WORK,
	}

	public abstract String getName();

	public abstract int getCost();

	public abstract TreatmentType getType();
}
