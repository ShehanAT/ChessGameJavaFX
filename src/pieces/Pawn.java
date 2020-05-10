package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import board.ChessBoard;

public class Pawn extends Piece{
	private Image image;
	public Pawn(int type, int x, int y) {
		super(type, x, y);
		name = "Pawn";
		if(type == 1) {
			image = new Image("file:src/resources/White_Pawn.png");
		}else if(type == 2) {
			image = new Image("file:src/resources/Black_Pawn.png");
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
		if(chessBoard.check && !this.saviorPiece) {
			return ;
		}
		if(gameLogic.horizontalProtection(chessBoard, this.xPos, this.yPos, this.type))
			return ;
		if(this.type == 1) {
			if(!gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
				if(this.yPos - 1 >= 0 && chessBoard.getBoardPosition(this.xPos, this.yPos - 1) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, this.yPos - 1, this.type))
							chessBoard.colorSquare(this.xPos, this.yPos - 1, false);
					}else {
						chessBoard.colorSquare(this.xPos, this.yPos - 1, false);
					}
				}
				if(this.firstTimeMove && chessBoard.getBoardPosition(this.xPos, this.yPos - 2) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, this.yPos - 2, this.type))
							chessBoard.colorSquare(this.xPos, this.yPos - 2, false);
					}else {
						chessBoard.colorSquare(this.xPos, this.yPos - 2, false);
					}
				}
				if(!gameLogic.verticalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
					if(!gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
						if(this.yPos - 1 >= 0 && this.xPos - 1 >= 0 && chessBoard.getBoardPosition(this.xPos - 1, this.yPos - 1) != this.type && chessBoard.getBoardPosition(this.xPos - 1, this.yPos - 1) != 0) {
							if(chessBoard.check) {
								if(gameLogic.isThisProtecting(chessBoard, this.xPos - 1, this.yPos - 1, this.type))
									chessBoard.colorSquare(this.xPos - 1, this.yPos - 1, false);
							}else {
								chessBoard.colorSquare(this.xPos - 1, this.yPos - 1, false);
							}
						}
					}
					if(!gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
						if(this.yPos - 1 >= 0 && this.xPos + 1 < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(this.xPos + 1, this.yPos - 1) != this.type && chessBoard.getBoardPosition(this.xPos + 1, this.yPos - 1) != 0) {
							if(chessBoard.check) {
								if(gameLogic.isThisProtecting(chessBoard, this.xPos + 1, this.yPos - 1, this.type))
									chessBoard.colorSquare(this.xPos + 1, this.yPos - 1, false);
							}else {
								chessBoard.colorSquare(this.xPos + 1, this.yPos - 1, false);
							}
						}
					}
				}
				
			}
		}
		else if(this.type == 2){
			if(!gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
				if(this.yPos + 1 < chessBoard.getBoardHeight() && chessBoard.getBoardPosition(this.xPos, this.yPos + 1) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, this.yPos + 1, this.type))
							chessBoard.colorSquare(this.xPos, this.yPos + 1, false);
					}else {
						chessBoard.colorSquare(this.xPos, this.yPos + 1, false);
					}
				}
				if(this.firstTimeMove && chessBoard.getBoardPosition(this.xPos, this.yPos + 2) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, this.yPos + 2, this.type))
							chessBoard.colorSquare(this.xPos, this.yPos + 2, false);
					}else {
						chessBoard.colorSquare(this.xPos, this.yPos + 2, false);
					}
				}
				if(!gameLogic.verticalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
					if(!gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
						if(this.yPos + 1 < chessBoard.getBoardHeight() && this.xPos - 1 >= 0 && chessBoard.getBoardPosition(this.xPos - 1, this.yPos + 1) != this.type && chessBoard.getBoardPosition(this.xPos - 1, this.yPos + 1) != 0) {
							if(chessBoard.check) {
								if(gameLogic.isThisProtecting(chessBoard, this.xPos - 1, this.yPos + 1, this.type))
									chessBoard.colorSquare(this.xPos - 1, this.yPos + 1, false);
							}else {
								chessBoard.colorSquare(this.xPos - 1, this.yPos + 1, false);
							}
						}
					}
					if(!gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
						if(this.yPos + 1 < chessBoard.getBoardHeight() && this.xPos + 1 < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(this.xPos + 1, this.yPos + 1) != this.type && chessBoard.getBoardPosition(this.xPos + 1, this.yPos + 1) != 0) {
							if(chessBoard.check) {
								if(gameLogic.isThisProtecting(chessBoard, this.xPos + 1, this.yPos + 1, this.type))
									chessBoard.colorSquare(this.xPos + 1, this.yPos + 1, false);
							}else {
								chessBoard.colorSquare(this.xPos + 1, this.yPos + 1, false);
							}
						}
					}
				}
				
			}
		}
	}
	
	

}
