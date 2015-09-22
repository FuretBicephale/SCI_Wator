package wator;

import core.Agent;
import core.Environnement;

public class Shark extends Fish {

	public Shark(Environnement env, int birthCycle) {
		super(env, birthCycle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide() {
		super.decide();
		
		int [] indexNeighboursX = {-1, -1, -1, 0, 0, 1, 1, 1};
		int [] indexNeighboursY = {-1, 0, 1, -1, 1, -1, 0, 1};
		int nbNeighbour = indexNeighboursX.length;
		int indexSnackX=0;
		int indexSnackY=0;
		
		for (int i=0 ; i < nbNeighbour ; i++) {
			int x = indexNeighboursX[i] + this.posX;
			int y = indexNeighboursY[i] + this.posY;
			
			// Si on tombe dans une case en dehors des frontières
			// TODO : gerer le toric
			if (x < 0 || x > this.env.getWidth() - 1 || y < 0 || y > this.env.getHeight() - 1)
				continue;
			
			Agent a = this.env.isBusy(x, y);
			
//			if (a != null && a instanceof Tuna) {
//				indexSnackX = a.posX;
//				indexSnackY = a.posY;
//			}
		}
	}

}
