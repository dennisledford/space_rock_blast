package entities;

public interface Entity {

	public void draw();

	public void update(int delta, double rotate);

	public void setLocation(double x, double y);

	public void setX(double x);

	public void setY(double y);

	public void setHeight(double height);

	public void setWidth(double width);

	public double getX();

	public double getY();

	public double getHeight();

	public double getWidth();

	public boolean intersects(Entity e);
}
