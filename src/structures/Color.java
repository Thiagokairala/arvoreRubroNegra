package structures;

public enum Color {
	BLACK(1), RED(2);

	public int colorOfTheNode;

	private Color(int colorOfTheNode) {
		this.colorOfTheNode = colorOfTheNode;
	}
}
