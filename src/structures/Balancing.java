package structures;

public enum Balancing {
	CASE_ONE(1), CASE_TWO(2), CASE_THREE(3), CASE_FOUR(4), CASE_FIVE(5);

	public int balancingCase;

	private Balancing(int balancingCase) {
		this.balancingCase = balancingCase;
	}
}
