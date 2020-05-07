package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.GameLogic;
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
		
		if(chessBoard.check && !this.saviorPiece) {
			return ;
		}
		if(gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
			return ;
		}
		if(gameLogic.slashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type) || gameLogic.backslashDiagonalProtection(chessBoard, this.xPos, this.yPos, this.type)) {
			return ;
		}
		if(!gameLogic.horizontalProtection(chessBoard, this.xPos, this.yPos, this.type)){
			// for horizontal up
			for(int y = yPos - 1; y >= 0; y--) {
				if(chessBoard.getBoardPosition(this.xPos, y) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, y, this.type)) {
							chessBoard.colorSquare(this.xPos, y, false);
						}
					}else {
						chessBoard.colorSquare(this.xPos, y, false);
					}
				}
				else if(chessBoard.getBoardPosition(this.xPos, y) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, y, this.type)) {
							chessBoard.colorSquare(this.xPos, y, false);
						}
					}
					else
						chessBoard.colorSquare(this.xPos, y, false);
					break;
				}
			}
			// for horizontal down 
			for(int y = yPos + 1; y < chessBoard.getBoardHeight(); y++) {
				if(chessBoard.getBoardPosition(this.xPos, y) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, y, this.type)) {
							chessBoard.colorSquare(this.xPos, y, false);
						}
					}else {
						chessBoard.colorSquare(this.xPos, y, false);
					}
				}
				else if(chessBoard.getBoardPosition(this.xPos, y) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, this.xPos, y, this.type)) {
							chessBoard.colorSquare(this.xPos, y, false);
						}
					}
					else
						chessBoard.colorSquare(this.xPos, y, false);
					break;
				}
			}
		}
		if(!gameLogic.verticalProtection(chessBoard, this.xPos, this.yPos, this.type)){
			// for vertical up
			for(int x = xPos - 1; x >= 0; x--) {
				if(chessBoard.getBoardPosition(x, this.yPos) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, this.yPos, this.type)) {
							chessBoard.colorSquare(x, this.yPos, false);
						}
					}else {
						chessBoard.colorSquare(x, this.yPos, false);
					}
				}
				else if(chessBoard.getBoardPosition(x, this.yPos) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, this.yPos, this.type)) {
							chessBoard.colorSquare(x, this.yPos, false);
						}
					}
					else
						chessBoard.colorSquare(x, this.yPos, false);
					break;
				}
			}
			// for horizontal down 
			for(int x = xPos + 1; x < chessBoard.getBoardWidth(); x++) {
				if(chessBoard.getBoardPosition(x, this.yPos) == 0) {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, this.yPos, this.type)) {
							chessBoard.colorSquare(x, this.yPos, false);
						}
					}else {
						chessBoard.colorSquare(x, this.yPos, false);
					}
				}
				else if(chessBoard.getBoardPosition(x, this.yPos) == this.type)
					break;
				else {
					if(chessBoard.check) {
						if(gameLogic.isThisProtecting(chessBoard, x, this.yPos, this.type)) {
							chessBoard.colorSquare(x, this.yPos, false);
						}
					}
					else
						chessBoard.colorSquare(x, this.yPos, false);
					break;
				}
			}
		}
	}

}
