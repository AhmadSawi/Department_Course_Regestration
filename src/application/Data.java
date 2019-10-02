package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Data {

	private ArrayList<Instructor> instructors;
	private ArrayList<Course> courses;
	private ArrayList<TimeSlot> times;
	private ArrayList<Room> rooms;
	
	public Data() throws FileNotFoundException {
		super();
		fill();
	}
	
	private void fill() throws FileNotFoundException{
		FileHandeler handle = new FileHandeler();
		courses = handle.readCourses();
		instructors = handle.readInstructors();
		rooms = handle.readRooms();
		times = handle.readTimes();
	}

	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(ArrayList<Instructor> instructors) {
		this.instructors = instructors;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public ArrayList<TimeSlot> getTimes() {
		return times;
	}

	public ArrayList<TimeSlot> getLabTimes() {
		ArrayList<TimeSlot> labTimes = new ArrayList<TimeSlot>();
		
		for(int i = 0; i < TimeSlot.count; i++){
			if(times.get(i).isLab())
				labTimes.add(times.get(i));
		}
		return labTimes;
	}
	
	public ArrayList<TimeSlot> getCourseTimes() {
		ArrayList<TimeSlot> courseTimes = new ArrayList<TimeSlot>();
		
		for(int i = 0; i < TimeSlot.count; i++){
			if(!times.get(i).isLab())
				courseTimes.add(times.get(i));
		}
		return courseTimes;
	}
	
	public void setTimes(ArrayList<TimeSlot> times) {
		this.times = times;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
}
