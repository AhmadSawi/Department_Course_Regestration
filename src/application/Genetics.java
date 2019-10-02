package application;

public class Genetics {

	private Data data;

	public Genetics(Data data){
		this.data = data;
	}

	public Population evolve(Population population){ 
		return mutatePopulation(crossoverPopulation(population)); 
	}


	Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(Main.POPULATION_COUNT, data); 
		
		for(int i = 0; i < Main.ELITE_SCHEDULES; i++)
			crossoverPopulation.getSchedules().set(i, population.getSchedules().get(i));
		
		for(int i = Main.ELITE_SCHEDULES; i < Main.POPULATION_COUNT; i++){
			if(Math.random() < Main.CROSSOVER){
				Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
				Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
				crossoverPopulation.getSchedules().set(i,crossoverSchedule(schedule1, schedule2));
			}
		}
		return crossoverPopulation;
	}


	Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) { 
		Schedule crossoverSchedule = new Schedule(data);
		crossoverSchedule.randomizeScedule();
		
		for(int i = 0; i < Course.count; i++){
			if(Math.random() > 0.5)
				crossoverSchedule.getClasses().set(i, schedule1.getClasses().get(i));
			else
				crossoverSchedule.getClasses().set(i, schedule2.getClasses().get(i));
		}
		
		return crossoverSchedule;
	}
	
	
	Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(Main.POPULATION_COUNT, data);
		
		for(int i = 0; i < Main.ELITE_SCHEDULES; i++)
			mutatePopulation.getSchedules().set(i, population.getSchedules().get(i));
		
		for(int i = Main.ELITE_SCHEDULES; i < Main.POPULATION_COUNT; i++)
			mutatePopulation.getSchedules().set(i, mutateSchedule(population.getSchedules().get(i)));
		
		return mutatePopulation;
	}
	
	
	Schedule mutateSchedule(Schedule mutateSchedule) { 
		Schedule schedule = new Schedule(data);
		schedule.randomizeScedule();
		
		for(int i = 0; i < Course.count; i++){
			if(Math.random() < Main.MUTATION)
				mutateSchedule.getClasses().set(i, schedule.getClasses().get(i));
		}
		
		return mutateSchedule;
	}
	
	
	Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(Main.TOURNAMENT, data);
		
		for(int i = 0; i < Main.TOURNAMENT; i++)
			tournamentPopulation.getSchedules().set(i, population.getSchedules().get((int)(Math.random() * population.getSchedules().size())));
		
		return tournamentPopulation;
	}
	
}
