package core;

import java.util.ArrayList;
import java.util.List;

public class Environnement {
	
	private Agent espace[][];
	private List<Agent> newAgents, deadAgents;
	private int width, height;
	private boolean toric;
	
	public Environnement(int width, int height, boolean toric) {
		this.width = width;
		this.height = height;
		this.toric = toric;
		this.newAgents = new ArrayList<Agent>();
		this.deadAgents = new ArrayList<Agent>();
	}
	
	// Create the 2D array of Agent
	public void init() {
		this.espace = new Agent[width][height];
	}
	
	public void putAgent(int x, int y, Agent a) {
		this.espace[x][y] = a;
	}
	
	public void putNewAgent(int x, int y, Agent a) {
		this.espace[x][y] = a;
		this.newAgents.add(a);
	}
	
	public void removeAgent(int x, int y) {
		this.espace[x][y] = null;
	}

	public void removeDeadAgent(Agent a) {
		this.deadAgents.add(a);
		this.espace[a.getPosX()][a.getPosY()] = null;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	// Return the Agent at [x, y] or null if it's empty
	public Agent isBusy(int x, int y) {
		return this.espace[x][y];
	}
	
	public boolean isToric() {
		return toric;
	}
	
	public List<Agent> getNewAgents() {
		return this.newAgents;
	}
	
	public List<Agent> getDeadAgents() {
		return this.deadAgents;
	}
	
	public void clearNewAgents() {
		this.newAgents.clear();
	}
	
	public void clearDeadAgents() {
		this.deadAgents.clear();
	}
	
}
