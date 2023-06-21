package vn.edu.iuh.doctorreceivermq;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    @FXML
    private Button btnCall;

    @FXML
    private Button btnUpdateInfo;

    @FXML
    private ListView<?> lst;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCMND;

    @FXML
    private TextArea txtContent;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
