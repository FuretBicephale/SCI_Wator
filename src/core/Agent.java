package core;

import java.util.Random;

public abstract class Agent {
	
	protected static Random r = new Random();
	
	protected int posX;
	protected int posY;
	protected int oldPosX;
	protected int oldPosY;
	protected Environnement env;
	
	// Create the agent and choose a random position (cell must be empty) in the environment
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
	
	// Create the agent at [x, y] (cell must be empty) in the environment
	public Agent(Environnement env, int posX, int posY) throws BusyCellException {
		
		this.env = env;
		
		if(env.isBusy(posX, posY) == null) {
			this.oldPosX = this.posX = posX;
			this.oldPosY = this.posY = posY;
			this.env.putAgent(posX, posY, this);
		} else {
			throw new BusyCellException("Cell [x, y] already occupied");
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

