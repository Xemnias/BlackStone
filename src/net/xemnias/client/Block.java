package net.xemnias.client;

import net.xemnias.client.block.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Block extends GameObject implements Cloneable
{
	public int id;
	public Image sprite;
	public String Name;
	public boolean collision = false;
	
	public boolean updatable = true;
	
	public Block(int i, String name)
	{
		id = i;
		Name = name;
		

		sprite = BlackStone.loader.getTextureById(i);
		
		for(int z = 0; z < BlackStone.blockList.size(); z++)
		{
			if(i == BlackStone.blockList.get(z).id && i != -2)
			{
				System.err.println("ID block similaire : "+i);
				System.exit(0);
			}
		}
		BlackStone.blockList.add( this);
		
		if(id == -2)
			return;
		
		BlackStone.blockOut.beginPart("Nouveau block : ");
			BlackStone.blockOut.p("Nom : "+name);
			BlackStone.blockOut.p("ID : "+id);
		BlackStone.blockOut.stopPart();
	}
	
	public static Block nullBlock = new Block(-1, "nullBlock");
	public static BlockGrass dirt = new BlockGrass(0, "Terre");
	public static BlockDirt grass = new BlockDirt(1, "Herbe");
	public static BlockVegetal01 vegetal01 = new BlockVegetal01(2, "Véegétal 01");
	public static BlockStump01 stump01 = new BlockStump01(3, "Souche 01");
	public static BlockSign01 sign01 = new BlockSign01(4, "Petit panneau de bois");
	public static BlockRock01 rock01 = new BlockRock01(5, "Rocher en pierre et mousse");
	public static BlockChess chess = new BlockChess(6, "Coffre");
	public static BlockSign02 sign02 = new BlockSign02(7, "Grand Panneau en bois");
	public static BlockTreePopper01 treePopper01 = new BlockTreePopper01(8, "Générateur d'arbre 01");
	public static BlockTreePopper02 treePopper02 = new BlockTreePopper02(9, "Générateur d'arbre 02");
	
	
	public void draw(float xPos, float yPos, Graphics g)
	{
		sprite.drawS(xPos, yPos,parentTile.Zindex);
	}
	
	protected void renderWithDelta(Graphics g, BlackStone cc, int delta)
	{
		
	}
	
	/** DOIT ETRE APPELER EN SUPER SI SURCHARGÉE !**/
	protected void afterCreation()
	{
		parentTile.ZIndexBox = new Rectangle(parentTile.getxPos(), parentTile.getyPos(), sprite.getWidth(), sprite.getHeight());
	}
	
	public Vector2f distanceWithEntity(Entity e, boolean downPosition)
	{
		Vector2f v;
		if(downPosition)
		{
			v = new Vector2f(e.x-parentTile.getxPos(), (e.y+e.h) - (parentTile.getyPos()+parentTile.getHeight()));
			if(v.x < 0)
				v.x*=-1;

			if(v.y < 0)
				v.y*=-1;
			return v;
		}
		
		v =  new Vector2f(e.x-parentTile.getxPos(), e.y - parentTile.getyPos());
		if(v.x < 0)
			v.x*=-1;

		if(v.y < 0)
			v.y*=-1;
		return v;
	}
	
	public Block getBlockById(int id2)
	{
		for(int i = 0; i < BlackStone.blockList.size(); i++)
		{
			if(BlackStone.blockList.get(i).id == id2)
			{
				try {
					return (Block) BlackStone.blockList.get(i).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public Tile getTileUpTo(Block b)
	{
		if(b.getParentTile().isBlock)
		{
			Tile t = b.getParentTile();
			return getTileAt(t.getxPos(), t.getyPos()-32);
		}
		
		return null;
	}	
	
	private Tile getTileAt(float x, float y) 
	{
		for(int i = 0; i < BlackStone.world.tiles.size(); i++)
		{
			if(BlackStone.world.tiles.get(i).getxPos() == x && BlackStone.world.tiles.get(i).getyPos() == y)
			{
				return BlackStone.world.tiles.get(i);
			}
		}
		
		return null;
	}

	public Tile getParentTile() {
		return parentTile;
	}

	public void setParentTile(Tile parentTile) {
		this.parentTile = parentTile;
		xPos = parentTile.getxPos();
		yPos = parentTile.getyPos();
	}


}
