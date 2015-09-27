package wator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import core.Agent;
import core.SMA;
import core.Vue;

public class VueWator extends Vue {

	public VueWator(int width, int height, int cellSize) {
		super(width, height, cellSize);
	}
	
	public void update(Observable arg0, Object arg1) {
		SMA sma = (SMA)arg0;
		Graphics g = this.envPanel.getGraphics();

		for(int i = 0; i < sma.getNbAgents(); i++) {
			Agent a = sma.getAgent(i);
			if(a instanceof Tuna) {
				eraseTuna((Tuna)a, g);
				drawTuna((Tuna)a, g);
			} else if(a instanceof Shark) {
				eraseShark((Shark)a, g);
				drawShark((Shark)a, g);				
			}
		}
		
	}
	
	private void drawTuna(Tuna t, Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(t.getPosX()*this.cellSize, t.getPosY()*this.cellSize, this.cellSize, this.cellSize);		
	}
	
	private void eraseTuna(Tuna t, Graphics g) {
		g.setColor(this.envPanel.getBackground());
		g.fillOval(t.getOldPosX()*this.cellSize, t.getOldPosY()*this.cellSize, this.cellSize, this.cellSize);		
	}
	
	private void drawShark(Shark s, Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(s.getPosX()*this.cellSize, s.getPosY()*this.cellSize, this.cellSize, this.cellSize);		
		
	}
	
	private void eraseShark(Shark s, Graphics g) {
		g.setColor(this.envPanel.getBackground());
		g.fillOval(s.getOldPosX()*this.cellSize, s.getOldPosY()*this.cellSize, this.cellSize, this.cellSize);	
	}

}
