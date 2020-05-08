package board;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Translate;
import javafx.scene.text.*;
public class Tile extends Group{
	private Translate pos;
	private Rectangle tile;
	private boolean isHighlight = false;
	public Tile(int i) {
		pos = new Translate();
		tile = new Rectangle();
		tile.getTransforms().add(pos);
		if(i == 0) {
			tile.setFill(Color.DIMGREY);
		}
		else if(i == 1) {
			tile.setFill(Color.WHITE);
		}
		getChildren().add(tile);
	}
	
	@Override 
	public void resize(double width, double height) {
		super.resize(width, height);
		tile.setHeight(height);
		tile.setWidth(width);
	}
	
	@Override 
	public void relocate(double x, double y) {
		super.relocate(x, y);
		pos.setX(x);
		pos.setY(y);
	}

	
	public void highlightWindow(Color color) {
		tile.setStrokeType(StrokeType.CENTERED);
		tile.setStrokeWidth(10);
		tile.setStroke(color);
		if(color == Color.GREEN) {
			isHighlight = true;
		}
		
	}
	
	public void unHighlight() {
		tile.setStrokeType(null);
		isHighlight = false;
	}
	
	public boolean checkHighlight() {
		return isHighlight;
	}
	
	public Rectangle getTile() {
		return tile;
	}
}
