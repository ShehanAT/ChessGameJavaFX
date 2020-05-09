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
		int y = this.yPos + 1;
		chessBoard.colorSquare(this.xPos, this.yPos, true);
		if(chessBoard.check && !this.saviorPiece) {
			return ;
		}
		if(gameLogic.horizontalProtection(chessBoard, this.xPos, this.yPos, this.type) || gameLogic.verticalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
			return ;
		}
		if(!gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
			for(int x = this.xPos + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++) {
				if(chessBoard.getBoardPosition(x, y) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if(chessBoard.getBoardPosition(x, y) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
			
			y = this.yPos - 1;
			for(int x = this.xPos - 1; x >= 0 && y >= 0; x--, y--) {
				if(chessBoard.getBoardPosition(x, y) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if(chessBoard.getBoardPosition(x, y) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
		}
		if(!gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
			y = yPos + 1;
			for(int x = this.xPos - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++) {
				if(chessBoard.getBoardPosition(x, y) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if(chessBoard.getBoardPosition(x, y) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
			y = this.yPos - 1;
			for(int x = this.xPos + 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--) {
				if(chessBoard.getBoardPosition(x, y) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if(chessBoard.getBoardPosition(x, y) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
		}
	}
	
	

}
