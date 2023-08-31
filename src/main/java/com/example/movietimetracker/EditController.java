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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    ListView<String> movieListView;
    ArrayList<String> movies = new ArrayList<>();
    ArrayList<Integer> seasons = new ArrayList<>();
    ArrayList<Integer> episodes = new ArrayList<>();
    ArrayList<String> durations = new ArrayList<>();

    @FXML
    Label oldMovieTitle;
    @FXML
    Label oldMovieSeason;
    @FXML
    Label oldMovieEpisode;
    @FXML
    Label oldMovieDuration;


    @FXML
    TextField newMovieSeason;
    @FXML
    TextField newMovieEpisode;

    @FXML
    TextField newMovieDurationHour;
    @FXML
    TextField newMovieDurationMin;
    @FXML
    TextField newMovieDurationSec;

    @FXML
    Button updateButton;

    @FXML
    Button addButtonSeason;
    @FXML
    Button subButtonSeason;

    @FXML
    Button addButtonEpisode;
    @FXML
    Button subButtonEpisode;

    @FXML
    Button addButtonDuration;
    @FXML
    Button subButtonDuration;

    @FXML
    Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // sets the actions for each of the buttons on screen
        backButton.setOnAction(this::changeToMainWindow);
        updateButton.setOnAction(this::updateMovie);

        addButtonSeason.setOnAction(e ->{
            newMovieSeason.setText(String.valueOf(Integer.parseInt(newMovieSeason.getText()) + 1));
        });

        subButtonSeason.setOnAction(e ->{
            if(Integer.parseInt(newMovieSeason.getText()) != 0)
                newMovieSeason.setText(String.valueOf(Integer.parseInt(newMovieSeason.getText()) - 1));
        });

        addButtonEpisode.setOnAction(e ->{
            newMovieEpisode.setText(String.valueOf(Integer.parseInt(newMovieEpisode.getText()) + 1));
        });

        subButtonEpisode.setOnAction(e ->{
            if(Integer.parseInt(newMovieEpisode.getText()) != 0)
                newMovieEpisode.setText(String.valueOf(Integer.parseInt(newMovieEpisode.getText()) - 1));
        });

        addButtonDuration.setOnAction(e ->{
            newMovieDurationMin.setText(String.valueOf(Integer.parseInt(newMovieDurationMin.getText()) + 10));
        });

        subButtonDuration.setOnAction(e ->{
            if((Integer.parseInt(newMovieDurationMin.getText()) - 10) >= 0)
                newMovieDurationMin.setText(String.valueOf(Integer.parseInt(newMovieDurationMin.getText()) - 10));
        });


        // begins the file reading
        try(FileReader movieFile = new FileReader("movies.txt")){
            BufferedReader br = new BufferedReader(movieFile);
            String line;
            while((line = br.readLine()) != null){
                // splits file based on ::
                String[] a = line.split("::");
                movies.add(a[0]);
                seasons.add(Integer.valueOf(a[1]));
                episodes.add(Integer.valueOf(a[2]));
                durations.add(a[3]);

            }
            movieListView.getItems().addAll(movies);

            movieListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
                oldMovieTitle.setText(movieListView.getSelectionModel().getSelectedItem());
                for(int i = 0; i < movies.size(); i++){
                    if(Objects.equals(movieListView.getSelectionModel().getSelectedItem(), movies.get(i))){
                        oldMovieSeason.setText(String.valueOf(seasons.get(i)));
                        oldMovieEpisode.setText(String.valueOf(episodes.get(i)));
                        oldMovieDuration.setText(durations.get(i));

                        newMovieSeason.setText(oldMovieSeason.getText());
                        newMovieEpisode.setText(oldMovieEpisode.getText());


                        String duration = oldMovieDuration.getText();
                        String[] t = duration.split(":");
                        newMovieDurationHour.setText(t[0]);
                        newMovieDurationMin.setText(t[1]);
                        newMovieDurationSec.setText(t[2]);

                    }
                }
            });



    } catch (IOException e) {
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


    public void updateMovie(ActionEvent event){
        try{


            String oldValue = "";
            String newValue = oldMovieTitle.getText() + "::" + newMovieSeason.getText() + "::" + newMovieEpisode.getText() + "::" + newMovieDurationHour.getText() + ":" + newMovieDurationMin.getText() + ":" + newMovieDurationSec.getText();
            BufferedReader reader = new BufferedReader(new FileReader("movies.txt"));
            String currentReadingLine = reader.readLine();
            String originalFileContent = "";
            while(currentReadingLine != null){
                originalFileContent += currentReadingLine;
                String[] a = currentReadingLine.split("::");
                if(Objects.equals(oldMovieTitle.getText(), a[0])){
                    oldValue = a[0] + "::" + a[1] + "::" + a[2] + "::" + a[3];
                }

                currentReadingLine = reader.readLine();
            }
            String modifiedFileContent = originalFileContent.replaceAll(oldValue, newValue);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("movies.txt")));
            writer.println(modifiedFileContent);
            writer.flush();
            writer.close();
            
            changeToMainWindow(event);


            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
