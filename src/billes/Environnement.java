package billes;

import java.util.ArrayList;

public class Environnement {
	
	private Agent espace[][];
	private int width, height;
	private boolean toric;
	
	public Environnement(int width, int height, boolean toric) {
		this.width = width;
		this.height = height;
		this.toric = toric;
	}
	
	// Create the 2D array of Agent
	public void init() {
		this.espace = new Agent[width][height];
	}
	
	public void putAgent(int x, int y, Agent a) {
		this.espace[x][y] = a;
	}
	
	public void removeAgent(int x, int y) {
		this.espace[x][y] = null;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Agent isBusy(int x, int y) {
		return this.espace[x][y];
	}
	
	public boolean isToric() {
		return toric;
	}
	
	
}
