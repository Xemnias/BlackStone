package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class EntityNPC extends Entity
{
	public int npcID;
	public String npcDialog;
	private NPCIdentity identity;
	private Image icon;
	private Image OriginalIcon;
	private static ArrayList<NPCIdentity> npcs = new ArrayList<NPCIdentity>();
	private Image icon_mouse;

	private GuiNPCSeller gui;
	
	public EntityNPC(int id, String n, boolean passive) 
	{
		super(id, n, passive);
	}

	public void load(String n)
	{

		for(NPCIdentity i : npcs)
		{
			if(i.Name.equalsIgnoreCase(n))
				identity = i;
		}
		
		npcID = identity.npcID;
		npcDialog = identity.npcDialog;
		skin = identity.skin;
		icon = identity.icon;
		OriginalIcon = icon;
		w = skin.getWidth();
		icon_mouse = identity.icon_mouse;
		h = skin.getHeight();
		parentTile.ZIndexBox = new Rectangle(parentTile.getxPos(), parentTile.getyPos(), skin.getWidth(), skin.getHeight());
		gui = new GuiNPCSeller(identity.seller);
	}
	
	public static ArrayList<NPCIdentity> getArray()
	{
		return npcs;
	}
	
	public void clickOnIt(BlackStone cc)
	{
		 cc.closeAllDialog();
		 cc.showOrHideDialog(gui);
	}
	
	public void mouseOnIt(BlackStone cc)
	{
		icon = icon_mouse;
	}
	
	public void mouseExit(BlackStone cc)
	{
		icon = OriginalIcon;
	}
	
	public void render(BlackStone cc, Graphics g)
	{
		icon.draw(x, y-icon.height-5);
	}
}
