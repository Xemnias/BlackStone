package net.xemnias.client;


public class SpriteSheet
{
	private int w, h;
	private Image Sheet;
	
	public SpriteSheet(int wP, int hP, Image image)
	{
		w = wP;
		h = hP;
		Sheet = image;
	}
	
	public void renderInUse(int x, int y, int sx, int sy)
	{
		getSpriteAt(sx, sy).drawEmbedded(x, y, w, h);
	}
	
	public Image getSpriteAt(int x, int y)
	{
		return Sheet.getSubImage(x*32, y*32, w, h);
	}
	
	public int getHorizontalCount()
	{
		return Sheet.getWidth()/w;
	}
	
	public int getVerticalCount()
	{
		return Sheet.getHeight()/h;
	}
}
