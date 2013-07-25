package net.xemnias.client;

import java.util.ArrayList;

public class UpdateEngine 
{
	private ArrayList<Block> blocks;
	private ArrayList<Entity> entities;
	private Block oldActualMouseBlock;
	private Entity oldActualMouseEntity;
	
	public UpdateEngine(ArrayList<Block> b, ArrayList<Entity> e)
	{
		BlackStone.out.beginPart("### UPDATE ENGINE PART");
		BlackStone.out.p("Nombre de block à mettre à jour : "+b.size());
		BlackStone.out.p("Nombre d'entités à mettre à jour : "+e.size());
		BlackStone.out.stopPart();
		entities = e;
		blocks = b;
	}

	public void updateAll(BlackStone cc) 
	{
		updateMouseObject(cc);
	}

	private void updateMouseObject(BlackStone cc) 
	{
		Block actualMouseBlock = null;
		Entity actualMouseEntity = null;
		
		for(int i = 0; i < blocks.size(); i++)
		{
			if(blocks.get(i).updatable)
			{
				// mouseOnIt function
				if(blocks.get(i).getParentTile().getxPos() < cc.ABSOLUTE_MOUSE_X && blocks.get(i).getParentTile().getxPos()+blocks.get(i).getParentTile().getWidth() > cc.ABSOLUTE_MOUSE_X && blocks.get(i).getParentTile().getyPos() < cc.MOUSE_Y && blocks.get(i).getParentTile().getyPos()+blocks.get(i).getParentTile().getHeight() > cc.MOUSE_Y)
				{

					actualMouseBlock = blocks.get(i);
					
					if(oldActualMouseBlock != actualMouseBlock)
					{
						if(oldActualMouseBlock != null)
							oldActualMouseBlock.mouseExit(cc);
						oldActualMouseBlock = actualMouseBlock;
					}
					
					actualMouseBlock.mouseOnIt(cc);
					if(cc.MOUSE_BUTTON_0)
						actualMouseBlock.clickOnIt(cc);
					
				}
				
			}
		}
		if(oldActualMouseBlock != null && actualMouseBlock == null && oldActualMouseBlock.updatable)
		{
			oldActualMouseBlock.mouseExit(cc);
			oldActualMouseBlock = null;
		}
		if(cc.MOUSE_BUTTON_0)
		{
			if(cc.getPlayer().getSelectedItem() != null)
			cc.getPlayer().getSelectedItem().onMouseClick(cc);
		}
		if(cc.getPlayer().getSelectedItem() != null)
			cc.getPlayer().getSelectedItem().update(cc.getContainer(), cc, cc.currentDelta);
		
		
		
		for(Entity e : entities)
		{
			if(e.x < cc.ABSOLUTE_MOUSE_X && e.x+e.w > cc.ABSOLUTE_MOUSE_X && e.y < cc.MOUSE_Y && e.y+e.h > cc.MOUSE_Y)
			{
				actualMouseEntity = e;
				if(actualMouseEntity != oldActualMouseEntity)
				{
					oldActualMouseEntity = e;
				}
				
				actualMouseEntity.mouseOnIt(cc);
				if(cc.MOUSE_BUTTON_0)
					actualMouseEntity.clickOnIt(cc);
				
			}

		}
		if(actualMouseEntity == null)
		{
			if(oldActualMouseEntity != null)
				oldActualMouseEntity.mouseExit(cc);
			oldActualMouseEntity = actualMouseEntity;
		}
		
	}
}
