package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ItemSlot extends GuiCompoment 
{
	private Item item;
	private boolean itemOnMouse = false;
	public boolean equipedSlot = false;
	
	public ItemSlot(float i, float j)
	{
		x = i;
		y = j;		
		width = 32;
		height = 32;
	}
	
	public void init(GameContainer gc, BlackStone cc)
	{
		
	}

	public void update(GameContainer gc, BlackStone cc, int delta) 
	{
	}
	
	public void render(GameContainer gc, BlackStone cc, Graphics g) 
	{
		Color caseColor = Color.lightGray;
		
		
		if(mouseOnSlot(cc))
		{
			caseColor = Color.gray;
		}
		
		drawRectWithColor(g, getX(), getY(), 32, 32, Color.black);
		fillRectWithColor(g, getX()+1, getY()+1, 31, 31, caseColor);
		
		if(item != null)
		{
			if(!itemOnMouse)
				item.drawAsIcon(getX()+1, getY()+1, g);
			else if(itemOnMouse)
				item.drawAsIcon(gc.getInput().getMouseX()+1, gc.getInput().getMouseY()+1, g);
		}

	}

	private boolean mouseOnSlot(BlackStone cc)
	{
		if(cc.MOUSE_X > getX() && cc.MOUSE_X < getX() + width && cc.MOUSE_Y > getY() && cc.MOUSE_Y < getY() + height)
			return true;
		return false;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isItemOnMouse() {
		return itemOnMouse;
	}

	public void setItemOnMouse(boolean itemOnMouse) {
		this.itemOnMouse = itemOnMouse;
	}
	
	public boolean isOccuped()
	{
		if(item != null)
			return true;
		return false;
	}

	public void setRelativePosition(GuiDialog d)
	{
		relativeTo = d;
	}

	public void renderAt(int xtranslate, int ytranslate, GameContainer gc, BlackStone bs, Graphics g) 
	{
		Color caseColor = Color.lightGray;
		float ox = getX();
		float oy = getY();
		
		setX(getX()-xtranslate);
		setY(getY()-ytranslate);
		if(mouseOnSlot(bs))
		{
			caseColor = Color.gray;
		}
		
		drawRectWithColor(g, getX(), getY(), 32, 32, Color.black);
		fillRectWithColor(g, getX()+1, getY()+1, 31, 31, caseColor);
		
		if(item != null)
		{
			if(!itemOnMouse)
				item.drawAsIcon(getX()+1, getY()+1, g);
			else if(itemOnMouse)
				item.drawAsIcon(gc.getInput().getMouseX()+1, gc.getInput().getMouseY()+1, g);
		}
		setX(ox);
		setY(oy);
	}

}
