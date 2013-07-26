package net.xemnias.client;

import org.newdawn.slick.geom.Rectangle;

public class Arrow 
{
	private Image img;
	float angle;
	public float x;
	public float y;
	public int SPEED = 3;
	public boolean isStopped= false;
	public long time;
	Rectangle box;
	
	public Arrow(double xArrow, double yArrow, double angleRadian) 
	{
		angle = (float) angleRadian;
		x = (float) xArrow;
		y = (float) yArrow;
		img = (RessourceLoader.loadImageWithFilter("old data/arrow.png", null));
		img.setCenterOfRotation(0, 0);
		img.setRotation((float) Math.toDegrees(angle));
		time = System.currentTimeMillis();
	}
	
	public void draw()
	{
		img.draw(x, y);
	}
}
