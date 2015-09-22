package wator;

import core.Agent;
import core.Environnement;
import core.SMA;

public abstract class Fish extends Agent {
	
	private int birthCycle;
	private int currentBirthCycle;

	public Fish(Environnement env, int birthCycle) {
		super(env);
		this.birthCycle = birthCycle;
		this.currentBirthCycle = 0;
	}

	public void decide() {
		if(this.canGiveBirth()) {
			currentBirthCycle = 0;
			this.giveBirth();
		}
		
		currentBirthCycle++;
	}
	
	private boolean canGiveBirth() {		
		if(this.currentBirthCycle < this.birthCycle)
			return false;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(this.env.isBusy(this.getPosX() + i, this.getPosY() + j) == null)
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
	
	protected abstract void giveBirth();
	
}
