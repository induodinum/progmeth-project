package model.projectile;

import javafx.scene.canvas.GraphicsContext;
import model.Entity;
import model.RenderableHolder;

public class ProjectileArrow extends Projectile {
	private static final double defaultWidth = 20;
	private static final double defaultHeight = 8;
	private static final double defaultSpeed = 8;
	private static final int defaultDamage = 5;
	
	public ProjectileArrow(double x, double y, double targetX, double targetY) {
		super(x, y, defaultWidth, defaultHeight, defaultSpeed, defaultDamage, targetX, targetY);
	}

	public ProjectileArrow(double x, double y, Entity target) {
		super(x, y, defaultWidth, defaultHeight, defaultSpeed, defaultDamage, target);
	}

	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc, RenderableHolder.projectile_arrow_img);
	}

}
