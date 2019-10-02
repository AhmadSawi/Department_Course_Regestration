package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandeler {
	private String InstructorsFileAddress = "Instructors.txt";
	private String RoomFileAddress = "Rooms.txt";
	private String TimesFileAddress = "Times.txt";
	private String CoursesFileAddress = "Courses.txt";

	
	ArrayList<Instructor> readInstructors() throws FileNotFoundException{
		File file = new File(InstructorsFileAddress);
		Scanner sc = new Scanner(file);
		
		ArrayList<Instructor> instructorsFromFile = new ArrayList<Instructor>();
		
		int lineState = 0; //state 0 is file header //state 1 is space //state 2 is instructor name //states 3 and 4 are courses
		String instructorName = null;
		ArrayList<String> favCourses = new ArrayList<String>();
		ArrayList<String> favLabs = new ArrayList<String>();
		
		while (sc.hasNextLine()){
			String line;
			line = sc.nextLine();
			
			if(lineState == 2){
				instructorName = line;
				instructorName = instructorName.trim();
			}else if(lineState == 3){
				String[] splitted = line.split("-");  //0 is course //1 is first course and so on
				favCourses.add(splitted[1].trim());
				favCourses.add(splitted[2].trim());
				favCourses.add(splitted[3].trim());
				favCourses.add(splitted[4].trim());
				favCourses.add(splitted[5].trim());
			}else if(lineState == 4){
				String[] splitted = line.split("-");  //0 is course //1 is first course and so on
				favLabs.add(splitted[1].trim());
				favLabs.add(splitted[2].trim());
				favLabs.add(splitted[3].trim());
				favLabs.add(splitted[4].trim());
				favLabs.add(splitted[5].trim());

				@SuppressWarnings("unchecked")
				Instructor temp = new Instructor(Instructor.counter, instructorName, (ArrayList<String>)favCourses.clone(), (ArrayList<String>)favLabs.clone());

				instructorsFromFile.add(temp);
				
				lineState = 0;
				favCourses.clear();
				favLabs.clear();
			}
			
				lineState++;
		}
		
		sc.close();
		return instructorsFromFile;
	} 
	
	
	ArrayList<Room> readRooms() throws FileNotFoundException{
		File file = new File(RoomFileAddress);
		Scanner sc = new Scanner(file);
		
		ArrayList<Room> roomsFromFile = new ArrayList<Room>();
		
		int lineState = 0; //state 0 is file header //state 1 is empty //state 2 is building //state 3 are rooms
		String buildingName = null;
		String roomName = null;
		int capacity = 0;
		
		while (sc.hasNextLine()){
			String line;
			line = sc.nextLine();
			
			if(line.equals("")){
				lineState = 0;
			}
			
			if(lineState == 1){
				buildingName = line;
				buildingName = buildingName.trim();
			}else if(lineState == 2){
				String[] splitted = line.split("-");  //0 is room name //1 is capacity
				roomName = splitted[0].trim();
				capacity = Integer.parseInt(splitted[1].trim()); 
				
				Room room = new Room(roomName, new Building(Building.count, buildingName), false, capacity);
				roomsFromFile.add(room);
				lineState = 1;
			} 
			
				lineState++;
		}
		
		sc.close();
		return roomsFromFile;
	} 
	
	
	ArrayList<TimeSlot> readTimes() throws FileNotFoundException{
		File file = new File(TimesFileAddress);
		Scanner sc = new Scanner(file);
		
		ArrayList<TimeSlot> TimesFromFile = new ArrayList<TimeSlot>();
		
		int lineState = 0; //state 0 is file header //state 1 is empty //state 2 is building //state 3 are rooms
		
		while (sc.hasNextLine()){
			String line;
			line = sc.nextLine();
			
			if(lineState == 3){   //SMW
				String[] splitted = line.split("-"); 
				
				for(int i = 0; i < splitted.length; i++){
					TimeSlot time = new TimeSlot(TimeSlot.count, splitted[i].trim(), true, false, false);
					TimesFromFile.add(time);
				}
				//manually adding lab times which are static at all times
				TimeSlot time = new TimeSlot(TimeSlot.count, "2", true, false, true);
				TimesFromFile.add(time);
			}
				
			if(lineState == 3){   //TR
				String[] splitted = line.split("-"); 
				
				for(int i = 0; i < splitted.length; i++){
					TimeSlot time = new TimeSlot(TimeSlot.count, splitted[i].trim(), false, true, false);
					TimesFromFile.add(time);
				}
				//manually adding lab times which are static at all times
				TimeSlot time = new TimeSlot(TimeSlot.count, "8", false, true, true);
				TimesFromFile.add(time);
				time = new TimeSlot(TimeSlot.count, "11", false, true, true);
				TimesFromFile.add(time);
				time = new TimeSlot(TimeSlot.count, "2", false, true, true);
				TimesFromFile.add(time);
			}

				lineState++;
		}
		
		sc.close();
		return TimesFromFile;
	} 
	
	
	ArrayList<Course> readCourses() throws FileNotFoundException{
		File file = new File(CoursesFileAddress);
		Scanner sc = new Scanner(file);
		
		ArrayList<Course> coursesFromFile = new ArrayList<Course>();
		
		int lineState = 0; //state 0 is file header //state 1 is empty //state 2 is building //state 3 are rooms
		int numberOfSections = 0;
		int numberOfStudentsPerSection = 0;
		
		while (sc.hasNextLine()){
			String line;
			line = sc.nextLine();

			if(lineState == 2){
				String[] splitted = line.split("-"); //0 is kind //1 is id //2 is name //3 is section //4 is count
				Boolean lab = false;
				
				if(splitted[0].trim().equals("L"))
					lab = true;
				
				int level = Character.getNumericValue(splitted[1].trim().charAt(4));
				
				numberOfStudentsPerSection = Integer.parseInt(splitted[4].trim());
				
				numberOfSections = Integer.parseInt(splitted[3].trim());
				for(int i = 1; i <= numberOfSections; i++){
					Course course = new Course(splitted[1].trim(), splitted[2].trim(), level, null, i, numberOfStudentsPerSection, lab, false);
					coursesFromFile.add(course);
					//System.out.println(course);
				}
				
				lineState = 1;
			}
			
				lineState++;
		}
		
		sc.close();
		return coursesFromFile;
	} 
	
	
	
}
