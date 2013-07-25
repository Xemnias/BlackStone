package net.xemnias.client;

public class TextureFX 
{
	private Animation anim;
	
	public TextureFX(int x, int y, int w, int h, int fxSpeed, int fxCount, String fileName) 
	{
		Image fx = BlackStone.loader.getImageByName(fileName).getSubImage(x, y, w*fxCount, h);
		anim = new Animation(new SpriteSheet( w,h,fx), fxSpeed);
	}
	
	public Animation getFXRender()
	{
		return anim;
	}
}
