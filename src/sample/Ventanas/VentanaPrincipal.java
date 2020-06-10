package sample.Ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipal extends Stage {
    public VentanaPrincipal(){
        this.setTitle("Smart Part Servidor");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Layouts/principal_layout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 800.0, 800.0);
        this.setScene(scene);
        this.show();
    }
}
