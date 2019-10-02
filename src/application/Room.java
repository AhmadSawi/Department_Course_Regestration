/*
 * this class represents a class room. where stuff are to be taught in it
 * */
package application;

public class Room {

	static int count = 0;
	private String id;
	private Building building;
	private boolean projector;
	private int maxStudentCount;
	
	public Room(String id, Building building, boolean projector, int maxStudentCount) {
		super();
		this.id = id;
		this.building = building;
		this.projector = projector;
		this.maxStudentCount = maxStudentCount;
		count++;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Building getBuilding() {
		return building;
	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	public boolean isProjector() {
		return projector;
	}
	
	public void setProjector(boolean projector) {
		this.projector = projector;
	}
	
	
	public int getMaxStudentCount() {
		return maxStudentCount;
	}

	public void setMaxStudentCount(int maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", building=" + building + ", projector=" + projector + ", maxStudentCount="
				+ maxStudentCount + "]";
	}
}
