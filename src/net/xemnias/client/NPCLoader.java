package net.xemnias.client;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class NPCLoader
{
	private ArrayList<File> npcs_data = new ArrayList<File>();
	private SAXBuilder sxb;
	
	public NPCLoader()
	{
		sxb = new SAXBuilder();

		BlackStone.out.beginPart("PNJ INIT PART");
		detectData();
		
		try 
		{
			loadNPC();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		BlackStone.out.p("Taches terminer.");
		BlackStone.out.stopPart();
	}
	
	private void loadNPC() throws Exception
	{
		BlackStone.out.p("Initialisation des PNJs...");
		for(int i = 0; i < npcs_data.size(); i++)
		{
			File f = npcs_data.get(i);
			Document doc = sxb.build(f);
			Element root = doc.getRootElement();
			
			Element Ename = root.getChild("name");
			Element Edialog = root.getChild("dialog");
			Element Eskin = root.getChild("skin");
			Element Eid = root.getChild("id");
			Element Eicon = root.getChild("icon");
			Element Ejob = root.getChild("job");
			
			File FILE_DIALOG = new File("data/pnj/dialog/"+Edialog.getText());
			
			@SuppressWarnings("resource")
			FileReader reader = new FileReader(FILE_DIALOG);
			StringBuilder strb = new StringBuilder();
			int c;
			while((c = reader.read()) != -1)
			{
				strb.append((char)c);
			}
			
			EntityNPC.getArray().add(i, new NPCIdentity());
			EntityNPC.getArray().get(i).npcDialog = (strb.toString());
			EntityNPC.getArray().get(i).skin = BlackStone.loader.getPNJSkin(Eskin.getText());
			EntityNPC.getArray().get(i).Name = Ename.getText();
			EntityNPC.getArray().get(i).npcID = Integer.parseInt(Eid.getText());
			EntityNPC.getArray().get(i).icon =  BlackStone.loader.getPNJIcon(Eicon.getText()+".png");
			EntityNPC.getArray().get(i).icon_mouse =  BlackStone.loader.getPNJIcon(Eicon.getText()+"_mouse.png");
			
			if(Ejob.getText().equalsIgnoreCase("seller"))
			{
				EntityNPC.getArray().get(i).seller = new  JobSeller();
				Element Eitems = root.getChild("inventory");
				List<Element> items = Eitems.getChildren("item");
				
				for(int z = 0; z < items.size(); z++)
				{
					System.out.println(Item.bow.getItemById(Integer.parseInt(items.get(z).getText())));
					EntityNPC.getArray().get(i).seller.sellableItems.add(Item.bow.getItemById(Integer.parseInt(items.get(z).getText())));
				}
			}
			

			BlackStone.npcOut.beginPart("Nouveau PNJ charger : ");
				BlackStone.npcOut.p("Nom : " + Ename.getText());
				BlackStone.npcOut.p("ID du PNJ : " + Integer.parseInt(Eid.getText()));
				BlackStone.npcOut.p("Skin du PNJ : " + Eskin.getText());
				BlackStone.npcOut.p("Dialogue du PNJ : " + Edialog.getText());
			BlackStone.npcOut.stopPart();
				
		}
		BlackStone.out.p("Initialisation des PNJs terminer.");
	}

	private void detectData() 
	{
		String path = "data/pnj/";
		File dir = new File(path);
		File[] files = dir.listFiles();
		BlackStone.out.p("Nombres de pnjs détectés : "+files.length);
		BlackStone.out.p("Chargement des données...");
		for(int i =0; i < files.length; i++)
		{
			if(files[i].getName().endsWith(".npc"))
				npcs_data.add(files[i]);
		}
	}
}