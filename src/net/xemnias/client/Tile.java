package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Tile
{
	private int xPos, yPos, width, height;
	public boolean isBlock, isItem, isNull;
	public Rectangle box;
	
	private Item item;
	private Block block;
	public Entity entity;
	
	public int Zindex;
	public MapLayout parentLayout;
	public Rectangle ZIndexBox;
	
	private int nullId;
	
	public Tile(int i, int j, int w, int h,	Block blockP, MapLayout mapLayout)
	{
		isBlock = true;
		xPos = i;
		yPos = j;
		width = w;
		height = h;
		block = blockP;
		box = new Rectangle(i, j, width, height);
		block.setParentTile(this);
		parentLayout = mapLayout;
		Zindex = mapLayout.index;
		
		
		block.afterCreation();
	}
	
	public Tile(int i, int j, int w, int h,	Item itemP, MapLayout mapLayout)
	{
		isItem = true;
		xPos = i;
		yPos = j;
		width = w;
		height = h;
		item = itemP;
		box = new Rectangle(i, j, width, height);
		item.setParentTile(this);
		parentLayout = mapLayout;
		Zindex = mapLayout.index;
	}
	
	public Tile(int i, int j, int w, int h, Entity entityP, MapLayout mapLayout) 
	{
		xPos = i;
		yPos = j;
		width = w;
		height = h;
		entity = entityP;
		box = new Rectangle(i, j, width, height);
		BlackStone.entities.add(entity);
		parentLayout = mapLayout;
		Zindex = mapLayout.index;
		entity.setParentTile(this);
	}
	
	public Tile(int i, int j, int w, int h, EntityNPC e, MapLayout mapLayout, String npcName) 
	{
		xPos = i;
		yPos = j;
		width = w;
		height = h;
		entity = e;
		box = new Rectangle(i, j, width, height);
		BlackStone.entities.add(entity);
		parentLayout = mapLayout;
		Zindex = mapLayout.index;
		entity.setParentTile(this);
		e.load(npcName);
	}

	public Tile(int i, int j, int w, int h, int id, MapLayout mapLayout) 
	{
		isNull = true;
		xPos = i;
		yPos = j;
		width = w;
		height = h;
		box = new Rectangle(i, j, width, height);
		parentLayout = mapLayout;
		Zindex = mapLayout.index;
		nullId = id;
	}

	public void render(GameContainer gc, Graphics g)
	{
		if(item != null && isItem)
			item.drawAsIcon(xPos, yPos, g);
		else if(block != null && isBlock)
			block.draw(xPos, yPos, g);
		else if(isNull)
			BlackStone.loader.getTextureById(nullId);
	}
	
	public Block getBlock()
	{
		return block;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

	public boolean isPlayer() 
	{
		return entity !=null && (entity instanceof EntityPlayer);
	}
	
}
