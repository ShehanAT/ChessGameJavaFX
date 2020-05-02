package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.ChessBoard;

public class King extends Piece{
	private Image image;
	public King(int type, int x, int y) {
		super(type, x, y);
		name = "King";
		if(type == 1) {
			image = new Image("file:src/resources/White_King.png");
		}else if(type == 2) {
			image = new Image("file:src/resources/Black_King.png");
		}
		imageView.setImage(image);
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
		chessBoard.colorSquare(xPos, yPos, true);
		if(chessBoard.check) {
			return ;
		}
	}
	
	

}
