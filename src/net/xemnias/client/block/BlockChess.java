package net.xemnias.client.block;

import net.xemnias.client.*;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;


public class BlockChess extends Block
{
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean opened = false;
	private GuiHelp gui = new GuiHelp();
	private int timer = 0;
	
	public BlockChess(int i, String name) 
	{
		super(i, name);
		collision = true;
	}
	
	public void afterCreation()
	{
		super.afterCreation();
		parentTile.box.setBounds(parentTile.getxPos()+1, parentTile.getyPos()+25, parentTile.getWidth()-2, parentTile.getHeight()-25);
	}
	public void mouseOnIt(BlackStone cc)
	{
		sprite = BlackStone.loader.getTextureById(0, BlackStone.loader.getImageByName("interactiveBlock.png"));
	}
	
	public void mouseExit(BlackStone cc)
	{
		if(updatable)
			sprite = BlackStone.loader.getTextureById(id);
	}
	
	public void clickOnIt(BlackStone cc)
	{
		Vector2f vector = distanceWithEntity(cc.getPlayer(), true);
		
		if(vector.x <= 48 && vector.y < 48)
			openChess(cc);
			
	}
	
	public void renderWithDelta(Graphics g, BlackStone cc, int delta)
	{
		timer += delta;
		if(opened && !(timer > 3000))
		{
			for(int i =1; i < items.size()+1; i++)
			{
				float yPos = (parentTile.getyPos()-13*i);
				gui.drawCenteredStringWithPrefix(items.get(i-1).getCompleteNameWithColor(), (int)parentTile.getxPos(), (int)yPos, g);
			}
		}
		else if(!opened)
			timer =0;
	}
	
	@SuppressWarnings("unchecked")
	private void openChess(BlackStone cc) 
	{
		sprite = BlackStone.loader.getTextureById(1, BlackStone.loader.getImageByName("interactiveBlock.png"));
		updatable = false;
		opened = true;
		
		cc.getPlayer().getInventory().addItems((ArrayList<Item>) items.clone());
	}

	public void addItems(Item[] i)
	{
		for(int z = 0; z < i.length; z++)
			items.add(i[z]);
	}
	
	public void addItem(Item i)
	{
		items.add(i);
	}

	public void addItemAt(Item i, int index)
	{
		items.add(index, i);
	}
	
	public ArrayList<Item> getItems() 
	{
		return items;
	}

	public void setItems(ArrayList<Item> items) 
	{
		this.items = items;
	}

}
