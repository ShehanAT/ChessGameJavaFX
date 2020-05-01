package board;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Translate;

public class Tile {
	private Translate pos;
	private Rectangle tile;
	private boolean isHighlight = false;
	public Tile(int i) {
		pos = new Translate();
		tile = new Rectangle();
		if(i == 0) {
			tile.setFill(Color.BLACK);
		}
		else if(i == 1) {
			tile.setFill(Color.WHITE);
		}
		
	
	}
}
