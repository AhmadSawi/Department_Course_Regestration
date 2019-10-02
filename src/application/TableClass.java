package application;

public class TableClass {
	
	String classID;
	String instructorName;
	String CourseID;
	String timeSlot;
	String Room;
	String section;
	
	public TableClass(String classID, String instructorName, String courseID, String timeSlot, String room,
			String section) {
		super();
		this.classID = classID;
		this.instructorName = instructorName;
		CourseID = courseID;
		this.timeSlot = timeSlot;
		Room = room;
		this.section = section;
	}
	
	public String getClassID() {
		return classID;
	}
	
	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	public String getInstructorName() {
		return instructorName;
	}
	
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	
	public String getCourseID() {
		return CourseID;
	}
	
	public void setCourseID(String courseID) {
		CourseID = courseID;
	}
	
	public String getTimeSlot() {
		return timeSlot;
	}
	
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public String getRoom() {
		return Room;
	}
	
	public void setRoom(String room) {
		Room = room;
	}
	
	public String getSection() {
		return section;
	}
	
	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "TableClass [classID=" + classID + ", instructorName=" + instructorName + ", CourseID=" + CourseID
				+ ", timeSlot=" + timeSlot + ", Room=" + Room + ", section=" + section + "]";
	}
	
	
	
}
