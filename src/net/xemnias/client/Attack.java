package net.xemnias.client;

import org.newdawn.slick.geom.Rectangle;

public class Attack 
{
	private long time;
	private int baseDamage;
	private float distance;
	private TimerMillis timer = null;
	private Animation animation;
	private EntityPlayer p;
	private Entity touchedEntity;
	
	public boolean RUNNING;
	public boolean TOUCH;
	
	public Attack(long ti, int based, float dis, Animation a)
	{
		setTime(ti);
		setBaseDamage(based);
		setDistance(dis);
		setAnimation(a);
		
		timer = new TimerMillis(time, new Action()
		{
			
			public void actionPerformed() 
			{
				RUNNING = false;
				animation.stop();

				//p.box = new Rectangle(p.x, p.y, p.w, p.h);
				TOUCH = false;
				if(touchedEntity != null)
					touchedEntity.haveDamage(false);
			}
		});
	}
	
	public void start(BlackStone cc, int turn)
	{
		p = cc.getPlayer();
		if(turn == 0)
		{
			p.box = new Rectangle(p.x-distance, p.y, p.w, p.h);
		}
		if(turn == 1)
		{
			p.box = new Rectangle(p.x+distance, p.y, p.w, p.h);
		}
		RUNNING = true;
		timer.start();
		animation.setCurrentFrame(0);
		animation.start();

	}

	public int calculDamage(EntityPlayer entityPlayer) 
	{
		if(entityPlayer.getSelectedItem() != null)
			if(entityPlayer.getSelectedItem().isWeapon)
				return baseDamage + entityPlayer.getSelectedItem().damage;
		return baseDamage;
	}
	
	
	
	

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void setTouchedEntity(Entity collideEntity) 
	{
		touchedEntity = collideEntity;
	}

}
