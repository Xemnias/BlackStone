package net.xemnias.client;

import org.newdawn.slick.geom.Rectangle;

public class CollisionManager 
{

	public static Tile checkCollisionTile(Rectangle rect) 
	{
		for(int i = 0; i < BlackStone.world.tiles.size(); i++)
		{
			if(BlackStone.world.tiles.get(i).isBlock)
			{
				if(BlackStone.world.tiles.get(i).getBlock() != null && BlackStone.world.tiles.get(i).getBlock().collision)
				{
					Tile tile = BlackStone.world.tiles.get(i);
					if(tile.box.intersects(rect))
						return tile;
				}
			}
		}
		return null;
	}
	// 0 = absolute same, 1 = equals or sup, 2 = absolute sup
	public static Tile checkCollisionTile(Rectangle rect, int Zindex, int condition) 
	{
		for(int i = 0; i < BlackStone.world.tiles.size(); i++)
		{
			if(BlackStone.world.tiles.get(i).isBlock)
			{
				if(BlackStone.world.tiles.get(i).getBlock() != null && BlackStone.world.tiles.get(i).getBlock().collision)
				{
					if(condition == 0)
					{
						if(BlackStone.world.tiles.get(i).Zindex == Zindex)
						{
							if(BlackStone.world.tiles.get(i).box.intersects(rect))
								return BlackStone.world.tiles.get(i);
						}
					}
					else if(condition == 1)
					{
						if(BlackStone.world.tiles.get(i).Zindex >= Zindex)
						{
							if(BlackStone.world.tiles.get(i).box.intersects(rect))
								return BlackStone.world.tiles.get(i);
						}
					}
					else if(condition == 2)
					{
						if(BlackStone.world.tiles.get(i).Zindex > Zindex)
						{
							if(BlackStone.world.tiles.get(i).box.intersects(rect))
								return BlackStone.world.tiles.get(i);
						}
					}

				}
			}
		}
		return null;
	}
	
	public static Entity checkCollisionEntity(Entity e) 
	{
		for(int i = 0; i < BlackStone.entities.size(); i++)
		{

			if(BlackStone.entities.get(i) != e)
			{
				Entity tile = BlackStone.entities.get(i);
				if(tile.box.intersects(e.box))
					return tile;
			}
				
		}
		return null;
	}
	public static Entity checkCollisionEntity(Rectangle rect, int Zindex, int condition) 
	{		
		for(int i = 0; i < BlackStone.entities.size(); i++)
		{
			if(BlackStone.entities.get(i).enemy)
			{
					if(condition == 0)
					{
						if(BlackStone.entities.get(i).parentTile.Zindex == Zindex)
						{
							if(BlackStone.entities.get(i).box.intersects(rect))
								return BlackStone.entities.get(i);
						}
					}
					else if(condition == 1)
					{
						if(BlackStone.entities.get(i).parentTile.Zindex >= Zindex)
						{
							if(BlackStone.entities.get(i).box.intersects(rect))
								return BlackStone.entities.get(i);
						}
					}
					else if(condition == 2)
					{
						if(BlackStone.entities.get(i).parentTile.Zindex > Zindex)
						{
							if(BlackStone.entities.get(i).box.intersects(rect))
								return BlackStone.entities.get(i);
						}
					}
	
				
			}
		}
		return null;
	}

}
