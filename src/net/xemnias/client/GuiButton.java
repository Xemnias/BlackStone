package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class GuiButton extends GuiCompoment 
{
	protected int id;
	private String displayString;
	private Circle circle;
	
	public GuiButton(int i, float xPos, float yPos, String str)
	{
		id = i;
		x = xPos;
		y = yPos;
		
		displayString = str;
		
		width = 400;
		height = 40;
	}

	public GuiButton(int i, float xPos, float yPos, float w, float h)
	{

		id = i;
		x = xPos;
		y = yPos;
		width = w;
		height = h;
	}
	
	public GuiButton(int i, float xP, float yP, int ray)
	{
		id = i;
		x = xP;
		y = yP;
		circle = new Circle(xP, yP, ray);
	}
	
	protected void render(GameContainer gc, BlackStone cc, Graphics g)
	{
		g.setColor(Color.white);
		int i = 0;
		if(isMouseOnIt(gc.getInput()))
		{
			g.setColor(Color.lightGray);
			i+=40;
		}
		drawTexturalQuad(g, BlackStone.loader.getImageByName("gui.png"),0,i,400,40, x, y);
		drawCenteredString(displayString, x+200, y+20, g);

	}



	protected void init(GameContainer gc, BlackStone sbg)
	{
		
	}

	protected void update(GameContainer gc, BlackStone sbg, int delta) 
	{
		
	}
	
	public boolean isMouseOnIt(Input input)
	{
		if(input.getMouseX() > x && input.getMouseX() < x + width && input.getMouseY() > y && input.getMouseY() < y + height)
			return true;
		return false;
	}
	

	public boolean isMouseOnIt(float mouseX, float mouseY)
	{
		if( Math.abs(circle.getxCenter() - mouseX) <= (1/2 + circle.getRayon()))
		{
			if( Math.abs(circle.getyCenter() - mouseY) <= (1/2 + circle.getRayon()))
				return true;
		}
			return false;
	}
	
	public Circle getCircle()
	{
		return circle;
	}
	
}
