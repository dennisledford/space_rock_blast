/**
 * 
 */
package space;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.JGameConstants;
import util.TimeHelper;

/**
 * @author dennisledford
 * 
 */
public class EnemyArmy {

	protected List<Enemy> enemies;

	protected List<Enemy> deadEnemies;

	protected int lastEnemyCreateTime;

	private TimeHelper enemyTimeHelper;

	public EnemyArmy() {
		enemies = new ArrayList<Enemy>();
		deadEnemies = new ArrayList<Enemy>();
		enemyTimeHelper = new TimeHelper();
	}

	/**
	 * @return the enemies
	 */
	public List<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * @param enemies
	 *            the enemies to set
	 */
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	//TODO:rework enlisting enemy
	public void enlistEnemy() {
		if (allowEnlist()) {
			this.lastEnemyCreateTime = (int) enemyTimeHelper.getTime();
			Random randomGenerator = new Random();
			double rotate = randomGenerator.nextFloat()*360;
			Enemy enemy = new Enemy(0, 0, 30, 30, rotate);
			enemy.setId(enemies.size() + 1);
			enemy = setRecruitPosition(enemy);
			enemies.add(enemy);
		}

	}

	private Enemy setRecruitPosition(Enemy enemy) {
		Random randomGenerator = new Random();
		if(randomGenerator.nextBoolean()){
			enemy.setX(randomGenerator.nextFloat() * (JGameConstants.DISPLAYWIDTH+10));
			enemy.setY(-10);
		}else{
			enemy.setX(-10);
			enemy.setY(randomGenerator.nextFloat() * (JGameConstants.DISPLAYHEIGHT+10));
		}
		
		return enemy;
	}

	private boolean allowEnlist() {
		if ((enemyTimeHelper.getTime() - lastEnemyCreateTime) > 3000) {
			return true;
		}
		return false;
	}

	public void drawEnemies(int delta) {
		for (Enemy enemy : enemies) {
			enemy.update(delta, enemy.getRotate());
			enemy.draw();
		}
	}


	// TODO fix this to be better
	public void buryEnemies() {

		for (Enemy enemy : enemies) {
			if (enemy.isDead()) {
				deadEnemies.add(enemy);
			}
		}
		for (Enemy dead : deadEnemies) {
			try {
				if (enemies.size() > 0) {
					enemies.remove(dead);
				}
			} catch (Exception e) {
				System.out.println("Something went wrong - size = "
						+ enemies.size());
			}
		}
	}

}
