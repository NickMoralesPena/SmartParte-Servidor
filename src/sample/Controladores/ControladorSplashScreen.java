package sample.Controladores;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Ventanas.VentanaLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorSplashScreen implements Initializable {
    @FXML
    ImageView imagen_splash_screen;
    Task tareaSecundaria;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        FadeTransition transicion = new FadeTransition(Duration.seconds(1), imagen_splash_screen);
        transicion.setFromValue(0.8);
        transicion.setToValue(1.0);
        transicion.play();

        transicion.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(tareaSecundaria).start();
            }
        });

        tareaSecundaria.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                VentanaLogin ventanaLogin = new VentanaLogin();
                Stage stage = (Stage) imagen_splash_screen.getScene().getWindow();
                stage.hide();
            }
        });


    }
    private void instancias (){
        tareaSecundaria = new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i=0;i<30;i++){
                    Thread.sleep(30);
                }
                return null;
            }
        };
    }
}
