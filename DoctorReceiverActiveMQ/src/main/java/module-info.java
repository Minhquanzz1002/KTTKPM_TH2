module vn.edu.iuh {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens vn.edu.iuh to javafx.fxml;
    exports vn.edu.iuh;
}