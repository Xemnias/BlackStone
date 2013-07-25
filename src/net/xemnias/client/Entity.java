package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Entity extends GameObject implements Cloneable
{
	public int id;
	public String Name;
	public boolean Passive;
	public Rectangle box;
	private boolean haveDamage;

	public Image skin;

	public int turn = 0;
	
	protected float x, y, w, h;
	protected int life = 100;
	protected int mana = 100;
	protected int xp = 0;
	protected int nextLevel = 100;
	protected int level = 1;
	
	protected Animation animation;
	public boolean enemy;

	public static final EntityPlayer entityPlayer = new EntityPlayer(0, "Xemnias", false);
	public static final EntityCorruptedSoul corruptedSoul = new EntityCorruptedSoul(1, "Soul", true);
	public static final EntityNPC npc = new EntityNPC(2, "<PNJ DINAMYC NAME VALUE>", false);
	
	protected IAPathManager IAPath;
	
	public Entity(int i, String name, boolean passive)
	{
		id = i;
		Name = name;
		Passive = passive;
		box = new Rectangle(x, y, w, h);
		
		
		for(int z = 0; z < BlackStone.loadedEntities.size(); z++)
		{
			if(i == BlackStone.loadedEntities.get(z).id)
			{
				System.err.println("ID entity similaire : "+i);
				System.exit(0);
			}
		}
		BlackStone.loadedEntities.add(this);
		BlackStone.entityOut.beginPart("Nouvelle entité chargée :");
			BlackStone.entityOut.p("Nom : " + name);
			BlackStone.entityOut.p("ID : " + id);
			BlackStone.entityOut.p("Passive : " + passive);

			BlackStone.entityOut.stopPart();
	}
	
	protected void init(BlackStone cc)
	{
		
	}
	
	protected void update(BlackStone cc, int delta)
	{
		
	}
	
	protected void render(BlackStone cc, Graphics g)
	{
		
	}
	
	public void renderEntityByList(ArrayList<Entity> entities)
	{
		for(int i = 0; i < entities.size(); i++)
			renderEntity(entities.get(i));
	}

	public void renderEntity(Entity entity)
	{
		if(entity.animation != null)
		{
			if(!haveDamage)
				entity.animation.drawS((int)entity.getX(), (int)entity.getY(), entity.parentTile.Zindex);
			else
				entity.animation.drawS((int)entity.getX(), (int)entity.getY(), ProjectColor.redAlpha095,parentTile.Zindex);
		}
		else
			entity.skin.drawS((int)entity.getX(), (int)entity.getY(), entity.parentTile.Zindex);
	}
	
	protected void collideWithEntity(Entity e)
	{
		
	}

	protected void collideWithTile(Tile t)
	{
		
	}
	
	public Entity getEntityById(int id)
	{
		for(int i = 0; i < BlackStone.loadedEntities.size(); i++)
		{
			if(BlackStone.loadedEntities.get(i).id == id)
				try {
					return (Entity) BlackStone.loadedEntities.get(i).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	public void die(EntityPlayer p) 
	{
		p.addXp(xp);
		BlackStone.entityOut.p("L'entité : "+Name+" (id : "+id+") est morte.");
	}
	

	public void haveDamage(boolean bool)
	{
		haveDamage = bool;
	}
	
	public void setAnimationSprite(Image imageByName)
	{
		SpriteSheet sheet = new SpriteSheet(32, 64, imageByName);
		animation = new Animation(sheet, 200);
	}
	
	public void setAnimation(Animation anim)
	{
		animation = anim;
	}

	
	public IAPathManager getIAPath() 
	{
		
		return IAPath;
	}

	public void setIAPath(IAPathManager iAPath) {
		IAPath = iAPath;
	}

	public float getX() {
		return x;
	}
	
	public int getNextLevel()
	{
		return nextLevel;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public boolean isAlive()
	{
		if(life > 0)
			return true;
		else
			return false;
	}

	public void setParentTile(Tile tile) 
	{
		parentTile = tile;
		setX(tile.getxPos());
		setY(tile.getyPos());
		parentTile.ZIndexBox = box;
	}

	public boolean isDamaged() {
		return haveDamage;
	}
	
	public void damage(int damage)
	{
		life -= damage;
	}

	public static EntityNPC getNPCCopy() 
	{
		try 
		{
			return (EntityNPC) npc.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}


}