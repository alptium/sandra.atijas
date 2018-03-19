package elitesports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main  {
	
	public static void main(String[] args) throws IOException {
		
		String[] candidates_names;
		String[] candidates_surnames;
		double[] candidates_ages;
		double[] candidates_height;
		double[] candidates_weight;
		List<String> lstAnswers = new ArrayList<String>();
		int totalNumberCandidates = 0;
		
		int minAge = 20;
		int minTeams = 2;
		String status = "Rejected";
	
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Type the number of candidates who will do the questionaire?");
			totalNumberCandidates = sc.nextInt();
			
			candidates_names = new String[totalNumberCandidates];
			candidates_surnames = new String[totalNumberCandidates];
			candidates_ages = new double[totalNumberCandidates];
			candidates_height = new double[totalNumberCandidates];
			candidates_weight = new double[totalNumberCandidates];
			
			String[] listStatuses = new String[totalNumberCandidates];
			char toQuit = 'N';
			
			while(true) {
				for (int i = 0; i < totalNumberCandidates; i++) {
					
					System.out.println("===========QUESTIONNAIRE===========");
					
					lstAnswers.clear();
					
					System.out.println("Enter candidate's name?");
					candidates_names[i] = sc.next();
					
					System.out.println("Enter candidate's surname?");
					candidates_surnames[i] = sc.next();
					
					System.out.println("1. question: Enter your age?");
					candidates_ages[i]= sc.nextDouble();
					
					System.out.println("2. question: Enter your height (in cm)?");
					candidates_height[i]= sc.nextDouble();
					
					System.out.println("3. question: Enter your weight (in kg)?");
					candidates_weight[i]= sc.nextDouble();
					
					System.out.println("4. question: Did you play in more than 2 teams before?");
					lstAnswers.add(sc.next());
					
					System.out.println("5. question: Do you have some health problems (Y/N)?");
					lstAnswers.add(sc.next());
					
					System.out.println("6. question: Are you smoker (Y/N)?");
					lstAnswers.add(sc.next());
					
					System.out.println("7. question: Can you focus on game under presure? (Y/N)?");
					lstAnswers.add(sc.next());
					
					System.out.println("8. question: Which possition would you play?");
					System.out.println("C- center; PG - Point Guard; SG - Shooting Guard; SF - Small Forward; PF - Power Forward");
					
					lstAnswers.add(sc.next());
					
					System.out.println("9. question: Are a team player (have a team spirit) (Y/N)?");
					lstAnswers.add(sc.next());
					
					System.out.println("10. question: Are you ready to share your statitics with all players? (Y/N)?");
					lstAnswers.add(sc.next());
					
					System.out.println("===========" + candidates_names[i] + " " +  candidates_surnames[i] + "===========");
					System.out.println("Age:" + candidates_ages[i]); 
					System.out.println("Height:" + candidates_height[i]); 
					System.out.println("Weight:" + candidates_weight[i]); 
					for (int j = 0; j < lstAnswers.size(); j++) {
						System.out.println(lstAnswers.get(j));
					}
					
					
					// Candidate needs to be older than minAge and already played somewhere
					if (candidates_ages[i] > minAge && lstAnswers.get(0).equalsIgnoreCase("Y")) {
	
						// Candidate cannot have health problems, need t have sport's spirit, to focus even with preasure, to share statistics with teammates
						if (
						   lstAnswers.get(1).equalsIgnoreCase("N") && lstAnswers.get(3).equalsIgnoreCase("Y") &&
						   lstAnswers.get(6).equalsIgnoreCase("Y") && lstAnswers.get(5).equalsIgnoreCase("Y") &&
							((lstAnswers.get(4)).equalsIgnoreCase("C") || (lstAnswers.get(4)).equalsIgnoreCase("SG"))
							) 
						    {
							
							status = "Accepted";
						}
					}
					listStatuses[i] = candidates_names[i] + " " +  candidates_surnames[i] + " with status " + status;
						
				}		
				
				for (int j = 0; j<listStatuses.length; j++){
					System.out.println(listStatuses[j]);
				}
				
				System.out.println("Do you want to quit the program? Y/N ");
				toQuit = sc.next().charAt(0);
				
				if (toQuit ==  'Y' || toQuit == 'y') {
					System.exit(0);
				}	
			}
		}
				
	}
	
}

