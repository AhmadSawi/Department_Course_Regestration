package application;

import java.util.ArrayList;

public class GenerationViewer {
	int generationID;
	ArrayList<TableClass> solution; 
	int hardFitness;
	int softFitness;
	
	public GenerationViewer(int generationID, ArrayList<TableClass> solution, int hardFitness, int softFitness) {
		super();
		this.generationID = generationID;
		this.solution = solution;
		this.hardFitness = hardFitness;
		this.softFitness = softFitness;
	}

	public int getGenerationID() {
		return generationID;
	}

	public void setGenerationID(int generationID) {
		this.generationID = generationID;
	}

	public ArrayList<TableClass> getSolution() {
		return solution;
	}

	public void setSolution(ArrayList<TableClass> solution) {
		this.solution = solution;
	}

	public int getHardFitness() {
		return hardFitness;
	}

	public void setHardFitness(int hardFitness) {
		this.hardFitness = hardFitness;
	}

	public int getSoftFitness() {
		return softFitness;
	}

	public void setSoftFitness(int softFitness) {
		this.softFitness = softFitness;
	}
}
