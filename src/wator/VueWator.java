package wator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import core.Agent;
import core.SMA;
import core.Vue;

public class VueWator extends Vue {

	public VueWator(int width, int height, int cellSize, String name) {
		super(width, height, cellSize, name);
	}
	
	public void update(Observable arg0, Object arg1) {
		SMA sma = (SMA)arg0;
		Graphics g = this.envPanel.getGraphics();

		for(Agent a : sma.getDeadAgents()) {
			eraseDeadFish((Fish) a, g);
		}
		sma.clearDeadAgents();
		
		int nbTuna = 0;
		int nbShark = 0;
		
		for(Agent a : sma.getAgents()) {
			eraseFish((Fish) a, g);
		}
		
		for(Agent a : sma.getAgents()) {
			if(a instanceof Tuna) {
				drawTuna((Tuna)a, g);
				nbTuna++;
			} else if(a instanceof Shark) {
				drawShark((Shark)a, g);
				nbShark++;
			}
		}
		
	}
	
	private void drawTuna(Tuna t, Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(t.getPosX()*this.cellSize, t.getPosY()*this.cellSize, this.cellSize, this.cellSize);		
	}
	
	private void drawShark(Shark s, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(s.getPosX()*this.cellSize, s.getPosY()*this.cellSize, this.cellSize, this.cellSize);		
		
	}
	
	private void eraseFish(Fish f, Graphics g) {
		g.setColor(this.envPanel.getBackground());
		g.fillOval(f.getOldPosX()*this.cellSize, f.getOldPosY()*this.cellSize, this.cellSize, this.cellSize);	
	}
	
	private void eraseDeadFish(Fish f, Graphics g) {
		g.setColor(this.envPanel.getBackground());
		g.fillOval(f.getPosX()*this.cellSize, f.getPosY()*this.cellSize, this.cellSize, this.cellSize);	
	}

}
