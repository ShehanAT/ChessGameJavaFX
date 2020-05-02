package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.ChessBoard;

public class Bishop extends Piece{
	private Image image;
	public Bishop(int type, int x, int y) {
		super(type, x, y);
		name = "Bishop";
		if(type == 1) {
			image = new Image("file:src/resources/White_Bishop.png");
		}else if(type == 2) {
			image = new Image("file:src/resources/Black_Bishop.png");
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
