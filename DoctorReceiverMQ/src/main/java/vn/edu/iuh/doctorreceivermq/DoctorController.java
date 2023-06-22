package vn.edu.iuh.doctorreceivermq;

import jakarta.xml.bind.JAXBException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.BasicConfigurator;
import vn.edu.iuh.doctorreceivermq.data.Patient;
import vn.edu.iuh.doctorreceivermq.helper.XMLConvert;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class DoctorController implements Initializable{

    @FXML
    private Button btnCall;

    @FXML
    private Button btnUpdateInfo;

    @FXML
    private ListView<Patient> lst;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                Connection connection =factory.createConnection("admin","admin");
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageConsumer receiver = session.createConsumer(destination);
                System.out.println("toi day");
                receiver.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        try {
                            System.out.println("toi day 2");
                            if (message instanceof TextMessage) {
                                TextMessage tm = (TextMessage) message;
                                String txt = tm.getText();
                                Patient patient = new Patient();
                                patient = new XMLConvert<Patient>(patient).xmlToObject(txt);
                                System.out.println(patient.toString());

//                            lst.getItems().add(new Patient(1, "1", "quan", "1"));
//                            lst.getItems().add("1");
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

                        } catch (JMSException e) {
                            throw new RuntimeException(e);
                        } catch (JAXBException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            } catch (NamingException e) {
                throw new RuntimeException(e);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }


    }
}
