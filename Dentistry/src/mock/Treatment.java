package mock;

import model.TreatmentInterface;

public class Treatment implements TreatmentInterface {
	public static final Treatment[] MOCK_DATA = {
			new Treatment(2000, "Jimmies rustled", "type?"),
			new Treatment(10000, "Screw caps adjusted", "type?"),
			new Treatment(20, "Free sticker", "type?"),
			new Treatment(4500, "Mystery box", "type?"),
	};

	private int cost;
	private String name;
	private String type;

	public Treatment(int cost, String name, String type) {
		this.cost = cost;
		this.name = name;
		this.type = type;
	}

	@Override
	public int getCost() {
		return cost;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getType() {
		return type;
	}
}
