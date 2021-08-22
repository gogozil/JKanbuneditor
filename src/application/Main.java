package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public EditorConfiguration ec;
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("editor_main.fxml"));
        Scene scene=new Scene(root, 1000, 750);
        scene.getStylesheets().add(Main.class.getResource("kunten.css").toExternalForm());
        primaryStage.setTitle("漢文 Editor1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        AnnotatedText.init();
        launch(args);
    }
}