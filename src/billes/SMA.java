package billes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class SMA extends Observable {
	
	private int sleepLength;
	private Environnement env;
	private List<Agent> agents;
	
	public SMA(int width, int height, boolean toric, int sleepLength) {
		this.env = new Environnement(width, height, toric);
		this.sleepLength = sleepLength;
	}
	
	// Initialize environment and agent
	public void init() {
		this.env.init();
		this.agents = new ArrayList<Agent>();		
	}
	
	public void addAgent() {
		this.agents.add(new Agent(this.env));
//		this.setChanged();
//		this.notifyObservers();
	}
	
	public int getNbAgents() {
		return this.agents.size();
	}
	
	public Agent getAgent(int i) {
		return this.agents.get(i);
	}

	// Run the simulation for nbTours turns. Each turn, agents is shuffled and each agent are asked to make a decision.
	public void run(int nbTours) throws InterruptedException {
		this.setChanged();
		this.notifyObservers();
		
		for(int i = 0; i < nbTours; i++) {
			Collections.shuffle(this.agents);
			for(Agent a : agents) {
				a.decide();
			}
			
			this.setChanged();
			this.notifyObservers();

			Thread.sleep(sleepLength);
		}
		
	}
	
	// Run the simulation indefinitely.
	public void run() throws InterruptedException {
		this.setChanged();
		this.notifyObservers();
		
		while(true) {
			Collections.shuffle(this.agents);
			for(Agent a : agents) {
				a.decide();
			}
			
			this.setChanged();
			this.notifyObservers();
			
		}
		
	}
	
}
