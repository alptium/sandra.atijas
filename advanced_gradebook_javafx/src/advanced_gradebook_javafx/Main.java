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
	
	private TextField tfNumStudents = new TextField();
	private TextField tfStundetFirstName = new TextField();
    private TextField tfStudentLastName = new TextField();
    private TextField tfStudentGrade = new TextField();
	
	Label lb = new Label();
	Label lb_max = new Label();
	Label lb_min = new Label();
	Label lb_avgGrade = new Label();
	GridPane gridPaneReport = new GridPane();
	int totalNumberStudents = 0;
	String[] names;
	String[] surnames;
	int[] grades; 
	int totalGrade = 0;
	double avgGrade = 0.0;
	int i = 0;
	
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
        GridPane gridPane = new GridPane();
     
        gridPane.setVgap(15); //distance between objects vertically
        gridPane.setHgap(10); //distance between objects horizontally
        
        gridPane.add(new Label("Enter the Number of Students: "), 0, 0); // column=0 row=0
        gridPane.add(tfNumStudents,1 ,0);
        
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
        gridPane.add(lb, 0, 7);
        gridPane.add (gridPaneReport, 0, 8);
        gridPane.add(lb_max, 0, 9);
        gridPane.add(lb_min, 0, 10);
        gridPane.add(lb_avgGrade, 0, 11);
        
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
			
			totalNumberStudents = Integer.parseInt(tfNumStudents.getText());
			names = new String[totalNumberStudents];
			surnames = new String[totalNumberStudents];
			grades = new int[totalNumberStudents];

			lb.setText("Number of students is " + Integer.toString(totalNumberStudents));
		} 
		catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Warning");
	        alert.setHeaderText("Invalid Values");
	        alert.setContentText("You need to enter posstive Integer number.");
	        alert.showAndWait();
			}
    }
	
	public void addStudentData(){
		
		if (i < totalNumberStudents) {
			
			names[i] = tfStundetFirstName.getText();
			surnames[i] = tfStudentLastName.getText();
            int tmpGrades = 0;
            
            try {	
            	tmpGrades = Integer.parseInt(tfStudentGrade.getText());
            	
            	if (tmpGrades < 0) {
            		Alert alert = new Alert(Alert.AlertType.WARNING);
            		alert.setTitle("Warning");
					alert.setHeaderText("Invalid Values");
					alert.setContentText("The student's grade has to be a positive number.");
					alert.showAndWait();
					}	
            	else {
                	grades[i] = tmpGrades;
                    i++;
            	}
                
            } 
            catch (Exception e) {	
            	Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setTitle("Warning");
			    alert.setHeaderText("Invalid Values");
			    alert.setContentText("The student's grade has to be a positive number...");
			    alert.showAndWait();
			}
            
			tfStundetFirstName.clear();
			tfStudentLastName.clear();
			tfStudentGrade.clear();
			
		}
		
		if (i == totalNumberStudents )
			btNextStudent.setDisable(true);
	}
	
	
	public void studentsReport() {
		
	    for(int i = 0 ; i < totalNumberStudents; i++){
	    		
	    		gridPaneReport.add(new Label(names[i] + " " + surnames[i] + " has grade " + Integer.toString(grades[i])),0, i);
	    		totalGrade += grades[i];
		}
		
		avgGrade = (double)totalGrade/totalNumberStudents;
		
		lb_avgGrade.setText("Average grade is " + Double.toString(avgGrade));
		
		//Minimal i maximal grade of all students
		int minGrade = grades[0];
		int maxGrade = grades[0];
		
		for(int i = 0 ; i < grades.length; i++){
			
			if (grades[i] < minGrade) {
				minGrade = grades[i];
			}
			
			if (grades[i] > maxGrade) {
				maxGrade = grades[i];
			}		
		}
		
		lb_max.setText("Maximal grade is: " + Integer.toString(maxGrade));
		lb_min.setText("Minimal grade is: " + Integer.toString(minGrade));

	}
	 
	
	public void quitApplication() {
		Platform.exit(); //Close application
	}


}
