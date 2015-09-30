package wator;

import core.Agent;
import core.BusyCellException;
import core.Environnement;

public class Shark extends Fish {

	private final static int SHARK_BIRTH_CYCLE = 10;
	private final static int SHARK_HUNGER_CYCLE = 3;

	private int hungerCycle;
	private int currentHungerCycle;
	
	public Shark(Environnement env) {
		super(env, SHARK_BIRTH_CYCLE);
		this.hungerCycle = SHARK_HUNGER_CYCLE;
		this.currentHungerCycle = 0;
	}

	public Shark(Environnement env, int posX, int posY) throws BusyCellException {
		super(env, posX, posY, SHARK_BIRTH_CYCLE);		
	}
	
	@Override
	public void decide() {
		super.decide();

		if(this.dead)
			return;
		
		int [] indexNeighboursX = {-1, -1, -1, 0, 0, 1, 1, 1};
		int [] indexNeighboursY = {-1, 0, 1, -1, 1, -1, 0, 1};
		int nbNeighbour = indexNeighboursX.length;
		int indexSnackX=-1;
		int indexSnackY=-1;
		
		this.env.removeAgent(this.posX, this.posY);
		
		this.oldPosX = this.posX;
		this.oldPosY = this.posY;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int nextX = this.getNextX(i);
				int nextY = this.getNextY(j);
								
				if(nextX == this.posX && nextY == this.posY)
					continue;
								
				// Provoque un problème de déplacement. A mettre après les boucles.
				if(this.env.isBusy(nextX, nextY) == null) {
					this.posX = nextX;
					this.posY = nextY;
					//break;
					/*this.env.putAgent(this.posX, this.posY, this);
					return;*/
				} else if(this.env.isBusy(nextX, nextY) instanceof Tuna) {
					indexSnackX = nextX;
					indexSnackY = nextY;
				}
			}				
			/*if(this.posX != this.oldPosX || this.posY != this.oldPosY)
				break;*/
		}

		this.env.putAgent(this.posX, this.posY, this);
		
		/*for (int i=0 ; i < nbNeighbour ; i++) {
			int x = indexNeighboursX[i] + this.posX;
			int y = indexNeighboursY[i] + this.posY;
			
			// Si on tombe dans une case en dehors des frontières
			// TODO : gerer le toric
			if (x < 0 || x > this.env.getWidth() - 1 || y < 0 || y > this.env.getHeight() - 1)
				continue;
			
			Agent a = this.env.isBusy(x, y);
			
			if (a != null && a instanceof Tuna) {
				indexSnackX = a.getPosX();
				indexSnackY = a.getPosY();
			}
		}*/
		
		if(indexSnackX != -1 && indexSnackY != -1) {
			System.out.println("MIAM");
			this.currentHungerCycle = 0;
			((Fish)this.env.isBusy(indexSnackX, indexSnackY)).die();
		} else {
			this.currentHungerCycle++;	
			
			if(this.currentHungerCycle >= this.hungerCycle) {
				System.out.println("DIE");
				this.die();
				return;
			}
			
		}
		
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
						Shark child = new Shark(this.env, nextX, nextY);
						this.env.putNewAgent(child.getPosX(), child.getPosY(), child);
					} catch (BusyCellException e) {
						System.err.println("Error : Trying to create a Shark in a busy cell");
					}
					return;
				}
			}
		}	
		
	}
	
}