package application;

import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import board.ChessBoard;

public class Timer {
	public int whiteTimer = 20;
	public int blackTimer = 20;
	public int playerTurn = 0;
	public boolean timeOver = false;
	private ChessBoard chessBoard;
	
	public Timer(ChessBoard _chessBoard) {
		chessBoard = _chessBoard;
	}
	
	public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
		@Override 
		public void handle(ActionEvent event) {
			if(playerTurn == 1 && !timeOver && !chessBoard.checkMate && !chessBoard.stalement) {
				whiteTimer -= 1;
				chessBoard.getStatusBar().setWhitePlayerTimerText(TimeUnit.SECONDS.toMinutes(whiteTimer) + ":" + (whiteTimer % 60));
				
			}
			else if(playerTurn == 2 && !timeOver && !chessBoard.checkMate && !chessBoard.stalement) {
				blackTimer -= 1;
				chessBoard.getStatusBar().setBlackPlayerTimerText(TimeUnit.SECONDS.toMinutes(blackTimer) + ":" + (blackTimer % 60));
			}
			if(!timeOver && (whiteTimer == 0 || blackTimer == 0)) { // if playerTurn is 0 
				chessBoard.timerOver(playerTurn);
				timeOver = true;
				
			}
		}
	}));

}
