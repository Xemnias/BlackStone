package net.xemnias.client;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Screen extends BasicGameState
{
	
	public boolean isDialogOpen = false;
	public ArrayList<GuiDialog> dialog = new  ArrayList<GuiDialog>();
	public BlackStone parent;
	public boolean isGui = true;
	
	public Screen currentstate;
	public ScreenGui currentGuiState;

	public Screen(BlackStone communityGame)
	{
		parent = communityGame;
	}

	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		if(!isGui)
			currentstate.preLoad(arg0, parent);
		if(isGui)
		{
			currentGuiState.preLoad(arg0, parent);
		}
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		if(!isGui)
		{
			currentstate.render(arg0, arg2, parent);
			renderDialog(arg0, arg2);
		}
		if(isGui)
		{
			currentGuiState.render(arg0, arg2, parent);
		}
		arg2.translate(ScreenGame.X_TRANSLATE_GRAPHICS, 0);
		arg2.pushTransform();
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		parent.currentDelta = arg2;
		doTheControlTest(arg0);
		
		if(!isGui)
		{
			currentstate.update(arg0, arg2, parent);
			if(dialog.size() > 0)
			{
				for(GuiDialog d : dialog)
				{
					if(d.isEnabled)
					{
						d.update(arg0,parent,arg2);
						d.updateButton(arg0, parent, arg2);
						
						if(parent.MOUSE_BUTTON_0)
							d.checkOnClick(arg0, parent);
					}
				}
			}
		}
		if(isGui)
		{
			currentGuiState.update(arg0, arg2, parent);
		}
	}

	private void doTheControlTest(GameContainer arg0) 
	{
		
		parent.MOUSE_X = arg0.getInput().getMouseX();
		parent.ABSOLUTE_MOUSE_X = arg0.getInput().getMouseX()+(-ScreenGame.X_TRANSLATE_GRAPHICS);
		parent.MOUSE_Y = arg0.getInput().getMouseY();
		
		if(arg0.getInput().isMousePressed(0))
			parent.MOUSE_BUTTON_0 = true;
		else
			parent.MOUSE_BUTTON_0 = false;
		

		if(arg0.getInput().isMousePressed(1))
			parent.MOUSE_BUTTON_1 = true;
		else
			parent.MOUSE_BUTTON_1 = false;
	}
	
	protected void renderDialog(GameContainer arg0, Graphics arg2)
	{

		if(dialog.size() > 0)
		{
			for(GuiDialog d : dialog)
			{
				if(d.isEnabled)
				{
					d.render(arg0,parent, arg2);
					d.drawButton(arg2, arg0, parent);
				}
				
			}
		}
	}

	public int getID() 
	{
		return 0;
	}
	
	public void preLoad(GameContainer gc, BlackStone cc)
	{
		
	}
	public void update(GameContainer gc, int delta, BlackStone cc)
	{
		
	}
	public void render(GameContainer gc, Graphics g, BlackStone cc){
		
	}

}
