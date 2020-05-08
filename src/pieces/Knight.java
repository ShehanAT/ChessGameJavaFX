package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.ChessBoard;

public class Knight extends Piece{
	private Image image;
	public Knight(int type, int x, int y) {
		super(type, x, y);
		name = "Knight";
		if(type == 1) {
			image = new Image("file:src/resources/White_Knight.png");
		}else if(type == 2) {
			image = new Image("file:src/resources/Black_Knight.png");
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
		int x = 0;
		chessBoard.colorSquare(this.xPos, this.yPos, true);
		if(chessBoard.check && !this.saviorPiece) {
			return ;
		}
		if(gameLogic.verticalProtection(chessBoard, this.xPos, this.yPos, this.type) || gameLogic.horizontalProtection(chessBoard, this.xPos, this.yPos, this.type) || 
			gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type) || gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)){
				return ;
		}
		for(int y = -2; y <= 2; y++) {
			if(y != 0) {
				x = y % 2 == 0 ? 1 : 2;
				if(this.yPos + y >= 0 && this.yPos + y < chessBoard.getBoardHeight() && this.xPos - x >= 0 && this.xPos - x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(this.xPos - x, this.yPos + y) != this.type) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos - x, this.yPos + y, this.type)) {
							chessBoard.colorSquare(this.xPos - x, this.yPos + y, false);
						}
					}
					else 
						chessBoard.colorSquare(this.xPos - x, this.yPos + y, false);
						
					}
				if(this.yPos + y >= 0 && this.yPos + y < chessBoard.getBoardHeight() && this.xPos + x >= 0 && this.xPos + x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(this.xPos + x,  this.yPos + y) != this.type) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos + x, this.yPos + y, this.type))
							chessBoard.colorSquare(this.xPos + x, this.yPos + y, false);
					}
					else 
						chessBoard.colorSquare(this.xPos + x, this.yPos + y, false);
				}	
			}	
		}
	}
	
	

}
