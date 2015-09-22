package billes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vue implements Observer {

	JFrame frame;
	JPanel envPanel;
	int width, height, marbleSize;
	
	public Vue(int width, int height, int marbleSize) {
		this.width = width;
		this.height = height;
		this.marbleSize = marbleSize;
	}
	
	public void init() {
		this.frame = new JFrame("SCI_Billes");
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		
		this.envPanel = new JPanel();
		this.envPanel.setPreferredSize(new Dimension(this.width * this.marbleSize, this.height * this.marbleSize));
		this.envPanel.setBackground(Color.white);
		
		this.frame.setContentPane(this.envPanel);
		this.frame.pack();
	}
	
	public void update(Observable arg0, Object arg1) {
		SMA sma = (SMA)arg0;
		Graphics g = this.envPanel.getGraphics();

		//this.envPanel.paint(g);
		
//		g.setColor(Color.black);
//		for(int i = 0; i < this.width-1; i++) g.drawLine((i+1) * this.marbleSize, 0, (i+1) * this.marbleSize, height * this.marbleSize);
//		for(int i = 0; i < this.height-1; i++) g.drawLine(0, (i+1) * this.marbleSize, width * this.marbleSize, (i+1) * this.marbleSize);	
		
		for(int i = 0; i < sma.getNbAgents(); i++) {
			Agent a = sma.getAgent(i);
			eraseMarble(a, g);
			drawMarble(a, g);
		}
		
	}
	
	private void drawMarble(Agent a, Graphics g) {
		g.setColor(a.getColor());
		g.fillOval(a.getPosX()*this.marbleSize, a.getPosY()*this.marbleSize, this.marbleSize, this.marbleSize);
	}
	
	private void eraseMarble(Agent a, Graphics g) {
		g.setColor(this.envPanel.getBackground());
		g.fillOval(a.getOldPosX()*this.marbleSize, a.getOldPosY()*this.marbleSize, this.marbleSize, this.marbleSize);
	}

}
