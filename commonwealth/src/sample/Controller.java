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
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB.*;


public class Controller {

    public TextField nameField;
    public TextField projectNameField;
    public TextField numOfHelpersField;
    public TextField locationField;
    public TextField fileName;
    createProject form;
    createProject output;
    public void goHome(ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene homeScene = new Scene(homeRoot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Commonwealth");
        window.setScene(homeScene);
        homeScene.getStylesheets().add(Main.class.getResource("homestyle.css").toExternalForm());
        window.show();
    }
    public void getForm(ActionEvent event) {
        output = createProject.getInstance();
        try{
            System.out.println("name of file is " + fileName.getText());
            FileInputStream fi = new FileInputStream(new File(fileName.getText() + ".txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            output = (createProject) oi.getObjectInputFilter();
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
        MongoClient mongoClient = null;
        try {
            // Set up mongo db
            mongoClient = new MongoClient();
            DB database = mongoClient.getDB("myMongoDb");
            boolean auth = database.authenticate("username", "pwd".toCharArray());

            mongoClient = new MongoClient( );
            DB database = mongoClient.getDB("myMongoDb");
            database.createCollection("services", null);
            System.out.println("Connected to MongoDB!");

            // form completion
            /*
            form = createProject.getInstance();
            form.setName(nameField.getText());
            form.setLocation(locationField.getText());
            form.setNumOfHelpers(Integer.parseInt(numOfHelpersField.getText()));
            form.setProjectName(projectNameField.getText());
*/
            DBCollection collection = database.getCollection("services");
            BasicDBObject formEntry = new BasicDBObject();
            formEntry.put("nameField", nameField.getText());
            formEntry.put("locationField", locationField.getText());
            formEntry.put("numOfHelpers", numOfHelpersField.getText());
            formEntry.put("projectNameField", projectNameField.getText());
            collection.insert(formEntry);
            /*
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
            */


        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            if(mongoClient!=null)
                mongoClient.close();
        }


    }
}
