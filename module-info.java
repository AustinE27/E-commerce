module com.example.ecommercestoreprojecttemp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.mail;


    opens com.example.ecommercestoreprojecttemp to javafx.fxml;
    exports com.example.ecommercestoreprojecttemp;
}