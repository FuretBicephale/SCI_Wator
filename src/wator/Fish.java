package wator;

import core.Agent;
import core.BusyCellException;
import core.Environnement;
import core.SMA;

public abstract class Fish extends Agent {
	
	private int birthCycle;
	private int currentBirthCycle;
	protected boolean dead;

	public Fish(Environnement env, int birthCycle) {
		super(env);
		this.birthCycle = birthCycle;
		this.currentBirthCycle = 0;
	}
	
	public Fish(Environnement env, int posX, int posY, int birthCycle) throws BusyCellException {
		super(env, posX, posY);
		this.birthCycle = birthCycle;
		this.currentBirthCycle = 0;
	}

	public void decide() {
		if(this.dead)
			return;
		
		if(this.canGiveBirth()) {
			currentBirthCycle = 0;
			System.out.println("BIRTH");
			this.giveBirth();
		}
		
		currentBirthCycle++;
	}
	
	private boolean canGiveBirth() {		
		if(this.currentBirthCycle < this.birthCycle)
			return false;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				
				int nextX = this.getNextX(i);
				int nextY = this.getNextY(j);
				
				if(nextX == this.posX && nextY == this.posY)
					continue;
				
				if(this.env.isBusy(nextX, nextY) == null)
					return true;
			}
		}
		
		return false;
	}

	protected int getNextX(int step) {		
		int newPosX;
		
		if(this.env.isToric()) {
			newPosX = (this.env.getWidth() + this.posX + step) % this.env.getWidth();
		} else {
			if(this.posX + step >= this.env.getWidth() || this.posX + step < 0)
				step = 0;
			newPosX = this.posX + step;
		}
		
		return newPosX;		
	}
	
	protected int getNextY(int step) {		
		int newPosY;
		
		if(this.env.isToric()) {
			newPosY = (this.env.getHeight() + this.posY + step) % this.env.getHeight();
		} else {
			if(this.posY + step >= this.env.getHeight() || this.posY + step < 0)
				step = 0;
			newPosY = this.posY + step;
		}
		
		return newPosY;
	}
	
	protected void die() {
		this.dead = true;
		this.env.removeDeadAgent(this);
	}
	
	protected abstract void giveBirth();
	
}