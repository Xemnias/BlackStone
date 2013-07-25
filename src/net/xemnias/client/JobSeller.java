package net.xemnias.client;

import java.util.ArrayList;

public class JobSeller extends Job
{
	public ArrayList<Item> sellableItems = new ArrayList<Item>();
	public int sellerMoney = 10000;
	public boolean hasInOffMoney(int price)
	{
		return sellerMoney >= price;
	}
	public boolean canBuy(Item item2, GuiNPCSeller inventory) 
	{
		for(int i = 0; i < inventory.slots.size(); i++)
		{
			if(!inventory.slots.get(i).isOccuped() || (inventory.slots.get(i).getItem().getClass() == item2.getClass() && item2.isStackable))
			{
				return true;
			}
		}
		return false;
	}
	public void buy(Item item)
	{
		sellableItems.add(item);
		sellerMoney -= item.price;
	}
}
