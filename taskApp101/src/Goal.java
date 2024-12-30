public class Goal {
	
	String goalName="NA";
	boolean status =false;
	
	
	public Goal(String s , boolean st) {
		goalName = s;
		status =st;
	}
	public Goal(String name) {
		goalName = name;
	}
	
	public void setStat(boolean status) {
		this.status=status;
	}
	
	public void setName(String name) {
		goalName = name;
	}
	
	public String getName() {
		return goalName;
	}
	public boolean getStat() {
		return status;
	}

	public String toString() {
		
		//Name: , Status: 
		return getName()+","+getStat();
	}
}
