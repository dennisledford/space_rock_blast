package entities;

import org.lwjgl.util.vector.Vector2f;

public interface MoveableEntity extends Entity {
	
	public double getSpeed();
		
	public void setSpeed(double speed);
	
	public Vector2f getVelocity();
	
	public void setVelocity(Vector2f velocity);

}
