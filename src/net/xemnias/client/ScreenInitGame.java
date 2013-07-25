package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ScreenInitGame extends ScreenGui 
{
	private Image xemProd, maciaProd;
	public boolean finish = false;
	private boolean macia= false, xemnias = true;
	public ScreenInitGame(BlackStone cc)
	{
		super(cc);
	}

	public void preLoad(GameContainer gc, BlackStone cc) 
	{
		try {
			xemProd = new Image("data/image/xemProd.png");
			maciaProd = new Image("data/image/maciaProd.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer gc, int delta, BlackStone cc) 
	{
		if(finish)
			parent.switchGuiToOtherGui(new ScreenMainMenu(parent));
	}

	private float a = 1.0f;
	private int loop= 0;
	
	private int side = 0;
	
	public void render(final GameContainer gc, final Graphics g, BlackStone cc) 
	{
		if(xemnias)
		{
			g.drawImage(xemProd, 0, 0);
			if(side == 0)
			{
				if(loop == 30)
				{
					a-= 0.035f;
					loop = 0;
					if((float) a < -0.5f)
						side = 1;
				}
			}
			else if(side == 1)
			{
				if(loop == 30)
				{
					a+= 0.035f;
					loop = 0;
				}
				if(a > 1.2f)
				{
					side = 0;
					a=1.0f;
					xemnias = false;
					loop = 0;
				}
			}
			gui.fillRectWithAlpha(0, 0, 840, 580, Color.black, a, g);
		}
		
		if(!xemnias && !macia)
		{
			if(loop == 1250)
			{
				macia = true;
				loop = 0;
			}
				g.setColor(Color.white);
			g.drawString("En association avec...", gc.getWidth() /2 - g.getFont().getWidth("En association avec...")/2, gc.getHeight() /2 - g.getFont().getHeight("En association avec...")/2);
			
		}
		
		if(macia)
		{
			g.drawImage(maciaProd, 0, 0);
			if(side == 0)
			{
				if(loop == 30)
				{
					a-= 0.035f;
					loop = 0;
					if((float) a < -0.5f)
						side = 1;
				}
			}
			else if(side == 1)
			{
				if(loop == 30)
				{
					a+= 0.035f;
					loop = 0;
				}
				if(a > 1.2f)
				{
					finish = true;
				}
			}
			gui.fillRectWithAlpha(0, 0, 840, 580, Color.black, a, g);
		}
		loop++;
	}
}
