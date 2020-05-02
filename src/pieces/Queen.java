package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.ChessBoard;

public class Queen extends Piece{
	private Image image;
	public Queen(int type, int x, int y) {
		super(type, x, y);
		name = "Queen";
		if(type == 1) {
			image = new Image("file:src/resources/White_Queen.png");
		}
		else if(type == 2) {
			image = new Image("file:src/resources/Black_Queen.png");
		}
		imageView.setImage(image);
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
		chessBoard.colorSquare(xPos, yPos, true);
		int y = yPos + 1;
		if(chessBoard.check) {
			return ;
		}
		
	}
}
