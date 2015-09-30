package wator;

import core.BusyCellException;
import core.Environnement;

public class Tuna extends Fish {

	private final static int TUNA_BIRTH_CYCLE = 5;
	
	public Tuna(Environnement env) {
		super(env, TUNA_BIRTH_CYCLE);
	}
	
	public Tuna(Environnement env, int posX, int posY) throws BusyCellException {
		super(env, posX, posY, TUNA_BIRTH_CYCLE);		
	}

	public void decide() {
		super.decide();

		if(this.dead)
			return;
		
		this.env.removeAgent(this.posX, this.posY);
		
		this.oldPosX = this.posX;
		this.oldPosY = this.posY;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int nextX = this.getNextX(i);
				int nextY = this.getNextY(j);
								
				if(nextX == this.posX && nextY == this.posY)
					continue;
								
				if(this.env.isBusy(nextX, nextY) == null) {
					this.posX = nextX;
					this.posY = nextY;
					this.env.putAgent(this.posX, this.posY, this);
					return;
				}				
			}						
		}

		this.env.putAgent(this.posX, this.posY, this);
		
	}
	
	protected void giveBirth() {
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int nextX = this.getNextX(i);
				int nextY = this.getNextY(j);
				
				if(nextX == this.posX && nextY == this.posY)
					continue;
				
				if(this.env.isBusy(nextX, nextY) == null) {
					try {
						Tuna child = new Tuna(this.env, nextX, nextY);
						this.env.putNewAgent(child.getPosX(), child.getPosY(), child);
					} catch (BusyCellException e) {
						System.err.println("Error : Trying to create a Tuna in a busy cell");
					}
					return;
				}
			}
		}	
		
	}

}