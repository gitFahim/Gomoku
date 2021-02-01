
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUIClass extends JPanel {
	
	
	private Graphics2D g2D;
	private BufferedImage image;
	
	private static final long serialVersionUID = 1L;
	
	private int boardWidth; 
	private int totalCell; 
	private final int cellLength;
	
	
	public GUIClass(int boardWidth, int totalCell) {
		this.boardWidth = boardWidth;
		this.totalCell = totalCell;
		this.cellLength  = (boardWidth -300) / totalCell;
		
		
		image = new BufferedImage(boardWidth, boardWidth, BufferedImage.TYPE_INT_ARGB);
		
		g2D = (Graphics2D)image.getGraphics();
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                			 RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2D.setColor(Color.cyan);
		g2D.fillRect(0,0,boardWidth, boardWidth);
		
		g2D.setColor(Color.black);
		/*
		for(int i=0; i<=totalCell; i++) {
			g2D.drawLine(i*cellLength, 100, i*cellLength, boardWidth-200);
		}
		
		
		for(int i=0; i<=totalCell; i++) {
			g2D.drawLine(0, 100+i*cellLength, boardWidth-300, 100+i*cellLength);
		}
		*/
		for(int i=0; i<=totalCell; i++) {
			g2D.drawLine(i*cellLength, 0, i*cellLength, boardWidth-200);
		}
		
		
		for(int i=0; i<=totalCell; i++) {
			g2D.drawLine(0, 100+i*cellLength, boardWidth-300, 100+i*cellLength);
		}
		
		int x = 320,y=850;
		int arrowValue = 10;
		g2D.setFont(new Font("Serif", Font.BOLD, 20));
		g2D.drawString("Arrows Left", 200, 850);
		g2D.drawString(Integer.toString(arrowValue),x,y);
	
		
		
	}
	
	
	public int getRelativePos(int x) {
		if(x >= boardWidth) x = boardWidth-1;
		
		return (int) ( x * totalCell / (boardWidth-300) );
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(boardWidth, boardWidth);
	}
	
	
	public void printWinner(int winner, String text) {
		FontMetrics metrics = g2D.getFontMetrics(g2D.getFont());		
		
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
   			 				 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2D.setFont(new Font(g2D.getFont().getName(), Font.PLAIN, 48));
		
		g2D.setColor(Color.black);
		int x = (boardWidth/2 - metrics.stringWidth(text)*2);
		int y = boardWidth/2;
		

		
		g2D.setColor(winner == 2 ? Color.green : (winner == 1 ? Color.red : Color.blue));
		
		g2D.drawString(text,x,y);
		
		repaint();
		
	}
	
	
	public void drawStone(int posX, int posY, boolean human) {
		
		if(posX >= totalCell || posY >= totalCell) return;
		
		
		
		g2D.setColor(human ? Color.green : Color.orange);
		g2D.fillOval((int)(cellLength*(posX+0.2)), 
					 (int)(cellLength*(posY+0.2)), 
					 (int)(cellLength*0.7), 
					 (int)(cellLength*0.7));
		
		g2D.setColor(Color.blue);
		g2D.setStroke(new BasicStroke((float)1.5));
		g2D.drawOval((int)(cellLength*(posX+0.2)), 
					 (int)(cellLength*(posY+0.2)), 
					 (int)(cellLength*0.7), 
					 (int)(cellLength*0.7));
		
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		g2D.setColor(Color.black);
		g2D.setStroke(new BasicStroke(2));
		g2D.drawOval((int)(cellLength*(posX+0.2)), 
					 (int)(cellLength*(posY+0.2)), 
					 (int)(cellLength*0.7), 
					 (int)(cellLength*0.7));
		
		repaint();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g.create();
		
		// Draw the board
		g2D.drawImage(image, 0, 0, boardWidth, boardWidth, null);
		
		
		// Drawing border
		g2D.setColor(Color.black);
        g2D.drawRect(0, 0, boardWidth, boardWidth);
	}
	
	
	public void attachListener(MouseListener listener) {
		addMouseListener(listener);
	}
	
	

}
