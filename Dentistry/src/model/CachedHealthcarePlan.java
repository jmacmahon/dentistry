package model;

public class CachedHealthcarePlan extends HealthcarePlan {
	private int maxCheckUps;
	private int maxHygieneVisits;
	private int maxRepairWork;
	private int usedCheckUps;
	private int usedHygieneVisits;
	private int usedRepairWork;

	private String name;
	private int cost;

	public CachedHealthcarePlan(int patientId, int maxCheckUps, int maxHygieneVisits, int maxRepairWork, int usedCheckUps,
			int usedHygieneVisits, int usedRepairWork, String name, int cost) {
		super(patientId);
		this.maxCheckUps = maxCheckUps;
		this.maxHygieneVisits = maxHygieneVisits;
		this.maxRepairWork = maxRepairWork;
		this.usedCheckUps = usedCheckUps;
		this.usedHygieneVisits = usedHygieneVisits;
		this.usedRepairWork = usedRepairWork;
		this.name = name;
		this.cost = cost;
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
	public int getUsedCheckUps() {
		return usedCheckUps;
	}
	public int getUsedHygieneVisits() {
		return usedHygieneVisits;
	}
	public int getUsedRepairWork() {
		return usedRepairWork;
	}
	public String getName() {
		return name;
	}
	public int getCost() {
		return cost;
	}

	public static CachedHealthcarePlan getNoPlan(int patientId) {
		return new CachedHealthcarePlan(patientId, 0, 0, 0, 0, 0, 0, "No plan", 0);
	}
}
