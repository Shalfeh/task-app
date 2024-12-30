import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {
	  private static ArrayList<Data> data = new ArrayList<>();
	  
	public static void main(String[] args) {
	try{
		getDeta();
	   }catch(IOException e) {
		 System.out.println("File problems");}
	System.out.print("here");
	try {
		  new LoginLuncher();
	}
	catch(Exception e) {
	System.out.println("Problem with log in ");}
	
	for(Data x: data) {
		System.out.println(x);
	}
	
	
	
	
	}// main 
	

	
    private static void getDeta() throws IOException { // reads all data then makes it an Obj in arraylist<>data
        FileInputStream dataFile = new FileInputStream("deta.txt");
        Scanner readDeta = new Scanner(dataFile);
        	
        if (!readDeta.hasNext()) {
        	System.out.println("no info in the file");
        }else {
        		System.out.println("there is data");
        while (readDeta.hasNext()) {
        		String temp = readDeta.nextLine();
            Data obj = new Data(temp.substring(0,temp.indexOf(",")),temp.substring(temp.indexOf(",")+1,temp.indexOf("#"))
            		,temp.substring(temp.indexOf("#")+1,temp.indexOf("^")));
            System.out.println(obj);
            data.add(obj);
            
        }//while
        }//else
    }
	
   public static boolean lookupData(String name, String pass) {
        boolean status = false;
        for (Data user : data) {
            if (user.getName().equalsIgnoreCase(name)) {
                if (user.getPass().equals(pass)) {
                	LoginLuncher.setData(user);
                    status = true;
                } else {
                    System.out.println("Wrong password!");
                }
            }
        }
        if (!status) {
            System.out.println("Username not found or wrong password!");
        }
        return status;
    }
   
   public static ArrayList<Data>getDataArray(){
	   return data;
   }
   
   public  static void sendArrayListBack(Data data1 ) {
	  data.add(data1);
   }

}
