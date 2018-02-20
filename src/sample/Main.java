package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DBHandler.DatabaseHandler;
import sample.CommunicationHandler.BsServer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        //this just to check what is stored in database
        DatabaseHandler bb;
        bb = new DatabaseHandler();
        bb.select();

        BsServer bsServer=new BsServer();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
