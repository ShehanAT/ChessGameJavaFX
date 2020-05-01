package board;

import java.util.ArrayList;
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
	
	private boolean check = false;
	private boolean checkMate = false;
	private boolean stalement = false;
	
	public ChessBoard() {
		board = new int[boardHeight][boardWidth];
		tiles = new Tile[boardHeight][boardWidth];
		pieces = new Piece[boardHeight][boardWidth];
		
		background = new Rectangle();
		background.setFill(Color.GHOSTWHITE);
		
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
			}
		}
		current_player = white_player;
		
	}
	
	public void initPiece() {
		
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
 
}
