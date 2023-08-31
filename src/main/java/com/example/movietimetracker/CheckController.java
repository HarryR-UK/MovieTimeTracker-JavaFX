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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CheckController implements Initializable {
    @FXML
    ListView<String> movieListView;
    ArrayList<String> movies = new ArrayList<>();
    ArrayList<Integer> seasons = new ArrayList<>();
    ArrayList<Integer> episodes = new ArrayList<>();
    ArrayList<String> durations = new ArrayList<>();

    @FXML
    Label movieTitleLabel;
    @FXML
    Label seasonLabel;
    @FXML
    Label episodeLabel;
    @FXML
    Label durationLabel;

    @FXML
    Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backButton.setOnAction(this::changeToMainWindow);

        try(FileReader movieFile = new FileReader("movies.txt")){
            BufferedReader br = new BufferedReader(movieFile);
            String line;
            while((line = br.readLine()) != null){
                String[] a = line.split("::");
                movies.add(a[0]);
                seasons.add(Integer.valueOf(a[1]));
                episodes.add(Integer.valueOf(a[2]));
                durations.add(a[3]);

            }
            movieListView.getItems().addAll(movies);

            movieListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
                movieTitleLabel.setText(movieListView.getSelectionModel().getSelectedItem());
                for(int i = 0; i < movies.size(); i++){
                    if(Objects.equals(movieListView.getSelectionModel().getSelectedItem(), movies.get(i))){
                        seasonLabel.setText(String.valueOf(seasons.get(i)));
                        episodeLabel.setText(String.valueOf(episodes.get(i)));
                        durationLabel.setText(durations.get(i));
                    }
                }
            });



        }catch(Exception e){e.printStackTrace();}
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
