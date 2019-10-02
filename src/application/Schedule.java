package application;

import java.util.ArrayList;
import java.util.Arrays;

public class Schedule {
	private ArrayList<Class> classes;
	private Data data;
	
	public Schedule(Data data) {
		super();
		this.data = data;
		classes = new ArrayList<Class>(); //(Course.count)
	}
	
	public void randomizeScedule(){
		
		for(int courseIndex = 0; courseIndex < Course.count; courseIndex++){
			Course currentCourse = data.getCourses().get(courseIndex);
			Instructor randomInstructor = data.getInstructors().get((int) (Math.random() * Instructor.counter));
			Room randomRoom = data.getRooms().get((int) (Math.random() * Room.count));
			
			
			//TimeSlot randomTimee = data.getTimes().get((int) (Math.random() * TimeSlot.count));
			/*
			 * to only allow labs courses to take lab times and normal courses to take course times
			 * */
//			if(currentCourse.isLab()){
//				if(randomTime.isLab())
//					currentCourse.setTime(randomTime);
//				
//				else{
//					while(!randomTime.isLab())
//						 randomTime = data.getTimes().get((int) (Math.random() * TimeSlot.count));
//					currentCourse.setTime(randomTime);
//				}
//			}
//			
//			if(!currentCourse.isLab()){
//				if(!randomTime.isLab())
//					currentCourse.setTime(randomTime);
//				
//				else{
//					while(randomTime.isLab())
//						 randomTime = data.getTimes().get((int) (Math.random() * TimeSlot.count));
//					currentCourse.setTime(randomTime);
//				}
//			}
	
			TimeSlot randomTime = null;
			
			if(currentCourse.isLab())
				randomTime = data.getLabTimes().get((int) (Math.random() * data.getLabTimes().size()));
			else
				randomTime = data.getCourseTimes().get((int) (Math.random() * data.getCourseTimes().size()));
			
			currentCourse.setTime(randomTime);
			
			Class randomClass = new Class(Class.count, currentCourse, randomInstructor, randomRoom, randomTime);
			classes.add(randomClass);
			//System.out.println(randomClass);
		}
		Class.count = 0;
	}
	
	public int calculateHardCounstraintFitness(){
		int fitness = 0;

		ArrayList<Integer> instructorIds = new ArrayList<Integer>();
		
		for(int i = 0; i < classes.size(); i++){
			int instructorID = classes.get(i).getInstructor().getId();
			
			//check if course from favorites
			String currentCourseId = classes.get(i).getCourse().getId();
			
			if(classes.get(i).getCourse().isLab()){
				if(!data.getInstructors().get(instructorID).getFavLabs().contains(currentCourseId))
					fitness = fitness - 10;
			}else {
				if(!data.getInstructors().get(instructorID).getFavCourses().contains(currentCourseId))
					fitness = fitness - 10;
			}
			
			
			//keep array of instructor ids for future cheking
			if(!instructorIds.contains(instructorID))
				instructorIds.add(instructorID);
			
			//check for conflicts 
			for(int j = i+1; j < classes.size(); j++){
				int nextInstructorID = classes.get(j).getInstructor().getId();
				
				if(instructorID == nextInstructorID){
					if(classes.get(i).getTime().getId() ==  classes.get(j).getTime().getId())
						fitness = fitness - 10;
				}
			}
		}
		
//		for(int i = 0; i < instructorIds.size(); i++){
//			System.out.println(instructorIds.get(i));
//		}
		
		//now we have a list of all instructor ids, now we find their courses
		for(int i = 0; i < instructorIds.size(); i++){
			ArrayList<Course> courses = getCoursesInstructorTeaches(i);
			int totalHours = 0;
			int totalNonLabCourses = 0;
			
			for(int j = 0; j < courses.size(); j++){
				totalHours += courses.get(j).getLength();
				if(!courses.get(j).isLab())
					totalNonLabCourses++;
			}
			
//			if(totalHours < 12)
//				fitness = fitness - (12 - totalHours);
//			
//			if(totalHours > 18)
//				fitness = fitness - (totalHours - 18);

			if(!(totalHours >= 12 && totalHours <= 18))
				fitness = fitness - 1;
			
			if(totalNonLabCourses < 2)
				fitness = fitness - 10;

			
			//checking if 4 consiquitive classes constraint is broken
			if(courses.size() >= 4){
				//we will be using two arrays each one representing a day, each cell is a unit of time and we will count breaks afterwards
				boolean smwArray[] = new boolean[9]; //0 is 8-9 //1 is 9-10 and so on
				boolean trArray[] = new boolean[6];
				//if lab it will take two slots from tr or 3 slots from smw
				
				Arrays.fill(smwArray, false);
				
				Arrays.fill(trArray, false);
				
				for(int j = 0; j < courses.size(); j++){
					if(!(courses.get(j).getTime().isLab()) && courses.get(j).getTime().isSMW()){
						if(courses.get(j).getTime().equals("8"))
							smwArray[0] = true;
						else if(courses.get(j).getTime().equals("9"))
							smwArray[1] = true;
						else if(courses.get(j).getTime().equals("10"))
							smwArray[2] = true;
						else if(courses.get(j).getTime().equals("11"))
							smwArray[3] = true;
						else if(courses.get(j).getTime().equals("12"))
							smwArray[4] = true;
						else if(courses.get(j).getTime().equals("1"))
							smwArray[5] = true;
						else if(courses.get(j).getTime().equals("2"))
							smwArray[6] = true;
						else if(courses.get(j).getTime().equals("3"))
							smwArray[7] = true;
						else if(courses.get(j).getTime().equals("4"))
							smwArray[8] = true;
					}else if(!(courses.get(j).getTime().isLab()) && courses.get(j).getTime().isTR()){
						if(courses.get(j).getTime().equals("8"))
							trArray[0] = true;
						else if(courses.get(j).getTime().equals("9:30"))
							trArray[1] = true;
						else if(courses.get(j).getTime().equals("11"))
							trArray[2] = true;
						else if(courses.get(j).getTime().equals("12:30"))
							trArray[3] = true;
						else if(courses.get(j).getTime().equals("2"))
							trArray[4] = true;
						else if(courses.get(j).getTime().equals("3:30"))
							trArray[5] = true;
					}
				}
				
				//check if 4 or more consicutive trues in arrays
				//smw array
				for(int j = 0; j < smwArray.length - 3; j++){
					if(!(smwArray[j] && smwArray[j+1] && smwArray[j+2] && smwArray[j+3])){
						//fitness = fitness - 1;		
					}
				}
				
				//tr array
				for(int j = 0; j < trArray.length - 3; j++){
					if(!(trArray[j] && trArray[j+1] && trArray[j+2] && trArray[j+3])){
						//fitness = fitness - 1;	
					}
				}
			}
		}
		
		//System.out.println("Hard constarint fitness = " + fitness);
		return fitness;
	}
	
	
	int calculateSoftCounstraintFitness(){
		int fitness = 0;
		//we chose to do the soft constraint that minimizes number of lectures at 8 
		fitness -= calculateCoursesAt8();
		
		//and also reducing courses in the same level at the same time slot
		fitness -= calculateNumberOfSameLevelCoursesAtSameTime();
		
		//and the courses outside of masri
		fitness -= calculateNumberOfNonMasriRooms();
		
		//System.out.println("soft Constraint fitness = " + fitness);
		return fitness;
	}
	
	
	private int calculateCoursesAt8(){
		int numberOfCoursesAt8 = 0;
		
		for(int i = 0; i < classes.size(); i++){
			if(classes.get(i).getTime().getTime().equals("8"))
				numberOfCoursesAt8++;
		}
		return numberOfCoursesAt8;
	}
	
	private int calculateNumberOfSameLevelCoursesAtSameTime(){
		int numberOfSameLevelCoursesAtSameTime = 0;
		
		//get the courses at each level
		//suppose we have 5 levels only 1 through 5
		for(int i = 1; i <= 5; i++){
			int times[] = new int[TimeSlot.count]; //represents ids
			Arrays.fill(times, 0);
			for(int j = 0; j < classes.size(); j++){
				if(classes.get(j).getCourse().getLevel() == i){
					times[classes.get(j).getTime().getId()]++;
				}
			}
			for(int j = 0; j < times.length; j++){
				if(times[j] >= 2)
					numberOfSameLevelCoursesAtSameTime += (times[j] - 1);
			}
		}
		return numberOfSameLevelCoursesAtSameTime;
	}
	
	
	private int calculateNumberOfNonMasriRooms(){
		int numberOfNonMasriRooms = 0;
		
		for(int i = 0; i < classes.size(); i++){
			if(!(classes.get(i).getRoom().getBuilding().getName().equals("Masri")))
					numberOfNonMasriRooms++;
		}
		return numberOfNonMasriRooms;
	}
	
	
	private ArrayList<Course> getCoursesInstructorTeaches(int id){
		ArrayList<Course> courses = new ArrayList<Course>();
		
		for(int i = 0; i < classes.size(); i++){
			int instructorId = classes.get(i).getInstructor().getId();
			if(instructorId == id)
				courses.add(classes.get(i).getCourse());
		}
		return courses;
	}

	public ArrayList<Class> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}

	
}
