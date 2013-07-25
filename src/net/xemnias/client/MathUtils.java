package net.xemnias.client;

public class MathUtils
{
	public static int getXValueOn100(int val, int size)
	{
		return (val*size)/100;
	}
	
	public static int getXValueForXP(int val, int size, int div)
	{
		return (val*size)/div;
	}
}
