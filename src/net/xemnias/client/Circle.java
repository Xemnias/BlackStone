package net.xemnias.client;

import org.newdawn.slick.Graphics;

public class Circle 
{
	private float xCenter, yCenter;
	private int rayon;
	
	public Circle(float xCen, float yCen, int ray)
	{
		setxCenter(xCen);
		setyCenter(yCen);
		setRayon(ray);
	}
	
	public void render(Graphics g)
	{
		g.draw(new org.newdawn.slick.geom.Circle(xCenter, yCenter, rayon));
	}

	public float getxCenter() {
		return xCenter;
	}

	public void setxCenter(float xCenter) {
		this.xCenter = xCenter;
	}

	public float getyCenter() {
		return yCenter;
	}

	public void setyCenter(float yCenter) {
		this.yCenter = yCenter;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
}
