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
 * @author phesto
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

	public void enlistEnemy() {
		if (allowEnlist()) {
			this.lastEnemyCreateTime = (int) enemyTimeHelper.getTime();
			Enemy enemy = new Enemy(0, 0, 20, 20, 0);
			enemy.setId(enemies.size() + 1);
			enemy = setRecruitPosition(enemy);
			enemies.add(enemy);
		}

	}

	private Enemy setRecruitPosition(Enemy enemy) {
		Random randomGenerator = new Random();
		enemy.setX(randomGenerator.nextFloat() * JGameConstants.DISPLAYWIDTH);
		enemy.setY(randomGenerator.nextFloat() * JGameConstants.DISPLAYHEIGHT);
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
			enemy.update(delta, 0);
			enemy.draw();
		}
	}

	private void buryEnemy(Enemy enemy) {
		enemies.remove(enemy);
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
