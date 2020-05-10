package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	private StackPane mainLayout;
	private CustomControl customControl;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Chess Game");
			primaryStage.setScene(new Scene(mainLayout, 600, 700));
			primaryStage.setMinWidth(300);
			primaryStage.setMinHeight(300);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void stop() {
		
	}
	
	@Override
	public void init() {
		mainLayout = new StackPane();
		customControl = new CustomControl();
		mainLayout.getChildren().add(customControl);
	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
