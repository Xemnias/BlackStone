package net.xemnias.client;

public class Location 
{
	public int x, y;
	
	public Location(int i, int j)
	{
		x =i;
		y =j;
	}

	public Location(float x2, float y2) {
		x = (int) x2;
		y = (int) y2;
	}
}
