package moves;

public class Move {
	private String name;
	private int basePower;
	private int accuracy;
	
	
	
	public Move(String newName, int newBasePower, int newAccuracy) {
		name = newName;
		basePower = newBasePower;
		accuracy = newAccuracy;
	}
	
	// set-get functions
	public void setBasePower(int newBasePower) { basePower = newBasePower; }
	public int getBasePower() { return basePower; }
	
	public void setAccuracy(int newAccuracy) { accuracy = newAccuracy; }
	public int getAccuracy() { return accuracy; }
	
	public void setName(String newName) { name = newName; }
	public String getName() { return name; }
}
