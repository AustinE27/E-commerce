/**
    Main for Cart/AddToCart.
    Launches GUI and has test Product.
    @author J. Hernandez-Velazquez
    @version 1.0
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/cart.fxml");
        System.out.println("FXML Location: " + fxmlLocation);
        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Ecommerce Site");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}