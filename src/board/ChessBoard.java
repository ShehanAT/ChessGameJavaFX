package board;

import java.util.ArrayList;
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
	
	public boolean check = false;
	private boolean checkMate = false;
	public boolean stalement = false;
	
	public ChessBoard(StatusBar newStatusBar) {
		
		statusBar = newStatusBar; // set statusbar passed in from custom control constructor
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
			for(int j = 0 ; j < 8 ; j++) {
				board[i][j] = empty_tile;
				pieces[i][i] = null;
				if(blackTile) {
					tiles[i][j] = new Tile(0); //set tile to black color
					blackTile = false;
				}else {
					tiles[i][j] = new Tile(1); //set tile to white color
					blackTile = true;
				}
				getChildren().add(tiles[i][j]);
			}
			
		}
		for(int i = 0 ; i < getChildren().size(); i++) {
			System.out.println(getChildren().get(i));
		}
		current_player = white_player;
		
	}
	
	public void initPiece() {
		
	}
	
	@Override 
	public void resize(double width, double height) {
		//calling super class's resize method 
		super.resize(width, height);
		
		background.setWidth(width);
		background.setHeight(height);
		
		cell_width = width / 8.0; //calculating the width of each tile on the chess board 
		cell_height = height / 8.0; //calculating the height of each tile on the chess board 
		
		//update the piece positions on the chess board
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				
				
				tiles[i][j].resize(cell_width, cell_height);
				tiles[i][j].relocate(i * cell_width, j * cell_height);
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
