package net.xemnias.client;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class RessourceLoader
{
	private ArrayList<Image> imageList = new ArrayList<Image>();
	private Color transColor = new Color(168,0,70);
	public RessourceLoader() throws SlickException
	{
		
	}
	
	public void bindImageDirectoryToList(String str) throws SlickException
	{
		File path = new File(str);
		
		if (path.isDirectory()) 
		{
			File[] flist = path.listFiles();
            
            if (flist != null)
            {
            	BlackStone.out.p("RessourceLoader : Trouver " +flist.length+ " fichier à charger dans : "+ str);
            	
                for ( int i = 0; i < flist.length; i++) 
                {
	                	imageList.add(i, new Image(flist[i].getPath(), transColor));
	                	imageList.get(i).setName(flist[i].getName());
                } 
            }
            else 
            {
            	System.err.println(path + " : Erreur de lecture.");
            }
		}
	}
	
	public Image getImageByName(String str)
	{
		for(int i = 0; i < imageList.size(); i++)
		{
			if(imageList.get(i).getName().equalsIgnoreCase(str))
				return imageList.get(i);
		}
		return null;
	}
	
	public Image getSpriteWithSheet(String sheetName, int id, int id2)
	{
		for(int i = 0; i < imageList.size(); i++)
		{
			if(imageList.get(i).getName().equalsIgnoreCase(sheetName))
				return imageList.get(i).getSubImage(id*32, id2*32, 32, 32);
		}
		return null;
	}

	public Image getTextureById(int i) 
	{
		int y = i/8;
		int x = i-(y*8);
		SpriteSheet terrainSheet = new SpriteSheet(32, 32, getImageByName("spritesheet.png"));
		
		return terrainSheet.getSpriteAt(x, y);
	}

	public Image getTextureById(int i, Image sheet) 
	{
		int y = i/8;
		int x = i-(y*8);
		SpriteSheet terrainSheet = new SpriteSheet(32, 32, sheet);
		
		return terrainSheet.getSpriteAt(x, y);
	}
	
	public Image getTextureById(int i, int tilePerLine, String nameOfImage) 
	{
		int y = i/tilePerLine;
		int x = i-(y*tilePerLine);
		SpriteSheet terrainSheet = new SpriteSheet(32, 32, getImageByName(nameOfImage));
		
		return terrainSheet.getSpriteAt(x, y);
	}

	public Image getAnimationByName(String string)
	{
		try {
			return new Image("data/entity/animation/"+string);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Image getBackgroundByName(String string) 
	{
		try {
			return new Image("data/map/background/"+string);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image loadImageWithFilter(String ref, Color c)
	{
		try {
			return new Image(ref, c);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Image getPNJSkin(String text) 
	{
		try {
			return new Image("data/pnj/sprite/"+text);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Image getPNJIcon(String text)
	{
		try {
			return new Image("data/pnj/icon/"+text);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
}
