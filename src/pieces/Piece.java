package pieces;

import javafx.scene.Group;
import application.GameLogic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import board.ChessBoard;
public abstract class Piece extends Group{
	public int type; // black or white piece 
	public int xPos; // x coordinate of piece 
	public int yPos; // y coordinate of piece
	public String name; //name of piece
	protected ImageView imageView = new ImageView(); //image of the piece 
	protected Translate pos;
	protected GameLogic gameLogic = new GameLogic();
	public boolean firstTimeMove; //to check if the piece has been used before 
	public boolean saviorPiece; //to check if piece can be moved when in check situation
	
	public Piece(int type, int xPos, int yPos) {
		this.type = type; 
		this.xPos = xPos;
		this.yPos = yPos;
		this.firstTimeMove = firstTimeMove;
	}
	
	public void resize(double width, double height) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
	}
	
	public void relocate(double x, double y) {
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);
		centerImage();
	}
	
	public void centerImage() {
		Image img = imageView.getImage();
		if(img != null) {
			double w = 0;
			double h = 0;
			
			double ratioX = imageView.getFitWidth() / img.getWidth();
			double ratioY = imageView.getFitHeight() / img.getHeight();
			
			double reduceCoeff = 0;
			if(ratioX <= ratioY) {
				reduceCoeff = ratioY;
			}else {
				reduceCoeff = ratioX;
			}
			
			w = img.getWidth() * reduceCoeff;
			h = img.getHeight() * reduceCoeff;
			
			imageView.setX((imageView.getFitWidth() - w) / 2);
			imageView.setY((imageView.getFitHeight() - h) / 2);
			
		}	
	}
	
	//abstract method for all piece types
	public void SelectPiece(ChessBoard chessBoard) {
		
	}
	
	
	public void movePiece(ChessBoard chessBoard, int x, int y) {
	/* after selecting and picking highlighted square to move to this method moves the piece
	 * there
	 */
		chessBoard.setBoard(this.xPos, this.yPos, 0);
		chessBoard.setPiece(this.xPos, this.yPos, null);
		
		if(!chessBoard.check && this.canCastle(chessBoard) != 0){
			if(this.canCastle(chessBoard) == 1) {
				chessBoard.setBoard(x-1, y, this.type);
				chessBoard.setPiece(x-1, y, this);
				this.xPos = x - 1;
				chessBoard.setBoard(5, y, chessBoard.getPiece(7, y).type);
				chessBoard.setPiece(5, y, chessBoard.getPiece(7, y));
				chessBoard.getPiece(7, y).xPos = 7; //changing xPos of piece
				chessBoard.setBoard(7, y, 0);
				chessBoard.setPiece(7, y, null);
			}
			if(this.canCastle(chessBoard) == 2){
				chessBoard.setBoard(x+2, y, this.type);
				chessBoard.setPiece(x+2, y, this);
				this.xPos = x + 2;
				chessBoard.setBoard(3, y, chessBoard.getPiece(0, y).type);
				chessBoard.setPiece(3, y, chessBoard.getPiece(0, y));
				chessBoard.getPiece(3, y).xPos = 3; //changing xPos of piece
				chessBoard.setBoard(0, y, 0);
				chessBoard.setPiece(0, y, null);
			}
			if(this.canCastle(chessBoard) == 3) {
				chessBoard.setBoard(x-1, y, this.type);
				chessBoard.setPiece(x-1, y, this);
				this.xPos = x - 1;
				chessBoard.setBoard(5, y, chessBoard.getPiece(7, y).type);
				chessBoard.setPiece(5, y, chessBoard.getPiece(7, y));
				chessBoard.getPiece(5, y).xPos = 5; //changing xPos of piece
				chessBoard.setBoard(7, y, 0);
				chessBoard.setPiece(7, y, null);
			}
			if(this.canCastle(chessBoard) == 4) {
				chessBoard.setBoard(x+2, y, this.type);
				chessBoard.setPiece(x+2, y, this);
				this.xPos = x + 2;
				chessBoard.setBoard(3, y, chessBoard.getPiece(0, y).type);
				chessBoard.setPiece(3, y, chessBoard.getPiece(0, y));
				chessBoard.getPiece(3, y).xPos = 3; //changing xPos of piece
				chessBoard.setBoard(0, y, 0);
				chessBoard.setPiece(0, y, null);
			}
		}
		else {
			this.xPos = x;
			this.yPos = y;
			if(chessBoard.getPiece(x, y) != null) 
				chessBoard.getPiece(x, y).capture(chessBoard);
			chessBoard.setPiece(x, y, this);
			chessBoard.setBoard(x, y, this.type);
			if(this.name == "Pawn" && ((this.type == 1 && this.yPos == 0) || (this.type == 2 && this.yPos == 7)))
			{
				chessBoard.createPromotePiece(this);
				if(this.type == 1)
					chessBoard.playerOnePawn--;
				else
					chessBoard.playerTwoPawn--;
				
			}
			
		}
	}
	
	public void capture(ChessBoard chessBoard) {
		if(this.type == 1) {
			if(this.name == "Rook") {
				chessBoard.playerOneRook--;
			}
			else if(this.name == "Queen") {
				chessBoard.playerOneQueen--;
			}
			else if(this.name == "Bishop" && ((this.yPos + this.xPos) % 2 != 0)) {
				chessBoard.playerOneBishopDarkSquare--;
			}
			else if(this.name == "Bishop" && ((this.yPos + this.xPos) % 2 == 0)) {
				chessBoard.playerOneBishopLightSquare--;
			}
			else if(this.name == "Pawn") {
				chessBoard.playerOnePawn--;
			}
			else if(this.name == "Knight") {
				chessBoard.playerOneKnight--;
			}
		}else {
			if(this.name == "Rook") {
				chessBoard.playerTwoRook--;
			}
			else if(this.name == "Queen") {
				chessBoard.playerTwoQueen--;
			}
			else if(this.name == "Bishop" && ((this.yPos + this.xPos) % 2 != 0)) {
				chessBoard.playerTwoBishopDarkSquare--;
			}
			else if(this.name == "Bishop" && ((this.yPos + this.xPos) % 2 == 0)) {
				chessBoard.playerTwoBishopLightSquare--;
			}
			else if(this.name == "Pawn") {
				chessBoard.playerTwoPawn--;
			}
			else if(this.name == "Knight") {
				chessBoard.playerTwoKnight--;
			}
			
		}
		chessBoard.getChildren().remove(this.getImage());
	}
	
	public int canCastle(ChessBoard chessBoard) {
		return 0;
	}
	
	
	
	public ImageView getImage() {
		return imageView;
		
	}
	
	public boolean isFirstTimeMove() {
		return firstTimeMove;
	}
	
	public void setFirstTimeMove(boolean firstTime) {
		this.firstTimeMove = firstTime;
	}
	
	public int getX() {
		return this.xPos;
	}
	
	public int getY() {
		return this.yPos;
	}
	

}
