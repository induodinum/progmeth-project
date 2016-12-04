package model;

import logic.CollisionManager;
import logic.IBlockable;
import logic.ICollidable;

public abstract class BlockingEntity extends MovingEntity implements IBlockable {
	protected double lastX, lastY;

	public BlockingEntity(double x, double y, double width, double height, double speed, int hp) {
		super(x, y, width, height, speed, hp);
	}

	public void move() {
		// move with blocking behavior
		lastX = x;
		lastY = y;
		x += velX * speed;
		if (CollisionManager.isBlocked(this)) {
			x = lastX;
		}
		y += velY * speed;
		if (CollisionManager.isBlocked(this))
			y = lastY;
	}

}
