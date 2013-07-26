package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class GuiTabbed extends Gui 
{
	public int x, y, w, h;
	private ArrayList<GuiTab> tabs = new ArrayList<GuiTab>();
	private int selectedIndex;
	
	public GuiTabbed(int i, int j, int width, int height)
	{
		x = i;
		y = j;
		w = width;
		h = height;
	}
	
	public void addTab(GuiTab t)
	{
		tabs.add(t);
		if(tabs.size() <= 0)
			selectedIndex = 0;
	}

	protected void init(GameContainer gc, BlackStone bs) 
	{
		if(tabs.size() > 0)
			tabs.get(selectedIndex).init(gc, bs);
	}
	
	protected void update(GameContainer gc, BlackStone bs, int delta) 
	{
		if(bs.MOUSE_BUTTON_0)
			click(bs);
		tabs.get(selectedIndex).update(gc, bs, delta);
	}

	protected void render(GameContainer gc, BlackStone bs, Graphics g) 
	{
		fillRoundRectWithAlphaColor(g, x+3, y+3, w, h,0.5f,7, Color.black);
		drawRoundRectWithColor(g, x, y, w, h, 7, Color.black);
		fillRoundRectWithColor(g, x+1, y+1, w-1, h-1, 7, Color.gray);
		int spacin =0;
		for(GuiTab t: tabs)
		{
			int widthOfTab = g.getFont().getWidth(t.tabName)+10;
			if(tabs.get(selectedIndex) == t)
			{

				fillRoundRectWithColor(g, x+8+spacin, y-18, widthOfTab, 19, 7, Color.gray);
				drawRoundRectWithColor(g, x+8+spacin, y-18, widthOfTab, 19, 7, Color.black);
				drawRectWithColor(g, x+12+spacin, y, widthOfTab-8, 1, Color.gray);
			}
			else
			{

				fillRoundRectWithColor(g, x+8+spacin, y-16, widthOfTab, 16, 7, Color.lightGray);
			}
			g.setColor(Color.decode("#E0E0E0"));
			drawCenteredString(t.tabName, spacin + x+8+widthOfTab/2, y-10, g);
			if(t.isNotSet)
			{
				t.isNotSet = false;
				t.xB = x+8+spacin;
				t.yB = y-18;
				t.wB = widthOfTab;
				t.hB = 17;
			}
			spacin += widthOfTab;
		}
		
		if(tabs.size() > 0)
			tabs.get(selectedIndex).render(gc, bs, g);
	}
	
	public void click(BlackStone bs)
	{
		for(int i = 0; i< tabs.size(); i++)
		{
			GuiTab t = tabs.get(i);
			if(bs.MOUSE_X >= t.xB && bs.MOUSE_X <= t.xB+t.wB && bs.MOUSE_Y >= t.yB && bs.MOUSE_Y <= t.yB+t.hB)
			{
				selectedIndex = i;
			}
		}
	}
}
