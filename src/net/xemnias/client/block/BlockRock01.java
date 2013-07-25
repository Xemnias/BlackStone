package net.xemnias.client.block;

import net.xemnias.client.*;
public class BlockRock01 extends Block 
{

	public BlockRock01(int i, String name)
	{
		super(i, name);
		collision = true;
	}
	
	public void afterCreation()
	{
		super.afterCreation();
		parentTile.box.setBounds(parentTile.getxPos(), parentTile.getyPos()+20, parentTile.getWidth(), parentTile.getHeight()-20);
	}

}
