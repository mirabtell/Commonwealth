package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

    public void goCreate(ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene homeScene = new Scene(homeRoot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Create Project");
        window.setScene(homeScene);
        window.show();
    }
}
