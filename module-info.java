module com.example.commerceproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires jakarta.mail;
    requires jakarta.activation;


    opens com.example.commerceproj to javafx.fxml;
    exports com.example.commerceproj;
}