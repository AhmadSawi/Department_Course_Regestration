/*
 * this class represents the instructor/proffessors that will be teaching courses and their favorite courses to teach
 * */
package application;

import java.util.ArrayList;

public class Instructor {

	static int counter = 0;
	private int id;
	private String name;
	private ArrayList<String> favCourses;
	private ArrayList<String> favLabs;
	
	public Instructor(int id, String name, ArrayList<String> favCourses, ArrayList<String> favLabs) {
		super();
		this.id = id;
		this.name = name;
		this.favCourses = favCourses;
		this.favLabs = favLabs;
		Instructor.counter++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getFavCourses() {
		return favCourses;
	}

	public void setFavCourses(ArrayList<String> favCourses) {
		this.favCourses = favCourses;
	}

	public ArrayList<String> getFavLabs() {
		return favLabs;
	}

	public void setFavLabs(ArrayList<String> favLabs) {
		this.favLabs = favLabs;
	}

	@Override
	public String toString() {
		return "Instructors [id=" + id + ", name=" + name + ", favCourses=" + favCourses + ", favLabs=" + favLabs + "]";
	}
	
}
