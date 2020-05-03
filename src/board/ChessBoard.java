package board;

import java.util.ArrayList;
import application.Timer;
import javafx.scene.transform.Rotate;
import application.StatusBar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pieces.Piece;
import pieces.*;
public class ChessBoard extends Pane{
	private double cell_width;
	private double cell_height;
	private int current_player;
	private final int empty_tile = 0;
	private final int white_player = 1;
	private final int black_player = 2;
	private int boardHeight = 8;
	private int boardWidth = 8;
	private int[][] board;
	private Tile[][] tiles;
	private Piece[][] pieces;
	private boolean blackTile;
	private Rectangle background;
	private Piece selectedPiece;
	private StatusBar statusBar; 
	private Timer timer;
	
	//declare pieces 
	private Pawn whitePawn_1;
	private Pawn whitePawn_2;
	private Pawn whitePawn_3;
	private Pawn whitePawn_4;
	private Pawn whitePawn_5;
	private Pawn whitePawn_6;
	private Pawn whitePawn_7;
	private Pawn whitePawn_8;
	private Queen whiteQueen_1;
	private King whiteKing_1;
	private Knight whiteKnight_1;
	private Knight whiteKnight_2;
	private Bishop whiteBishop_1;
	private Bishop whiteBishop_2;
	private Rook whiteRook_1;
	private Rook whiteRook_2;
	
	private Pawn blackPawn_1;
	private Pawn blackPawn_2;
	private Pawn blackPawn_3;
	private Pawn blackPawn_4;
	private Pawn blackPawn_5;
	private Pawn blackPawn_6;
	private Pawn blackPawn_7;
	private Pawn blackPawn_8;
	private Queen blackQueen_1;
	private King blackKing_1;
	private Knight blackKnight_1;
	private Knight blackKnight_2;
	private Bishop blackBishop_1;
	private Bishop blackBishop_2;
	private Rook blackRook_1;
	private Rook blackRook_2;
	
	
	
	public boolean check = false;
	public boolean checkMate = false;
	public boolean stalement = false;
	
	public ChessBoard(StatusBar newStatusBar) {
		
		statusBar = newStatusBar; // set statusbar passed in from custom control constructor
		
		statusBar.whitePlayerAlert.setText("White Player's turn!");
		statusBar.blackPlayerAlert.setText("");
		statusBar.whitePlayerTimer.setText("1:00");
		statusBar.blackPlayerTimer.setText("1:00");
		
		board = new int[boardHeight][boardWidth];
		tiles = new Tile[boardHeight][boardWidth];
		pieces = new Piece[boardHeight][boardWidth];
		
		background = new Rectangle();
		background.setFill(Color.WHITE);
		
		getChildren().add(background);
		
		for(int i = 0 ; i < 8 ; i++) {
			if(i%2 == 0 || i == 0) 
				blackTile = true;
			else
				blackTile = false;
			for(int j = 0 ; j < 8 ; j++) { //bug with blackTile
				board[i][j] = empty_tile;
				if(blackTile) {
					tiles[i][j] = new Tile(0); //set tile to black color
					blackTile = false;
				}else {
					tiles[i][j] = new Tile(1); //set tile to white color
					blackTile = true;
				}
				getChildren().add(tiles[i][j]);
				pieces[i][j] = null;
			}
			
		}
		
		initPiece();
		current_player = white_player;
		timer = new Timer(this); // set the timer
		timer.timeline.setCycleCount(Timeline.INDEFINITE); // cause the timer to repeat indefinitely 
		timer.timeline.play();
		timer.playerTurn = current_player; // start timer for white player
		
	}
	
	public void initPiece() {
		// initialize white pieces
		whiteRook_1 = new Rook(1, 0, 7);
		whiteKnight_1 = new Knight(1, 1, 7);
		whiteBishop_1 = new Bishop(1, 2, 7);
		whiteQueen_1 = new Queen(1, 3, 7);
		whiteKing_1 = new King(1, 4, 7);
		whiteBishop_2 = new Bishop(1, 5, 7);
		whiteKnight_2 = new Knight(1, 6, 7);
		whiteRook_2 = new Rook(1, 7, 7);
		whitePawn_1 = new Pawn(1, 0, 6);
		whitePawn_2 = new Pawn(1, 1, 6);
		whitePawn_3 = new Pawn(1, 2, 6);
		whitePawn_4 = new Pawn(1, 3, 6);
		whitePawn_5 = new Pawn(1, 4, 6);
		whitePawn_6 = new Pawn(1, 5, 6);
		whitePawn_7 = new Pawn(1, 6, 6);
		whitePawn_8 = new Pawn(1, 7, 6);
		
		//initialize black pieces
		blackRook_1 = new Rook(2, 0, 0);
		blackKnight_1 = new Knight(2, 1, 0);
		blackBishop_1 = new Bishop(2, 2, 0);
		blackKing_1 = new King(2, 3, 0);
		blackQueen_1 = new Queen(2, 4, 0);
		blackBishop_2 = new Bishop(2, 5, 0);
		blackKnight_2 = new Knight(2, 6, 0);
		blackRook_2 = new Rook(2, 7, 0);
		blackPawn_1 = new Pawn(2, 0, 1);
		blackPawn_2 = new Pawn(2, 1, 1);
		blackPawn_3 = new Pawn(2, 2, 1);
		blackPawn_4 = new Pawn(2, 3, 1);
		blackPawn_5 = new Pawn(2, 4, 1);
		blackPawn_6 = new Pawn(2, 5, 1);
		blackPawn_7 = new Pawn(2, 6, 1);
		blackPawn_8 = new Pawn(2, 7, 1);
		
		pieces[0][0] = whiteRook_1;
		pieces[0][1] = whiteKnight_1;
		pieces[0][2] = whiteBishop_1;
		pieces[0][3] = whiteQueen_1;
		pieces[0][4] = whiteKing_1;
		pieces[0][5] = whiteBishop_2;
		pieces[0][6] = whiteKnight_2;
		pieces[0][7] = whiteRook_2;
		pieces[1][0] = whitePawn_1;
		pieces[1][1] = whitePawn_2;
		pieces[1][2] = whitePawn_3;
		pieces[1][3] = whitePawn_4;
		pieces[1][4] = whitePawn_5;
		pieces[1][5] = whitePawn_6;
		pieces[1][6] = whitePawn_7;
		pieces[1][7] = whitePawn_8;
		
	
		
		for(int i = 0 ; i < boardWidth; i++) {
			for(int j = 2 ; j < 6; j++) { // setting the squares between the white and black sides to null
				pieces[j][i] = null; 
			}
		}
		
		pieces[7][0] = blackRook_1;
		pieces[7][1] = blackKnight_1;
		pieces[7][2] = blackBishop_1;
		pieces[7][3] = blackQueen_1;
		pieces[7][4] = blackKing_1;
		pieces[7][5] = blackBishop_2;
		pieces[7][6] = blackKnight_2;
		pieces[7][7] = blackRook_2;
		pieces[6][0] = blackPawn_1;
		pieces[6][1] = blackPawn_2;
		pieces[6][2] = blackPawn_3;
		pieces[6][3] = blackPawn_4;
		pieces[6][4] = blackPawn_5;
		pieces[6][5] = blackPawn_6;
		pieces[6][6] = blackPawn_7;
		pieces[6][7] = blackPawn_8;
		
	
		
		for(int i = 0 ; i < boardWidth; i++) { // columns
			for(int j = 0; j < boardHeight; j++) { // rows
				if(i == 0 || i == 1) {
					board[i][j] = 1; //indicate this is a white piece
				}
				else if(i == 6 || i == 7) {
					board[i][j] = 2; //indicate this is a black piece
				}
				else {
					board[i][j] = 0; //indicate this is a unoccupied piece
				}
			}
		}
		
		for(int i = 0 ; i < 8; i++) {
			getChildren().addAll(pieces[0][i].getImage(), pieces[1][i].getImage(), pieces[6][i].getImage(), pieces[7][i].getImage());
		}
		
				
	}
	
	@Override 
	public void resize(double width, double height) {
		//calling super class's resize method 
		super.resize(width, height);
		
		background.setWidth(width);
		background.setHeight(height);
		
		System.out.println("width: " + width + ", height: " + height);
		cell_width = width / 8.0; //calculating the width of each tile on the chess board 
		cell_height = height / 8.0; //calculating the height of each tile on the chess board 
		
		//update the piece positions on the chess board
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++){
				if(board[i][j] != 0) {//if tile is not supposed to be empty add appropriate piece
					
					pieces[i][j].relocate(j * cell_height, i * cell_width);
					pieces[i][j].resize(cell_height, cell_width);
				}
				
				tiles[i][j].relocate(i * cell_width, j * cell_height);
				tiles[i][j].resize(cell_width, cell_height);	
			}
		}
		
	}
	
	
	public void colorSquare(int x, int y, boolean selectedPiece) {
		// This function highlights the current position of the selected piece in green and available move positions in yellow
		if(selectedPiece) {
			tiles[x][y].highlightWindow(Color.YELLOW);
		}else {
			tiles[x][y].highlightWindow(Color.GREEN);
		}
	}
	
	public void unHighlight() {
		for(int i = 0 ; i < boardWidth ; i++) {
			for(int j = 0; j < boardHeight ; j++) {
				if(tiles[i][j].getTile().getStroke() != null) { // if tile does not have default stroke value of null(meaning it is highlighted), unhighlight it 
					tiles[i][j].unHighlight();
				}
			}
		}
	}
	
	public void timerOver(int playerOutOfTime) {
		timer.timeline.stop(); //stops the timer 
		if(playerOutOfTime == 1) {
			statusBar.setWhitePlayerAlertText("White Player has run out of time!");
			statusBar.setBlackPlayerAlertText("Black Player has won!");
		}
		else if(playerOutOfTime == 2) {
			statusBar.setBlackPlayerAlertText("Black Player has run out of time!");
			statusBar.setWhitePlayerAlertText("White Player has won!");
		}
	}
	
	public int getBoardHeight() {
		return this.boardHeight;
	}
	
	public int getBoardWidth() {
		return this.boardWidth;
	}
	
	public int getBoardPosition(int x, int y) {
		return this.board[x][y];
	}
	
	public void setBoard(int x, int y, int type) {
		this.board[x][y] = type;
	}
	
	public void setPiece(int x, int y, Piece piece) {
		this.pieces[x][y] = piece;
	}
	
	public Piece getPiece(int x, int y) {
		return pieces[x][y];
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
 
}
