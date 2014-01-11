package space;

import static util.JGameConstants.DISPLAYHEIGHT;
import static util.JGameConstants.DISPLAYWIDTH;
import static util.JGameConstants.PLAYERHEIGHT;
import static util.JGameConstants.PLAYERWIDTH;

import java.util.Random;

import entities.AbstractMoveableEntity;
import org.lwjgl.opengl.GL11;

public class Enemy extends AbstractMoveableEntity{
	
	protected double rotate = 0;
	protected float colorRed,colorBlue,colorGreen;
	protected boolean dead = true;
	
	public double getRotate() {
		return rotate;
	}

	public void setRotate(double rotate) {
		this.rotate = rotate;
	}

	public void randomizeColors(){
		Random randomGenerator = new Random();
		colorRed = randomGenerator.nextFloat();
		colorBlue = randomGenerator.nextFloat();
		colorGreen = randomGenerator.nextFloat();
	}
	
	public Enemy(double x, double y, double width, double height, double rotate) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.rotate = rotate;
	}
	
	public boolean onScreen(){				
		if(this.x>(DISPLAYWIDTH+2)){
			return false;
		}else if(this.x<-2){
			return false;
		}
		if(this.y>(DISPLAYHEIGHT+2)){
			return false;
		}else if(this.y<-2){
			return false;
		}
		return true;
	}
	
	public void destory(){
		this.width=0;
		this.height=0;
		this.dead = true;
	}
	
	
	@Override
	public void draw() {
		GL11.glLoadIdentity();
		GL11.glColor3f(colorRed,colorGreen,colorBlue);
		GL11.glPushMatrix();
	    GL11.glTranslated(this.x,this.y, 0);
	    GL11.glRotated(rotate, 0, 0, 1);
		GL11.glBegin(GL11.GL_QUADS);
		{
		
			GL11.glVertex3d(-(this.width/2),-(this.height/2),0);	
			GL11.glVertex3d((this.width/2),-(this.height/2),0);
			GL11.glVertex3d((this.width/2),(this.height/2),0);
			GL11.glVertex3d(-(this.width/2),(this.height/2),0);		
			//GL11.glRectd(this.x, this.y, this.x+this.width, this.y+this.height);
		}
		GL11.glEnd();	
		//GL11.glTranslated(-DISPLAYWIDTH,-DISPLAYHEIGHT, 0);
		GL11.glPopMatrix();
				
	}

		
}
