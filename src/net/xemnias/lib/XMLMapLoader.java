package net.xemnias.lib;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLMapLoader 
{
	private XMLMap map;
	private SAXBuilder sxb;
	private Element root;
	private Document doc;
	
	public XMLMapLoader(XMLMap m) 
	{
		map = m;
		sxb = new SAXBuilder();
	}
	
	public void parseMap()
	{
		try
		{
			doc = sxb.build(map.getMapFile());
		}
		catch(Exception e){}
		root = doc.getRootElement();
	}
	
	public Element getElement(String elementName)
	{
		return root.getChild(elementName);
	}
	
	public int getCount(String nodeName)
	{
		return getElementListByChildren(nodeName).size();
	}
	
	public List<?> getElementListByChildren(String children)
	{
		return root.getChildren(children);
	}

}
