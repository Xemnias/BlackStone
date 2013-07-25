package net.xemnias.client;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class EntityPlayer extends Entity 
{
	
	private Rectangle up = new Rectangle(x+1, y, w-2, 1);
	private Rectangle left = new Rectangle(x, y+1, 1, h-2);
	private Rectangle down = new Rectangle(x+1, y+h-10, w-2, 1);
	private Rectangle right = new Rectangle(x+w-1, y+1, 1, h-2);
	
	public Tile collideTile = null;
	public Entity collideEntity = null;
	
	private Item item;
	private Attack attack;
	
	private GuiInventory inventory;
	private int money = 150;
	
	public EntityPlayer(int i, String name, boolean att)
	{
		super(i, name, att);
		w = 22;
		h = 62;


	}
		
	private void outPut() 
	{
		BlackStone.playerOut.beginPart("### PLAYER ACTUAL INFORMATION ###");
			BlackStone.playerOut.p("Nom du joueur : "+Name);
			BlackStone.playerOut.p("Niveau du joueur : "+level);
			BlackStone.playerOut.p("Niveau de vie du joueur : "+life);
			BlackStone.playerOut.p("Niveau de mana du joueur : "+mana);
			BlackStone.playerOut.p("XP actuel : "+xp);
			BlackStone.playerOut.p("XP restante jusqu'au prochain niveau : "+(nextLevel - xp));
		BlackStone.playerOut.stopPart();
		
		BlackStone.playerOut.beginPart("### PLAYER INVENTORY PART ###");
			BlackStone.playerOut.p("Nombre d'objet en inventaire : "+inventory.getItemCount());
			BlackStone.playerOut.p("Item selectionner : "+item +" (case numéro +"+inventory.selectedCase+")");
		BlackStone.playerOut.stopPart();
		
		
	}
	
	public void init(BlackStone cc)
	{
		inventory = new GuiInventory(cc);
		animation = new Animation();
		animation.addFrame(RessourceLoader.loadImageWithFilter("/old data/player.png", null), 100);
		attack = new Attack(200, 50, 30, AnimationList.attackRight);
		outPut();
	}
	
	public void update(BlackStone cc, int delta)
	{
		//updateAnimation();

		Zindex(cc, delta);
		move(cc, delta);
		
		translateMap(delta);
		
		toBetterUp(cc, delta);

		//attack(cc);
		
		
	}
	
	
	
	
	

	
	
	private void Zindex(BlackStone cc, int delta)
	{
		for(int i = 0; i < BlackStone.world.tiles.size(); i++)
		{
			Tile tile = BlackStone.world.tiles.get(i);
			if(!tile.isPlayer())
			{
				if(tile.ZIndexBox!=null)
					if(tile.Zindex > 0 && box.intersects(tile.ZIndexBox))
					{
						float deltaObj = cc.render.getCamera().getyPos()+tile.ZIndexBox.getY()+tile.ZIndexBox.getHeight();
						float deltaPla = cc.render.getCamera().getyPos()+y+h;
						
						if(deltaObj < deltaPla && parentTile.Zindex < tile.Zindex )
						{
							tile.Zindex--;
						}
						else if(deltaObj > deltaPla && parentTile.Zindex >= tile.Zindex)
						{
							tile.Zindex++;
						}
					}
			}
		}
	}

	@SuppressWarnings("unused")
	private void attack(BlackStone cc) 
	{
		if(cc.MOUSE_BUTTON_0 && !attack.RUNNING && clickOnGame(cc))
		{
			attack.start(cc, turn);
			animation = attack.getAnimation();

		}
		if(attack.RUNNING && !attack.TOUCH)
		{
			if((collideEntity = CollisionManager.checkCollisionEntity(this)) != null)
			{
				collideEntity.life -= attack.calculDamage(this);
				collideEntity.haveDamage(true);
				attack.setTouchedEntity(collideEntity);
				attack.TOUCH = true;
			}
		}
	}

	private boolean clickOnGame(BlackStone cc) 
	{
		return cc.MOUSE_X > 0 && cc.MOUSE_X < 832 && cc.MOUSE_Y>0 && cc.MOUSE_Y < 580-64;
	}
	
	private void toBetterUp(BlackStone cc, int delta) 
	{
		if(cc.getContainer().getInput().isKeyDown(Input.KEY_T))
		{
			if(!(xp >= nextLevel))
				xp ++;
			else
			{
				xp = 0;
				level++;
				nextLevel = 100*level;
			}
		}
		
	}

	/*private void updateAnimation() 
	{
		if(turn == 0 && !attack.RUNNING)
		{
			animation = AnimationList.playerStandingLeft;
			attack.setAnimation(AnimationList.attackLeft);
		}
		else if(turn == 1 && !attack.RUNNING)
		{
			animation = AnimationList.playerStandingRight;
			attack.setAnimation(AnimationList.attackRight);
		}
	}*/

	private void translateMap(int delta) 
	{
		if(x > -1*ScreenGame.X_TRANSLATE_GRAPHICS+832-320)
			ScreenGame.X_TRANSLATE_GRAPHICS-= 100 * delta / 1000f;
		if(ScreenGame.X_TRANSLATE_GRAPHICS !=0)
		{

			if(x < -ScreenGame.X_TRANSLATE_GRAPHICS+32*10)
			{
				ScreenGame.X_TRANSLATE_GRAPHICS+= 100 * delta / 1000f;
			}
		}
	}

	private void move(BlackStone cc, int delta) 
	{
		// ############################## MOVE ##############################
		//gravityTimer += delta/1000f;

		if(cc.getContainer().getInput().isKeyPressed(Input.KEY_UP))
			parentTile.Zindex++;
		else if(cc.getContainer().getInput().isKeyPressed(Input.KEY_DOWN))
			parentTile.Zindex--;
		
		
		
		if(cc.getContainer().getInput().isKeyDown(Input.KEY_Z))
		{
			y-= 100 * delta / 1000f;;
			updateBoxesPos();
			if((collideTile = CollisionManager.checkCollisionTile(up)) != null)
			{
				y+= 100 * delta / 1000f;;

			}
			

		}
		
		if(cc.getContainer().getInput().isKeyDown(Input.KEY_S))
		{
			y+= 100 * delta / 1000f;;
			updateBoxesPos();
			if((collideTile = CollisionManager.checkCollisionTile(down)) != null)
			{
				y-= 100 * delta / 1000f;;

			}
			

		}
		
		if(cc.getContainer().getInput().isKeyDown(Input.KEY_Q))
		{
			x-= 100 * delta / 1000f;
				
			turn = 0;
			updateBoxesPos();
			if((collideTile = CollisionManager.checkCollisionTile(left)) != null||x < 0)
			{
				x+= 100 * delta / 1000f;
			}
		}
		if(cc.getContainer().getInput().isKeyDown(Input.KEY_D))
		{
			x+= 100 * delta / 1000f;
			turn = 1;
			updateBoxesPos();
			if((collideTile = CollisionManager.checkCollisionTile(right)) != null)
			{
				x-= 100 * delta / 1000f;
			}
		}
		
		// ############################## END MOVE ##############################
	}

	private void updateBoxesPos()
	{
		up.setBounds(x+1, y+53, w-2, 1);
		left.setBounds(x, y+55, 1, 7);
		down.setBounds(x+1, y+h-1, w-2, 1);
		right.setBounds(x+w-1, y+55, 1, 7);
	}

	public void setSelectedItem(Item i)
	{
		item = i;
	}
	
	public Item getSelectedItem()
	{
		return item;
	}

	
	public void addXp(int p)
	{
		xp += p;
	}

	public GuiInventory getInventory()
	{
		return inventory;
	}

	public boolean hasInOffMoney(int price) 
	{
		return money >= price;
	}

	public void buy(Item i) 
	{
		money -= i.price;
		inventory.addItem(i);
	}

	public boolean canBuy(Item item2) 
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

	public int getMoney() 
	{
		return money;
	}

	public void addMonney(int price) 
	{
		money += price;
	}
}