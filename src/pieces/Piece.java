package pieces;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;

public abstract class Piece extends Group{
	private int type; // black or white piece 
	private int xPos; // x coordinate of piece 
	private int yPos; // y coordinate of piece
	private String name; //name of piece
	protected ImageView imageView = new ImageView();
	protected Translate pos;
	
	protected boolean firstTimeMove; //to check if the piece has been used before 
	protected boolean saviorPiece; //to check if piece can be moved when in check situation
	
	public Piece(int type, int xPos, int yPos) {
		this.type = type; 
		this.xPos = xPos;
		this.yPos = yPos;
		this.firstTimeMove = firstTimeMove;
	}
	
	public ImageView getImage() {
		return (imageView);
		
	}
	
	public boolean isFirstTimeMove() {
		return firstTimeMove;
	}
	
	public void setFirstTimeMove(boolean firstTime) {
		this.firstTimeMove = firstTime;
	}
	
	public int getX() {
		return this.xPos;
	}
	
	public int getY() {
		return this.yPos;
	}
	

}
