package net.xemnias.client;

import java.util.ArrayList;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GuiInventory extends GuiContainer
{
	public int selectedCase;
	private ArrayList<ItemSlot> equipeSlots = new ArrayList<ItemSlot>();
	public Item equipedItem;
	
	public GuiInventory(BlackStone cc)
	{
		for(int x = 0; x < 7; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				slots.add(new ItemSlot((270) + x*32, (270) +  y*32));
			}
		}
		
		int z = 0;
		
		for(int i = 21; i <28; i++)
		{
			slots.add(i, new ItemSlot((270) + z*32, 398));
			slots.get(i).equipedSlot = true;
			z++;
		}
		z=0;
		for(int i = 0; i < slots.size(); i++)
		{
			if(slots.get(i).equipedSlot)
			{
				equipeSlots.add(z, slots.get(i));
				z++;
			}
		}
		slots.get(0).setItem(Item.bow);
	}
	
	public void init(GameContainer gc, BlackStone sbg)
	{

	}

	public void update(GameContainer gc, BlackStone sbg, int delta)
	{
		enableItemMoving(sbg);
	}
	


	public void scrollSelectionBarDown(BlackStone sbg)
	{
			selectedCase--;
			if(selectedCase < 0)
				selectedCase = 6;
			
			equipedItem = equipeSlots.get(selectedCase).getItem();
			if(equipedItem != null)
			{
				System.out.println("Équipé de : " + equipedItem.getCompleteName());
			}
			sbg.getPlayer().setSelectedItem(equipedItem);
	}
	
	public void scrollSelectionBarUp(BlackStone sbg)
	{
			selectedCase++;
			if(selectedCase > 6)
				selectedCase = 0;
			
			equipedItem = equipeSlots.get(selectedCase).getItem();
			if(equipedItem != null)
			{
				System.out.println("Équipé de : " + equipedItem.getCompleteName());
			}
			sbg.getPlayer().setSelectedItem(equipedItem);
	}

	public void render(GameContainer gc, BlackStone sbg, Graphics g) 
	{

		fillRectWithColor(g, 250, 240, 270, 150, Color.gray);
		drawRectWithColor(g, 250, 240, 270, 150, Color.black);
		fillRectWithColor(g, 330, 100, 100, 150, Color.gray);
		drawRectWithColor(g, 330, 100, 100, 140, Color.black);
		drawRectWithColor(g, 340, 110, 80, 120, Color.black);
		fillRectWithColor(g, 345, 115, 70, 110, Color.darkGray);
		

		
		
		renderSlots(gc, sbg, g);
		
		BlackStone.loader.getImageByName("gui.png").getSubImage(400, 0, 35, 35).draw((270) + selectedCase*32 -1, 397);
		
		drawItemName(gc, g, sbg);
	}

	public void addItems(ArrayList<Item> items) 
	{
		for(int i = 0; i < slots.size(); i++)
		{
			for(int j = 0; j < items.size(); j++)
			{
				if(!slots.get(i).isOccuped())
				{
					slots.get(i).setItem(items.get(j));
					items.remove(j);
				}
			}
		}
	}

	public int getItemCount() 
	{
		int size = 0;
		for(int i = 0; i < equipeSlots.size(); i++)
		{
			if(equipeSlots.get(i).getItem() != null)
			{
				size++;
			}
		}
		for(int i = 0; i < slots.size(); i++)
		{
			if(slots.get(i).getItem() != null)
			{
				size++;
			}
			
		}
		return size;
	}


}
