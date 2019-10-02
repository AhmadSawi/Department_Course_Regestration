/*
 * This class represents a class which is a collection of a course, who will be teaching it, what time, and where
 * */
package application;

public class Class {

	static int count = 0;
	private int id;
	private Course course;
	private Instructor instructor;
	private Room room;
	private TimeSlot time;
	
	public Class(int id, Course course, Instructor instructor, Room room, TimeSlot time) {
		super();
		this.id = id;
		this.course = course;
		this.instructor = instructor;
		this.room = room;
		this.time = time;
		count++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public TimeSlot getTime() {
		return time;
	}

	public void setTime(TimeSlot time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Class [id=" + id + ", course=" + course + ", instructor=" + instructor + ", room=" + room + ", time="
				+ time + "]";
	}

}
