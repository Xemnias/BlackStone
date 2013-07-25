package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Item extends GameObject implements Cloneable
{
	private int coordinateX;
	private int coordinateY;
	private Image skin;
	public String Name;
	public String sufix = "";
	public String prefix = "";
	protected String colorHexa = "";
	public int id;
	public SpriteSheet itemSheet;
	private ItemSlot parentSlot;
	
	public boolean isMagic = false;
	
	public EnchantementType magicType;
	
	public boolean isWeapon = false;
	public boolean isArmor = false;
	public boolean isOther = false;
	public int damage;
	public int armor;
	public int  magicPower;
	
	public int price = 0;
	public boolean isStackable;
	
	
	
	public Item(int i, int xCoor, int yCoor, String name)
	{
		itemSheet = new SpriteSheet(32, 32, BlackStone.loader.getImageByName("item2.png"));
		setCoordinateX(xCoor);
		setCoordinateY(yCoor);
		id = i;
		Name = name;
		skin = itemSheet.getSpriteAt(xCoor, yCoor);
		
		
		for(int z = 0; z < BlackStone.itemList.size(); z++)
		{
			if(i == BlackStone.itemList.get(z).id)
			{
				System.err.println("ID item similaire : "+i);
				System.exit(0);
			}
		}
		BlackStone.itemList.add(this);
		
		BlackStone.itemOut.beginPart("Nouvel item : ");
			BlackStone.itemOut.p("Nom : "+name);
			BlackStone.itemOut.p("ID : "+id);
			/*
			CommunityGame.itemOut.p("Weapon : "+isWeapon);
			CommunityGame.itemOut.p("Armor : "+isArmor);
			CommunityGame.itemOut.p("Other : "+isOther);
			CommunityGame.itemOut.p("Magic : "+isMagic);
			if(isMagic)
			{
				CommunityGame.itemOut.p("MagicType : "+magicType);
				CommunityGame.itemOut.p("Force magique : "+magicPower);
			}
			*/
		BlackStone.itemOut.stopPart();
	}
	
	public static final ItemIronSword ironSword = new ItemIronSword(0, 0, 0, "Épée");
	public static final ItemIronHammer ironHammer = new ItemIronHammer(1, 1, 0, "Marteau");
	public static final ItemLittleKey key = new ItemLittleKey(2, 2, 0, "clé");
	public static final ItemBow bow = new ItemBow(3, 7, 0, "Arc");
	

	public Item getItemById(int id)
	{
		for(int i = 0; i < BlackStone.itemList.size(); i++)
		{
			if(BlackStone.itemList.get(i).id == id)
				return BlackStone.itemList.get(i);
		}
		return null;
	}
	
	protected void render(Graphics g, BlackStone cc)
	{
		
	}
	
	protected void update(GameContainer gc, BlackStone cc, int delta)
	{
		
	}
	
	protected void onMouseClick(BlackStone cc)
	{
		
	}
	
	public void renderItem(Item item)
	{
		item.skin.drawS(item.xPos, item.yPos, item.parentTile.Zindex);
	}
	
	public void drawAsIcon(float x, float y, Graphics g)
	{
		skin.draw(x,y);
	}
	
	public void setCoor(int x, int y)
	{
		skin = itemSheet.getSpriteAt(x, y);
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Image getSkin() {
		return skin;
	}

	public void setSkin(Image skin) {
		this.skin = skin;
	}
	
	public void getInitializeMessage()
	{
		System.out.println("Item ok");
	}

	public ItemSlot getParentSlot() {
		return parentSlot;
	}

	public void setParentSlot(ItemSlot parentSlot) 
	{
		this.parentSlot = parentSlot;
	}
	
	public String getCompleteName()
	{
		if(!prefix.isEmpty())
			Name.toLowerCase();
		return prefix + Name + sufix;
	}

	public String getCompleteNameWithColor() 
	{
		return colorHexa+getCompleteName();
	}

	public void setParentTile(Tile tile) 
	{
		parentTile = tile;

		xPos = parentTile.getxPos();
		yPos = parentTile.getyPos();
	}
}
