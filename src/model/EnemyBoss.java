package model;

import javafx.scene.canvas.GraphicsContext;

public class EnemyBoss extends Enemy {

	public EnemyBoss(double x, double y,int level) {
		super(x, y, 3, 100*level, 4*level, 20*level);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc, RenderableHolder.enemy_boss_img);
	}
}
