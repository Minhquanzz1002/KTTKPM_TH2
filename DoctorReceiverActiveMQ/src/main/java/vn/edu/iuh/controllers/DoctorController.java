package vn.edu.iuh.controllers;

import jakarta.xml.bind.JAXBException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.log4j.BasicConfigurator;
import org.springframework.stereotype.Controller;
import vn.edu.iuh.models.Doctor;
import vn.edu.iuh.models.Examine;
import vn.edu.iuh.models.Patient;
import vn.edu.iuh.helper.XMLConvert;
import vn.edu.iuh.services.ExamineService;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

@Controller
public class DoctorController implements Initializable {
    private ExamineService examineService;

    public DoctorController(ExamineService examineService) {
        this.examineService = examineService;
    }

    @FXML
    private Button btnCall;

    @FXML
    private Button btnUpdateInfo;

    @FXML
    private ListView<Patient> lst;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtIdentityCardId;

    @FXML
    private TextArea txtContent;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private Patient currentPatient;

    @FXML
    public void handleCallPatient() {
        Patient patient = lst.getSelectionModel().getSelectedItem();
        if (patient == null) {
            return;
        }
        txtId.setText(patient.getId() + "");
        txtIdentityCardId.setText(patient.getIdentityCardId());
        txtAddress.setText(patient.getAddress());
        txtName.setText(patient.getName());
        currentPatient = patient;
        lst.getItems().remove(patient);
    }

    @FXML
    public void handleSaveInfoExamines() {
        if (currentPatient == null) {
            alert("Lỗi", null, "Hãy chọn bệnh nhân trước!!!", Alert.AlertType.ERROR);
            return;
        }
        Doctor doctor = new Doctor(1, "Steve Harvey");
        String note = txtContent.getText();
        Examine examine = new Examine(doctor, currentPatient, new Date(), note);
        examineService.save(examine);
        clearInput();
        btnUpdateInfo.setDisable(true);
    }

    public void alert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void keyReleasedProperty() {
        String note = txtContent.getText().trim();
        boolean isDisable = note.isEmpty();
        btnUpdateInfo.setDisable(isDisable);
    }

    public void clearInput() {
        txtName.setText("");
        txtContent.setText("");
        txtAddress.setText("");
        txtId.setText("");
        txtIdentityCardId.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUpdateInfo.setDisable(true);
        lst.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        XMLConvert<Patient> xmlConvert = new XMLConvert<Patient>(new Patient());
        BasicConfigurator.configure();
        Properties settings = new Properties();
        settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        try {
            Context ctx = new InitialContext(settings);
            Object obj = ctx.lookup("ConnectionFactory");
            ConnectionFactory factory = (ConnectionFactory) obj;
            Destination destination = (Destination) ctx.lookup("dynamicQueues/thanthidet");
            Connection connection = factory.createConnection("admin", "admin");
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer receiver = session.createConsumer(destination);
            receiver.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage tm = (TextMessage) message;
                            String txt = tm.getText();
                            Patient patient = new Patient();
                            patient = new XMLConvert<Patient>(patient).xmlToObject(txt);
                            System.out.println(patient.toString());

                            lst.refresh();
                            Patient finalPatient = patient;
                            Platform.runLater(() -> {
                                lst.getItems().add(finalPatient);

                            });
                            message.acknowledge();
                        } else if (message instanceof ObjectMessage) {
                            ObjectMessage om = (ObjectMessage) message;
                            System.out.println(om);
                        }

                    } catch (JMSException | JAXBException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (NamingException | JMSException e) {
            throw new RuntimeException(e);
        }


    }
}
