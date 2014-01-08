package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class InputDemo {

	
	private static final int DISPLAYWIDTH = 640;
	private static final int DISPLAYHEIGHT = 480;
	private static final int FPS = 60;
	private static final int STARTX = DISPLAYWIDTH/2;
	private static final int STARTY = DISPLAYHEIGHT/2;
	private static final int PLAYERHEIGHT = 36;
	private static final int PLAYERWIDTH = 20;
	
	public InputDemo(){
		
		DisplayMode displayMode = new DisplayMode(DISPLAYWIDTH,DISPLAYHEIGHT);
		try {
			Display.setDisplayMode(displayMode);
			Display.setTitle("Input Demo");
			Display.create();
			
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		float playerX = STARTX;
		float playerY = STARTY;
		//OpenGL init stuff
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,DISPLAYWIDTH,DISPLAYHEIGHT,0,1,-1);
		glMatrixMode(GL_MODELVIEW);

		
		while(!Display.isCloseRequested()){
			//render
			glClear(GL_COLOR_BUFFER_BIT);	       
	        drawShip(playerX,playerY);	
	        
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
			
			int mouseX = Mouse.getX();
			int mouseY = (DISPLAYHEIGHT - Mouse.getY())-1;
			//System.out.println("mouse x = "+ mouseX + " - mouse y = " + mouseY);
			int dX = Mouse.getDX();
			int dY = -(Mouse.getDY());
			//System.out.println("mouse dx = "+ dX + " - mouse dy = " + dY);
			
			Display.update();
			Display.sync(FPS);
		}
		Display.destroy();
	}
	
	private static void drawShip(float x,float y)
	{
		glBegin(GL_TRIANGLES);
			glVertex2f(x,y-(PLAYERHEIGHT/2));
			glVertex2f(x+(PLAYERWIDTH/2),y+(PLAYERHEIGHT/2));
			glVertex2f(x-(PLAYERWIDTH/2),y+(PLAYERHEIGHT/2));
		glEnd();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InputDemo();
		
	}

}
