package net.xemnias.client;

public class Arrow 
{
	private Image img;
	private float angle;
	private float x, y;
	public long time;
	
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
