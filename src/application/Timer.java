package application;

import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import board.ChessBoard;

public class Timer {
	public int whiteTimer = 900;
	public int blackTimer = 900;
	public int playerTurn = 0;
	public boolean timeOver = false;
	private ChessBoard chessBoard;
	
	public Timer(ChessBoard _chessBoard) {
		chessBoard = _chessBoard;
	}
	
	public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
		@Override 
		public void handle(ActionEvent event) {
			if(playerTurn == 1 && !timeOver && !chessBoard.checkMate && !chessBoard.stalemate) {
				whiteTimer -= 1;
				String seconds =  String.valueOf(whiteTimer % 60);
				if(seconds.length() < 2)
					seconds = "0" + seconds;
				chessBoard.getStatusBar().setWhitePlayerTimerText(TimeUnit.SECONDS.toMinutes(whiteTimer) + ":" + seconds);
				
			}
			else if(playerTurn == 2 && !timeOver) {
				blackTimer -= 1;
				String seconds =  String.valueOf(blackTimer % 60);
				if(seconds.length() < 2)
					seconds = "0" + seconds;
				chessBoard.getStatusBar().setBlackPlayerTimerText(TimeUnit.SECONDS.toMinutes(blackTimer) + ":" + seconds);
			}
			if(!timeOver && (whiteTimer == 0 || blackTimer == 0)) { // if playerTurn is 0 
				chessBoard.timerOver(playerTurn);
				timeOver = true;
				
			}
		}
	}));

}
