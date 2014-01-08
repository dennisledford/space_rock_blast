package jgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import entities.AbstractMoveableEntity;
import entities.Entity;
import entities.MoveableEntity;

import util.State;
import static util.JGameConstants.*;

public class JGame {	
	
	private State state = State.INTRO;
	private DisplayMode displayMode = new DisplayMode(DISPLAYWIDTH,DISPLAYHEIGHT);
	private long lastFrame;
	Player player = null;
	Enemy enemy =null;
	List<Bullet> bullets = null;	
	
	public JGame(){		
		
		try {
			Display.setDisplayMode(displayMode);
			Display.setTitle("MY GAME!");
			Display.create();
			
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//init stuff
		init();
		
		//OpenGL init stuff
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0,DISPLAYWIDTH,DISPLAYHEIGHT,0,1,-1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);		
		
		lastFrame = getTime();
	
		while(!Display.isCloseRequested()){
			//render			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
			checkInput();
			Display.update();
			Display.sync(FPS);
		}
		Display.destroy();
	}
	
	private long getTime(){
		return ( (Sys.getTime()*1000) / Sys.getTimerResolution());
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}
	
	private void render(){
		switch(state){
			case INTRO:
			{
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
				GL11.glRectf(0, 0, DISPLAYWIDTH, DISPLAYHEIGHT);
				break;
			}
			case MAIN_MENU:
			{
				GL11.glColor3f(0.0f, 0.0f, 1.0f);
				GL11.glRectf(0, 0, DISPLAYWIDTH, DISPLAYHEIGHT);
				break;
			}
			case GAME:
			{
				int delta = getDelta();				
				player.randomizeColors();
        		player.update(delta,player.getTravelRotate());
				player.draw();			
				for(Bullet bullet: bullets){						
						if(bullet.onScreen()){
							bullet.update(delta,bullet.getRotate());
							bullet.draw();		
							if(bullet.intersects(enemy)){
								System.out.println("HIT");
								enemy.destory();
								bullet.setHeight(0);
								bullet.setWidth(0);
							}
						}
						
				}
				enemy.update(delta, 0);
				enemy.draw();
				break;
			}
			default:
			{
				break;
			}
		}
	}
	
	
	private void checkInput(){
		
		switch(state){
			case INTRO:
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
					state = State.MAIN_MENU;
				}
				break;
			}
			case MAIN_MENU:
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
					state = State.GAME;
					break;
				}
				while(Keyboard.next()){
					if(Keyboard.getEventKey()==Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()){
						Display.destroy();
						System.exit(0);
						break;
					}
				}
				
			}
			case GAME:
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
					state = State.MAIN_MENU;
				}else{
					while(Keyboard.next()){
			        	if(Keyboard.getEventKey()==Keyboard.KEY_UP && Keyboard.getEventKeyState()){	        		
			        		//player.setAcceleration(player.getAcceleration()+.1);
			        		player.setSpeed(player.getSpeed()+1.02);
			        		player.setTravelRotate(player.getShipRotate());			        		
			        	}
			        	if(Keyboard.getEventKey()==Keyboard.KEY_DOWN && Keyboard.getEventKeyState()){
			        		player.setAcceleration(player.getAcceleration()-.083);
			        		player.setSpeed(player.getSpeed()-.83);
			        		
			        	}
			        	if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
			        				        		
			        		player.rotateShipClockwise();		        		
			        	
			        	}
			        	if(Keyboard.getEventKey()==Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
			        		
			        		player.rotateShipCounterClock();
			        	
			        	}
			        	if(Keyboard.getEventKey()==Keyboard.KEY_SPACE && Keyboard.getEventKeyState()){
			        		Bullet bullet = new Bullet(player.getX(),player.getY(),4,4, player.getShipRotate());
			        		bullet.setSpeed(10);
			        		bullets.add(bullet);
			        	}
			        }
				}
				break;
			}
			default:
			{
				break;
			}
		}
        
	}
	
	private void init(){
		player = new Player(STARTX, STARTY,PLAYERWIDTH,PLAYERHEIGHT);
		bullets = new ArrayList<Bullet>();		
		enemy = new Enemy(500,400,20,20,0);
		enemy.randomizeColors();
				
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JGame();
		
	}

}
