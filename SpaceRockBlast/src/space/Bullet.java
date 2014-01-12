package space;

import static util.JGameConstants.DISPLAYHEIGHT;
import static util.JGameConstants.DISPLAYWIDTH;
import static util.JGameConstants.PLAYERHEIGHT;
import static util.JGameConstants.PLAYERWIDTH;
import entities.AbstractMoveableEntity;
import org.lwjgl.opengl.GL11;

public class Bullet extends AbstractMoveableEntity {

	protected double rotate = 0;

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRotate() {
		return rotate;
	}

	public void setRotate(double rotate) {
		this.rotate = rotate;
	}

	public Bullet(double x, double y, double width, double height,
			double rotate, int id) {
		super(x, y, width, height);
		this.id = id + 1;
		this.speed = .5f;
		// TODO Auto-generated constructor stub
		this.rotate = rotate;
	}

	public boolean onScreen() {
		if (this.x > (DISPLAYWIDTH + 2)) {
			return false;
		} else if (this.x < -2) {
			return false;
		}
		if (this.y > (DISPLAYHEIGHT + 2)) {
			return false;
		} else if (this.y < -2) {
			return false;
		}
		return true;
	}

	@Override
	public void draw() {
		GL11.glLoadIdentity();
		GL11.glColor3f(1, 1, 1);
		GL11.glPushMatrix();
		GL11.glTranslated(this.x, this.y, 0);
		GL11.glRotated(rotate, 0, 0, 1);
		GL11.glBegin(GL11.GL_QUADS);
		{

			GL11.glVertex3d(-(this.width / 2), -(this.height / 2), 0);
			GL11.glVertex3d((this.width / 2), -(this.height / 2), 0);
			GL11.glVertex3d((this.width / 2), (this.height / 2), 0);
			GL11.glVertex3d(-(this.width / 2), (this.height / 2), 0);
			// GL11.glRectd(this.x, this.y, this.x+this.width,
			// this.y+this.height);
		}
		GL11.glEnd();
		// GL11.glTranslated(-DISPLAYWIDTH,-DISPLAYHEIGHT, 0);
		GL11.glPopMatrix();

	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Bullet))
			return false;
		Bullet other = (Bullet) o;
		return (this.x == other.getX() && this.y == other.getY() && this.id == other
				.getId());
	}

}
