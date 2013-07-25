package net.xemnias.client.block;

import net.xemnias.client.Image;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class GuiHelp {
	public void drawTexturalQuad(Graphics g, Image texture, int x, int y, int w, int h, float drawx, float drawy)
	{
		texture.getSubImage(x, y, w, h).draw(drawx, drawy);
	}
	
	public void drawTexturalQuad(Graphics g, Image texture, int x, int y, int w, int h, float drawx, float drawy, float drawwidth, float drawheight)
	{
		texture.getSubImage(x, y, w, h).draw(drawx, drawy, drawwidth, drawheight);
	}
	
	public void drawRectWithColor(Graphics g, float x, float y, int w, int h, Color black)
	{
		g.setColor(black);
		g.drawRect(x, y, w, h);
	}
	
	public void fillRectWithColor(Graphics g, float f, float g2, int w, int h, Color display)
	{
		g.setColor(display);
		g.fillRect(f, g2, w, h);
	}
	
	public void drawCenteredString(String displayString2, float x, float y, Graphics g)
	{
		g.drawString(displayString2, x- g.getFont().getWidth(displayString2)/2, y-g.getFont().getHeight(displayString2)/2);
	}
	
	public void drawCenteredStringWithPrefix(String displayString2, int x, int y, Graphics g) 
	{
		if(displayString2.startsWith("0x"))
		{
			g.setColor(Color.decode(displayString2.substring(0, 8)));
			displayString2 = displayString2.substring(8);
		}
		g.drawString(displayString2, x- g.getFont().getWidth(displayString2)/2, y-g.getFont().getHeight(displayString2)/2);
	}

	public void drawStringWithColor(String displayString2, int x, int y, Graphics g)
	{

		if(displayString2.startsWith("0x"))
		{
			g.setColor(Color.decode(displayString2.substring(0, 8)));
			displayString2 = displayString2.substring(8);
		}
		g.drawString(displayString2, x,y);
	}
	
	public void fillRectWithAlpha(int x, int y, int w, int h, Color c, float alpha, Graphics g)
	{
		Color cA = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
		g.setColor(cA);
		g.fillRect(x, y, w, h);
	}
}
