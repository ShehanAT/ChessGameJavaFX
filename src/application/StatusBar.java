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
	private Label whitePlayerAlert;
	private Label blackPlayerAlert;
	private Label whitePlayerTimer;
	private Label blackPlayerTimer;
	private Label winner;
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
		statusBarGridPane.setPrefSize(2000, 150);
		RowConstraints row = new RowConstraints();;
		row.setPercentHeight(45);
		statusBarGridPane.getRowConstraints().add(row);
		row = new RowConstraints();
		row.setPercentHeight(45);
		statusBarGridPane.getRowConstraints().add(row);
		statusBarGridPane.addRow(0, resetButton, whitePlayerAlert, blackPlayerAlert);
		statusBarGridPane.addRow(1, winner, whitePlayerTimer, blackPlayerTimer);
		
		statusBarGridPane.setVgap(30);
		statusBarGridPane.setHgap(30);
		statusBarGridPane.setPadding(new Insets(10, 10, 10, 10));
		
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
	
}
