package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GuiCompoment extends Gui
{
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	protected GuiDialog relativeTo = null;

	protected ArrayList<GuiButton> control = new ArrayList<GuiButton>();
	
	
	protected void render(GameContainer gc, BlackStone sbg, Graphics g)
	{
		
	}
	
	protected void init(GameContainer gc, BlackStone sbg)
	{
		
	}

	protected void update(GameContainer gc, BlackStone sbg, int delta)
	{
		
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

	protected void actionPerformed(int id, BlackStone cc)
	{
		
	}
	
	public float getX() {
		if(relativeTo != null)
			return x+relativeTo.xPos;
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		if(relativeTo != null)
			return y+relativeTo.yPos;
		
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
