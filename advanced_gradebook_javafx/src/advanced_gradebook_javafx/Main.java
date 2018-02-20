package advanced_gradebook_javafx;


import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private TextField tfNumOfStudents = new TextField();
	private TextField tfStundetFirstName = new TextField();
    private TextField tfStudentLastName = new TextField();
    private TextField tfStudentGrade = new TextField();
	
	Label lb = new Label();
	int totalNumberofStudents = 0;
	String[][] students; 
	int[] grades; 
	int totalGrade = 0;
	double avgGrade = 0.0;
	
	private Button btEnterNum = new Button("Submit");
	private Button btNextStudent = new Button("Add Student");
	private Button btRunReport = new Button("Run Report");
	private Button btQuit = new Button("Quit");
	
	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Create the UI
        GridPane gridPane =new GridPane();
     
        gridPane.setVgap(15); //distance between objects vertically
        gridPane.setHgap(10); //distance between objects horizontally
        
        gridPane.add(new Label("Enter the Number of Students: "), 0, 0); // column=0 row=0
        gridPane.add(tfNumOfStudents,1 ,0);
        
        gridPane.add(btEnterNum, 2, 0);

        gridPane.add(new Label("Enter student's First Name:"), 0, 1);
        gridPane.add(tfStundetFirstName, 1, 1);
        gridPane.add(new Label("Enter student's Last Name:"), 0, 2);
        gridPane.add(tfStudentLastName, 1, 2);
        gridPane.add(new Label("Enter student grade:"), 0, 3);
        gridPane.add(tfStudentGrade, 1, 3);
        gridPane.add(btNextStudent, 1, 4);
      
        
        gridPane.add(btQuit, 0, 6);
        gridPane.setAlignment(Pos.CENTER);
        tfStundetFirstName.setAlignment(Pos.BOTTOM_RIGHT);
        tfStudentLastName.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(btRunReport, 1, 6);
        gridPane.add(lb, 1, 7);
        
        btEnterNum.setOnAction(e -> getNumberOfStudents()); //On click it will call a method getArraySize
        btNextStudent.setOnAction(e -> addStudentData());
        btRunReport.setOnAction(e -> studentsReport());
        btQuit.setOnAction(e -> quitApplication()); //On click it will call a method quitApplication
	
		
		Scene scene = new Scene(gridPane,500, 500);
		stage.setTitle("Advanced gradebook"); // Set title
		
		stage.setScene(scene);
		stage.show();

		
	}
	
	
	public void getNumberOfStudents() {
		try {
			totalNumberofStudents = Integer.parseInt(tfNumOfStudents.getText());
			students = new String[totalNumberofStudents][2];
			grades = new int[totalNumberofStudents];
			lb.setText(Integer.toString(totalNumberofStudents));
		} 
		catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Warning");
	        alert.setHeaderText("Enter Values");
	        alert.setContentText("You have to enter Integer Numbers");
	        alert.showAndWait();
			}
    }
	
	public void addStudentData(){
		 
		 for(int i = 0 ; i < totalNumberofStudents; i++){
            
			students[i][0] = tfStundetFirstName.getText();
			students[i][1] = tfStudentLastName.getText();
            grades[i] = Integer.parseInt(tfStudentGrade.getText());
		 }  
		// btNextStudent.setDisable(true);
		 
		 tfStundetFirstName.clear();
		 tfStudentLastName.clear();
		 tfStudentGrade.clear();
	}
	
	
	public void studentsReport() {
		
	    for(int i = 0 ; i < totalNumberofStudents ; i++){
	    	for (int j = 0; j < 2; i++) {
	    		Label lb_students = new Label();
	    		lb_students.setText("********");
	    		lb_students.setText(students[i][j]);
	    	//lb_students[i].setText("Student " + students[i][0] + " " + students[i][1] + " has grade " + grades[i]);
			//totalGrade += grades[i];
	    	}
		}
		
	 //   empPayroll.println("Total grade of all students is " + totalGrade);
		
	   // empPayroll.println("Number of all students is " + totalNumberofStudents);
		
		avgGrade = (double)totalGrade/totalNumberofStudents;
				
		//empPayroll.println("Average grade of all students is " + String.format("%.2g%n", avgGrade));
		
		
		//Minimal i maximal grade of all students
		int minGrade = grades[0];
		int maxGrade = grades[0];
		
		for(int i = 0 ; i < grades.length ; i++){
			
			if (grades[i] < minGrade) {
				minGrade = grades[i];
			}
			
			if (grades[i] > maxGrade) {
				maxGrade = grades[i];
			}		
		}
		             
		//empPayroll.println("Minimal grade of all students is " + minGrade);
		//empPayroll.println("Maximal grade of all students is " + maxGrade + "\n")
	}
	 
	
	public void quitApplication() {
		Platform.exit(); //Close application
	}


}
