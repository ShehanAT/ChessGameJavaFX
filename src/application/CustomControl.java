package application;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import board.ChessBoard;

public class CustomControl extends Control {
	private ChessBoard chessBoard;
	private StatusBar statusBar;
	private int statusBarSize; 
	
	public CustomControl() {
		setSkin(new ControlSkin(this));
		statusBar = new StatusBar();
		statusBarSize = 150; // this is the height of the statusBar
		chessBoard = new ChessBoard(statusBar);
		//getChildren().add() adds children elements(chessBoard, Tiles, Pieces, statusBar) to mainLayout stackpane
//		getChildren().addAll(statusBar, chessBoard);
		getChildren().add(statusBar);
		getChildren().add(chessBoard);
		for(int i = 0; i < getChildren().size() ; i++) {
			System.out.println(getChildren().get(i));
		}
		
	}
	
	public void resize(double width, double height) {
		super.resize(width, height - statusBarSize); // resize the application to fit the chess board and the status bar
		chessBoard.setTranslateY(statusBarSize / 2); // setTranslate makes dynamic adjustments to chessBoard's initial position
		chessBoard.resize(width, height - statusBarSize);
		statusBar.resize(width, statusBarSize);
		statusBar.setTranslateY(-(statusBarSize / 2));
		
	}
}
