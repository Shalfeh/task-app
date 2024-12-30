
public class Data {

	    private String name;
	    private String pass;
	    private User user;
	    private String email;
	    public Data(String name, String pass, String email) {
	        this.name = name;
	        this.pass = pass;
	        this.email=email;
	        user = new User();
	    } // constructor 

	    public String getName() {
	        return name;
	    }// name

	    public String getPass() {
	        return pass;
	    }// pass 


	    
	    public void setUser(User user){
	    	this.user=user;
	    }
	    public User getUser() {
	    	return user;
	    }
	    
	    public String getEmail() {
	    	return this.email;
	    }
	    public String toString(){
	    	
	    	return this.name+","+this.pass+"#"+this.email+"^"; 
	    }
	    
	    
	}// class
