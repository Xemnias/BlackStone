package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GuiDialog extends Gui
{
	protected ArrayList<GuiButton> control = new ArrayList<GuiButton>();
	

	
	public boolean isEnabled = false;
	
	public boolean mouseDragging = false;
	public boolean draggable = true;
	
	public float xPos, yPos;
	
	protected void actionPerformed(int id, BlackStone cc)
	{
		
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
			}
			

			else if(control.get(i).isMouseOnIt(gc.getInput().getMouseX(), gc.getInput().getMouseY()))
			{
				actionPerformed(control.get(i).id, cc);
			}
		}
	}

	public void open()
	{
		isEnabled = true;
	}
	
	protected void onOpen(BlackStone game) 
	{
		
	}

	public void closeDialog()
	{
		isEnabled = false;
	}

	protected void onClose(BlackStone game) 
	{
		
	}

}
