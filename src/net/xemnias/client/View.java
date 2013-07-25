package net.xemnias.client;

import org.newdawn.slick.geom.Rectangle;

public class View
{
	private float xPos, yPos, width, height;
	
	public View()
	{

		BlackStone.out.p("Initialisation de la caméra du jeu : 840x580");
		xPos = 00;
		yPos = 00;
		width = 840;
		height = 580;
	}

	public boolean isOnScreen(Tile tile)
	{
		Rectangle r = new Rectangle(xPos, yPos, width, height);
		Rectangle r2 = new Rectangle(tile.getxPos(), tile.getyPos(), tile.getWidth(), tile.getHeight());
		
		return r.intersects(r2);
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}


	
	
}
