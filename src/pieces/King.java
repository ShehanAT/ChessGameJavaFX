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
		int x = this.xPos;
		int y = this.yPos;
		chessBoard.colorSquare(this.xPos, this.yPos, true);
		for(y = this.yPos - 1; y <= this.yPos + 1; y++) {
			for(x = this.xPos - 1; x <= this.xPos + 1; x++) {
				if(y >= 0 && y < chessBoard.getBoardHeight() && x >= 0 && x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(x, y) != this.type) {
					if(!chessBoard.check)
						this.canCastle(chessBoard);
					if(!gameLogic.isCheck(chessBoard, x, y, this.type, true))
						chessBoard.colorSquare(x, y, false);
				}
			}
		}
		
	}
	
	public int canCastle(ChessBoard chessBoard) {
		int canCastle = 0;
		//for player black 
		// castling short 
		if(type == 2 && this.firstTimeMove && chessBoard.getBoardPosition(5, 0) == 0 && chessBoard.getBoardPosition(6, 0) == 0 && chessBoard.getPiece(7, 0) != null && chessBoard.getPiece(7, 0).firstTimeMove) {
			canCastle = 1;
			chessBoard.colorSquare(7, 0, false);
		}
		
		// castling long  
		if(type == 2 && this.firstTimeMove && chessBoard.getBoardPosition(1, 0) == 0 && chessBoard.getBoardPosition(2, 0) == 0 && chessBoard.getBoardPosition(3, 0) == 0 && chessBoard.getPiece(0, 0) != null && chessBoard.getPiece(0, 0).firstTimeMove) {
			canCastle = 2;
			chessBoard.colorSquare(0, 0, false);
		}
		
		//for player white 
		// castling short
		if(type == 1 && this.firstTimeMove && chessBoard.getBoardPosition(5, 7) == 0 && chessBoard.getBoardPosition(6, 7) == 0 && chessBoard.getPiece(7, 7) != null && chessBoard.getPiece(7, 7).firstTimeMove) {
			canCastle = 3;
			chessBoard.colorSquare(7, 7, false);
		}
		
		// castling long 
		if(type == 1 && this.firstTimeMove && chessBoard.getBoardPosition(1, 7) == 0 && chessBoard.getBoardPosition(2, 7) == 0 && chessBoard.getBoardPosition(3, 7) == 0 && chessBoard.getPiece(0, 7) != null && chessBoard.getPiece(0, 7).firstTimeMove) {
			canCastle = 4;
			chessBoard.colorSquare(0, 7, false);
		}
		return canCastle;
		
		
	}
	
	

}
