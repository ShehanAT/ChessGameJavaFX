package application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import board.ChessBoard;

public class CustomControl extends Control {
	private ChessBoard chessBoard;
	
	public CustomControl() {
		setSkin(new ControlSkin(this));
		
		chessBoard = new ChessBoard();
		//getChildren().add() adds children elements(chessBoard, Tiles, Pieces) to mainLayout stackpane
		getChildren().add(chessBoard);
		
		
		
	}
}
