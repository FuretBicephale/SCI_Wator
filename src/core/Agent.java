package core;

import java.awt.Color;
import java.util.Random;

public abstract class Agent {
	
	protected static Random r = new Random();
	
	protected int posX;
	protected int posY;
	protected int oldPosX;
	protected int oldPosY;
	protected Environnement env;
	
	// Create the agent and choose a random position (cell must be empty) and direction in the environment
	public Agent(Environnement env) {
		
		boolean positionFound = false;
		this.env = env;
		
		while (!positionFound) {
			int x = r.nextInt(env.getWidth());
			int y = r.nextInt(env.getHeight());
			
			if (env.isBusy(x, y) == null) {
				this.oldPosX = this.posX = x;
				this.oldPosY = this.posY = y;
				this.env.putAgent(x, y, this);
				positionFound = true;
			}
		}		
	}

	// Ask agent to make a decision for the current turn
	public abstract void decide();
	
	public int getOldPosX() {
		return oldPosX;
	}

	public int getOldPosY() {
		return oldPosY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
}

