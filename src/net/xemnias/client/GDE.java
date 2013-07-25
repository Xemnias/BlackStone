package net.xemnias.client;

import org.newdawn.slick.Graphics;

public class GDE implements Gestionary 
{
	Entity e = null;
	public void updateEntity(BlackStone cc, int delta) 
	{
		for(int i = 0; i < BlackStone.entities.size(); i++)
		{
			e = BlackStone.entities.get(i);
			e.update(cc, delta);
			e.box.setBounds((int)e.x, (int)e.y, e.w, e.h);
			
			if(e.life <= 0)
			{
				e.die(cc.getPlayer());
				BlackStone.entities.remove(e);
			}
		}
	}
	public void registerEntity(Entity en)
	{
		BlackStone.entities.add(en);
		if(en instanceof EntityPlayer)
			BlackStone.PLAYER_RAM_ADRESS = BlackStone.entities.indexOf(en);
	}
	
	public void updateIAPath(int delta)
	{
		for(int i = 0; i < BlackStone.entities.size(); i++)
		{
			if(BlackStone.entities.get(i).getIAPath() != null)
				BlackStone.entities.get(i).getIAPath().moveEntity(BlackStone.entities.get(i), delta);
		}
	}
	
	public void drawIAPath(Graphics g)
	{
		for(int i = 0; i < BlackStone.entities.size(); i++)
		{
			if(BlackStone.entities.get(i).getIAPath() != null)
				BlackStone.entities.get(i).getIAPath().drawPath(g);
		}
	}
	
	public void renderAdditionalStuff(Graphics g, BlackStone cc)
	{
		for(int i = 0; i < BlackStone.entities.size(); i++)
			BlackStone.entities.get(i).render(cc, g);
		for(int i =0; i < BlackStone.world.tiles.size(); i++)
		{
			if(BlackStone.world.tiles.get(i).isBlock)
			{
				BlackStone.world.tiles.get(i).getBlock().renderWithDelta(g, cc, cc.getDelta());
			}
		}
		cc.render.renderSelectedItemEffect(cc.getPlayer().getSelectedItem(), g);
	}
	
	public void initEntities(BlackStone cc)
	{
		BlackStone.out.p("GDE : Initialisation des entitées...");
		for(int i = 0; i < BlackStone.entities.size(); i++)
			BlackStone.entities.get(i).init(cc);
	}
}