package model;

import javafx.scene.canvas.GraphicsContext;

public class TileObjectTree extends TileObject {

	public TileObjectTree(Tile tile) {
		super(tile.getX(), tile.getY(), 100, tile);
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.tileObject_tree_img, x, y);
	}

}
