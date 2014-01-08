package jgame;

import static util.JGameConstants.*;

import java.util.Random;
import org.lwjgl.opengl.GL11;

import entities.AbstractMoveableEntity;

public class Player extends AbstractMoveableEntity{

	protected double travelRotate=0;
	protected double shipRotate=0;
	protected double acceleration=0.0000001;
	protected double xForce, yForce;
	
	
	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getShipRotate() {
		return shipRotate;
	}

	public void setShipRotate(double shipRotate) {
		this.shipRotate = shipRotate;
	}

	protected float colorRed,colorBlue,colorGreen;

	public Player(double x, double y, double width, double height) {
		super(x, y, width, height);	
		randomizeColors();		
	}
	
	
	public void randomizeColors(){
		Random randomGenerator = new Random();
		colorRed = randomGenerator.nextFloat();
		colorBlue = randomGenerator.nextFloat();
		colorGreen = randomGenerator.nextFloat();
	}
	
	public boolean isOnScreen(){		
		if(((x+(PLAYERWIDTH/2))>DISPLAYWIDTH) || (x-(PLAYERWIDTH/2)<0)){
			return false;
		}
		if((y+(PLAYERHEIGHT/2))>DISPLAYHEIGHT || ((y-(PLAYERHEIGHT/2))<0)){
			return false;
		}
		return true;
	}
	
	public void rotateShipClockwise(){
		
		shipRotate += 10;
	}
	
	public double getTravelRotate() {
		return travelRotate;
	}

	public void setTravelRotate(double travelRotate) {
		this.travelRotate = travelRotate;
	}
	
	public void rotateShipCounterClock(){
		shipRotate -= 10;
	}

	@Override
	public void draw() {
		GL11.glLoadIdentity();
		GL11.glColor3f(colorRed,colorGreen,colorBlue);
		GL11.glPushMatrix();
		//System.out.println(this.x +","+ this.y);
	    GL11.glTranslated(this.x,this.y, 0);
	    GL11.glRotated(shipRotate+90, 0, 0, 1);	  	
	  	GL11.glBegin(GL11.GL_QUADS);
		{
			//System.out.println(this.x +","+ this.y);
			GL11.glVertex3d(-(PLAYERWIDTH/2),(PLAYERHEIGHT/2),0);			
			GL11.glVertex3d(0,-(PLAYERHEIGHT/2),0);
			GL11.glVertex3d((PLAYERWIDTH/2),(PLAYERHEIGHT/2),0);
			GL11.glVertex3d(0,(PLAYERHEIGHT/4),0);			
		}		
		GL11.glEnd();
		//GL11.glTranslated(-this.x,-this.y, 0);
		GL11.glPopMatrix();		
	}
	
}
