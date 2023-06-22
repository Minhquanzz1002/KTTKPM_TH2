module vn.edu.iuh.doctorreceivermq {
    requires javafx.controls;
    requires javafx.fxml;
    requires activemq.all;
    requires jakarta.xml.bind;
    requires java.naming;

    opens vn.edu.iuh.doctorreceivermq to javafx.fxml;
    opens vn.edu.iuh.doctorreceivermq.data to jakarta.xml.bind;
    exports vn.edu.iuh.doctorreceivermq;
}