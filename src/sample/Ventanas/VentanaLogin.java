package sample.Ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaLogin extends Stage {
    public VentanaLogin(){
        this.setTitle("Ventana de Login");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Layouts/login_layout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 800.0, 800.0);
        this.setScene(scene);
        this.show();
    }
}
