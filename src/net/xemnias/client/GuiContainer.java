package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GuiContainer extends GuiDialog 
{
	protected ArrayList<ItemSlot> slots = new ArrayList<ItemSlot>();
	protected boolean haveItemOnMouse = false;
	protected Item itemOnMouse = null;
	public boolean renderEquipementSlot = true;
	
	public ItemSlot whatCaseClick(GameContainer gc)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			ItemSlot slot = slots.get(i);
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			if(mouseX > slot.getX() && mouseX < slot.getX() + slot.getWidth() && mouseY > slot.getY() && mouseY < slot.getY() + slot.getHeight())
				return slot;
		}
		return null;
	}
	
	protected ItemSlot getClickedSlot(BlackStone bs)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			ItemSlot slot = slots.get(i);
			
			if(bs.MOUSE_X > slot.getX() && bs.MOUSE_X < slot.getX() + slot.getWidth() && bs.MOUSE_Y > slot.getY() && bs.MOUSE_Y < slot.getY() + slot.getHeight() && bs.MOUSE_BUTTON_0)
				return slot;
		}
		return null;
	}
	
	protected ItemSlot getClickedSlotAt(int x, int y, BlackStone bs)
	{
		if(bs.MOUSE_BUTTON_0)
		{
			int translateX = (int) (slots.get(0).getX() - x);
			int translateY = (int) (slots.get(0).getY() - y);
			if(bs.MOUSE_BUTTON_0)
				return isMouseOnSlotAt(translateX, translateY, bs.getContainer());
		}
		return null;
	}
	
	public ItemSlot isMouseOnSlot(GameContainer gc)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			ItemSlot slot = slots.get(i);
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			if(mouseX > slot.getX() && mouseX < slot.getX() + slot.getWidth() && mouseY > slot.getY() && mouseY < slot.getY() + slot.getHeight())
				return slot;
		}
		return null;
	}
	
	public void clickOnCase(ItemSlot slot)
	{
		if( slot != null)
		{
			if(slot.getItem() != null && !haveItemOnMouse)
			{
				haveItemOnMouse = true;
				itemOnMouse = slot.getItem();
				itemOnMouse.setParentSlot(slot);
				slot.setItemOnMouse(true);
				
			}
			else if(slot.getItem() == null && haveItemOnMouse)
			{
				haveItemOnMouse = false;
				for(int i = 0; i < slots.size(); i++)
				{
					if(slots.get(i).isItemOnMouse())
					{
						slots.get(i).setItemOnMouse(false);
						slots.get(i).setItem(null);
					}
				}
				slot.setItem(itemOnMouse);			
			}
		}
	}
	
	public void drawItemName(GameContainer gc, Graphics g, BlackStone sbg)
	{

		if(isMouseOnSlot(gc) != null)
			if(isMouseOnSlot(gc).getItem() != null)
			{
				Item item = isMouseOnSlot(gc).getItem();
				String[] infos = new String[4];
				String damage = "Dégats : "+item.damage;
				String protection = "Armure : "+ item.armor;
				String magicType = "Type de magie : "+item.magicType;
				String magicPower = "Puissance magique : "+item.magicPower;
				String name = item.getCompleteName();
				
				if(item.isWeapon)
					infos[0] = damage;
				else if(item.isArmor)
					infos[0] = protection;
				else
					infos[0] = "Objet d'inventaire";
				if(item.isMagic)
				{
					infos[1] = magicType;
					infos[2] = magicPower;
				}
				else
				{
					infos[1] = "";
					infos[2] = "";
				}
				infos[3] = name;
				
				int widthOfBox = 0;
				int heightOfBox = 0;
				
				for(int i = 0; i < infos.length; i++)
				{
					if(g.getFont().getWidth(infos[i]) > widthOfBox)
						widthOfBox = g.getFont().getWidth(infos[i]);
					heightOfBox += g.getFont().getHeight(infos[i]);
				}
				
				g.setColor(new Color(0,0,0,0.85f));
				g.fillRect(sbg.MOUSE_X+2, sbg.MOUSE_Y - heightOfBox -2, widthOfBox+6, heightOfBox+6);
				
				
		
				g.setColor(Color.white);
				if(item.isMagic)
					g.setColor(Color.decode("#FFA100"));
				g.drawString(item.getCompleteName(), sbg.MOUSE_X+ 5, sbg.MOUSE_Y- heightOfBox);
				
		
				g.setColor(Color.decode("#E5E5E5"));
				for(int i = 0; i < 3; i++)
				{
					g.drawString(infos[i], sbg.MOUSE_X+ 5, sbg.MOUSE_Y- heightOfBox + 13*i+18);
				}
			}
	}
	
	public void drawItemNameWithPrice(GameContainer gc, Graphics g, BlackStone sbg)
	{

		if(isMouseOnSlot(gc) != null)
			if(isMouseOnSlot(gc).getItem() != null)
			{
				Item item = isMouseOnSlot(gc).getItem();
				String[] infos = new String[5];
				String damage = "Dégats : "+item.damage;
				String protection = "Armure : "+ item.armor;
				String magicType = "Type de magie : "+item.magicType;
				String magicPower = "Puissance magique : "+item.magicPower;
				String price = "0xd2dd4f\nPrix  : $"+item.price;
				String name = item.getCompleteName();
				
				if(item.isWeapon)
					infos[0] = damage;
				else if(item.isArmor)
					infos[0] = protection;
				else
					infos[0] = "Objet d'inventaire";
				if(item.isMagic)
				{
					infos[1] = magicType;
					infos[2] = magicPower;
				}
				else
				{
					infos[1] = "";
					infos[2] = "";
				}
				infos[3] = price;
				infos[4] = name;
				
				
				int widthOfBox = 0;
				int heightOfBox = 0;
				
				for(int i = 0; i < infos.length; i++)
				{
					if(g.getFont().getWidth(infos[i]) > widthOfBox)
						widthOfBox = g.getFont().getWidth(infos[i]);
					
					heightOfBox += g.getFont().getHeight(infos[i]);
				}

				g.setColor(new Color(0,0,0,0.85f));
				g.fillRect(sbg.MOUSE_X+2, sbg.MOUSE_Y - heightOfBox -2, widthOfBox+6, heightOfBox+6);
				
				
		
				g.setColor(Color.white);
				if(item.isMagic)
					g.setColor(Color.decode("#FFA100"));
				g.drawString(name, sbg.MOUSE_X+ 5, sbg.MOUSE_Y- heightOfBox);
				
		
				g.setColor(Color.decode("#E5E5E5"));
				int rent = 0;
				for(int i = 0; i < 4; i++)
				{
					if(infos[i] == "")
						rent++;
					drawStringWithColor(infos[i], (int)sbg.MOUSE_X+ 5, (int) (sbg.MOUSE_Y- heightOfBox + 13*(i-rent)+18), g);
				}
			}
	}
	
	public void drawItemNameWithPriceAt(int x, int y, GameContainer gc, Graphics g, BlackStone sbg)
	{

		int translateX = (int) (slots.get(0).getX() - x);
		int translateY = (int) (slots.get(0).getY() - y);
		if(isMouseOnSlotAt(translateX,translateY,gc) != null)
			if(isMouseOnSlotAt(translateX,translateY,gc).getItem() != null)
			{
				Item item = isMouseOnSlotAt(translateX, translateY, gc).getItem();
				String[] infos = new String[5];
				String damage = "Dégats : "+item.damage;
				String protection = "Armure : "+ item.armor;
				String magicType = "Type de magie : "+item.magicType;
				String magicPower = "Puissance magique : "+item.magicPower;
				String price = "0xd2dd4f\nPrix  : $"+item.price;
				String name = item.getCompleteName();
				
				if(item.isWeapon)
					infos[0] = damage;
				else if(item.isArmor)
					infos[0] = protection;
				else
					infos[0] = "Objet d'inventaire";
				if(item.isMagic)
				{
					infos[1] = magicType;
					infos[2] = magicPower;
				}
				else
				{
					infos[1] = "";
					infos[2] = "";
				}
				infos[3] = price;
				infos[4] = name;
				
				
				int widthOfBox = 0;
				int heightOfBox = 0;
				
				for(int i = 0; i < infos.length; i++)
				{
					if(g.getFont().getWidth(infos[i]) > widthOfBox)
						widthOfBox = g.getFont().getWidth(infos[i]);
					
					heightOfBox += g.getFont().getHeight(infos[i]);
				}

				g.setColor(new Color(0,0,0,0.85f));
				g.fillRect(sbg.MOUSE_X+2, sbg.MOUSE_Y - heightOfBox -2, widthOfBox+6, heightOfBox+6);
				
				
		
				g.setColor(Color.white);
				if(item.isMagic)
					g.setColor(Color.decode("#FFA100"));
				g.drawString(name, sbg.MOUSE_X+ 5, sbg.MOUSE_Y- heightOfBox);
				
		
				g.setColor(Color.decode("#E5E5E5"));
				int rent = 0;
				for(int i = 0; i < 4; i++)
				{
					if(infos[i] == "")
						rent++;
					drawStringWithColor(infos[i], (int)sbg.MOUSE_X+ 5, (int) (sbg.MOUSE_Y- heightOfBox + 13*(i-rent)+18), g);
				}
			}
	}


	private ItemSlot isMouseOnSlotAt(int x, int y, GameContainer gc)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			ItemSlot slot = slots.get(i);
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			if(mouseX > slot.getX()-x && mouseX < slot.getX() + slot.getWidth() -x&& mouseY > slot.getY()-y && mouseY < slot.getY() + slot.getHeight()-y)
				return slot;
		}
		return null;
	}

	public void renderSlotsAt(int x, int y, GameContainer gc, BlackStone bs, Graphics g) 
	{
		int translateX = (int) (slots.get(0).getX() - x);
		int translateY = (int) (slots.get(0).getY() - y);
		
		for(int i = 0; i < slots.size(); i++)
		{
			if(!slots.get(i).isItemOnMouse())
			{
				if(slots.get(i).equipedSlot)
					if(!renderEquipementSlot)
						return;
				slots.get(i).renderAt(translateX,translateY,gc, bs, g);
			}
		}
		for(int i = 0; i < slots.size(); i++)
		{
			if(slots.get(i).isItemOnMouse())
			{
				if(slots.get(i).equipedSlot)
					if(!renderEquipementSlot)
						return;
				slots.get(i).renderAt(x,y,gc, bs, g);
			}
		}
	}
	
	protected void enableItemMoving(BlackStone sbg) 
	{
		if(sbg.MOUSE_BUTTON_0)
		{
			clickOnCase(whatCaseClick(sbg.getContainer()));
		}
	}
	
	public void renderSlots(GameContainer gc, BlackStone cc, Graphics g)
	{		
		for(int i = 0; i < slots.size(); i++)
		{
			if(!slots.get(i).isItemOnMouse())
			{
				if(slots.get(i).equipedSlot)
					if(!renderEquipementSlot)
						return;
				slots.get(i).render(gc, cc, g);
			}
		}
		for(int i = 0; i < slots.size(); i++)
		{
			if(slots.get(i).isItemOnMouse())
			{
				if(slots.get(i).equipedSlot)
					if(!renderEquipementSlot)
						return;
				slots.get(i).render(gc, cc, g);
			}
		}
	}

	protected void updateSlots(GameContainer gc, BlackStone cc, int delta)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			slots.get(i).update(gc, cc, delta);
		}
	}
	
	public void close() 
	{
		if(haveItemOnMouse)
		{
			itemOnMouse.getParentSlot().setItem(itemOnMouse);
			itemOnMouse.getParentSlot().setItemOnMouse(false);
			itemOnMouse = null;
			haveItemOnMouse = false;
		}
	}
	public void addItem(Item buy) 
	{
		for(int i = 0; i < slots.size(); i++)
		{
			if(!slots.get(i).isOccuped())
			{
				slots.get(i).setItem(buy);
				return;
			}
			if(slots.get(i).getItem().getClass() ==  buy.getClass())
			{
				// TODO : Faire un systeme de stack (ici c'est une fonction appelé a l'achat d'un objet unique)
				return;
			}
		}
	}
}
