package net.xemnias.client;

import java.util.ArrayList;

import net.xemnias.client.block.GuiHelp;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
public class ScreenGui extends Screen
{
	protected ArrayList<GuiButton> control = new ArrayList<GuiButton>();
	protected GuiHelp gui = new GuiHelp();
	
	public ScreenGui(BlackStone communityGame)
	{
		super(communityGame);
	}
	
	public void actionPerformed(int id, BlackStone cc) {}
	
	
	public void drawDefaultBackground()
	{
		Image img = BlackStone.loader.getBackgroundByName("mainMenuBg.png");
		img.draw();
	}	
	
	public void drawButton(Graphics g, GameContainer gc, BlackStone cc)
	{
		for(int i = 0; i < control.size(); i++)
		{
			control.get(i).render(gc, cc, g);
		}
	}
	
	public void updateButton(GameContainer gc, BlackStone cc, int delta)
	{
		for(int i = 0; i < control.size(); i++)
		{
			control.get(i).update(gc, cc, delta);
		}
	}
	
	public void checkOnClick(GameContainer gc ,BlackStone cc)
	{
		for(int i = 0; i < control.size(); i++)
		{
			if(control.get(i).isMouseOnIt(gc.getInput()))
			{
				actionPerformed(control.get(i).id, cc);
				Sound.button.play();
			}
		}
	}
	
	public void preLoad(GameContainer gc, BlackStone cc)
	{
		
	}
	public void update(GameContainer gc, int delta, BlackStone cc)
	{
		
	}
	public void render(GameContainer gc, Graphics g, BlackStone cc){
		
	}
}
