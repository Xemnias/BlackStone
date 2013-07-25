package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GuiBar extends GuiCompoment
{
	private Image skin;
	private int healthState, manaState;
	private int ExpState;
	
	private GuiButton button = new GuiButton(0, 416, 580-31, 30);
	int time = 3000;
	
	public boolean nameDrawed;
	
	public GuiBar(BlackStone parent)
	{
		this.control.add(button);
		try 
		{
			skin = new Image("data/tiles/guiPlayerBar.png");
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}

	public void init(GameContainer gc, BlackStone cc) 
	{
		
	}

	public void update(GameContainer gc, BlackStone cc, int delta) 
	{
		updateButton(gc, cc, delta);
		if(cc.MOUSE_BUTTON_0)
		{
			checkOnClick(gc, cc);
		}
		
		time += delta;
	}
	
	public void render(GameContainer gc, BlackStone cc, Graphics g) 
	{
		skin.getSubImage(0, 0, 832, 64).draw(0,516);
		
		healthState = calculState(cc.getPlayer().life, "LEFT", cc);
		manaState = calculState(cc.getPlayer().mana, "RIGHT", cc);
		ExpState = calculState(cc.getPlayer().xp, "EXP", cc);
		
		skin.getSubImage(0,64, healthState, 38).draw(1,gc.getHeight() - 64 +13);
		skin.getSubImage(390,64, 389-manaState, 38).draw(441,gc.getHeight() - 64 +13);
		skin.getSubImage(0,102, ExpState, 7).draw(2,gc.getHeight() - 63);
		if(button.isMouseOnIt(gc.getInput().getMouseX(), gc.getInput().getMouseY()))
		{
			skin.getSubImage(0, 109, 58, 62).draw(387, 580-62);
		}
		
		if(cc.getPlayer().getSelectedItem() != null)
		{
			cc.getPlayer().getSelectedItem().drawAsIcon(400, 580-64+16, g);
			if(time < 2000)
			{
				drawCenteredStringWithPrefix(cc.getPlayer().getSelectedItem().getCompleteNameWithColor(), 416, 580-96, g);
				nameDrawed = true;
			}
			else if(!nameDrawed)
			{
				time = 0;
			}
			
		}

	}

	public void actionPerformed(int id, BlackStone cc) 
	{
		if(id == 0)
		{
			cc.screenGame.openInventory();
		}
	}
	
	private int calculState(int ref, String side, BlackStone cc)
	{
		if(ref <= 0 && !side.contentEquals("EXP"))
		{
			if(side.contentEquals("LEFT"))
				return 0;
			else
				return 389;
		}
		else
		{
			if(side.equalsIgnoreCase("LEFT"))
			{
				return (ref*389) / 100;
			}
			else if(side.equalsIgnoreCase("RIGHT"))
			{
				return (-ref*(389))/100 + 389;
			}
			
			else if(side.equalsIgnoreCase("EXP"))
			{
				return (ref*828) / (cc.getPlayer().getNextLevel());
			}
		}
		return -1;
	}
}
