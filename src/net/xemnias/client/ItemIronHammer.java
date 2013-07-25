package net.xemnias.client;

public class ItemIronHammer extends Item {

	public ItemIronHammer(int i, int xCoor, int yCoor, String name)
	{
		super(i, xCoor, yCoor, name);
		isWeapon = true;
		damage = 50;
		sufix = " en fer";
		price = 150;
	}


}