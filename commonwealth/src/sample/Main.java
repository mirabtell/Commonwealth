package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("Commonwealth");
        Scene s1 = new Scene(root, 260, 358);
        primaryStage.setScene(s1);
        s1.getStylesheets().add(Main.class.getResource("homestyle.css").toExternalForm());
        primaryStage.show();

    }


    public static void main(String[] args) { launch(args);
    }
}
