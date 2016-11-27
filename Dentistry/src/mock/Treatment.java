package mock;

public class Treatment extends model.Treatment {
	public static final Treatment[] MOCK_DATA = {
			new Treatment(2000, "Jimmies rustled", TreatmentType.HYGIENE_VISIT),
			new Treatment(10000, "Screw caps adjusted", TreatmentType.CHECK_UP),
			new Treatment(20, "Free sticker", TreatmentType.CHECK_UP),
			new Treatment(4500, "Mystery box", TreatmentType.REPAIR_WORK),
	};

	private int cost;
	private String name;
	private TreatmentType type;

	public Treatment(int cost, String name, TreatmentType type) {
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
	public TreatmentType getType() {
		return type;
	}
}
