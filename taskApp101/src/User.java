import java.util.ArrayList;

public class User {

    private ArrayList<Goal>goals;
    private ArrayList<Task>tasks;
    
    public User() {
    	
        ArrayList<Goal>goals=new ArrayList<Goal>();
        ArrayList<Task>tasks=new ArrayList<Task>();
    }
    
    public void addTask(Task task) {
    	tasks.add(task);
    }
    
    
    public void addGoal(Goal goal) { 
    	goals.add(goal);
    }
    
    
    public void swipGoal(ArrayList<Goal> coming) {
    	goals = coming;
    }
    
    public void swipTasks(ArrayList<Task> coming) {
    	tasks= coming;
    }
    
    
    public ArrayList<Goal> bringGoals(){
    	return goals;
    }
        
    public ArrayList<Task> bringTasks(){
    	return tasks;
    }
}
