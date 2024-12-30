import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class LoginLuncher extends JFrame    {
	private static ArrayList<Data>data= Main.getDataArray();

	JFrame frame;
	JLabel label;
    private static JTextField userNameField;
    private static JPasswordField userPassField;
    // after runuing 
    
	private static int caseNum;
	static int indexA;
	static int indexB;
	private static  ArrayList<Goal> goals_for_user=null;
	private static ArrayList<Task>TaskAt_thatDay=null;
	static DefaultTableModel  model1;
	final static String[] columnNames1 = {"GOAL", "Select"};
    final private static ImageIcon mainIcon= new ImageIcon("iconTT.png");
    private static Data found=null;
    private String userName;
    private String userPass;
    private static int countTure;
    private static String name="s";
    private static String pass;
    private static String email;
    private static String passAgain;
	
    
    public LoginLuncher() {
    	if (mainIcon.getImageLoadStatus() != MediaTracker.COMPLETE) { System.err.println("Error: Icon image not loaded."); }
    	// create frame 
    	frame = new JFrame("Login"); 
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.setIconImage(mainIcon.getImage());
    	frame.setSize(350,200);
    	frame.setLayout(new GridLayout(3,2));
    	
    	userNameField = new JTextField();
    	userPassField= new JPasswordField();
    	
    	// adding User info / name/ pass be side the  textField
    		//User NAme and entey 
    		frame.add(new JLabel("User Name: "));
    		userNameField = new JTextField();
    		frame.add(userNameField);
    		
    		// user Pass
    		frame.add(new JLabel ("User Paswrod:"));
            userPassField = new JPasswordField();
            frame.add(userPassField);
             // botton 
            

             JButton loginButton = new JButton("Login");
             loginButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                      userName = userNameField.getText();
                      userPass = new String(userPassField.getPassword());

                     if (Main.lookupData(userName, userPass)) {//-----------------------------------------------here
                         JOptionPane.showMessageDialog(frame, "Login successful!");
                        // mainMenu();
                         LunchApp(userName);
                         frame.dispose(); // Close the login window
                     } else { // if user not found
                         JOptionPane.showMessageDialog(frame, "Invalid credentials, try again.");
                     }
                 }
             });
             
             JButton signup = new JButton("signUP!");
             	signup.addActionListener(new ActionListener() {
            	 @Override
            	 public void actionPerformed(ActionEvent o) {
            		 singUpFrame() ;
            	 }
             	}
             	);
       
             
             
             
             
             frame.add(signup);
             frame.add(loginButton);
             frame.setVisible(true);
             
    }

    public static void singUpFrame() {

    	
    	JTextField userName =new JTextField("EX. Mar321_!");
    	JTextField userPass= new JTextField("password");
    	JTextField userEmailnew=new JTextField("sadmas@yahoo.com");
    	JTextField userPassAgain=new JTextField("password");
    	
    	JFrame frame = new JFrame("SignUP");
    	frame.setIconImage(mainIcon.getImage());
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.setSize(500,150);
    	frame.setLayout(new GridLayout(5,2));
    	
    	///-----------------Input Framing -------------------//
    	///
    	///---------------<USERNAME>--------------///
    	///
    	frame.add(new Label("username: "));
    	frame.add(userName); // fix me find if info is already available
    	///		
    	///---------------<EMAIL>--------------///
    	///
    	frame.add(new JLabel("New Email"));
    	frame.add(userEmailnew);// fix me find if info is already available
    	///
    	///---------------<PASSWORD1>--------------///
    	///
    	frame.add(new JLabel("create new Password: "));
    	frame.add(userPass);
    	pass=userPass.getText();
    	///
    	///---------------<PASSWORD2>--------------///
    	///
    	///
    	frame.add(new JLabel("write Password again: ")); // fix me try find match
    	frame.add(userPassAgain);

    	//-----------------Framing only ENDED-------------------//
    	
    	// **************CHECK BUTTON *******// fixme
    	JButton check = new JButton("check");
    	check.addActionListener(new ActionListener() {
    	@Override
   	 public void actionPerformed(ActionEvent o) {

        	 countTure=0;
 ////---------------<Prossising Start>-----------------------////    	
        	
        	 
        	///---------------<USERNAME>-------------
        	
    		if(checkUserNameAvilabelty(userName.getText())) {
    			// FIXME add Error message 
    			name=userName.getText();
    			
    			userName.setText(name+" is not available ");
    			userName.setForeground(Color.RED);
    			System.out.print("userNAme true ===== name isnt avialble");
    		}
    		else if (!userName.getText().equalsIgnoreCase("EX. Mar321_!")){
    			
    			name = userName.getText();
    			System.out.print("\n Name is :"+name+" \n ");
    			countTure++;
    			
    		}else {
    			
    			userName.setText("Enter Username");
    			userName.setForeground(Color.RED);
    			userName.setBorder(BorderFactory.createLineBorder(Color.RED));
    		}
    		
    		
    		///---------------<EMAIL>--------------{checking point}
        	if(getEmailstat(userEmailnew.getText())) {
    			userEmailnew.setText("Email already exists");
    			userEmailnew.setForeground(Color.BLUE);
    			userEmailnew.setBorder( BorderFactory.createLineBorder(Color.RED));

        	}else{
        		System.out.println("EMAIL IS AVIALBLE");// TESTIG
        		
        		email = userEmailnew.getText();
        		if(!email.equalsIgnoreCase("sadmas@yahoo.com")){
        				countTure++;
        		}else {
        			userEmailnew.setText("enter your email");
        			userEmailnew.setForeground(Color.RED);
        			userEmailnew.setBorder( BorderFactory.createLineBorder(Color.RED));
        		     } 
        		}
         	///---------------<PASSWORD1>--------------{checking point}
        	pass=userPass.getText();
        	countTure++;
         	///---------------<PASSWORD2>--------------{checking point}
        	if(!checkPass(userPassAgain.getText(), pass)) {
        		//Error
        		passAgain="";
        		userPassAgain.setBorder(BorderFactory.createLineBorder(Color.RED));
        		userPass.setBorder(BorderFactory.createLineBorder(Color.RED));
        		System.out.println("pass Didnt match");
        	}else {
        		//pass
        		if(pass.length()>1&& !pass.equals("password")) {
        	System.out.println("Pass mached");
        	passAgain = userPassAgain.getText();
        	countTure++;
        		}//if pass>1
        		else {
        			System.out.println("problem");
        		}
        	}
       ////---------------<Prossising END>-----------------------////
      
   	 			}//OVERRIDE
    				} // ACTION LISTENER
    					);//PERAMTER END 	
    	
    	
    	
    	
    	
    	
    	JButton Done = new JButton("Done");
    	Done.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (countTure==4) {
    				ArrayList<Data> data = Main.getDataArray();
    				Data obj  = new Data (name , pass , email);
    				data.add(obj);
    				Main.sendArrayListBack(obj);
    				System.out.println("user have been made");
    				System.out.println(obj.getName()+" "+obj.getEmail()+" "+obj.getPass());
    				try {
						writeNewData(obj);
					} catch (IOException e1) {
							System.out.println("Problem at Done ");
						e1.printStackTrace();
					}
    				frame.dispose();
    			}
    		}
    	}
    	);
    	
    	frame.add(check);
    	frame.add(Done);
    	frame.setVisible(true);
    	
    }
    private static boolean checkUserNameAvilabelty(String newUser) {
    	
    	int i;
    	for (i=0; i< data.size();i++) {
    		if(newUser.equals(data.get(i).getName())) {
    			return true;
    		}
    	}
    	
    return false; }
    private static boolean getEmailstat(String newEmail) {
    	ArrayList<Data>data = Main.getDataArray();
    	for(int i=0; i<data.size();i++) {
    		if (data.get(i).getEmail().equalsIgnoreCase(newEmail)) {
    			return true;
    		}
    	}
    return false;}
    
    private static boolean checkPass(String passEnterdSec, String passEnterdFirst) {
    	if(passEnterdSec.equals(passEnterdFirst)) {
    		return true;
    	}
    return false;}
    
	public static void findError() {

		
	}
	
	
	public static void LunchApp(String  userNameGot) {
		
	
			String userName= userNameGot;
			int tasksAtThatday=5;
			
			
			ImageIcon background_photo = new ImageIcon("123.jpg");
			JLabel backGoundLabel =new JLabel();
			backGoundLabel.setIcon(background_photo);
			backGoundLabel.setBounds(0,0,1000,500);
			
			// Main Panel to layer 2 - has all the Jpanels that has comps
			JPanel MAIN_PANEL= new JPanel();
			MAIN_PANEL.setLayout(new BorderLayout());
			MAIN_PANEL.setOpaque(false);
			MAIN_PANEL.setBounds(0,0,1000,500);
			
			//# North panel to set the header
			JLabel headerLabel = new JLabel("Welcome to your daly Tasks "+ userName);// FIXME getuserName(); 
			headerLabel.setHorizontalAlignment(JLabel.CENTER);
			headerLabel.setFont(new Font ("Playfair Display", Font.BOLD,24) );
			headerLabel.setForeground(Color.WHITE);
		  	ImageIcon taskIcon =new ImageIcon("4345800.png");
		  	headerLabel.setIcon(taskIcon);
		  	headerLabel.setHorizontalTextPosition(JLabel.RIGHT);
		  	headerLabel.setBackground(new Color(0x8996b));
		  	headerLabel.setOpaque(true);
			MAIN_PANEL.add(headerLabel , BorderLayout.NORTH);
			
			// #EastBorder - up coming to do list 
			JPanel eastPanel =new JPanel(); 
			eastPanel.setLayout(new BorderLayout());
			eastPanel.setBorder(BorderFactory.createLineBorder(new Color(0x0BFFF),5));
			eastPanel.setPreferredSize(new Dimension(300,300));
			eastPanel.setOpaque(true);
			
			//- EastBorder - north// FIXME - add icon set icon bounds and fonts
			JPanel eastPanel_north= new JPanel();
			eastPanel_north.setLayout(new FlowLayout());
			JLabel notheHeader_goals= new JLabel("Goals");
			eastPanel_north.setSize(new Dimension (100,100));
			notheHeader_goals.setForeground(Color.WHITE);
			eastPanel_north.add(notheHeader_goals);
			eastPanel_north.setBackground(new Color(0x0BFFF));
			//eastPanel_north.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		    eastPanel.add(eastPanel_north, BorderLayout.NORTH);
			
		    //- EastBorder -CENTER - THERE IS NO SOUTH// FIXME 
		     JPanel eastPanel_Center = new JPanel();
	        eastPanel_Center.setLayout(new BorderLayout());

	        
	        
	        // Populate the goals_for_user list with sample data
	        goals_for_user = new ArrayList<Goal>(); // FIXME TO GET USER FROM DATA ARRAY LIST
			try {
				setAllGoals(found,goals_for_user);
			} catch (IOException e1) {
				System.out.print("Problem");
				e1.printStackTrace();
			}
	        
	        
	        
	        // Define column names
	      //  String[] columnNames1 = {"GOAL", "Select"};

	        // Populate dataGoal array with data from goals_for_user
	        Object[][] dataGoal = new Object[goals_for_user.size()][2];
	        for (int i = 0; i < goals_for_user.size(); i++) {
	            Goal goal = goals_for_user.get(i);
	            dataGoal[i][0] = goal.getName();
	            dataGoal[i][1] = goal.getStat();
	        }

	        // Create a table model
	        DefaultTableModel model1 = new DefaultTableModel(dataGoal, columnNames1) {
	            @Override
	            public Class<?> getColumnClass(int column) {
	                switch (column) {
	                    case 1:
	                   // 	System.out.println("Ture happens here "); // edit the status here // FIXME
	                        return Boolean.class;
	                    default:
	                        return String.class;
	                }
	            }
	        };
	        
	        // Create a table with the model
	        JTable goalTable = new JTable(model1);
	        JScrollPane goalScrollPane = new JScrollPane(goalTable);
	        // model 1 takes an action listener  for the checked boxes 
	        model1.addTableModelListener(new TableModelListener() {
	        		@Override
	        		public void tableChanged(TableModelEvent e) {
	                    int row = e.getFirstRow();
	                    int column = e.getColumn();
	        	
	                    if (column == 1) {  // box checked would get data from here and modify the goal obj by ref
	                    	
	                        Boolean checked = (Boolean) model1.getValueAt(row, column);
	                        Goal goal = goals_for_user.get(row);
	                        goal.setStat(checked);
	                        System.out.println(goal.toString());
	                        
	                    }
	        }});
	        
	        
	        
	        goalScrollPane.setBackground(new Color(0x0BFFF));
	        
	        // Add the scroll pane to the panel
	        eastPanel_Center.add(goalScrollPane, BorderLayout.CENTER);
		    
		    
		   eastPanel.add(eastPanel_Center,BorderLayout.CENTER );//FIXME
		   
		    //#CENERT PANEL - WELL TAKE A NORTH AND A CENER 
		    JPanel mainCenter =new JPanel();
		    mainCenter.setLayout(new BorderLayout());
		  //  mainCenter.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 10));
		    mainCenter.setOpaque(false);
		    
		    
		    //-north~CENERT PANEL
		    JPanel northMainCenter =new JPanel();
		   northMainCenter.setOpaque(false);
		   northMainCenter.setLayout(new FlowLayout(FlowLayout.LEADING,-1,-1));
		   northMainCenter.setPreferredSize(new Dimension(300,70));
		  // northMainCenter.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
		   	JLabel dateAndTime = new JLabel();
		   	ImageIcon dateAndTime_Photo= new ImageIcon("3968444.png");
		    dateAndTime.setIcon(dateAndTime_Photo);
		    dateAndTime.setHorizontalTextPosition(JLabel.RIGHT);
		    dateAndTime.setBackground(new Color(0x0BFFF));
		    dateAndTime.setOpaque(true);
		    
		    //>>>>>>>>>>>>>>>>>timer
		    
		    Timer timer = new Timer(1000, e -> {
		    	
		    	 Calendar calendar = Calendar.getInstance();
		    	 String amOrPm = calendar.get(Calendar.AM_PM) == 0 ? "AM": "PM";
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	            SimpleDateFormat sdf3= new SimpleDateFormat("dd/MM/yyyy");
	           dateAndTime.setText("Date: "+sdf3.format(calendar.getTime())+"           Time: "+sdf.format(calendar.getTime())+" "+amOrPm+"    ");
	            
	        });
	        timer.start();
		    //>>>>>>>>>>>>>> timer end
	        dateAndTime.setForeground(Color.WHITE);
	        dateAndTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		    northMainCenter.add(dateAndTime);
		    mainCenter.add(northMainCenter ,BorderLayout.NORTH);
		    
		    
			//-mainCenter~CENTER TAKSS FOR TO DAY  	//FIXME TO BE REMOVE AND REPLACER WITH DATA
	           TaskAt_thatDay =new ArrayList<Task>();
	           
	           try {
				getAllTasks(found,TaskAt_thatDay); // reading the data
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	           
	           
	           
	            JPanel tasksPanel = new JPanel();
	            tasksPanel.setPreferredSize(new Dimension(1000,300));
	            
	            // Define column names for the table
	            String[] columnNames = {"Status", "Task Name", "Notes", "Date"};
	        	
	            // Create a 2D array to hold the data from the tasks
	            Object[][] data = new Object[TaskAt_thatDay.size()][4];
	            
	            for (int i = 0; i < TaskAt_thatDay.size(); i++) {
	                Task task = TaskAt_thatDay.get(i);
	                data[i][0] = task.getStatus() ? "Completed" : "Pending";  // Convert boolean to text
	                data[i][1] = task.getTaskName();
	                data[i][2] = task.getTaskNote();
	                data[i][3] = task.getDateAndTime();
	            }
	            
	            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

	            // Create JTable with the model
	            JTable tasksTable = new JTable(tableModel);
	          
	            // Add the table to a scroll pane
	            JScrollPane scrollPane = new JScrollPane(tasksTable);
	            scrollPane.setPreferredSize(new Dimension(700,300));
	            tasksPanel.add(scrollPane);
	            tasksPanel.setOpaque(false);
		    	mainCenter.add(tasksPanel,BorderLayout.CENTER);
		    
		      //-mainCenter~bottom = buttons 
		    	JPanel buttons = new JPanel();
		    	buttons.setOpaque(false);
		    	buttons.setPreferredSize(new Dimension(70,70));//FIXME
		    	buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		    	 	JButton button1 = new JButton("Add Task");
		    	 	JButton button2 = new JButton("Remove Task");
		    	 	JButton button3 = new JButton("Edit Task");
		    	 	JButton button4 = new JButton("Add Goal");
		    	 	JButton button5 = new JButton("Remove Goal");
		    	 	JButton button6 = new JButton("Edit Goal");
		    	 	
		    	 	/// button1
		    	 	///
		    	 	button1.setFocusable(false);
		    	 	button1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent i) {
						JFrame frame = new JFrame("Add task");
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setSize(400,200);
						ImageIcon icon = mainIcon;
						frame.setIconImage(icon.getImage());
						frame.setResizable(false);
						frame.setLayout(new BorderLayout());
						JPanel centerPanel= new JPanel();
						centerPanel.setLayout(new GridLayout(2,2));
						JTextField name = new JTextField("Enter task name");
						JTextField note = new JTextField("Enter task note");
						JTextField date = new JTextField("MM/DD/YYYY");
						JButton save = new JButton("SAVE");
								save.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent c) {
									Task addTask = new Task
											(name.getText(),note.getText(),date.getText() );
									    TaskAt_thatDay.add(addTask);
									    
									    
									    Object[][] newData = new Object[TaskAt_thatDay.size()][4];
						                for (int i = 0; i < TaskAt_thatDay.size(); i++) {
						                	Task task = TaskAt_thatDay.get(i);
						                    newData[i][0] = task.getStatus() ? "Completed" : "Pending";
						                    newData[i][1] = task.getTaskName();
						                    newData[i][2] = task.getTaskNote();
						                    newData[i][3] = task.getDateAndTime();
						                }

						                // Update the table model with the new data
						                tableModel.setDataVector(newData, new String[]{"Status", "Task Name", "Notes", "Date"});
						                tableModel.fireTableDataChanged();
						                frame.dispose();
										
									}
									
								});
								
								
						centerPanel.add(name);
						centerPanel.add(note);
						centerPanel.add(date);
						centerPanel.add(save);
						frame.add(centerPanel, BorderLayout.CENTER);
						frame.setVisible(true);
					}
		    	 	}//Action over ride
		    	 	);//Parameter
		    	 	
		    	 // button2 
		    	 	
		    	 	button2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent i) {
							JFrame frame = new JFrame("Remove task");
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame.setSize(400,200);
							ImageIcon icon = mainIcon;
							frame.setIconImage(icon.getImage());
							frame.setResizable(false);
							frame.setLayout(new BorderLayout());
							JPanel centerPanel= new JPanel();
							centerPanel.setLayout(new GridLayout(2,2));
							
							
							JTextField name = new JTextField("Enter task name");
							JTextField date = new JTextField("MM/DD/YYYY");
							centerPanel.add(name);
							centerPanel.add(date);
							
							JButton remove= new JButton("REMOVE");
							remove.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
							
							String _name = name.getText();
							String _date = date.getText();
							boolean result = false;
							for( int J=0 ; J < TaskAt_thatDay.size(); J++) {
								Task find = TaskAt_thatDay.get(J);
								if(_name.equalsIgnoreCase(find.getTaskName()) && _date.equals(find.getDateAndTime())) {
									TaskAt_thatDay.remove(J);
									result=true;
								    Object[][] newData = new Object[TaskAt_thatDay.size()][4];
					                for (int I = 0; I < TaskAt_thatDay.size(); I++) {
					                	Task task = TaskAt_thatDay.get(I);
					                    newData[I][0] = task.getStatus() ? "Completed" : "Pending";
					                    newData[I][1] = task.getTaskName();
					                    newData[I][2] = task.getTaskNote();
					                    newData[I][3] = task.getDateAndTime();
					                }
					                	
					                // Update the table model with the new data
					                tableModel.setDataVector(newData, new String[]{"Status", "Task Name", "Notes", "Date"});
					                tableModel.fireTableDataChanged();
					                frame.dispose();
									
									
								}
							}
							if(result == false) {
								JOptionPane.showMessageDialog(null,"Didnt find the task", "Error", JOptionPane.PLAIN_MESSAGE);
							}
								}});
							centerPanel.add(remove);
							frame.add(centerPanel, BorderLayout.CENTER);
							frame.setVisible(true);
						}
		    	 	});
		    	 	
		    	 	
		    	 	//BUTTON JButton button3  -- EDIT TASK
		    	 	 button3.addActionListener(new ActionListener() {
		    	 		 @Override
		    	 		 public void actionPerformed(ActionEvent e) {
		    	 			 
								JFrame frame = new JFrame("Remove task");
								frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								frame.setSize(400,200);
								ImageIcon icon =mainIcon;
								frame.setIconImage(icon.getImage());
								frame.setResizable(false);
								frame.setLayout(new BorderLayout());
								JPanel centerPanel= new JPanel();
								centerPanel.setLayout(new GridLayout(2,2));
								
								String[]array = new String[TaskAt_thatDay.size()];
								for(int i=0; i< array.length; i++) {
									array[i]=TaskAt_thatDay.get(i).getTaskName();
								}
								JComboBox taskList = new JComboBox(array);
								///Using ~ indexA to get Indx of the task
								taskList.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										try {
											indexA=taskList.getSelectedIndex();
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								});
								
								
								
								
								String[]array2 = {"Name","Status","Date","Note"};
								JComboBox EDITList=new JComboBox(array2);
									EDITList.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											try {
												System.out.println(EDITList.getSelectedIndex());
												indexB=EDITList.getSelectedIndex();
												JLabel label=new JLabel();
												centerPanel.add(label);
												if(indexB==0) {
													centerPanel.remove(label);
												JLabel label2= new JLabel("enter a name");
												centerPanel.add(label2);
											     }else if(indexB==1) {
											    	 centerPanel.remove(label);
											    	 JLabel label2= new JLabel("T/F for status :)");
											    	 centerPanel.add(label2);
												}else if (indexB==2) {
													centerPanel.remove(label);
													JLabel label2= new JLabel("MM/DD/YYYY");
													centerPanel.add(label2);
												}else if(indexB==3 ) {
													centerPanel.remove(label);
													JLabel label2= new JLabel("Enter note !");
													centerPanel.add(label2);
													
												}
												
												centerPanel.revalidate();
												centerPanel.repaint();
											} catch (Exception e1) {
												System.out.println("Problem line 339 ");
												e1.printStackTrace();
											}
										}
									});
									JTextField txtF = new JTextField();
							
									JButton saveChanges = new JButton("Save");
									saveChanges.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent r) {
											System.out.println(indexA);
											System.out.println("b-------"+indexB);
											switch(indexB) {
											case 0:
												if(!txtF.getText().isEmpty()) {
												TaskAt_thatDay.get(indexA).setTaskName(txtF.getText()); //edit name
												}
												break;
											case 1:	
												
												txtF.setForeground(Color.BLUE);
												boolean temp = txtF.getText().equalsIgnoreCase("T")? true :false; //edit status
												TaskAt_thatDay.get(indexA).setStatus(temp);
												
												break;
											case 2:
												TaskAt_thatDay.get(indexA).setDateAndTime(txtF.getText());//FIXME needs improvement
											
											break;
											case 3:
												TaskAt_thatDay.get(indexA).setTaskNote(txtF.getText());								
											break;
											
											default : 
												if(!txtF.getText().isEmpty()) {
												TaskAt_thatDay.get(indexA).setTaskName(txtF.getText()); //edit name
												}
											}
											 // update tabel
										    Object[][] newData = new Object[TaskAt_thatDay.size()][4];
							                for (int I = 0; I < TaskAt_thatDay.size(); I++) {
							                	Task task = TaskAt_thatDay.get(I);
							                    newData[I][0] = task.getStatus() ? "Completed" : "Pending";
							                    newData[I][1] = task.getTaskName();
							                    newData[I][2] = task.getTaskNote();
							                    newData[I][3] = task.getDateAndTime();
							                }
							                	if(caseNum ==1)
							                		txtF.setText("T/F");
							                // Update the table model with the new data
							                tableModel.setDataVector(newData, new String[]{"Status", "Task Name", "Notes", "Date"});
							                tableModel.fireTableDataChanged();
							                frame.dispose();
										}
									});
								
								
									
				             
								centerPanel.add(taskList);
								centerPanel.add(EDITList);
								centerPanel.add(txtF);
								centerPanel.add(saveChanges);
								frame.add(centerPanel, BorderLayout.CENTER);
								frame.setVisible(true);
								
								
		    	 			 
		    	 		 }
		    	 	 });
		    	 	 
		    	 	 
		    	 	 
		    	 	 /// BUTTON 4 ADD GOAL 
		    	 	button4.addActionListener(new ActionListener() {
		    	 		
		    	 		@Override
		    	 		public void actionPerformed(ActionEvent e) {
		    	 			JFrame frame = new JFrame("Add goal");
		    	 			frame.setIconImage(mainIcon.getImage());
		    	 			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	 			frame.setSize(300,200);
		    	 			frame.setLayout(new FlowLayout(FlowLayout.LEADING));
		    	 			JTextField txtF = new JTextField("ENTER GOAL NAME");
		    	 			txtF.setPreferredSize(new Dimension(175,25));
		    	 			
		    	 			JButton TempButt = new JButton("ADD GOAL");
		    	 			TempButt.addActionListener(new ActionListener(){
		    	 				@Override
		    	 				public void actionPerformed(ActionEvent e) {
		    	 					String goalName = txtF.getText();
		    	 					if(goalName.isBlank()||goalName.equalsIgnoreCase("ENTER GOAL NAME") || goalName.equalsIgnoreCase("Enter a name ! ")) {
		    	 						txtF.setText("Enter a name ! ");
		    	 						txtF.setForeground(Color.RED);
		    	 					}else {
		    	 					Goal newGoal = new Goal(goalName);
		    	 					goals_for_user.add(newGoal);
		    	 					updateGoalList(goals_for_user,  model1); // fucking fixed it without using vidio nice one man !!!! 
		    	 					//model1.fireTableDataChanged(); /// THE TABLE IS NOT UPDATING 12/18/2024
		    	 					frame.dispose();
		    	 					}
		    	 				}
		    	 			});
		    	 			
		    	 	
		    	 			frame.add(txtF);
		    	 			frame.add(TempButt);
		    	 			
		    	 			
		    	 			
		    	 			frame.setVisible(true);	
		    	 		}
		    	 	});
		    	 	
		    	 	 			
		    	 	 
		    	 	 ///BUTTON 5 remove GOAL 
		    	 	 	    	 	
		    	 	button5.addActionListener(new ActionListener() {
		    	 		@Override
		    	 		public void actionPerformed(ActionEvent e){
		    	 			JFrame frame = new JFrame("Remove Goals ");
		    	 			frame.setIconImage(taskIcon.getImage());
		    	 			frame.setSize(200,100);
		    	 			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	 			
		    	 			
		    	 			JComboBox goalsList = new JComboBox(getNames(goals_for_user));

		    	 			
		    	 			JButton saveButton = new JButton("Save");
		    	 			saveButton.addActionListener(new ActionListener() {
		    	 				@Override
		    	 				public void actionPerformed (ActionEvent e) {
		    	 					int indexOfItem = goalsList.getSelectedIndex();
		    	 					goals_for_user.remove(indexOfItem);
		    	 					updateGoalList(goals_for_user, model1);
		    	 					frame.dispose();
		    	 					
		    	 				}
		    	 			});

		    	 			
		    	 		frame.setLayout(new FlowLayout(FlowLayout.LEADING));	
		    	 		frame.add(goalsList);
		    	 		frame.add(saveButton);	
		    	 		frame.setVisible(true); }
		    	 	});
		    	 	 
		    	 	 
		    	 	 // BUTTON 6 edit GOAL 
		    	 	
		    	 	button6.addActionListener(new ActionListener() {
		    	 		@Override
		    	 		public void actionPerformed(ActionEvent e){
		    	 			JFrame frame = new JFrame("Edit Goals ");
		    	 			frame.setIconImage(taskIcon.getImage());
		    	 			frame.setSize(250,100);
		    	 			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	 			JTextField txtF = new JTextField("Enter new name");
		    	 			txtF.setPreferredSize(new Dimension (150,25));
		    	 			txtF.setForeground(Color.BLUE);
		    	 			JComboBox goalsList = new JComboBox(getNames(goals_for_user));

		    	 			
		    	 			JButton saveButton = new JButton("Save");
		    	 			saveButton.addActionListener(new ActionListener() {
		    	 				@Override
		    	 				public void actionPerformed (ActionEvent e) {
		    	 					int indexOfItem = goalsList.getSelectedIndex();
		    	 					goals_for_user.get(indexOfItem).setName(txtF.getText());;
		    	 					updateGoalList(goals_for_user, model1);
		    	 					frame.dispose();
		    	 					
		    	 				}
		    	 			});
			    	 		frame.setLayout(new FlowLayout(FlowLayout.LEADING));	
			    	 		frame.add(goalsList);
			    	 		frame.add(txtF);
			    	 		frame.add(saveButton);	
			    	 		frame.setVisible(true);
		    	 		}});
		    	 	
		    	 	 
		    	 	buttons.add(button1);
		    	 	buttons.add(button2);
		    	 	buttons.add(button3);
		    	 	buttons.add(button4);
		    	 	buttons.add(button5);
		    	 	buttons.add(button6);
		    	 	
		    	mainCenter.add(buttons,BorderLayout.SOUTH);
			// MAIN PANEL - TO ADD A NEW WHOLE SIDE
		    MAIN_PANEL.add(mainCenter, BorderLayout.CENTER);
			MAIN_PANEL.add(eastPanel , BorderLayout.EAST);
			
			JLayeredPane layerdPane =new JLayeredPane();
			layerdPane.setBounds(0,0,1000,500);

			/// #add panels in here
			layerdPane.add(backGoundLabel,Integer.valueOf(0));
			layerdPane.add(MAIN_PANEL,Integer.valueOf(1));
			
			
			
			//# frame Settings
			JFrame frame = new JFrame("Main Menu-"+userName);
			frame.setIconImage(mainIcon.getImage());
			frame.add(layerdPane);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			//Send info on_Close
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					String ref = found.getName()+".txt";
					try(FileOutputStream  file = new FileOutputStream(ref)){
						PrintWriter write = new PrintWriter(file ,true);
						try {
							write.println(getAllGoals( found, goals_for_user ));
							write.println(sendAllTasks(found,TaskAt_thatDay));
							
							write.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}catch(IOException f ){
					System.out.println("\n ===File inport problem happened== \n");
					f.printStackTrace();
					}
					
					frame.dispose();
				}
				
			});
			frame.setLayout(null);
			frame.setResizable(false);
			frame.setSize(new Dimension(1000,500));
			frame.setVisible(true);
			

			
	}	
		
		
		
	
public static void updateGoalList(ArrayList<Goal> GOALLIST, DefaultTableModel  model1) {
	
	ArrayList<Goal> PASSED_goals_for_user= GOALLIST;
	System.out.print(PASSED_goals_for_user.size());
	System.out.println("size : "+ PASSED_goals_for_user.size());
    Object[][] dataGoal = new Object[PASSED_goals_for_user.size()][2];
    for (int i = 0; i < PASSED_goals_for_user.size(); i++) {
        Goal goal = PASSED_goals_for_user.get(i);
        dataGoal[i][0] = goal.getName();
        dataGoal[i][1] = goal.getStat();
    }
        model1.setDataVector(dataGoal,columnNames1 );
        model1.fireTableDataChanged();
        
}		

public static String[] getNames(ArrayList<Goal> goal_for_user) {
	String []names=null;
	if (goal_for_user!= null && goal_for_user.size()>=1) {
	names = new String[goal_for_user.size()];
	for(int i=0; i<names.length; i++)
	names[i]=goal_for_user.get(i).getName();
	}
	else {
		System.out.print("array at method get name failed ! goal array List is empty ");
	}
	return names;}

public static void  setData(Data user) {
	found = user;
}

// get all the goals form the Array and adds them in one formed String 
public static String getAllGoals(Data found, ArrayList<Goal> goals_for_user ) throws IOException {
	  StringWriter out   = new StringWriter();
	    PrintWriter  writer = new PrintWriter(out);
	    writer.println("Start_Goals");
	    for(int i=0; i< goals_for_user.size();i++) {
	    	System.out.println(goals_for_user.get(i));
	    	writer.println(goals_for_user.get(i).toString());
	    }
	    writer.println("END_Goals");
	    writer.close();
	   System.out.print(out);
	return out.toString();
}

// get the goal from a the data Username file if not found makes new one 
public static void setAllGoals(Data found, ArrayList<Goal> goals_for_user ) throws IOException {
	try (FileInputStream file = new FileInputStream(found.getName()+".txt")){
		Scanner read = new Scanner(file);
		//System.out.println("Here");
		if(read.hasNext()) {
			String enterGoal = read.nextLine();
			int index=-1; // FIXME
			//System.out.println("Here2");FIXME
			if (enterGoal.equals("Start_Goals")) {
				String endGoal="";
				//System.out.println("Here3");FIXME
				while(!endGoal.equalsIgnoreCase("END_Goals")) {
					//System.out.println("Here4");FIXME
					endGoal = read.nextLine().trim();
					if (!endGoal.equalsIgnoreCase("END_Goals")) {
					String name = endGoal.substring(0,endGoal.indexOf(','));
					System.out.print("\n\n -----------------Status : "+endGoal.substring(endGoal.indexOf(',')+1)+"   \n\n");
					boolean status= endGoal.substring(endGoal.indexOf(',')+1).trim().equals("true")?true:false;
					Goal Temp = new Goal(name, status);
					goals_for_user.add(Temp);
					index++;
					System.out.println("Goal---"+goals_for_user.get(index));
					}
			
				}// while goals started
			}// if equals Start
		}// if has goals in file 
		
		
	}catch(IOException f) {
		System.out.println("file has not been found i made new file and  i am writing in it");
		FileOutputStream newFile= new FileOutputStream(found.getName()+".txt");
		newFile.close();
		// IF FILE WAS NOT FOUND THIS FILE MAKES A NEW ONE AND THE PRINER AT THE CLOSING WOULD CONSTRUCT THE FILE TO ACEPT DETA AS START TO END
	}
		
}//method 
public static void  getAllTasks(Data found,ArrayList<Task> TaskAt_thatDay ) throws IOException {
FileInputStream file = new FileInputStream(found.getName()+".txt");
		Scanner read = new Scanner(file);
		System.out.println("Here at task");
		if(read.hasNext()) {	
			String enterTask = read.nextLine();
			System.out.println(enterTask);
			int index=-1; // FIXME
			System.out.println("Here at task 2");//FIXME
			while(!enterTask.equalsIgnoreCase("Start_Tasks")) {
				enterTask=read.nextLine();
				}//dumy reader to empty buffer
			if (enterTask.equalsIgnoreCase("Start_Tasks")) {
				String endTask="";
				System.out.println("Here at task 3");//FIXME
				while(!endTask.equalsIgnoreCase("END_Task")) {
					System.out.println("Here4");//FIXME
					
					endTask = read.nextLine().trim();
					if (!endTask.equalsIgnoreCase("END_Task")) {
						
						
					String name = endTask.substring(0,endTask.indexOf(','));
					System.out.println("Name : "+name);
			
					
					boolean status= endTask.substring(endTask.indexOf(',')+1,endTask.indexOf('<')).trim().equals("true")?true:false;
					System.out.println("Status: "+status);
					
					String date = endTask.substring(endTask.indexOf('<')+1,endTask.indexOf('>'));
						System.out.println("Date : "+date);
						
					String note = endTask.substring(endTask.indexOf('>')+1,endTask.indexOf('#'));
					System.out.println("Note : "+note);
						
						
					Task Temp = new Task(status,name,note,date);// FIXME
				    TaskAt_thatDay.add(Temp);
					index++;
					System.out.println("\n{Taks}\n"+TaskAt_thatDay.get(index));
					}
			
				}// while task started
			}// if equals Start
		}
		read.close();
}
public static String sendAllTasks(Data found,ArrayList<Task> TaskAt_thatDay )throws IOException{
	//FIXME to wite in  a String
	
	  StringWriter out   = new StringWriter();
	    PrintWriter  writer = new PrintWriter(out);
	    
	    System.out.println("Start_Tasks"); // REMOVEME FIXME
	    writer.println("Start_Tasks");
	    
	    for(int i=0; i<TaskAt_thatDay.size();i++) {
	    	writer.println(TaskAt_thatDay.get(i).getTaskForDataSaving());
	    	System.out.println("Testing this should be the task beaing transfered : "+TaskAt_thatDay.get(i).getTaskForDataSaving());
	    }
	    
	    writer.println("END_Task");
	    System.out.println("END_Task");// REMOVEME FIXME
	    
	return out.toString();
}


public static void writeNewData(Data obj) throws IOException{
	
	FileInputStream file = new FileInputStream("deta.txt");
	Scanner read = new Scanner(file);
	
	StringWriter readedFromFile=new StringWriter();
	PrintWriter p = new PrintWriter(readedFromFile);

		while(read.hasNext()) {
		p.println(read.nextLine());
		}
		
		p.println(obj.toString());
		FileOutputStream f = new FileOutputStream("deta.txt");
		PrintWriter s = new PrintWriter(f);
		String str= readedFromFile.toString();
		s.println(str);
		s.close();
		f.close();
		read.close();
		readedFromFile.close();
		p.close();
		System.out.println("new data file have been made with the new data");
	
	
}
}//class
