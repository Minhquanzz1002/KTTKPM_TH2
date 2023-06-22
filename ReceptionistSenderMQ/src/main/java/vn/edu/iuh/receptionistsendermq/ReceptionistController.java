package vn.edu.iuh.receptionistsendermq;

import jakarta.xml.bind.JAXBException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.BasicConfigurator;
import vn.edu.iuh.receptionistsendermq.data.Patient;
import vn.edu.iuh.receptionistsendermq.helper.XMLConvert;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ReceptionistController {

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

    public void sendToDoctor() throws NamingException, JMSException, JAXBException {
        long id = Long.parseLong(txtId.getText());
        String identityCardId = txtIdentityCardId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Patient patient = new Patient(id, identityCardId, name, address);
        System.out.println(patient.toString());

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
        String xml = new XMLConvert<Patient>(patient).objectToXML(patient);
        Message message = session.createTextMessage(xml);
        producer.send(message);

        session.close();
        connection.close();
        System.out.println("Finished");
    }
}
