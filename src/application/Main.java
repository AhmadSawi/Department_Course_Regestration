package application;
	
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	static int POPULATION_COUNT = 9;
	static int ELITE_SCHEDULES = 3;
	static double CROSSOVER = 0.9;
	static double MUTATION = 0.1;
	static int TOURNAMENT = 3;
	
	static int hardFitness = 0;
	static int softFitness = 0;
	static ArrayList<GenerationViewer> generationData = new ArrayList<GenerationViewer>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("ScheduleScene.fxml"));
			Scene scene = new Scene(root,800,642);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
		
//		Data d = new Data();
//		Population p = new Population(POPULATION_COUNT, d).sortByFitness();
//		
//		System.out.println("____________GEN 1________________");
//
//		
//		for(int i = 0; i < POPULATION_COUNT; i++)
//			System.out.println("Hard:" + p.getSchedules().get(i).calculateHardCounstraintFitness() + "Soft: " + p.getSchedules().get(i).calculateSoftCounstraintFitness());
//		
//		Genetics g = new Genetics(d);
//		Population eveolved = g.evolve(p).sortByFitness();
//		
//		System.out.println("____________GEN 2________________");
//		
//		for(int i = 0; i < POPULATION_COUNT; i++)
//			System.out.println("Hard:" + eveolved.getSchedules().get(i).calculateHardCounstraintFitness() + "Soft: " + eveolved.getSchedules().get(i).calculateSoftCounstraintFitness());
//		
//		
//		Population e2 = g.evolve(eveolved).sortByFitness();
//		
//		System.out.println("____________GEN 3________________");
//		
//		for(int i = 0; i < POPULATION_COUNT; i++)
//			System.out.println("Hard:" + e2.getSchedules().get(i).calculateHardCounstraintFitness() + "Soft: " + e2.getSchedules().get(i).calculateSoftCounstraintFitness());
//		
//		Population e4 = g.evolve(e2).sortByFitness();
//
//		
//		System.out.println("____________GEN 4________________");
//		
//		for(int i = 0; i < POPULATION_COUNT; i++)
//			System.out.println("Hard:" + e4.getSchedules().get(i).calculateHardCounstraintFitness() + "Soft: " + e4.getSchedules().get(i).calculateSoftCounstraintFitness());
//		
//		Population e5 = g.evolve(e4).sortByFitness();
//
//		
//		System.out.println("____________GEN 5________________");
//		
//		for(int i = 0; i < POPULATION_COUNT; i++)
//			System.out.println("Hard:" + e5.getSchedules().get(i).calculateHardCounstraintFitness() + "Soft: " + e5.getSchedules().get(i).calculateSoftCounstraintFitness());
//		
//		
//		System.out.println("FINAL SCHEDULE");
//		
//		for(int i = 0; i < Course.count; i++)
//			System.out.println(e5.getSchedules().get(0).getClasses().get(i));
		
		
		//lastScheduel(10);

	}
	
	@SuppressWarnings("unchecked")
	static ArrayList<TableClass> lastScheduel(Data data ,int generations) throws FileNotFoundException{
		ArrayList<TableClass> last = new ArrayList<TableClass>();
		ArrayList<TableClass> current = new ArrayList<TableClass>();

		Population initialPopulation = new Population(POPULATION_COUNT, data).sortByFitness();
		Genetics geneticAlgorithm = new Genetics(data);
		
		System.out.println("------------Initial Generation------------");
		for(int j = 0; j < POPULATION_COUNT; j++)
			System.out.println("Hard:" + initialPopulation.getSchedules().get(j).calculateHardCounstraintFitness() + " | Soft: " + initialPopulation.getSchedules().get(j).calculateSoftCounstraintFitness());
		
		current.clear();
		for(int k = 0; k < Course.count; k++){
			System.out.println(initialPopulation.getSchedules().get(0).getClasses().get(k));
			
			String smw = "SMW ";
			String tr = "TR ", time = "";
			if(initialPopulation.getSchedules().get(0).getClasses().get(k).getTime().isSMW()){
				time = smw + initialPopulation.getSchedules().get(0).getClasses().get(k).getTime().getTime();
			}else{
				time = tr + initialPopulation.getSchedules().get(0).getClasses().get(k).getTime().getTime();
			}
			
			current.add(new TableClass( "" + initialPopulation.getSchedules().get(0).getClasses().get(k).getId(), initialPopulation.getSchedules().get(0).getClasses().get(k).getInstructor().getName(), initialPopulation.getSchedules().get(0).getClasses().get(k).getCourse().getId(), time, initialPopulation.getSchedules().get(0).getClasses().get(k).getRoom().getId(), "" + initialPopulation.getSchedules().get(0).getClasses().get(k).getCourse().getSection()));
		}
		
		generationData.add(new GenerationViewer(0, (ArrayList<TableClass>) current.clone(), initialPopulation.getSchedules().get(0).calculateHardCounstraintFitness(), initialPopulation.getSchedules().get(0).calculateSoftCounstraintFitness()));
		
		
		Population evolvedPopulation = null;
		
		for(int i = 0; i < generations-1; i++){
			evolvedPopulation = geneticAlgorithm.evolve(initialPopulation).sortByFitness();
			System.out.println("------------Generation " + (i+2) + "------------");
			for(int j = 0; j < POPULATION_COUNT; j++)
				System.out.println("Hard:" + evolvedPopulation.getSchedules().get(j).calculateHardCounstraintFitness() + " | Soft: " + evolvedPopulation.getSchedules().get(j).calculateSoftCounstraintFitness());
			
			current.clear();
			for(int k = 0; k < Course.count; k++){
				System.out.println(evolvedPopulation.getSchedules().get(0).getClasses().get(k));
				
				String smw = "SMW ";
				String tr = "TR ", time = "";
				if(evolvedPopulation.getSchedules().get(0).getClasses().get(k).getTime().isSMW()){
					time = smw + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getTime().getTime();
				}else{
					time = tr + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getTime().getTime();
				}
				
				current.add(new TableClass( "" + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getId(), evolvedPopulation.getSchedules().get(0).getClasses().get(k).getInstructor().getName(), evolvedPopulation.getSchedules().get(0).getClasses().get(k).getCourse().getId(), time, evolvedPopulation.getSchedules().get(0).getClasses().get(k).getRoom().getId(), "" + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getCourse().getSection()));
			}
			
			generationData.add(new GenerationViewer(i, (ArrayList<TableClass>) current.clone(), evolvedPopulation.getSchedules().get(0).calculateHardCounstraintFitness(), evolvedPopulation.getSchedules().get(0).calculateSoftCounstraintFitness()));
			
			initialPopulation = evolvedPopulation;
		}
		
		for(int k = 0; k < Course.count; k++){
			String smw = "SMW ";
			String tr = "TR ", time = "";
			if(evolvedPopulation.getSchedules().get(0).getClasses().get(k).getTime().isSMW()){
				time = smw + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getTime().getTime();
			}else{
				time = tr + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getTime().getTime();
			}
			
			last.add(new TableClass( "" + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getId(), evolvedPopulation.getSchedules().get(0).getClasses().get(k).getInstructor().getName(), evolvedPopulation.getSchedules().get(0).getClasses().get(k).getCourse().getId(), time, evolvedPopulation.getSchedules().get(0).getClasses().get(k).getRoom().getId(), "" + evolvedPopulation.getSchedules().get(0).getClasses().get(k).getCourse().getSection()));
		}
		
		hardFitness = evolvedPopulation.getSchedules().get(0).calculateHardCounstraintFitness();
		softFitness = evolvedPopulation.getSchedules().get(0).calculateSoftCounstraintFitness();
		
		return last;
		
	}
	
}
