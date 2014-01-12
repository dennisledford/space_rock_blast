package entities;

import static util.JGameConstants.*;

import org.lwjgl.util.vector.Vector2f;

public abstract class AbstractMoveableEntity extends AbstractEntity implements
		MoveableEntity {

	protected Vector2f velocity = new Vector2f();
	protected double speed = 0;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public AbstractMoveableEntity(double x, double y, double width,
			double height) {
		super(x, y, width, height);

	}

	@Override
	public void update(int delta, double rotate) {
		checkBounds();
		this.x += Math.cos(Math.toRadians(rotate)) * delta * speed;
		this.y += Math.sin(Math.toRadians(rotate)) * delta * speed;
	}

	protected void checkBounds() {
		if (this.x > ((DISPLAYWIDTH) + (PLAYERWIDTH / 2))) {
			this.x = (-(PLAYERWIDTH / 2));
		} else if (this.x < (-(PLAYERWIDTH / 2))) {
			this.x = ((DISPLAYWIDTH) + (PLAYERWIDTH / 2));
		}
		if (this.y > ((DISPLAYHEIGHT) + (PLAYERHEIGHT / 2))) {
			this.y = (-(PLAYERHEIGHT / 2));
		} else if (this.y < (-(PLAYERHEIGHT / 2))) {
			this.y = ((DISPLAYHEIGHT) + (PLAYERHEIGHT / 2));
		}
	}

}
