package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class SimpleOGLDemo {

	
	private static final int DISPLAYWIDTH = 640;
	private static final int DISPLAYHEIGHT = 480;
	private static final int FPS = 60;
	private static final int STARTX = 0;
	private static final int STARTY = 0;
	private static final int PLAYERHEIGHT = 32;
	private static final int PLAYERWIDTH = 32;
	
	public SimpleOGLDemo(){
		
		DisplayMode displayMode = new DisplayMode(DISPLAYWIDTH,DISPLAYHEIGHT);
		try {
			Display.setDisplayMode(displayMode);
			Display.setTitle("Hello World!");
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
			
			Display.update();
			Display.sync(FPS);
		}
		Display.destroy();
	}
	
	private static void drawShip(float x,float y)
	{
		glBegin(GL_QUADS);
			glVertex2f(x-(PLAYERWIDTH/2),y+(PLAYERHEIGHT/2));	
			glVertex2f(x,y-(PLAYERHEIGHT/2));
			glVertex2f(x+(PLAYERWIDTH/2),y+(PLAYERHEIGHT/2));
			glVertex2f(x,y+(PLAYERHEIGHT/4));			
		glEnd();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SimpleOGLDemo();
		
	}

}
