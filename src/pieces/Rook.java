package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.ChessBoard;

public class Rook extends Piece{
	private Image image;
	public Rook(int type, int x, int y) {
		super(type, x, y); //passing the piece color and coordinates to Piece super class
		name = "Rook";
		if(type == 1) { // if type = 1, this is a white rook
			image = new Image("file:src/resources/White_Rook.png");
		}
		else if(type == 2) { // if type = 2, this is a black rook 
			image = new Image("file:src/resources/Black_Rook.png");
		}
		imageView.setImage(image); // imageView inherited from Piece
		imageView.fitWidthProperty();
		imageView.fitHeightProperty();
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setCache(true);
	}
	
	@Override 
	public ImageView getImage() {
		return imageView;
	}
	
	@Override 
	public void SelectPiece(ChessBoard chessBoard) {
		chessBoard.colorSquare(this.xPos, this.yPos, true);
		if(chessBoard.check) {
			return ;
		}
	}

}
