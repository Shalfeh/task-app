public class Task {
	boolean status;		
	String taskName;
	String taskNote;
	String dateAndTime;
	public Task() {
		this.status=false;
		this.taskName="UNKOWN";
		this.dateAndTime=" not date UNKOWN";
		this.taskNote="not notes UNKOWN";
	}
	
	public Task(boolean status,String taskName,String taskNote, String dateAndTime) {
		this.status=status;
		this.taskName=taskName;
		this.dateAndTime=dateAndTime;
		this.taskNote=taskNote;
	}
	public Task(String taskName) {
		this.status=false;
		this.taskName=taskName;
		this.dateAndTime="UNKOWN";
		this.taskNote="UNKOWN";
	}
	public Task(int zero3 ,String taskName,String taskNote,int zero1) {
		this.status=false;
		this.taskName=taskName;
		this.dateAndTime="UNKOWN";
		this.taskNote=taskNote;
	}
	public Task(String taskName,String taskNote,String dateAndTime) {
		this.status=false;
		this.taskName=taskName;
		this.dateAndTime=dateAndTime;
		this.taskNote=taskNote;
	}
	
	
	public String getTaskName(){	
	return taskName;
	}
	public String getTaskNote() {
		
	return taskNote;}
	
	public String getDateAndTime() {
		return dateAndTime;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean newStatus) {
		status=newStatus;
	}
	
	public void setDateAndTime(String dateAndTime){ 
		this.dateAndTime= dateAndTime;
	}
	
	public void setTaskNote(String newTaskNote) {
		this.taskNote = newTaskNote;
	}
	
	public void setTaskName(String newName){
		this.taskName=newName;
	}
	
	public String getTaskForDataSaving() {
		
		return this.taskName+","+this.status+"<"+this.dateAndTime+">"+this.taskNote+"#";
	}
}
