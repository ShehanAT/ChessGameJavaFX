package pieces;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import board.ChessBoard;
public abstract class Piece extends Group{
	private int type; // black or white piece 
	public int xPos; // x coordinate of piece 
	public int yPos; // y coordinate of piece
	public String name; //name of piece
	protected ImageView imageView = new ImageView(); //image of the piece 
	protected Translate pos;
	
	protected boolean firstTimeMove; //to check if the piece has been used before 
	protected boolean saviorPiece; //to check if piece can be moved when in check situation
	
	public Piece(int type, int xPos, int yPos) {
		this.type = type; 
		this.xPos = xPos;
		this.yPos = yPos;
		this.firstTimeMove = firstTimeMove;
	}
	
	public void resize(double width, double height) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
	}
	
	public void relocate(double x, double y) {
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		centerImage();
	}
	
	public void centerImage() {
		Image img = imageView.getImage();
		if(img != null) {
			double w = 0;
			double h = 0;
			
			double ratioX = imageView.getFitWidth() / img.getWidth();
			double ratioY = imageView.getFitHeight() / img.getHeight();
			
			double reduceCoeff = 0;
			if(ratioX <= ratioY) {
				reduceCoeff = ratioY;
			}else {
				reduceCoeff = ratioX;
			}
			
			w = img.getWidth() * reduceCoeff;
			h = img.getHeight() * reduceCoeff;
			
			imageView.setX((imageView.getFitWidth() - w) / 2);
			imageView.setY((imageView.getFitHeight() - h) / 2);
			
		}	
	}
	
	//abstract method for all piece types
	public void SelectPiece(ChessBoard chessBoard) {
		
	}
	
	
	public ImageView getImage() {
		return imageView;
		
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
