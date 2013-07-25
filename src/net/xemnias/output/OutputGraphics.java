package net.xemnias.output;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.newdawn.slick.GameContainer;


public class OutputGraphics extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points = new ArrayList<Point>();
	private int xPosition;
	private GameContainer gc;
	JScrollPane parent;
	private float timeLaunch;
	public OutputGraphics(GameContainer g)
	{
		gc = g;
		Thread t = new Thread(new Timer());
		t.start();
		setPreferredSize(new Dimension(4000, getHeight()));
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		if(xPosition > 1000)
		{
			points.clear();
			xPosition = 0;

			if(parent.getComponentOrientation() == ComponentOrientation.LEFT_TO_RIGHT)
				parent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

			else if(parent.getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT)
				parent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		}
		
		if(xPosition > g.getClipBounds().width)
			this.scrollRectToVisible(new Rectangle(xPosition-g.getClipBounds().width, 0, g.getClipBounds().width, getHeight()));
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.setComposite(AlphaComposite.SrcOver.derive(0.1f));
		
		for(int x =0; x < this.getWidth()/32 +3; x++)
		{

			for(int y =0; y < this.getHeight()/32 +3; y++)
			{
				g2d.drawRect(x*32, y*32, 32, 32);
			}
		}
		
		g2d.setComposite(AlphaComposite.SrcOver.derive(0.2f));
		g2d.drawLine(xPosition+4, 0, xPosition+4, this.getHeight());
		g2d.setComposite(AlphaComposite.SrcOver.derive(1f));
		g.setColor(Color.GREEN);
		for(int i = 0; i < points.size(); i++)
		{
			Point p = points.get(i);
				try
				{
					g.drawLine(points.get(i-1).x, this.getHeight()-points.get(i-1).y, p.x, this.getHeight()-p.y);
				}
				catch(Exception e)
				{
					
				}
			
		}
		g.setColor(Color.white);
		g.drawString("Temps de lancement : "+timeLaunch, 10, 10);
		repaint(); 
	}
	
	private class Point
	{
		public Point(int xPosition, int fps) {
			x = xPosition;
			y = fps;
		}

		public int x,y;
	}
	
	private class Timer implements Runnable 
	{

		public void run() 
		{
			while(true)
			{

				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Point p = new Point(xPosition, (gc.getFPS()*800)/500);
				xPosition++;
				if(p.y > 0 && timeLaunch == 0)
				{
					timeLaunch = (xPosition*150)/1000;
				}
				
				if(timeLaunch > 0)
				{

					points.add(p);
				}
			}	
				
		}
	}
	public void setParent(JScrollPane p)
	{
		parent = p;
		parent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
}
