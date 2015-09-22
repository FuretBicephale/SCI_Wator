package billes;

import java.awt.Color;
import java.util.Random;

import core.Agent;
import core.Environnement;

public class Bille extends Agent {
	
	protected int stepX;
	protected int stepY;
	protected Color color;
	
	// Create the agent and choose a random position (cell must be empty) and direction in the environment
	public Bille(Environnement env) {
		
		super(env);
		
		this.stepX = r.nextInt(3) - 1;
		this.stepY = r.nextInt(3) - 1;
		
		this.color = new Color(r.nextInt(192), r.nextInt(192), r.nextInt(192));
		
		return;
	}

	// Ask agent to make a decision for the current turn
	public void decide() {
		
		int newPosX;
		int newPosY;
		Bille aCollision;
		
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
		
		if ((aCollision = (Bille)env.isBusy(newPosX, newPosY)) != null) {
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

	private void handleCollision(Bille agent) {
		this.stepX = agent.stepX;
		this.stepY = agent.stepY;
	}
	
	public Color getColor() {
		return color;
	}
	
}

