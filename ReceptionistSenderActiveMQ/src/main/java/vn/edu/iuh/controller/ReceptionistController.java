package vn.edu.iuh.controller;

import jakarta.xml.bind.JAXBException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vn.edu.iuh.helper.XMLConvert;
import vn.edu.iuh.models.Patient;
import vn.edu.iuh.services.PatientService;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

@Controller
public class ReceptionistController implements Initializable {
    @Autowired
    private PatientService patientService;
    @FXML
    private Button btnSave;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdentityCardId;

    @FXML
    private TextField txtName;

    @FXML
    public void sendToDoctor() throws NamingException, JMSException, JAXBException {
        long id = Long.parseLong(txtId.getText());
        String identityCardId = txtIdentityCardId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Patient patient = new Patient(id, identityCardId, name, address);
        System.out.println(patient);

        BasicConfigurator.configure();
        Properties settings = new Properties();
        settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
        Context ctx = new InitialContext(settings);

        ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
        Destination destination = (Destination) ctx.lookup("dynamicQueues/thanthidet");
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        String xml = new XMLConvert<>(patient).objectToXML(patient);
        Message message = session.createTextMessage(xml);
        producer.send(message);

        session.close();
        connection.close();
        System.out.println("Finished");
        patientService.save(patient);
    }

    @FXML
    public void keyReleasedProperty() {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String id = txtId.getText();
        String identityCardId = txtIdentityCardId.getText();
        boolean isDisable = (name.isEmpty() || address.isEmpty() || id.isEmpty() || identityCardId.isEmpty());
        btnSave.setDisable(isDisable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
