package net.xemnias.client;

public class ItemIronSword extends Item 
{
	public ItemIronSword(int i, int xCoor, int yCoor, String name)
	{
		super(i, xCoor, yCoor, name);
		isWeapon = true;
		damage  = 40;
		magicType = EnchantementType.ELEMENTAL;
		magicPower = 10;
		isMagic = true;
		sufix = " en fer �l�mentaire";
		colorHexa = "0xFFDA23";
	}
}
