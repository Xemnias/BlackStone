package net.xemnias.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class EntityCorruptedSoul extends Entity {

	public EntityCorruptedSoul(int i, String name, boolean att)
	{
		super(i, name, att);
		animation = AnimationList.corruptedSoulStantingRight;
		level  = 1;
		xp = 10*level;
		w = animation.getCurrentFrame().getWidth();
		h = animation.getCurrentFrame().getHeight();
		enemy = true;
	}

	protected void render(BlackStone cc, Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(x + 3, y - 10, life /3, 4);
	}
}
