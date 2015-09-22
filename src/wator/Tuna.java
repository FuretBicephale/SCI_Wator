package wator;

import core.Environnement;

public class Tuna extends Fish {

	public Tuna(Environnement env, int birthCycle) {
		super(env, birthCycle);
	}

	public void decide() {
		super.decide();
		
		this.env.removeAgent(this.posX, this.posY);
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int nextX = this.getNextX(i);
				int nextY = this.getNextX(j);
				
				if(nextX == 0 && nextY == 0)
					continue;
				
				if(this.env.isBusy(nextX, nextY) == null) {
					this.posX = nextX;
					this.posY = nextY;
					break;
				}				
			}
		}

		this.env.putAgent(this.posX, this.posY, this);
		
	}
	
	protected void giveBirth() {
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int nextX = this.getNextX(i);
				int nextY = this.getNextX(j);
				
				if(nextX == 0 && nextY == 0)
					continue;
				
				if(this.env.isBusy(nextX, nextY) == null) {
					// Add agent
					return;
				}
			}
		}	
		
	}

}
