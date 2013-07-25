package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public abstract class GuiTab extends GuiCompoment 
{
	public String tabName;
	public int xB, yB, wB, hB;
	public int x, y, w, h;
	public boolean isNotSet =true;
	public GuiTab(String name, GuiTabbed parent)
	{
		tabName = name;
		x = parent.x;
		y = parent.y;
		w = parent.w;
		h = parent.h;
	}

	protected abstract void render(GameContainer gc, BlackStone bs, Graphics g);
	
	protected abstract void update(GameContainer gc, BlackStone bs, int delta);
	
	protected abstract void init(GameContainer gc, BlackStone bs);
}
