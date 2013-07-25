package net.xemnias.client;

import org.newdawn.slick.Renderable;

public class BackGround implements Renderable
{
	public Image texture;
	private float x = -500;
	
	public BackGround(net.xemnias.client.Image image)
	{
		texture = image;
	}
	
	public void draw(float arg0, float arg1) 
	{
		texture.draw(x, 0);
	}

	private int loopedMoveRight = 0;
	private int loopedMoveLeft = 0;
	
	public void moveRight()
	{
		if(loopedMoveRight == 10)
		{
			loopedMoveRight = 0;
			x++;
		}
		else
			loopedMoveRight++;
	}
	
	public void moveLeft()
	{
		if(loopedMoveLeft == 10)
		{
			loopedMoveLeft = 0;
			x--;
		}
		else
			loopedMoveLeft++;
	}
}
