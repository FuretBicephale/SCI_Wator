package billes;

import java.awt.Color;
import java.util.Random;

public class Agent {
	private static Random r = new Random();
	private int posX;
	private int posY;
	private int oldPosX;
	private int oldPosY;
	private int stepX;
	private int stepY;
	private Environnement env;
	private Color color;
	
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
		
		this.stepX = r.nextInt(3) - 1;
		this.stepY = r.nextInt(3) - 1;
		
		this.color = new Color(r.nextInt(192), r.nextInt(192), r.nextInt(192));
		
		return;
	}
	
	public Color getColor() {
		return color;
	}

	// Ask agent to make a decision for the current turn
	public void decide() {
		int newPosX;
		int newPosY;
		Agent aCollision;
		
		this.env.removeAgent(this.posX, this.posY);
		
		this.oldPosX = this.posX;
		this.oldPosY = this.posY;
		
		if(env.isToric()) {
			newPosX = (this.env.getWidth() + this.posX + this.stepX) % this.env.getWidth();
			newPosY = (this.env.getHeight() + this.posY + this.stepY) % this.env.getHeight();
		} else {
			if(this.posX + this.stepX >= this.env.getWidth() || this.posX + this.stepX < 0)
				this.stepX = -this.stepX;
			
			if(this.posY + this.stepY >= this.env.getHeight() || this.posY + this.stepY < 0)
				this.stepY = -this.stepY;
			
			newPosX = this.posX + this.stepX;
			newPosY = this.posY + this.stepY;
		}
		
		if ((aCollision = env.isBusy(newPosX, newPosY)) != null) {
			aCollision.handleCollision(this);
			
			this.stepX = -this.stepX;
			this.stepY = -this.stepY;
		}
		else {
			this.posX = newPosX;
			this.posY = newPosY;
		}
		
		this.env.putAgent(this.posX, this.posY, this);
	}
	
	public int getOldPosX() {
		return oldPosX;
	}

	public int getOldPosY() {
		return oldPosY;
	}

	private void handleCollision(Agent agent) {
		this.stepX = agent.stepX;
		this.stepY = agent.stepY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
}

