package space;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import util.State;
import util.TimeHelper;
import static util.JGameConstants.*;

public class JGame {
	// TODO: objects can't exist in same spot - increase collision dectection
	private State state = State.INTRO;
	private DisplayMode displayMode = new DisplayMode(DISPLAYWIDTH,
			DISPLAYHEIGHT);
	Player player = null;
	List<Bullet> bullets = null;
	List<Bullet> cleanUpBullets = null;
	EnemyArmy enemyArmy;
	TimeHelper timeHelper;

	public JGame() {

		try {
			Display.setDisplayMode(displayMode);
			Display.setTitle("MY GAME!");
			Display.create();

		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// init stuff
		init();

		// OpenGL init stuff
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, DISPLAYWIDTH, DISPLAYHEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		timeHelper = new TimeHelper();

		while (!Display.isCloseRequested()) {
			// render
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
			checkInput();
			Display.update();
			Display.sync(FPS);
		}
		Display.destroy();
	}

	private void render() {
		switch (state) {
		case INTRO: {
			GL11.glColor3f(1.0f, 0.0f, 0.0f);
			GL11.glRectf(0, 0, DISPLAYWIDTH, DISPLAYHEIGHT);
			break;
		}
		case MAIN_MENU: {
			GL11.glColor3f(0.0f, 0.0f, 1.0f);
			GL11.glRectf(0, 0, DISPLAYWIDTH, DISPLAYHEIGHT);
			break;
		}
		case GAME: {
			int delta = timeHelper.getDelta();
			player.randomizeColors();
			player.update(delta, player.getTravelRotate());
			player.draw();
			enemyArmy.enlistEnemy();
			for (Bullet bullet : bullets) {
				if (bullet.onScreen()) {
					bullet.update(delta, bullet.getRotate());
					bullet.draw();
					for (Enemy enemy : enemyArmy.getEnemies()) {
						if (bullet.intersects(enemy)) {
							System.out.println("HIT");
							enemy.destory();
							bullet.setHeight(0);
							bullet.setWidth(0);
						}

					}
				} else {
					cleanUpBullets.add(bullet);
				}
			}
			for (Enemy enemy : enemyArmy.getEnemies()) {
				if (!enemy.isDead()) {
					if (player.intersects(enemy)) {
						System.out.println("DEAD");
					}
				}
			}
			cleanUp();
			enemyArmy.drawEnemies(delta);

			break;
		}
		default: {
			break;
		}
		}
	}

	private void checkInput() {

		switch (state) {
		case INTRO: {
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				state = State.MAIN_MENU;
			}
			break;
		}
		case MAIN_MENU: {
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				state = State.GAME;
				break;
			}
			while (Keyboard.next()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE
						&& Keyboard.getEventKeyState()) {
					Display.destroy();
					System.exit(0);
					break;
				}
			}

		}
		case GAME: {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				state = State.MAIN_MENU;
			} else {
				while (Keyboard.next()) {
					if (Keyboard.getEventKey() == Keyboard.KEY_UP
							&& Keyboard.getEventKeyState()) {
						// player.setAcceleration(player.getAcceleration()+.1);
						if(player.getShipRotate()==player.getTravelRotate()){
							player.setSpeed(player.getSpeed() + .102);
						}
						player.setTravelRotate(player.getShipRotate());
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_DOWN
							&& Keyboard.getEventKeyState()) {
						player.setAcceleration(player.getAcceleration() - .083);
						player.setSpeed(player.getSpeed() - .083);

					}
					if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT
							&& Keyboard.getEventKeyState()) {

						player.rotateShipClockwise();

					}
					if (Keyboard.getEventKey() == Keyboard.KEY_LEFT
							&& Keyboard.getEventKeyState()) {

						player.rotateShipCounterClock();

					}
					if (Keyboard.getEventKey() == Keyboard.KEY_SPACE
							&& Keyboard.getEventKeyState()) {
						Bullet bullet = new Bullet(player.getX(),
								player.getY(), 4, 4, player.getShipRotate(),
								bullets.size());
						bullets.add(bullet);
					}
				}
			}
			break;
		}
		default: {
			break;
		}
		}

	}

	private void init() {
		Keyboard.enableRepeatEvents(true);
		player = new Player(STARTX, STARTY, PLAYERWIDTH, PLAYERHEIGHT);
		bullets = new ArrayList<Bullet>();
		cleanUpBullets = new ArrayList<Bullet>();
		enemyArmy = new EnemyArmy();
	}

	// TODO fix this to be better
	private void cleanUp() {
		enemyArmy.buryEnemies();
		for (Bullet bullet : cleanUpBullets) {
			try {
				if (bullets != null && bullets.size() > 0) {
					bullets.remove(bullet);
				}
			} catch (Exception e) {
				System.out.println("What happened here?");
			}
		}
		cleanUpBullets.clear();
	}
}
