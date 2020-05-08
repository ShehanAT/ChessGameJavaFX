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
			chessBoard.stalemate = true;
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
			System.out.println(chessBoard.getBoardPosition(x, y));
			if(chessBoard.getBoardPosition(x, y) == type && chessBoard.getPiece(x, y).name == "King") {
				y = yPos + 1;
				for(x = xPos - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++) {
					if(chessBoard.getBoardPosition(x, y) == type) {
						break;
					}
					else if(chessBoard.getBoardPosition(x, y) == enemyType) {
						if(chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"){
							return true;
						}
						else
							break;
					}
				}
				break;
			}
			else if(chessBoard.getBoardPosition(x, y) != 0)
				break;
		}
		
		//check for protecting piece on the diagonal down direction of the king
		y = yPos + 1;
		for(int x = xPos - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++) {
			if(chessBoard.getBoardPosition(x, y) == type && chessBoard.getPiece(x, y).name == "King") {
				y = yPos - 1;
				for(x = xPos + 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--) {
					if(chessBoard.getBoardPosition(x, y) == type) {
						break;
					}
					else if(chessBoard.getBoardPosition(x, y) == enemyType) {
						if(chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"){
							return true;
						}
						else
							break;
					}
				}
				break;
			}
			else if(chessBoard.getBoardPosition(x, y) != 0)
				break;
		}
		return false;
	}
	
	public boolean backslashDiagonalProtection(ChessBoard chessBoard, int xPos, int yPos, int type) {
		int enemyType = 0;
		if(type == 1)
			enemyType = 2;
		else 
			enemyType = 1;
		int y = yPos - 1;
		for(int x = xPos - 1; x >= 0 && y >= 0; x--, y--) {
			if(chessBoard.getBoardPosition(x, y) == type && chessBoard.getPiece(x, y).name == "King") {
				y = yPos + 1;
				for(x = xPos + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++) {
					if(chessBoard.getBoardPosition(x, y) == type)
						break;
					else if(chessBoard.getBoardPosition(x, y) == enemyType) {
						if(chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop")
							return true;
						else 
							break;
					}
						
				}
				break;
			}
			else if(chessBoard.getBoardPosition(x, y) != 0)
				break;
			
		}
		
		//check for king on the diagonal down 
		y = yPos + 1;
		for(int x = xPos + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++) {
			if(chessBoard.getBoardPosition(x, y) == type && chessBoard.getPiece(x, y).name == "King") {
				y = yPos - 1;
				for(x = xPos - 1; x >= 0 && y >= 0; x--, y--) {
					if(chessBoard.getBoardPosition(x, y) == type)
						break;
					else if(chessBoard.getBoardPosition(x, y) == enemyType) {
						if(chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop")
							return true;
						else 
							break;
					}
						
				}
				break;
			}
			else if(chessBoard.getBoardPosition(x, y) != 0)
				break;
			
		}
		return false;
		
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
		for(y = yPos + 1; y < chessBoard.getBoardHeight(); y++) {
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
		
		//Diagonal 1 / up
		for(y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(y == yPos - 1 && chessBoard.getPiece(x, yPos) != null && kingCanCapture && (chessBoard.getPiece(x, yPos).name == "King" || chessBoard.getPiece(x, yPos).name == "Pawn"))
					return true;
				else if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Bishop"))
					return true;
				else
					break;
			}
		}
		
		//Diagonal 1 / down
		for(y = yPos + 1, x = xPos + 1; y < chessBoard.getBoardHeight() && x < chessBoard.getBoardWidth(); y++, x++) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(y == yPos - 1 && chessBoard.getPiece(x, yPos) != null && kingCanCapture && (chessBoard.getPiece(x, yPos).name == "King" || chessBoard.getPiece(x, yPos).name == "Pawn"))
					return true;
				else if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Bishop"))
					return true;
				else
					break;
			}
		}
		
		//Diagonal 2 / down 
		for(y = yPos - 1, x = xPos + 1; y >= 0 && x < chessBoard.getBoardWidth(); y--, x++) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(y == yPos - 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && ((kingCanCapture && chessBoard.getPiece(x, yPos).name == "King") || (type == 1 && chessBoard.getPiece(x, yPos).name == "Pawn")))
					return true;
				else if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Bishop"))
					return true;
				else
					break;
			}
		}
		
		//Diagonal 2 / up 
		for(y = yPos + 1, x = xPos - 1; y< chessBoard.getBoardHeight() && x >= 0; y++, x--) {
			if(chessBoard.getBoardPosition(x, yPos) == type && chessBoard.getPiece(x, yPos).name != "King")
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(y == yPos + 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && ((kingCanCapture && chessBoard.getPiece(x, yPos).name == "King") || (type == 1 && chessBoard.getPiece(x, yPos).name == "Pawn")))
					return true;
				else if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Bishop"))
					return true;
				else
					break;
			}
		}
		
		// Knight 
		for(y = -2; y <= 2; y++) {
			if(y != 0) {
				x = y % 2 == 0 ? 1 : 2;
				if(yPos + y >= 0 && yPos + y < chessBoard.getBoardHeight() && xPos - x >= 0 && xPos - x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(xPos - x, yPos + y) != type && chessBoard.getBoardPosition(xPos - x, yPos + y) != 0)
					if(chessBoard.getPiece(xPos - x, yPos + y) != null && chessBoard.getPiece(xPos - x, yPos + y).name == "Knight")
						return true;
				if(yPos + y >= 0 && yPos + y < chessBoard.getBoardHeight() && xPos + x >= 0 && xPos + x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(xPos + x, yPos + y) != type && chessBoard.getBoardPosition(xPos + x, yPos + y) != 0)
					if(chessBoard.getPiece(xPos + x, yPos + y) != null && chessBoard.getPiece(xPos + x, yPos + y).name == "Knight")
						return true;
				
			}
		}
		return false;
	}
	
	public void findAllCheckPieces(ChessBoard chessBoard, int xPos, int yPos, int type) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if(type == 1)
			enemyType = 2;
		else 
			enemyType = 1;
		
		
		//check horizontal left 
		for(x = xPos - 1; x >= 0; x--) {
			if(chessBoard.getBoardPosition(x, yPos) == type)
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, yPos)); // adding piece that is in check to arrayList
				else
					break;
					
			}
		}
		
		//check horizontal left 
		for(x = xPos + 1; x < chessBoard.getBoardWidth(); x++) {
			if(chessBoard.getBoardPosition(x, yPos) == type)
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, yPos)); // adding piece that is in check to arrayList
				else
					break;
					
			}
		}
		
		
		// checking for vertical up 
		for(y = yPos - 1; y >= 0; y--) {
			if(chessBoard.getBoardPosition(xPos, y) == type)
				break;
			else if(chessBoard.getBoardPosition(xPos, y) == enemyType) {
				if(chessBoard.getPiece(xPos, y) != null && (chessBoard.getPiece(xPos, y).name == "Queen" || chessBoard.getPiece(xPos, y).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(xPos, y));
				else
					break;
			}
		}
		
		// checking for vertical down 
		for(y = yPos + 1; y < chessBoard.getBoardHeight(); y++) {
			if(chessBoard.getBoardPosition(xPos, y) == type)
				break;
			else if(chessBoard.getBoardPosition(xPos, y) == enemyType) {
				if(chessBoard.getPiece(xPos, y) != null && (chessBoard.getPiece(xPos, y).name == "Queen" || chessBoard.getPiece(xPos, y).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(xPos, y));
				else
					break;
			}
		}
		
		// diagonal 1 / up
		for(y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0 ; y--, x--) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(y == yPos - 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		
		// diagonal 1 / down
		for(y = yPos + 1, x = xPos + 1; y < chessBoard.getBoardHeight() && x < chessBoard.getBoardWidth() ; y++, x++) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(y == yPos + 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		
		// diagonal 2 / up
		for(y = yPos - 1, x = xPos + 1; y >= 0 && x < chessBoard.getBoardWidth() ; y--, x++) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(y == yPos - 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		
		// diagonal 2 / down
		for(y = yPos + 1, x = xPos - 1; y < chessBoard.getBoardHeight() && x >= 0 ; y++, x--) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(y == yPos + 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		
		// Knight 
		for(y = -2; y <= 2; y++) {
			if(y != 0) {
				x = y % 2 == 0? 1 : 2;
				if(yPos + y >= 0 && yPos + y < chessBoard.getBoardHeight() && xPos + x >= 0 && xPos + x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(xPos - x, yPos + y) != type && chessBoard.getBoardPosition(xPos - x, yPos + y) != 0)
					if(chessBoard.getPiece(xPos - x, yPos + y) != null && chessBoard.getPiece(xPos - x, yPos + y).name == "Knight")
						chessBoard.checkPieces.add(chessBoard.getPiece(xPos - x, yPos + y));
				if(yPos + y >= 0 && yPos + y < chessBoard.getBoardHeight() && xPos + x >= 0 && xPos + x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(xPos + x, yPos + y) != type && chessBoard.getBoardPosition(xPos + x, yPos + y) != 0)
					if(chessBoard.getPiece(xPos + x, yPos + y) != null && chessBoard.getPiece(xPos + x, yPos + y).name == "Knight")
						chessBoard.checkPieces.add(chessBoard.getPiece(xPos + x, yPos + y));
			}
		}
	}
	
	
	public void findAllSaviorPieces(ChessBoard chessBoard, int xPos, int yPos, int type, boolean protect) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if(type == 1)
			enemyType = 2;
		else
			enemyType = 1;
	
		//checking horizontal left 
		for(x = xPos - 1; x >= 0; x--) {
			if(chessBoard.getBoardPosition(x, yPos) == type)
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, yPos));
				else
					break;
				
			}
		}
		
		//checking horizontal right 
		for(x = xPos + 1; x < chessBoard.getBoardWidth(); x++) {
			if(chessBoard.getBoardPosition(x, yPos) == type)
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(chessBoard.getPiece(x, yPos) != null && (chessBoard.getPiece(x, yPos).name == "Queen" || chessBoard.getPiece(x, yPos).name == "Rook"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, yPos));
				else
					break;
				
			}
		}
		
		//checking vertical up 
		for(y = yPos - 1; y >= 0; y--) {
			if(chessBoard.getBoardPosition(x, yPos) == type)
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(enemyType == 2 && protect && y == yPos - 1 && chessBoard.getPiece(xPos, y) != null && chessBoard.getPiece(xPos, y).name == "Pawn") {
					chessBoard.saviorPieces.add(chessBoard.getPiece(xPos, y));
				}
				if(enemyType == 2 && protect && y == yPos - 2 && chessBoard.getPiece(xPos, y) != null && chessBoard.getPiece(xPos, y).name == "Pawn" && chessBoard.getPiece(xPos, y).isFirstTimeMove()) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(xPos, y));
				}
				if(chessBoard.getPiece(xPos, y) != null && (chessBoard.getPiece(xPos, y).name == "Queen" || chessBoard.getPiece(xPos, y).name == "Rook")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(xPos, y));
				}
				else {
					break;
				}
			}
			
		}
		
		//checking vertical down
		for(y = yPos + 1; y < chessBoard.getBoardHeight(); y++) {
			if(chessBoard.getBoardPosition(x, yPos) == type)
				break;
			else if(chessBoard.getBoardPosition(x, yPos) == enemyType) {
				if(enemyType == 1 && protect && y == yPos + 1 && chessBoard.getPiece(xPos, y) != null && chessBoard.getPiece(xPos, y).name == "Pawn") {
					chessBoard.saviorPieces.add(chessBoard.getPiece(xPos, y));
				}
				if(enemyType == 1 && protect && y == yPos + 2 && chessBoard.getPiece(xPos, y) != null && chessBoard.getPiece(xPos, y).name == "Pawn" && chessBoard.getPiece(xPos, y).isFirstTimeMove()) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(xPos, y));
				}
				if(chessBoard.getPiece(xPos, y) != null && (chessBoard.getPiece(xPos, y).name == "Queen" || chessBoard.getPiece(xPos, y).name == "Rook")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(xPos, y));
				}
				else {
					break;
				}
			}
			
		}
		
		//diagonal 1/up
		for(x = xPos - 1, y = yPos - 1; x >= 0 && y >= 00 ; x--, y--) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(protect == false && y == yPos - 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Rook")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				else
					break;
			}
		}
		
		//diagonal 1/up
		for(x = xPos + 1, y = yPos + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(protect == false && y == yPos + 1 &&  chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Rook")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				else
					break;
			}
		}
		
		//diagonal 2/up
		for(x = xPos + 1, y = yPos - 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(protect == false && y == yPos - 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Rook")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				else
					break;
			}
		}
		
		//diagonal 2/down 
		for(x = xPos - 1, y = yPos + 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++) {
			if(chessBoard.getBoardPosition(x, y) == type)
				break;
			else if(chessBoard.getBoardPosition(x, y) == enemyType) {
				if(protect == false && y == yPos + 1 && chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				if(chessBoard.getBoardPosition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Rook")) {
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				}
				else
					break;
			}
		}
		
		//Knight
		for(y = -2; y <= 2; y++) {
			if(y != 0) {
				x = y % 2 == 0 ? 1 : 2;
				if(y + yPos >= 0 && yPos + y > chessBoard.getBoardHeight() && xPos - x >= 0 && xPos - x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(xPos - x, yPos + y) != type && chessBoard.getBoardPosition(xPos - x, yPos + y) != 0) {
					if(chessBoard.getPiece(xPos - x, yPos + y) != null && chessBoard.getPiece(xPos - x, yPos + y).name == "Knight") {
						chessBoard.saviorPieces.add(chessBoard.getPiece(xPos - x, yPos + y));
					}
				}
				if(y + yPos >= 0 && yPos + y > chessBoard.getBoardHeight() && xPos + x >= 0 && xPos + x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(xPos + x, yPos + y) != type && chessBoard.getBoardPosition(xPos + x, yPos + y) != 0) {
					if(chessBoard.getPiece(xPos + x, yPos + y) != null && chessBoard.getPiece(xPos + x, yPos + y).name == "Knight") {
						chessBoard.saviorPieces.add(chessBoard.getPiece(xPos + x, yPos + y));
					}
				}
			}
		}
	
	}
	
	public void canCapture(ChessBoard chessBoard, Piece checkPiece) {
		findAllSaviorPieces(chessBoard, checkPiece.xPos, checkPiece.yPos, checkPiece.type, true);
	}
	
	public boolean isCheckMate(ChessBoard chessBoard, int xPos, int yPos, int type) {
		boolean checkMate = true;
		int y = 0;
		int x = 0;
		for(y = yPos - 1; y <= yPos + 1; y++) {
			for(x = xPos - 1; x <= xPos + 1; x++) {
				if(y >= 0 && y < chessBoard.getBoardHeight() && x >= 0 && x < chessBoard.getBoardWidth() && chessBoard.getBoardPosition(x,  y) != type) {
					if(!isCheck(chessBoard, x, y, type, true))
						checkMate = false;
						break;
				}
			}
			if(!checkMate)
				break;
		}
		
		if(chessBoard.checkPieces.size() < 2) {
			Piece checkPiece = chessBoard.checkPieces.get(0);
			canCapture(chessBoard, checkPiece);
			canProtect(chessBoard, xPos, yPos, type, checkPiece);
			if(!chessBoard.saviorPieces.isEmpty()) {
				for(Iterator<Piece> piece = chessBoard.saviorPieces.iterator(); piece.hasNext();) {
					Piece item = piece.next();
					item.saviorPiece = true;
					if(verticalProtection(chessBoard, item.xPos, item.yPos, item.type) || horizontalProtection(chessBoard, item.xPos, item.yPos, item.type) || slashDiagonalProtection(chessBoard, item.xPos, item.yPos, item.type) || backslashDiagonalProtection(chessBoard, item.xPos, item.yPos, item.type)) {
						item.saviorPiece = false;
						piece.remove();
					}
				}
			}
			if(!chessBoard.saviorPieces.isEmpty())
				checkMate = false;
		}
		return checkMate;
		
	}
	

	
	public void canProtect(ChessBoard chessBoard, int xPos, int yPos, int type, Piece checkPiece) {
		if(checkPiece.name == "Knight" || checkPiece.name == "Pawn")
			return ;
		//check for vertical up threat
		if(xPos == checkPiece.xPos && yPos > checkPiece.yPos) {
			for(int y = checkPiece.yPos + 1; y < yPos; y++) {
				findAllSaviorPieces(chessBoard, checkPiece.xPos, y, checkPiece.type, true);
			}
		}
		
		//check for vertical down threat
		if(xPos == checkPiece.xPos && yPos < checkPiece.yPos) {
			for(int y = checkPiece.yPos - 1; y > yPos; y--) {
				findAllSaviorPieces(chessBoard, checkPiece.xPos, y, checkPiece.type, true);
			}
		}
		
		//check for horizontal left threat 
		if(xPos > checkPiece.xPos && yPos == checkPiece.yPos) {
			for(int x = checkPiece.xPos + 1; x < xPos; x++) {
				findAllSaviorPieces(chessBoard, x, checkPiece.yPos, checkPiece.type, true);
			}
		}
		
		//check for horizontal right threat
		if(xPos < checkPiece.xPos && yPos == checkPiece.yPos) {
			for(int x = checkPiece.xPos - 1; x < xPos; x--) {
				findAllSaviorPieces(chessBoard, x, checkPiece.yPos, checkPiece.type, true);
			}
		}
		
		//diagonal 1/up threat
		int y = checkPiece.yPos + 1;
		if(xPos > checkPiece.xPos && yPos > checkPiece.yPos) {
			for(int x = checkPiece.xPos - 1; x < xPos && y < yPos; x++, y++) {
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
			}
		}
		
		//diagonal 1/down threat
		y = checkPiece.yPos - 1;
		if(xPos < checkPiece.xPos && yPos < checkPiece.yPos) {
			for(int x = checkPiece.xPos - 1; x > xPos && y > yPos; x--, y--){
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
			}
		}
		
		//diagonal 2/up threat
		y = checkPiece.yPos + 1;
		if(xPos < checkPiece.xPos && yPos > checkPiece.yPos) {
			for(int x = checkPiece.xPos - 1; x > xPos && y > yPos; x--, y--){
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
			}
		}
		
		//diagonal 2/down threat
		y = checkPiece.yPos - 1;
		if(xPos > checkPiece.xPos && yPos < checkPiece.yPos) {
			for(int x = checkPiece.xPos + 1; x < xPos && y > yPos; x++, y--){
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
			}
		}
	}
	
	public boolean isThisProtecting(ChessBoard chessBoard, int xPos, int yPos, int type) {
		Piece checkPiece = chessBoard.checkPieces.get(0);
		//vertical up threat 
		if(chessBoard.getKing(type).xPos == checkPiece.xPos && chessBoard.getKing(type).yPos > checkPiece.yPos)
			if(xPos == chessBoard.getKing(type).xPos && yPos < chessBoard.getKing(type).yPos && yPos > checkPiece.yPos)
				return true;
		//vertical down threat
		if(chessBoard.getKing(type).xPos == checkPiece.xPos && chessBoard.getKing(type).yPos < checkPiece.yPos)
			if(xPos == chessBoard.getKing(type).xPos && yPos > chessBoard.getKing(type).yPos && yPos < checkPiece.yPos)
				return true;
		//horizontal left threat
		if(chessBoard.getKing(type).xPos > checkPiece.xPos && chessBoard.getKing(type).yPos == checkPiece.yPos)
			if(xPos == chessBoard.getKing(type).xPos && yPos < chessBoard.getKing(type).yPos && yPos > checkPiece.yPos)
				return true;
		//horizontal left threat
		if(chessBoard.getKing(type).xPos < checkPiece.xPos && chessBoard.getKing(type).yPos == checkPiece.yPos)
			if(xPos == chessBoard.getKing(type).xPos && yPos > chessBoard.getKing(type).yPos && yPos < checkPiece.yPos)
				return true;
		
		//diagonal 1/up threat 
		int y = checkPiece.yPos;
		if(chessBoard.getKing(type).xPos > checkPiece.xPos && chessBoard.getKing(type).yPos > checkPiece.yPos)
			for(int x = checkPiece.xPos; x < chessBoard.getKing(type).xPos && y < chessBoard.getKing(type).yPos; x++, y++) {
				if(xPos == x && yPos == y)
					return true; 
			}
		//diagonal 1/down threat 
		y = checkPiece.yPos;
		if(chessBoard.getKing(type).xPos < checkPiece.xPos && chessBoard.getKing(type).yPos < checkPiece.yPos)
			for(int x = checkPiece.xPos; x > chessBoard.getKing(type).xPos && y > chessBoard.getKing(type).yPos; x--, y--) {
				if(xPos == x && yPos == y)
					return true; 
			}
		//diagonal 2/up threat 
		y = checkPiece.yPos;
		if(chessBoard.getKing(type).xPos < checkPiece.xPos && chessBoard.getKing(type).yPos > checkPiece.yPos)
			for(int x = checkPiece.xPos; x > chessBoard.getKing(type).xPos && y < chessBoard.getKing(type).yPos; x--, y++) {
				if(xPos == x && yPos == y)
					return true; 
			}
		//diagonal 2/down threat 
		y = checkPiece.yPos;
		if(chessBoard.getKing(type).xPos > checkPiece.xPos && chessBoard.getKing(type).yPos < checkPiece.yPos)
			for(int x = checkPiece.xPos; x < chessBoard.getKing(type).xPos && y > chessBoard.getKing(type).yPos; x++, y--) {
				if(xPos == x && yPos == y)
					return true; 
			}
		
		return false;
	}
	
	
	
	
}
