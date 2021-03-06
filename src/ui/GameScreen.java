package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.BuyManager;
import logic.IRenderable;
import logic.InputUtility;
import logic.TileManager;
import model.Entity;
import model.RenderableHolder;
import model.Tile;
import model.tileObject.tower.Tower;

public class GameScreen extends Canvas {
	public static int screen_width, screen_height;
	public static double scale;
	public GameScreen(int width,int height){
		super(width,height);
		screen_width = width;
		screen_height = height;
	}
	
	public void paintComponents(){
		GraphicsContext gc = this.getGraphicsContext2D();
		drawBackground(gc);
		drawEntities(gc);
		if(BuyManager.instance.buyMode){
			drawOverlay(gc);
			drawBuyingItem(gc);
		}
	}
	public void drawBuyingItem(GraphicsContext gc){
		int x = (int) (InputUtility.instance.getMouseX() / TileManager.tileSize);
		int y = (int) (InputUtility.instance.getMouseY() / TileManager.tileSize);
		int sizeX;
		int sizeY;
		try {
			sizeX = BuyManager.instance.currentObjectClass.getDeclaredField("sizeX").getInt(null);
			sizeY = BuyManager.instance.currentObjectClass.getDeclaredField("sizeY").getInt(null);
			if(Tower.class.isAssignableFrom(BuyManager.instance.currentObjectClass)) {
				gc.setGlobalAlpha(0.3);
				gc.setFill(Color.BLACK);
				double r = BuyManager.instance.currentObjectClass.getDeclaredField("shootingRange").getDouble(null);
				gc.fillOval((x+(double)(sizeX)/2)*TileManager.tileSize - r, (y+(double)(sizeY)/2)*TileManager.tileSize - r, 2*r, 2*r);
				gc.setGlobalAlpha(1);
			}
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return;
		}
		boolean canPlace = BuyManager.instance.canBuy();
		if(!canPlace) {
			gc.setGlobalAlpha(0.2);
		}	
		gc.drawImage(BuyManager.instance.currentObjectImage,x*TileManager.tileSize,y*TileManager.tileSize,sizeX*TileManager.tileSize,sizeY*TileManager.tileSize);
		gc.setGlobalAlpha(1.0);
	}
	public void drawOverlay(GraphicsContext gc){
		gc.setGlobalAlpha(0.5);
		for(Tile tile : TileManager.instance.tileList) {
			if(tile.tileObject == null) {
				gc.setFill(Color.GREEN);
				gc.fillRect(tile.getX(), tile.getY(), TileManager.tileSize, TileManager.tileSize);
			}
			else {
				gc.setFill(Color.DARKRED);
				gc.fillRect(tile.getX(), tile.getY(), TileManager.tileSize, TileManager.tileSize);
			}
		}
		gc.setGlobalAlpha(1);
	}
	public void drawBackground(GraphicsContext gc){
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0, screen_width,screen_height);
	}
	public void drawEntities(GraphicsContext gc){
		for(IRenderable o : RenderableHolder.getInstance().getEntities()) {
			o.draw(gc);
		}
		// draw healthbar
		for(IRenderable ir : RenderableHolder.getInstance().getEntities()) {
			if(ir instanceof Entity) {
				((Entity) ir).drawHealthBar(gc);
			}
		}
			
	}
}
