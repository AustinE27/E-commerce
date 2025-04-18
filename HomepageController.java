/**
 * Sample Skeleton for 'Homepage.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class homepageMain {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="helloLabel"
    private Label helloLabel; // Value injected by FXMLLoader

    @FXML // fx:id="homeButton"
    private Button homeButton; // Value injected by FXMLLoader

    @FXML // fx:id="productsButton"
    private Button productsButton; // Value injected by FXMLLoader

    @FXML // fx:id="purchasesButton"
    private Button purchasesButton; // Value injected by FXMLLoader

    @FXML // fx:id="signinButton"
    private Button signinButton; // Value injected by FXMLLoader

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void products(ActionEvent event) {

    }

    @FXML
    void purchases(ActionEvent event) {

    }

    @FXML
    void signin(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert helloLabel != null : "fx:id=\"helloLabel\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert productsButton != null : "fx:id=\"productsButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert purchasesButton != null : "fx:id=\"purchasesButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert signinButton != null : "fx:id=\"signinButton\" was not injected: check your FXML file 'Homepage.fxml'.";

    }

}
