package net.xemnias.client;

import org.newdawn.slick.Graphics;

public class IAPathManager extends IAManager
{
	public Location originalLocation;
	public Location futurLocation;
	public boolean goBack;
	
	public boolean isMovingToFutur, isMovingToOriginal;
	
	public IAPathManager(Location loc, Location futurLoc, boolean gb)
	{
		originalLocation = loc;
		futurLocation = futurLoc;
		goBack = gb;
	}
	
	public void moveEntity(Entity en, int delta)
	{
		Location loc = new Location((int)en.x, (int)en.y);
		
		if((int)originalLocation.x >= loc.x && (int)originalLocation.y == (int)loc.y) // si il est la position de départ
		{
			isMovingToFutur = true;
		}
		if((int)futurLocation.x <= loc.x && (int)futurLocation.y == (int)loc.y && goBack) // si on est a la position final && qu'on y retourne
		{
			isMovingToOriginal = true;

		}

		
		if(isMovingToFutur)
		{
			if((int)loc.x >= futurLocation.x && (int)loc.y == futurLocation.y)
			{
				isMovingToFutur = false;
			}
			
			if((int)originalLocation.x < futurLocation.x && isMovingToFutur)
			{
				en.x+=(int) ((100 * delta / 1000f));
			}
			if((int)originalLocation.x > futurLocation.x && isMovingToFutur)
			{
				en.x-=(int) (100 * delta / 1000f);
			}
			
			/*if((int)originalLocation.y < futurLocation.y && isMovingToFutur)
			{
				en.y+=(int) (100 * delta / 1000f);
			}
			if((int)originalLocation.y > futurLocation.y && isMovingToFutur)
			{
				en.y-=(int) (100 * delta / 1000f);
			}*/
			
		}
		if(isMovingToOriginal)
		{
			if((int)loc.x <= originalLocation.x && (int)loc.y == originalLocation.y)
			{
				isMovingToOriginal = false;
			}
			if((int)futurLocation.x < originalLocation.x && isMovingToOriginal)
			{
				en.x+=(int) (100 * delta / 1000f);
			}
			if(futurLocation.x > originalLocation.x && isMovingToOriginal)
			{
				en.x-=(int) (100 * delta / 1000f);
			}
			/*
			if(futurLocation.y < originalLocation.y && isMovingToOriginal)
			{
				en.y+=(int) (100 * delta / 1000f);
			}
			if(futurLocation.y > originalLocation.y && isMovingToOriginal)
			{
				en.y-= (int) (100 * delta / 1000f);
			}*/
		}
	}

	public void drawPath(Graphics g) 
	{
		//g.drawLine(originalLocation.x, originalLocation.y, futurLocation.x, futurLocation.y);
	}
}
