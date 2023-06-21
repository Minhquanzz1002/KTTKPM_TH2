package vn.edu.iuh.receptionistsendermq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReceptionistApplication.class.getResource("receptionist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Nhận bệnh");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}