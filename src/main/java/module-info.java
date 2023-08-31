module com.example.movietimetracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.movietimetracker to javafx.fxml;
    exports com.example.movietimetracker;
}