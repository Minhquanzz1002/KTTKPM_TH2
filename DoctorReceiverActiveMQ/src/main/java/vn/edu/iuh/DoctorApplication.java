package vn.edu.iuh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DoctorApplication.class.getResource("doctor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bác sĩ khám bệnh");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}