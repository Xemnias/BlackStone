package net.xemnias.client;

public class GameObject 
{
	public int xPos, yPos;
	public Tile parentTile;
	
	public void setParentTile(Tile t)
	{
		parentTile = t;
		xPos = t.getxPos();
		yPos = t.getyPos();
	}
	
	public Tile getParentTile() 
	{
		return parentTile;
	}

	protected void mouseOnIt(BlackStone cc) 
	{
		
	}

	protected void clickOnIt(BlackStone cc) 
	{
		
	}

	protected void mouseExit(BlackStone cc)
	{
		
	}

	
}
