package net.xemnias.client.block;

import net.xemnias.client.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class BlockSign02 extends Block
{
	private boolean mouseOnIt = false;
	private GuiHelp gui = new GuiHelp();
	private String displayMessage;
	
	public BlockSign02(int i, String name) 
	{
		super(i, name);
		collision = true;
		displayMessage = name;
	}
	
	public void mouseOnIt(BlackStone cc)
	{
		mouseOnIt = true;
	}
	
	public void mouseExit(BlackStone cc)
	{
		mouseOnIt = false;
	}
	
	public void renderWithDelta(Graphics g, BlackStone cc, int delta)
	{
		if(mouseOnIt)
		{
			Vector2f v = distanceWithEntity(cc.getPlayer(), true);
			if(v.x < 32 && v.y < 32)
			{
				g.setColor(Color.black);
				gui.drawCenteredString(displayMessage, parentTile.getxPos()+ parentTile.getWidth()/2, parentTile.getyPos()-g.getFont().getHeight(displayMessage)-5, g);
			}
		}
	}
	
	public void afterCreation()
	{
		super.afterCreation();
		parentTile.box.setBounds(parentTile.getxPos(), parentTile.getyPos()+25, parentTile.getWidth(), parentTile.getHeight()-25);
	}
}
