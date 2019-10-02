/*
 * This class represents a building which will have rooms in it. ex: Masri building
 * */
package application;

public class Building {
	
	static int count = 0;
	private int id;
	private String name;
	
	public Building(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		count++;
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

	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + "]";
	}
}
