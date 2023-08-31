package com.example.movietimetracker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Application implements Initializable, Runnable {

    @FXML
    Button addMovieButton;
    @FXML
    Button editMovieButton;
    @FXML
    Button checkMovieButton;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Program.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MovieTimes");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // change to check scene
        editMovieButton.setOnAction(this::changeToEditScene);

        // change to add scene
        addMovieButton.setOnAction(this::changeToAddScene);
    }


    @Override
    public void run() {
        launch();
    }

    public void changeToCheckScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckMovie.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeToAddScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeToEditScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditMovie.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
