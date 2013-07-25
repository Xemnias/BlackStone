package net.xemnias.client;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class MapLayout extends Layout
{
	private List<Element> blocks;
	private List<Element> items;
	private List<Element> entities;
	private List<Element> npcs;
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public MapLayout(int i)
	{
		index = i;
	}
	

	public void buildLayout(BlackStone cc) 
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			Element e = blocks.get(i);
			int id = readID(e);
			int x = readX(e);
			int y = readY(e);
			if(Block.nullBlock.getBlockById(id) != null)
				tiles.add(new Tile(x, y, 32, 32, Block.nullBlock.getBlockById(id), this));
			else
			{
				tiles.add(new Tile(x, y, 32, 32, id, this));
			}
		}
		
		for(int i = 0; i < items.size(); i++)
		{
			Element e = items.get(i);
			int id = readID(e);
			int x = readX(e);
			int y = readY(e);
			tiles.add(new Tile(x, y, 32, 32, Item.bow.getItemById(id), this));
		}
		
		for(int i = 0; i < entities.size(); i++)
		{

			Element e = entities.get(i);
			int id = readID(e);
			int x = readX(e);
			int y = readY(e);
			tiles.add(new Tile(x, y, 32, 32, Entity.entityPlayer.getEntityById(id), this));
		}

		for(int i = 0; i < npcs.size(); i++)
		{

			Element e = npcs.get(i);
			int x = readX(e);
			int y = readY(e);
			String npcName = e.getText();
			System.out.println("  --> Reading data : " + npcName);
			tiles.add(new Tile(x, y, 32, 32, Entity.getNPCCopy(), this, npcName));
		}
	}
	
	public List<?> getBlocks()
	{
		return blocks;
	}
	
	public List<?> getItems()
	{
		return items;
	}
	
	public List<?> getEntities()
	{
		return entities;
	}
	
	public void setBlocks(List<Element> list)
	{
		this.blocks = list;
	}

	public void setItems(List<Element> items) 
	{
		this.items = items;
	}

	public void setEntities(List<Element> entities) 
	{
		this.entities = entities;
	}

	public void setNPCS(List<Element> list)
	{
		this.npcs = list;
	}

	private int readX(Element e)
	{
		try 
		{
			return e.getAttribute("x").getIntValue();
		} 
		catch (DataConversionException e1)
		{
			e1.printStackTrace();
		}
		return -1;
	}
	
	private int readY(Element e)
	{
		try 
		{
			return e.getAttribute("y").getIntValue();
		} 
		catch (DataConversionException e1)
		{
			e1.printStackTrace();
		}
		return -1;
	}
	
	private int readID(Element e)
	{
		return Integer.parseInt(e.getText());
	}


	public void render(GameContainer gc, BlackStone cc, Graphics g, View cam)
	{
		for(int i =0; i < tiles.size(); i++)
				tiles.get(i).render(gc, g);
			
	}


}
