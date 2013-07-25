package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class TestTab extends GuiTab {

	public TestTab(String name, GuiTabbed parent) 
	{
		super(name, parent);
	}
	
	public void render(GameContainer gc, BlackStone sbg, Graphics g)
	{
		fillRectWithColor(g, x, y, 100, 100, Color.white);
	}

}
