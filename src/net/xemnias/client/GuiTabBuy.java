package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GuiTabBuy extends GuiTab 
{
	private GuiNPCSeller parent;
	private Image coin;
	public GuiTabBuy(String name, GuiTabbed parents, GuiNPCSeller g) 
	{
		super(name, parents);
		parent = g;
		try {
			coin = new Image("old data/coin.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void render(GameContainer gc, BlackStone bs, Graphics g) 
	{
		coin.draw(x+10, y+10);
		drawStringWithColor("0xFFFFFFVendeur : (Or du vendeur : $"+parent.npc.sellerMoney +")", x+32, y+10, g);
		drawStringWithColor("0xFFFFFFJoueur : (Or du joueur : $"+bs.getPlayer().getMoney() +")", x+32, y+157, g);
		parent.renderSlots(gc, bs, g);
		parent.drawItemNameWithPrice(gc, g, bs);
		
		bs.getPlayer().getInventory().renderEquipementSlot = false;
		bs.getPlayer().getInventory().renderSlotsAt(x+32+40, y + 182, gc, bs, g);
		bs.getPlayer().getInventory().drawItemNameWithPriceAt(x+32+40, y+182, gc, g, bs);
		bs.getPlayer().getInventory().renderEquipementSlot = true;
	}

	@Override
	protected void update(GameContainer gc, BlackStone bs, int delta) 
	{
		parent.updateSlots(gc, bs, delta);
		ItemSlot slot;
		if((slot = parent.getClickedSlot(bs)) != null)
		{
			if(slot.getItem() != null)
			{
				if(bs.getPlayer().hasInOffMoney(slot.getItem().price))
				{
					if(bs.getPlayer().canBuy(slot.getItem()))
					{
						bs.getPlayer().buy(slot.getItem());
						parent.npc.sellerMoney += slot.getItem().price;
						slot.setItem(null);
					}
				}
			}
		}
		
		if((slot = bs.getPlayer().getInventory().getClickedSlotAt(x+32+40, y + 182,bs)) != null)
		{
			if(slot.getItem() != null)
			{
				if(parent.npc.hasInOffMoney(slot.getItem().price))
				{
					if(parent.npc.canBuy(slot.getItem(), parent))
					{
						parent.addItem(slot.getItem());
						bs.getPlayer().addMonney(slot.getItem().price);
						parent.npc.sellerMoney -= slot.getItem().price;
						slot.setItem(null);
					}
				}
			}
		}
	}

	@Override
	protected void init(GameContainer gc, BlackStone bs) 
	{
		
	}

}
