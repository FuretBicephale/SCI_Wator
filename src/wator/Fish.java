package wator;

import core.Agent;
import core.Environnement;

public abstract class Fish extends Agent {
	private int birthCycle;
	private int currentBirthCycle;

	public Fish(Environnement env, int birthCycle) {
		super(env);
		this.birthCycle = birthCycle;
		this.currentBirthCycle = 0;
	}

	@Override
	public void decide() {
		if (currentBirthCycle >= birthCycle) {
			currentBirthCycle = 0;
			this.giveBirth();
		}
		
		currentBirthCycle++;
	}

	private void giveBirth() {
		// TODO Auto-generated method stub
		
	}
	
}
