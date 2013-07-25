package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ItemBow extends Item 
{
	private double angleRadian;
	private int doClick;
	double acceleration = -0.7;
	private float SPEED = 4;
	private double movement = 1;
	private double timer;
	private float originalPosX, originalPosY;
	private double xArrow,yArrow;
	private Image arrow;
	private ArrayList<Arrow> fichedArrow = new ArrayList<Arrow>();
	private Rectangle box;
	
	public ItemBow(int i, int xCoor, int yCoor, String name) 
	{
		super(i, xCoor, yCoor, name);
		arrow = (RessourceLoader.loadImageWithFilter("old data/arrow.png", null));
		isWeapon = true;
		damage = 20;
		sufix = " en bois";
		arrow.setCenterOfRotation(0, 0);
		box = new Rectangle((int)xArrow, (int)yArrow, 3, 4);
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
			timer =0;
			originalPosX = cc.getPlayer().x;
			originalPosY = cc.getPlayer().y;
			xArrow = cc.getPlayer().x;
			yArrow = cc.getPlayer().y;
			movement = 1;
			angleRadian = Math.atan2(cc.MOUSE_Y - originalPosY-10, cc.MOUSE_X - originalPosX-10);

			box = new Rectangle((int)xArrow, (int)yArrow, 3, 4);
		}
		if(doClick == 2)
		{
			if(cc.MOUSE_BUTTON_0)
				doClick =1;
			if(movement > 0)
			{
			movement = acceleration*timer + SPEED;
			xArrow += Math.cos(angleRadian) * movement;
            yArrow += Math.sin(angleRadian) * movement;
			}

			box.setLocation((float)xArrow, (float)yArrow);
		}
		timer+=delta/10;
		
		
		if(box != null && CollisionManager.checkCollisionTile(box, 1, 1) !=null)
		{
			Arrow a = new Arrow(xArrow, yArrow, angleRadian);
				fichedArrow.add(a);
			movement = -1;
			box = null;
			doClick = -1;
		}
		if(box != null && CollisionManager.checkCollisionEntity(box, 1, 1) != null)
		{
			Entity e  = CollisionManager.checkCollisionEntity(box, 1, 1);
			e.damage(damage);
			movement = -1;
			box = null;
			doClick = -1;
		}
	}

	public void render(Graphics g, BlackStone cc)
	{
		if(doClick == 2)
		{
			arrow.setRotation((float) Math.toDegrees(angleRadian));
			arrow.draw((float)xArrow, (float)yArrow);
			arrow.setRotation(0);
		}
		for(int i = 0; i < fichedArrow.size(); i++)
		{
			fichedArrow.get(i).draw();
			if((System.currentTimeMillis() - fichedArrow.get(i).time) > 5000)
				fichedArrow.remove(fichedArrow.get(i));
		}
	}

}
