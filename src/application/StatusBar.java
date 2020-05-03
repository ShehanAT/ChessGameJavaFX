package application;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.HBox;


public class StatusBar extends HBox{
	private Button resetButton;
	public Label whitePlayerAlert;
	public Label blackPlayerAlert;
	public Label whitePlayerTimer;
	public Label blackPlayerTimer;
	public Label winner;
	private GridPane statusBarGridPane; 
	
	
	public StatusBar() {
		statusBarGridPane = new GridPane();
		resetButton = new Button("Reset Game");
		whitePlayerAlert = new Label("Empty");
		blackPlayerAlert = new Label("Empty");
		whitePlayerTimer = new Label("Empty");
		blackPlayerTimer = new Label("Empty");
		winner = new Label("Empty");
		
		//setting three column constaints and adding them to statusBarGridPane
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(30);
		statusBarGridPane.getColumnConstraints().add(column);
		column = new ColumnConstraints();
		column.setPercentWidth(30);
		statusBarGridPane.getColumnConstraints().add(column);
		column = new ColumnConstraints();
		column.setPercentWidth(30);
		statusBarGridPane.getColumnConstraints().add(column);
		statusBarGridPane.setPrefSize(2000, 100);
		RowConstraints row = new RowConstraints();;
		statusBarGridPane.getRowConstraints().add(row);
		row = new RowConstraints();
		statusBarGridPane.getRowConstraints().add(row);
		whitePlayerAlert.setWrapText(true);
		
		statusBarGridPane.addRow(0, whitePlayerAlert, resetButton, blackPlayerAlert);
		statusBarGridPane.addRow(1, whitePlayerTimer, winner, blackPlayerTimer);
		
		statusBarGridPane.setVgap(0);
		statusBarGridPane.setHgap(0);
		
		statusBarGridPane.setPadding(new Insets(0 , 20, 0, 20));
		
		for(Node n: statusBarGridPane.getChildren()) {
			GridPane.setHalignment(n, HPos.CENTER);
			GridPane.setValignment(n, VPos.CENTER);
			if(n instanceof Label) {
				n.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold; -fx-opacity: 1.0;");
			}
		}
		
		statusBarGridPane.getStylesheets().addAll(this.getClass().getResource("statusBar.css").toExternalForm());
		statusBarGridPane.setId("statusBar");
		statusBarGridPane.setSnapToPixel(false);
		getChildren().add(statusBarGridPane);
		
	}
	
	public void resize(double width, double height) {
		super.resize(width, height);
		setWidth(width);
		setHeight(height);
	}
	
	public Button getResetButton() {
		return resetButton;
	}
	
	public void setResetButton(Button resetButton) {
		this.resetButton = resetButton;
	}
	
	public void setWhitePlayerTimerText(String time) {
		this.whitePlayerTimer.setText(time);
	}
	
	public void setBlackPlayerTimerText(String time) {
		this.blackPlayerTimer.setText(time);
	}
	
	public void setWhitePlayerAlertText(String message) {
		this.whitePlayerAlert.setText(message);
	}
	
	public void setBlackPlayerAlertText(String message) {
		this.blackPlayerAlert.setText(message);
	}
	
}
