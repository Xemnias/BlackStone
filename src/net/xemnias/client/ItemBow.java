package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ItemBow extends Item 
{
	private int doClick;
	private float originalPosX, originalPosY;
	private ArrayList<Arrow> fichedArrow = new ArrayList<Arrow>();
	
	public ItemBow(int i, int xCoor, int yCoor, String name) 
	{
		super(i, xCoor, yCoor, name);
		isWeapon = true;
		damage = 20;
		sufix = " en bois";
	}
	
	public void onMouseClick(BlackStone cc)
	{
		doClick =1;
	}
	
	public void update(GameContainer gc, BlackStone cc, int delta)
	{
		if(doClick == 1)
		{
			doClick = 2;
			originalPosX = cc.getPlayer().x;
			originalPosY = cc.getPlayer().y;
			Arrow a = new Arrow(cc.getPlayer().x, cc.getPlayer().y, Math.atan2(cc.MOUSE_Y - originalPosY-10, cc.MOUSE_X - originalPosX-10));
			fichedArrow.add(a);
			a.box = new Rectangle(a.x, a.y, 3, 4);
		}
		if(doClick == 2)
		{
			if(cc.MOUSE_BUTTON_0)
				doClick =1;
			
			for(Arrow a : fichedArrow)
			{
				if(a.SPEED > 0 && !a.isStopped)
				{
					a.x += Math.cos(a.angle) * a.SPEED;
		            a.y += Math.sin(a.angle) * a.SPEED;
					a.box.setLocation(a.x, a.y);
					

				}
				if(a.box != null && CollisionManager.checkCollisionTile(a.box, 1, 1) !=null)
				{
					a.SPEED = -1;
					a.box = null;
					a.isStopped = true;
					a.time = 0;
				}
				if(a.box != null && CollisionManager.checkCollisionEntity(a.box, 1, 1) != null)
				{
					Entity e  = CollisionManager.checkCollisionEntity(a.box, 1, 1);
					e.damage(damage);
					a.SPEED = -1;
					a.box = null;
					a.isStopped = true;
					a.time = 0;
				}
			}

		}
		
		
		
	}

	public void render(Graphics g, BlackStone cc)
	{
		for(int i = 0; i < fichedArrow.size(); i++)
		{
			fichedArrow.get(i).draw();
			if((System.currentTimeMillis() - fichedArrow.get(i).time) > 5000 && fichedArrow.get(i).isStopped)
				fichedArrow.remove(fichedArrow.get(i));
		}
	}

}
