package net.xemnias.client;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class ScreenGame extends Screen {

	public GuiBar playerBar = new GuiBar(parent);
	private GuiCompomentPlayerInformation pInfo;
	public static float X_TRANSLATE_GRAPHICS;
	
	public ScreenGame(BlackStone communityGame)
	{
		super(communityGame);
		pInfo = new GuiCompomentPlayerInformation(communityGame);
	}
	
	public void preLoad(GameContainer gc, BlackStone cc)
	{
		
	}
	public void update(GameContainer gc, int delta, BlackStone cc)
	{
		cc.render.getCamera().setxPos(-X_TRANSLATE_GRAPHICS);
		
		cc.update.updateAll(cc);
		
		if(gc.getInput().isKeyPressed(Input.KEY_E))
			openInventory();

		playerBar.update(gc, cc, delta);
		if(!isDialogOpen)
		{
			cc.gde.updateEntity(cc, delta);
			cc.gde.updateIAPath(delta);
		}
		if(cc.MOUSE_BUTTON_0)
		{
			test++;
		}
		else if(cc.MOUSE_BUTTON_1)
			test--;
	}
	int test = 0;
	public void render(GameContainer gc, Graphics g, BlackStone cc)
	{
		//cc.render.renderCurrentMapBackGround();
		g.translate((int)X_TRANSLATE_GRAPHICS, 0);
		g.pushTransform();
		
		cc.render.renderTiles(gc, g);
		cc.render.renderEntities(g);
		cc.gde.renderAdditionalStuff(g, cc);
		cc.gde.drawIAPath(g);
		
		g.translate(-X_TRANSLATE_GRAPHICS, 0);
		g.pushTransform();
		pInfo.render(gc, cc, g);
		playerBar.render(gc, cc, g);
	}
	
	public void openInventory()
	{

		parent.getPlayer().getInventory().close();
		parent.showOrHideDialog(parent.getPlayer().getInventory());
	}
}
