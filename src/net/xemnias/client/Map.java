package net.xemnias.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.xemnias.lib.XMLMap;
import net.xemnias.lib.XMLMapLoader;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.newdawn.slick.SlickException;

public class Map
{
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	public MapLayout[] layouts = new MapLayout[3];
	private String Name;
	private String pathToDirectory;
	private BackGround backGround;
	private XMLMap xmlMap;
	private XMLMapLoader mapLoader;
	private File mapFile;
	public float GRAVITY_CONST = (637.5f/BlackStone.DELTA_TARGET);
	
	public Map(String name, BlackStone parent) throws SlickException
	{
		Name = name;
		pathToDirectory = "data/map/";
		mapFile = new File(pathToDirectory+Name);
		xmlMap = new XMLMap(mapFile);
		mapLoader = new XMLMapLoader(xmlMap);
		
		BlackStone.out.beginPart("### WORLD INIT PART ###");
	}
	
	public void buildMap(BlackStone cc) throws IOException
	{
		BlackStone.out.p("Construction de la map ...");
		for(int i = 0; i < layouts.length; i++)
		{
			MapLayout layout = layouts[i];
			layout.buildLayout(cc);
			for(int z =0; z < layout.tiles.size(); z++)
				tiles.add(layout.tiles.get(z));
		}

		BlackStone.out.p("Taches terminés.");
		BlackStone.out.stopPart();
	}

	
	public void load() throws SlickException, DataConversionException
	{
		BlackStone.out.p("Analyse du fichier XML...");
		mapLoader.parseMap();
		List<?> list =  mapLoader.getElementListByChildren("layout");
		BlackStone.out.p("Nombre de \"layout\" trouvés : "+ list.size());
		BlackStone.out.p("Lectures des données XML...");
		for(int i = 0; i < list.size(); i++)
		{
			BlackStone.out.addTabulationPart("Layout numéro : "+i);
			Element e = (Element) list.get(i);
			int index = e.getAttribute("index").getIntValue();
			List<Element> block = e.getChildren("block");
			List<Element> item = e.getChildren("item");
			List<Element> entity = e.getChildren("entity");
			List<Element> npc = e.getChildren("npc");
			
			BlackStone.out.p("Nombre de blocks : "+block.size());
			BlackStone.out.p("Nombre d'items : "+item.size());
			BlackStone.out.p("Nombre d'entités : "+entity.size());
			BlackStone.out.p("Nombre de pnjs : "+npc.size());
			
			layouts[i] = (new MapLayout(index));
			layouts[i].setBlocks(block);
			layouts[i].setItems(item);
			layouts[i].setEntities(entity);
			layouts[i].setNPCS(npc);
			BlackStone.out.removeTabulationPart();
		}

		//tiles.add(new Tile(128, 256-32, 32, 32, Entity.entityPlayer.getEntityById(11)));
	}
	
	
	
	
	
	
	
	
	public ArrayList<Block> getWorldBlock()
	{

		ArrayList<Block> mapBlocks = new ArrayList<Block>();
		for(int i = 0; i < tiles.size(); i++)
			if(tiles.get(i).isBlock)
				mapBlocks.add(tiles.get(i).getBlock());
		return mapBlocks;
	}
	
	
	public void bindBackGround(BackGround bg)
	{
		backGround = bg;
		BlackStone.out.p("Ajout du backGround de niveau.");
	}

	public BackGround getBackGround() {
		return backGround;
	}

}
