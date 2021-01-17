package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {

    public TextField nameField;
    public TextField projectNameField;
    public TextField numOfHelpersField;
    public TextField locationField;
    public TextField fileName;
    test form;
    test output;
    public void goHome(ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene homeScene = new Scene(homeRoot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Home");
        window.setScene(homeScene);
        window.show();
    }
    public void getForm(ActionEvent event) {
        output = test.getInstance();
        try{
            System.out.println("name of file is " + fileName.getText());
            FileInputStream fi = new FileInputStream(new File(fileName.getText() + ".txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            output = (test) oi.getObjectInputFilter();
            oi.close();
            fi.close();
            System.out.println("Displaying current file:");
            System.out.println(output.name);
            System.out.println(output.projectName);
            System.out.println(output.location);
            System.out.println(output.numOfHelpers);
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }
    public void setForm(javafx.event.ActionEvent event){
        form = test.getInstance();
        form.setName(nameField.getText());
        form.setLocation(locationField.getText());
        form.setNumOfHelpers(Integer.parseInt(numOfHelpersField.getText()));
        form.setProjectName(projectNameField.getText());
        try{
            FileOutputStream f = new FileOutputStream(new File(form.projectName + ".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(form);
            o.close();
            f.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }
}
