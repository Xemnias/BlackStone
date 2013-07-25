package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GuiCompomentPlayerInformation extends GuiCompoment 
{
	private Image skin;
	private BlackStone cc;
	
	public GuiCompomentPlayerInformation(BlackStone parent)
	{
		cc = parent;
		skin = BlackStone.loader.getImageByName("guiCompomentPlayerInfo.png");
		x = cc.getContainer().getWidth() - skin.getWidth();
		y = 0;
	}
	
	public void render(GameContainer gc, BlackStone sbg, Graphics g)
	{
		skin.draw(x,y);
		fillRectWithColor(g, x+26, y+7, MathUtils.getXValueOn100(Entity.entityPlayer.life, 229), 9, Color.green);
		fillRectWithColor(g, x+26, y+18, MathUtils.getXValueOn100(Entity.entityPlayer.mana, 229), 9, Color.blue);	
		fillRectWithColor(g, x+26, y+29, MathUtils.getXValueForXP(Entity.entityPlayer.xp, 229, Entity.entityPlayer.getNextLevel()), 9, Color.yellow);
	}
	
	
}
