/*
 * this class represents a course which is to be taught by a dr
 * */
package application;

public class Course {

	static int count = 0;
	private String id;
	private String name;
	private int level;
	private TimeSlot time;
	private int section; 
	private int maxStudentCount;
	private boolean lab;
	private boolean needPrjector;

	public Course(String id, String name, int level, TimeSlot time, int section, int maxStudentCount, boolean lab,
			boolean needPrjector) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.time = time;
		this.section = section;
		this.maxStudentCount = maxStudentCount;
		this.lab = lab;
		this.needPrjector = needPrjector;
		count++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public TimeSlot getTime() {
		return time;
	}

	public void setTime(TimeSlot time) {
		this.time = time;
	}

	public int getMaxStudentCount() {
		return maxStudentCount;
	}

	public void setMaxStudentCount(int maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public boolean isLab() {
		return lab;
	}

	public void setLab(boolean lab) {
		this.lab = lab;
	}
	
	public boolean isNeedPrjector() {
		return needPrjector;
	}

	public void setNeedPrjector(boolean needPrjector) {
		this.needPrjector = needPrjector;
	}

	public int getLength(){
		if(this.isLab())
			return 2;
		else
			return 3;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", level=" + level + ", time=" + time + ", section=" + section
				+ ", maxStudentCount=" + maxStudentCount + ", lab=" + lab + ", needPrjector=" + needPrjector + "]";
	}
}
