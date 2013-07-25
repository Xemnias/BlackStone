package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class RenderEngine
{
	private View camera;
	public BlackStone parent;
	
	public RenderEngine(BlackStone cc)
	{
		BlackStone.out.beginPart("### GRAPHIC ENGINE INIT ###");
		BlackStone.out.p("Initialisation du moteur graphique.");
		parent = cc;
		camera = new View();
		BlackStone.out.stopPart();
	}
	
	public void renderView(Graphics g)
	{
		g.setColor(Color.white);
		g.drawRect(camera.getxPos(), camera.getyPos(), camera.getWidth(), camera.getHeight());
	}
	
	public void renderTiles(GameContainer gc, Graphics g)
	{
		for(int i =0; i< BlackStone.world.tiles.size(); i++)
			if(camera.isOnScreen(BlackStone.world.tiles.get(i)))
				BlackStone.world.tiles.get(i).render(gc, g);
	}
	
	public void renderEntityByList(ArrayList<Entity> entities)
	{
		for(int i = 0; i < entities.size(); i++)
			renderEntity(entities.get(i));
	}

	public void renderEntity(Entity entity)
	{
		entity.animation.draw((int)entity.getX(), (int)entity.getY());
	}
	
	public View getCamera() 
	{
		return camera;
	}

	public void renderEntities(Graphics g) 
	{
		for(int i = 0; i < BlackStone.entities.size(); i++)
		{
			BlackStone.entities.get(i).renderEntity(BlackStone.entities.get(i));
		}
	}
	
	public void renderSelectedItemEffect(Item i, Graphics g)
	{
		if(i != null)
		i.render(g, parent);
	}

	public void renderCurrentMapBackGround() 
	{
		BlackStone.world.getBackGround().draw(-500, 0);
	}

	
}
