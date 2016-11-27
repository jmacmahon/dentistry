package model;

public abstract class Treatment {
	public static enum TreatmentType {
		HYGIENE_VISIT,
		CHECK_UP,
		REPAIR_WORK,
	}

	private String name;
	private TreatmentType type;
	private int cost;
	public Treatment(String name, TreatmentType type, int cost) {
		super();
		this.name = name;
		this.type = type;
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public TreatmentType getType() {
		return type;
	}
	public int getCost() {
		return cost;
	}
}
