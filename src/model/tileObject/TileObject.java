package model.tileObject;

import java.util.ArrayList;
import java.util.List;

import logic.IBlockable;
import logic.TileManager;
import model.Entity;
import model.RenderableHolder;
import model.Tile;

/* Tile Object is an object that is fix to a tile (object which can't be moved) */
public abstract class TileObject extends Entity implements IBlockable {

	protected List<Tile> tile; // can take multiple tile
	public int sizeX = 1;
	public int sizeY = 1;

	public TileObject(Tile tile, int sizeX, int sizeY, int hp) {
		super(tile.getX(), tile.getY(), TileManager.tileSize * sizeX, TileManager.tileSize * sizeY, hp);
		this.tile = new ArrayList<Tile>();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.place(tile);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// remove link from tile
		for (Tile t : tile) {
			t.setTileObject(null);
		}
	}

	@Override
	public int getZ() {
		return 10;
	}
	
	@Override
	public void undoMove() {
		// tile object aren't supposed to move anyway
	}

	public void place(Tile tile) {
		for (int dx = 0; dx < this.sizeX; dx++) {
			for (int dy = 0; dy < this.sizeY; dy++) {
				int x = tile.getTileX() + dx;
				int y = tile.getTileY() + dy;
				TileManager.instance.tileArray[x][y].setTileObject(this);
				this.tile.add(TileManager.instance.tileArray[x][y]);
			}
		}
		RenderableHolder.getInstance().add(this);
	}
}
