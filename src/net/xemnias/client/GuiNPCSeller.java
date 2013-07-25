package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GuiNPCSeller extends GuiContainer
{
	public JobSeller npc;
	private GuiTabbed tabbed = new GuiTabbed(224, 100, 384, 300);
	
	public GuiNPCSeller(JobSeller n)
	{
		xPos = 224;
		yPos = 100;
		npc = n;
		for(int x =0; x < 10; x++)
		{
			for(int y =0; y < 3;y++)
			{
				ItemSlot s = new ItemSlot(x*32 + 32, y*32 + 32);
				s.setRelativePosition(this);
				slots.add(s);
			}
		}
		
		for(int x =0; x < npc.sellableItems.size(); x++)
		{
			slots.get(x).setItem(npc.sellableItems.get(x));
		}
		
		tabbed.addTab(new GuiTabBuy("Achat", tabbed, this));
	}
	
	public void init(GameContainer gc, BlackStone sbg) 
	{
	}

	public void update(GameContainer gc, BlackStone bs, int delta)
	{
		
		
		tabbed.update(gc, bs, delta);
	}
	

	public void render(GameContainer gc, BlackStone cc, Graphics g)
	{
		
		
		tabbed.render(gc, cc, g);
	}

}
