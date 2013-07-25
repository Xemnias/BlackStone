package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
public class ScreenMainMenu extends ScreenGui
{
	
	public ScreenMainMenu(BlackStone cc)
	{
		super(cc);
		control.add(new GuiButton(0, cc.getContainer().getWidth()/2 -200, cc.getContainer().getHeight()/4, "Jouer"));
	}

	public void preLoad(GameContainer gc, BlackStone cc) 
	{
		
	}

	public void update(GameContainer gc, int delta, BlackStone cc) 
	{
		updateButton(gc, parent, delta);
		if(cc.MOUSE_BUTTON_0)
			checkOnClick(gc, parent);
	}
	
	public void render(GameContainer gc, Graphics g, BlackStone cc) 
	{
		drawDefaultBackground();
		gui.fillRectWithAlpha(0, 0, 840, 580, Color.black, 0.3f, g);
		drawButton(g, gc, parent);
	}

	public void actionPerformed(int id, BlackStone cc) 
	{
		if(id == 0)
		{
			cc.screenGame = new ScreenGame(parent);
			parent.switchGuiTo(cc.screenGame);
		}
	}
}
