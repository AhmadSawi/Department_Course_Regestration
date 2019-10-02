package application;

import java.util.ArrayList;

public class Population {

	private ArrayList<Schedule> schedules;

	public Population(int size, Data data) {
		super();
		schedules = new ArrayList<Schedule>();
		
		for(int i = 0; i < size; i++){
			Schedule newSchedule = new Schedule(data);
			newSchedule.randomizeScedule();
			schedules.add(newSchedule);
		}
	}
	
	public Population sortByFitness() {
		schedules.sort((schedule1, schedule2) -> {
			int returnValue = 0;
			if(schedule1.calculateHardCounstraintFitness() == 0 && schedule2.calculateHardCounstraintFitness() == 0){
				if (schedule1.calculateSoftCounstraintFitness() > schedule2.calculateSoftCounstraintFitness()) returnValue =  -1;
				else if (schedule1.calculateSoftCounstraintFitness() < schedule2.calculateSoftCounstraintFitness()) returnValue =  1;
				return returnValue;
			}
			if (schedule1.calculateHardCounstraintFitness() > schedule2.calculateHardCounstraintFitness()) returnValue =  -1;
			else if (schedule1.calculateHardCounstraintFitness() < schedule2.calculateHardCounstraintFitness()) returnValue =  1;
			return returnValue;
		}); 
		return this;
	}
	
	public ArrayList<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<Schedule> schedules) {
		this.schedules = schedules;
	}
		
}
