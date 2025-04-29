/**
 * Sample Skeleton for 'Homepage.fxml' Controller Class
  Author: Danny Carpio-Tovar
 This is the Home Page of the E-commerce program.
 This should incorporate all of the data fields from the
 separate topics and Databases to power the whole program.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class homepageMain {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="add_to_cartButton"
    private Button add_to_cartButton; // Value injected by FXMLLoader

    @FXML // fx:id="cartButton"
    private Button cartButton; // Value injected by FXMLLoader

    @FXML // fx:id="helloLabel"
    private Label helloLabel; // Value injected by FXMLLoader

    @FXML // fx:id="homeButton"
    private Button homeButton; // Value injected by FXMLLoader

    @FXML // fx:id="line"
    private Line line; // Value injected by FXMLLoader

    @FXML // fx:id="price"
    private Label price; // Value injected by FXMLLoader

    @FXML // fx:id="priceShip"
    private Label priceShip; // Value injected by FXMLLoader

    @FXML // fx:id="productImage"
    private ImageView productImage; // Value injected by FXMLLoader

    @FXML // fx:id="productInfo"
    private Label productInfo; // Value injected by FXMLLoader

    @FXML // fx:id="productsButton"
    private Button productsButton; // Value injected by FXMLLoader

    @FXML // fx:id="purchasesButton"
    private Button purchasesButton; // Value injected by FXMLLoader

    @FXML // fx:id="subtitle"
    private Label subtitle; // Value injected by FXMLLoader

    @FXML
    void cart(ActionEvent event) {

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void products(ActionEvent event) {

    }

    @FXML
    void purchases(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert add_to_cartButton != null : "fx:id=\"add_to_cartButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert cartButton != null : "fx:id=\"cartButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert helloLabel != null : "fx:id=\"helloLabel\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert line != null : "fx:id=\"line\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert priceShip != null : "fx:id=\"priceShip\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert productImage != null : "fx:id=\"productImage\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert productInfo != null : "fx:id=\"productInfo\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert productsButton != null : "fx:id=\"productsButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert purchasesButton != null : "fx:id=\"purchasesButton\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert subtitle != null : "fx:id=\"subtitle\" was not injected: check your FXML file 'Homepage.fxml'.";

    }

}
