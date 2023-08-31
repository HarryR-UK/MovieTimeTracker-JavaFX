package com.example.movietimetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    TextField movieTitleTF;
    @FXML
    TextField seasonTF;
    @FXML
    TextField episodeTF;
    @FXML
    TextField durationHourTF;
    @FXML
    TextField durationMinTF;
    @FXML
    TextField durationSecTF;


    @FXML
    Button addMovieButton;

    @FXML
    Label movieStatusLabel;

    @FXML
    Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addMovieButton.setOnAction(this::addMovie);
        backButton.setOnAction(this::changeToMainWindow);

    }

    public void addMovie(ActionEvent event){
        try{
            FileWriter fw = new FileWriter("movies.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String movieDuration = durationHourTF.getText() + ":" + durationMinTF.getText() + ":" + durationSecTF.getText();
            pw.println(movieTitleTF.getText() + "::" + seasonTF.getText() + "::" + episodeTF.getText() + "::" + movieDuration);
            pw.flush();
            pw.close();
            movieStatusLabel.setText("MOVIE : " + movieTitleTF.getText() + " HAS BEEN ADDED");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void changeToMainWindow(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
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
