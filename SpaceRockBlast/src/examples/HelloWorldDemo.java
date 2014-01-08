package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class HelloWorldDemo {

	
	private static final int DISPLAYWIDTH = 640;
	private static final int DISPLAYHEIGHT = 480;
	private static final int FPS = 60;
	
	public HelloWorldDemo(){
		
		DisplayMode displayMode = new DisplayMode(DISPLAYWIDTH,DISPLAYHEIGHT);
		try {
			Display.setDisplayMode(displayMode);
			Display.setTitle("Hello World!");
			Display.create();
			
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//OpenGL init stuff
		
		while(!Display.isCloseRequested()){
			Display.update();
			Display.sync(FPS);
		}
		Display.destroy();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HelloWorldDemo();
		
	}

}
