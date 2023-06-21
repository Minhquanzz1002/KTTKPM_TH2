module vn.edu.iuh.receptionistsendermq {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires activemq.all;
    requires jakarta.xml.bind;
    requires java.naming;


    opens vn.edu.iuh.receptionistsendermq to javafx.fxml;
    exports vn.edu.iuh.receptionistsendermq;
    exports vn.edu.iuh.receptionistsendermq.data;
    opens vn.edu.iuh.receptionistsendermq.data to jakarta.xml.bind;
}