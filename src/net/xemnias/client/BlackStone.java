package net.xemnias.client;

import java.io.IOException;
import java.util.ArrayList;

import net.xemnias.output.OutputFrame;
import net.xemnias.output.OutputGraphics;
import net.xemnias.output.OutputPanel;

import org.jdom2.DataConversionException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class BlackStone extends StateBasedGame
{
	private static int height, width;
	public ScreenGame screenGame;

	protected int currentDelta = 0;
	private Screen currentScreen;
	
	public RenderEngine render;
	public UpdateEngine update;
	
	public static Map world;
	public GDE gde = new GDE();
	
	public static RessourceLoader loader;
	public NPCLoader npcsLoader;
	
	public static ArrayList<Item> itemList;
	public static ArrayList<Block> blockList;
	public static ArrayList<Entity> entities;
	public static ArrayList<Entity> loadedEntities;
	
	public static int PLAYER_RAM_ADRESS;
	
	public boolean MOUSE_BUTTON_0;
	public boolean MOUSE_BUTTON_1;
	public float MOUSE_X;
	public float ABSOLUTE_MOUSE_X;
	public float MOUSE_Y;
	public Vector2f DRAG_TRANSLATION;
	public static int DELTA_TARGET = 60;
	
	public static final OutputFrame out = new OutputFrame(0, 0, 630, 380);
	public static OutputGraphics fpsOut;
	public static final OutputPanel playerOut = new OutputPanel();
	public static final OutputPanel entityOut = new OutputPanel();
	public static final OutputPanel blockOut = new OutputPanel();
	public static final OutputPanel itemOut = new OutputPanel();
	public static final OutputPanel npcOut = new OutputPanel();
	
	public BlackStone(String name, int i, int j) throws SlickException
	{
		super(name);
		
		out.start();
		out.add(playerOut, "Player");
		out.add(entityOut, "Entities");
		out.add(blockOut, "Blocks");
		out.add(itemOut, "Items");
		out.add(npcOut, "PNJ");
		
		width = i;
		height = j;
		itemList = new ArrayList<Item>();
		blockList = new ArrayList<Block>();
		entities = new ArrayList<Entity>();
		
		
		loadedEntities = new ArrayList<Entity>();
	}


	private void initGame(GameContainer arg0) throws SlickException, IOException, DataConversionException
	{
		fpsOut = new OutputGraphics(arg0);
		out.add(fpsOut, "FPS");
		BlackStone.out.p(" --> #### INITIALISATION JEU ### <--\n");
		ProjectColor.init();

		
		loader = new RessourceLoader();
		loader.bindImageDirectoryToList("old data/");
		
		AnimationList.init();
		
		npcsLoader = new NPCLoader(); // CHARGE LES PNJs

		world = new Map("lvlTEST.map", this);
		world.bindBackGround(new BackGround(loader.getBackgroundByName("test.png")));
		world.load();
		world.buildMap(this);
		
		gde.initEntities(this);
		
		
		BlackStone.out.p("Initialisation du moteur de jeu.");
		currentScreen = new Screen(this);
		currentScreen.currentGuiState = new ScreenMainMenu(this);

		
		render = new RenderEngine(this);
		update = new UpdateEngine(world.getWorldBlock(), entities);
		
		
		setPlayerLast();
	}
	
	
	private void setPlayerLast() 
	{
		EntityPlayer p = null;
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i) instanceof EntityPlayer)
				p = (EntityPlayer) entities.get(i);
			
			if(p != null)
			{
				try
				{
					entities.set(i, entities.get(i+1));
				}
				catch(Exception e)
				{
					entities.set(i, p);
				}
			}
		}
	}


	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		try {
			initGame(arg0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		addState(currentScreen);
		out.p("\n --> ### DÉMARRAGE DU JEU ### <--");
		enterState(0);
	}
	
	/**
	 * Affiche une boite de dialogue 
	 * @param g
	 */
	public void showDialog(GuiDialog g)
	{
		currentScreen.dialog.add(g);
		currentScreen.isDialogOpen = true;
	}
	
	/**
	 * Affiche ou retire une boite de dialogue (selon si elle l'est déjà ou pas)
	 * 
	 * Le graphics du rendu
	 * @param g
	 */

	public void showOrHideDialog(GuiDialog g) 
	{
	
		if(g.isEnabled == false)
		{
			out.p("Affichage de la boite de dialogue : instance de "+g.getClass().getSimpleName() +"( "+(g+"").substring(19)+" )");
			currentScreen.dialog.add(g);
			g.open();
			g.onOpen(this);
			return;
		}
		else if(g.isEnabled == true)
		{
			if(currentScreen.dialog.contains(g))
			{
				out.p("Fermeture de la boite de dialogue : instance de "+g.getClass().getSimpleName() +"( "+(g+"").substring(19)+" )");
				g.closeDialog();
				g.onClose(this);
				currentScreen.dialog.remove(g);
				return;
			}
		}
	}


	public static void main(String[] args) throws SlickException
	{
		BlackStone game = new BlackStone("BlackStone (prealpha)", 832, 580);
		AppGameContainer app = new AppGameContainer(game);
		app.setDisplayMode(width, height, false);
		app.setVSync(true);
		app.setUpdateOnlyWhenVisible(false);
		app.start();
	}
	
	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		BlackStone.height = height;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		BlackStone.width = width;
	}


	public void switchGuiTo(Screen screenGame) 
	{
		currentScreen.isGui = false;
		currentScreen.currentGuiState = null;
		currentScreen.currentstate = screenGame;
		
		currentScreen.currentstate.preLoad(getContainer(), this);
		out.p("Sortie de GUI vers : instance de "+screenGame.getClass().getSimpleName());
	}
	
	public void switchGuiToOtherGui(ScreenGui screen) 
	{
		currentScreen.isGui = true;
		currentScreen.currentGuiState = screen;
		currentScreen.currentstate = null;
		
		currentScreen.currentGuiState.preLoad(getContainer(), this);
	}


	public EntityPlayer getPlayer() 
	{
		for(int i = 0; i < entities.size(); i++)
			if(entities.get(i) instanceof EntityPlayer)
				return (EntityPlayer) entities.get(i);
		return null;
	}
	
	public void mouseWheelMoved(int change)
	{
		if (change < 0) {
			getPlayer().getInventory().scrollSelectionBarDown(this);
		} 
		if (change > 0) {
			getPlayer().getInventory().scrollSelectionBarUp(this);
		}
		
		if(getPlayer().getSelectedItem() != null)
			screenGame.playerBar.nameDrawed = false;
	}
	
	public void mouseDragged(int oldx, int oldy, int newx, int newy)
	{
		DRAG_TRANSLATION = new Vector2f(newx, newy);
	}
	
	public void mouseReleased(int button, int x, int y)
	{
		DRAG_TRANSLATION = null;
	}
	
	public int getDelta() 
	{
		return currentDelta;
	}


	public static void addTile(Tile tile) 
	{
		world.tiles.add(tile);
	}


	public void closeAllDialog()
	{
		for(GuiDialog dialog : screenGame.dialog)
		{
			dialog.closeDialog();
			screenGame.dialog.remove(dialog);
		}
	}


}
