package net.xemnias.client.block;

import net.xemnias.client.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class BlockTreePopper01 extends Block
{

	public BlockTreePopper01(int i, String name) 
	{
		super(i, name);
		sprite = BlackStone.loader.getTextureById(0, 16, "forest.png");
		collision = true;
	}

	public void afterCreation()
	{
		super.afterCreation();
		parentTile.ZIndexBox = new Rectangle(parentTile.getxPos()+12, parentTile.getyPos()+10, 40, 54);
		parentTile.box =  new Rectangle(parentTile.getxPos()+20, parentTile.getyPos()+52, 24, 10);
		Tile t1;
		Tile t2;
		Tile t3;
		BlackStone.addTile(t1 = new Tile(parentTile.getxPos()+32, parentTile.getyPos(), 32, 32, new upRight(-2, "Feuilles haut droite"), BlackStone.world.layouts[1]));
		BlackStone.addTile(t2 = new Tile(parentTile.getxPos(), parentTile.getyPos()+32, 32, 32, new downLeft(-2, "Feuilles bas gauche"), BlackStone.world.layouts[1]));
		BlackStone.addTile(t3 = new Tile(parentTile.getxPos()+32, parentTile.getyPos()+32, 32, 32, new downRight(-2, "Feuilles bas droite"), BlackStone.world.layouts[1]));
		
		t1.ZIndexBox = parentTile.ZIndexBox;
		t2.ZIndexBox = parentTile.ZIndexBox;
		t3.ZIndexBox = parentTile.ZIndexBox;
		t1.box = parentTile.box;
		t2.box = parentTile.box;
		t3.box = parentTile.box;
	}
	
	public void renderWithDelta(Graphics g, BlackStone cc, int delta)
	{
		//g.draw(parentTile.ZIndexBox);
		//g.draw(cc.getPlayer().parentTile.ZIndexBox);
	}
	
	private class upRight extends Block
	{

		public upRight(int i, String name) {
			super(i, name);
			sprite = BlackStone.loader.getTextureById(1, 16, "forest.png");
		}
		
	}
	
	private class downLeft extends Block
	{

		public downLeft(int i, String name) {
			super(i, name);
			sprite = BlackStone.loader.getTextureById(2, 16, "forest.png");
		}
		
	}
	
	private class downRight extends Block
	{

		public downRight(int i, String name) {
			super(i, name);
			sprite = BlackStone.loader.getTextureById(3, 16, "forest.png");
		}
		
	}
}
