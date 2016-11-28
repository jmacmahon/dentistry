package model;

public class CachedTreatment extends Treatment {
	private String name;
	private TreatmentType type;
	private int cost;
	public CachedTreatment(int id, String name, TreatmentType type, int cost) {
		super(id);
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
