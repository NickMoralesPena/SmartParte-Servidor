package sample.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Utils.CRUD;
import sample.Utils.Parte;
import sample.Ventanas.VentanaLogin;
import sample.Ventanas.VentanaPrincipal;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPrincipal implements Initializable {
    @FXML
    Button bEExcel, bATabla, bCSesion, nombreUs;
    @FXML
    TableView<Parte> tablaDatos;
    @FXML
    TableColumn<Parte, String> columIdParte;
    @FXML
    TableColumn<Parte, String> columFecha;
    @FXML
    TableColumn<Parte, String> columDNICliente;
    @FXML
    TableColumn<Parte, Boolean> columEstado;

    ObservableList<Parte> observableList = FXCollections.observableArrayList();

    Parte parte;
    private CRUD conexionCRUD = new CRUD();;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarPartes();
        personalizarTextField();
        acciones();


    }

    private void cargarPartes() {
        Connection conexion2 = null;
        try {
             conexion2 = conexionCRUD.realizarConexion();
            ResultSet resultSet = conexion2.createStatement().executeQuery("SELECT partes.ID_parte, partes.fecha, partes.DNI_cliete, partes.procesado FROM partes");

            while(resultSet.next()){
                observableList.add(new Parte(resultSet.getString("ID_parte"), resultSet.getString("Fecha"),resultSet.getString("DNI_cliete"), resultSet.getBoolean("procesado")));
            }
        } catch (SQLException u) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, u);
        }

        columIdParte.setCellValueFactory(new PropertyValueFactory<>("propIdParte"));
        columFecha.setCellValueFactory(new PropertyValueFactory<>("propFecha"));
        columDNICliente.setCellValueFactory(new PropertyValueFactory<>("propDNICliente"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("propProcesado"));

        tablaDatos.setItems(observableList);

    }

    private void personalizarTextField() {
                nombreUs.setText("Sesión de Administrador");
    }

    private void acciones() {
        bCSesion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VentanaLogin ventanaLogin = new VentanaLogin();
                Stage stage = (Stage) bCSesion.getScene().getWindow();
                stage.hide();
            }
        });
        bEExcel.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {//cogemos el id del elemento qeu hemos pulsado y se lo pasamos por parametros al metodoexcelParte()
                    parte = tablaDatos.getSelectionModel().getSelectedItem();
                    conexionCRUD.excelParte(parte.getPropIdParte());


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    Connection conexion = null;
                    try {//Actualizamos el estado del parte
                        conexion = conexionCRUD.realizarConexion();
                        PreparedStatement ps = conexion.prepareStatement("UPDATE partes SET procesado = 1 WHERE ID_parte = ?");

                        ps.setString(1,parte.getPropIdParte());
                        ps.executeUpdate();

                        conexion.close();


                        String command = "powershell.exe Start-Process \"DatosExcel.xls\"";
                        Process powerShellProcess = null;
                        try {//con esto la hoja de excel se abre automaticamente al pulsar exportar
                            powerShellProcess = Runtime.getRuntime().exec(command);
                            powerShellProcess.getOutputStream().close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }));
        bATabla.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                Stage stage = (Stage) bEExcel.getScene().getWindow();
                stage.hide();
            }
        });
    }
}
