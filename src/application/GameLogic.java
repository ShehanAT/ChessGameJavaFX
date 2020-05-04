package application;

import java.util.Iterator;
import board.ChessBoard;
import pieces.Piece;

public class GameLogic {

	private boolean isOneKingStalemate(ChessBoard chessBoard, Piece king, int type) {
		int nbPiece = 0;
		boolean stalement = true;
		
		for(int y = 0; y < chessBoard.getBoardHeight(); y++) {
			for(int x = 0; x < chessBoard.getBoardWidth(); x++) {
				if(chessBoard.getBoardPosition(x,  y) == type) {
					nbPiece++;
				}
			}
		}
		if(nbPiece == 1) {
			for(int y = king.yPos - 1; y <= king.yPos + 1; y++) {
				for(int x = king.xPos - 1; x <= king.xPos + 1; x++) {
					if(y >= 0 && y < chessBoard.getBoardHeight() && x >= 0 && x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(x, y) != type) {
						if(!isCheck(chessBoard, x, y, type, true)) {
							stalement = false;
							break;
						}
					}
				}
				if(!stalement) {
					break;
				}
			}
		}else {
			stalement = false;
		}
		return stalement;
	}
	
	public boolean isLimitPieceStalemate(ChessBoard chessBoard) {
		/*if both players lose a king and:
		 * - a Queen
		 * - a Rook 
		 * - two knights
		 * - a bishop and a knight 
		 * - two bishops 
		 * - at least one pawn 
		 * then this function returns true for stalemate 
		 */
		if(chessBoard.playerOneQueen != 0 || chessBoard.playerTwoQueen != 0) {
			return false;
		}
		else if(chessBoard.playerOneRook != 0 || chessBoard.playerTwoRook != 0) {
			return false;
		}
		else if(chessBoard.playerOneKnight > 1 || chessBoard.playerTwoKnight > 1) {
			return false;
		}
		else if(((chessBoard.playerOneBishopDarkSquare != 0 || chessBoard.playerOneBishopLightSquare != 0) && chessBoard.playerOneKnight != 0) || 
				((chessBoard.playerTwoBishopDarkSquare != 0 || chessBoard.playerTwoBishopLightSquare != 0) && chessBoard.playerTwoKnight != 0)) {
			return false;
		}
		else if((chessBoard.playerOneBishopDarkSquare != 0 && chessBoard.playerOneBishopLightSquare != 0) || (chessBoard.playerTwoBishopDarkSquare != 0 && chessBoard.playerTwoBishopLightSquare != 0)) {
			return false;
		}
		else if(chessBoard.playerOnePawn > 1 || chessBoard.playerTwoPawn > 1) {
			return false;
		}
		return true;
	}
	
	public boolean isStalemate(ChessBoard chessBoard, Piece king, int type) {
//		if a stalemate has been detect by isOneKingStalement() or isLimitPieceStalement() 
//		return true, else return false 
		if(isOneKingStalemate(chessBoard, king, type) || isLimitPieceStalemate(chessBoard)) {
			chessBoard.stalement = true;
			return true;
		}
		return false;
	}
	
	public boolean verticalProtection(ChessBoard chessBoard, int xPos, int yPos, int type) {
		//this method checks if a piece is protecting the king from a check 
		int y = 0;
		int enemyType = 0;
		if(type == 1) {
			enemyType = 2;
		}else {
			enemyType = 1;
		}
		
		for(y = yPos - 1; y >= 0; y--) {
			if(chessBoard.getBoardPosition(xPos, y) == type && chessBoard.getPiece(xPos, y).name == "King") {
				for(y = yPos + 1; y < chessBoard.getBoardHeight(); y++) {
					if(chessBoard.getBoardPosition(xPos, y) == type) {
						break;
					}
					else if(chessBoard.getBoardPosition(xPos, y) == enemyType) {
						if(chessBoard.getPiece(xPos, y).name == "Queen" || chessBoard.getPiece(xPos,  y).name == "Rook") {
							return true;
						}
						else {
							break;
						}
					}
				}
				break;
			}
			else if(chessBoard.getBoardPosition(xPos, y) != 0) {
				break;
			}
		}
		
		for(y = yPos + 1; y < chessBoard.getBoardHeight(); y++) {
			if(chessBoard.getBoardPosition(xPos, y) == type && chessBoard.getPiece(xPos, y).name == "King") {
				for(y = yPos - 1; y >= 0; y--) {
					if(chessBoard.getBoardPosition(xPos, y) == type) {
						break;
					}
					else if(chessBoard.getBoardPosition(xPos, y) == enemyType) {
						if(chessBoard.getPiece(xPos, y).name == "Queen" || chessBoard.getPiece(xPos,  y).name == "Rook") {
							return true;
						}
						else {
							break;
						}
					}
				}
				break;
			}
			else if(chessBoard.getBoardPosition(xPos, y) != 0) {
				break;
			}
		}
		
		return false;
		
	}
	
	public boolean horizontalProtection(ChessBoard chessBoard, int xPos, int yPos, int type) {
		int x = 0;
		int enemyType = 0;
		if(type == 1)
			enemyType = 2;
		else
			enemyType = 1;
		
		//check to the horizontal left of the king 
		for(x = xPos - 1; x >= 0; x--) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name == "King") {
				for(x = xPos + 1; x < chessBoard.getBoardWidth(); x++) {
					if(chessBoard.getBoardPosition(x, yPos) == type) {
						break;
					}
					else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
						if(chessBoard.getPiece(x,  yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook") {
							return true;
						}
						else {
							break;
						}
					}
				}
				break;
			}
			else if(chessBoard.getBoardPosition(x, yPos) != 0) {
				break;
			}
			
			//check to the horizontal right of the king
			for(x = xPos + 1; x < chessBoard.getBoardWidth(); x++) {
				if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x,  yPos).name == "King") {
					for(x = xPos - 1; x >= 0; x--) {
						if(chessBoard.getBoardPosition(x,  yPos) == type) {
							break;
						}
						else if(chessBoard.getBoardPosition(x,  yPos) == enemyType) {
							if(chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook") {
								return true;
							}
							else {
								return false;
							}
						}
					}
					break;
				}
				else if(chessBoard.getBoardPosition(x,  yPos) != 0) {
					break;
				}
			}
		}
		return false;
	}
	
	public boolean slashDiagonalProtection(ChessBoard chessBoard, int xPos, int yPos, int type) {
		int enemyType = 0;
		if(type == 1) {
			enemyType = 2;
		}
		else {
			enemyType = 1;
		}
		
		//check for protecting piece on the diagonal up direction of the king
		int y = yPos - 1;
		for(int x = xPos + 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--) {
			if(chessBoard.getBoardPosition(x, y) == type && chessBoard.getPiece(x, y).name == "King") {
				y = yPos + 1;
				for(x = xPos - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++) {
					if(chessBoard.getBoardPosition(x, y) == type) {
						break;
					}
					else if(chessBoard.getBoardPosition(x, y) == enemyType) {
						if(chessBoard.getPeice)
					}
				}
			}
		}
	}
	
	public boolean isCheck(ChessBoard chessBoard, int xPos, int yPos, int type, boolean kingCanCapture) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if(type == 1) {
			enemyType = 2;
		}else {
			enemyType = 1;
		}
		
		//for horizontal left side 
		for(x = xPos - 1; x >= 0; x--) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(x == xPos - 1 && chessBoard.getPiece(x, yPos) != null && kingCanCapture && chessBoard.getPiece(x, yPos).name == "King")
					return false;
				else if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name != "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					return true;
				else 
					break;
				
				
			}
		}
		
		//for horizontal right side 
		for(x = xPos + 1; x < chessBoard.getBoardWidth(); x++) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(x == xPos + 1 && chessBoard.getPiece(x,  yPos) != null && kingCanCapture && chessBoard.getPiece(x, yPos).name == "King")
					return true;
				else if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					return true;
				else
					break;
			}
		}
		
		
		//Vertical up 
		for(y = yPos - 1; y >= 0; y--) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(y == yPos - 1 && chessBoard.getPiece(x, yPos) != null && kingCanCapture && chessBoard.getPiece(x, yPos).name == "King")
					return true;
				else if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					return true;
				else
					break;
			}
		}
		
		//Vertical down 
		for(y = yPos + 1; y >= 0; y--) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(y == yPos - 1 && chessBoard.getPiece(x, yPos) != null && kingCanCapture && chessBoard.getPiece(x, yPos).name == "King")
					return true;
				else if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					return true;
				else
					break;
			}
		}
		
		//TBC
		
		
	}
	
	
	
	
}
