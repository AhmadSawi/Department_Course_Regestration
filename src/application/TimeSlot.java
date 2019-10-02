/*
 * This class repersents a time slot to be used for each course. and also has everything we need to know lhow long the course is and wha
 * kind of course it is
 * LAB = length 3
 * TR class = length 1.5
 * SMW class = length 1
 * */
package application;

public class TimeSlot {

	static int count = 0;
	private int id;
	private String time;
	private boolean smw;
	private boolean tr;
	private boolean lab;
	
	public TimeSlot(int id, String time, boolean smw, boolean tr, boolean lab) {
		super();
		this.id = id;
		this.time = time;
		this.smw = smw;
		this.tr = tr;
		this.lab = lab;
		count++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public boolean isSMW() {
		return smw;
	}
	
	public void setSMW(boolean smw) {
		this.smw = smw;
	}
	
	public boolean isTR() {
		return tr;
	}
	
	public void setTR(boolean tr) {
		this.tr = tr;
	}
	
	public boolean isLab() {
		return lab;
	}
	
	public void setLab(boolean lab) {
		this.lab = lab;
	}
	
	public double getLength(){
		if(isLab())
			return 3;
		else if(isSMW())
			return 1;
		else 
			return 1.5;
	}
	
	@Override
	public String toString() {
		return "TimeSlot [id=" + id + ", time=" + time + ", smw=" + smw + ", tr=" + tr + ", lab=" + lab + "]";
	}

}
