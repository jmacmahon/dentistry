package mock;

public class Treatment extends model.Treatment {
	public Treatment(int cost, String name, TreatmentType type) {
		super(name, type, cost);
	}

	public static final Treatment[] MOCK_DATA = {
			new Treatment(2000, "Jimmies rustled", TreatmentType.HYGIENE_VISIT),
			new Treatment(10000, "Screw caps adjusted", TreatmentType.CHECK_UP),
			new Treatment(20, "Free sticker", TreatmentType.CHECK_UP),
			new Treatment(4500, "Mystery box", TreatmentType.REPAIR_WORK),
	};
}
