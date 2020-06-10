package sample.Utils;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parte {

    String idParte, nombre, fecha, matriculaVehiculo;
    Boolean procesado;
    String dniCliente;
    SimpleStringProperty propIdParte, propFecha, propDNICliente;
    SimpleBooleanProperty propProcesado;

    public Parte(String idParte, String nombre, String fecha, String matriculaVehiculo, Boolean procesado, String dniCliente) {
        this.idParte = idParte;
        this.propIdParte = new SimpleStringProperty(idParte);
        this.nombre = nombre;
        this.fecha = fecha;
        this.propFecha = new SimpleStringProperty(fecha);
        this.matriculaVehiculo = matriculaVehiculo;
        this.procesado = procesado;
        this.propProcesado = new SimpleBooleanProperty(procesado);
        this.dniCliente = dniCliente;
        this.propDNICliente = new SimpleStringProperty(dniCliente);

    }

    public Parte(String idParte, String fecha, String dniCliente, Boolean procesado) {
        this.idParte = idParte;
        propIdParte = new SimpleStringProperty(idParte);
        this.fecha = fecha;
        propFecha = new SimpleStringProperty(fecha);
        this.dniCliente = dniCliente;
        propDNICliente = new SimpleStringProperty(dniCliente);
        this.procesado = procesado;
        propProcesado = new SimpleBooleanProperty(procesado);
    }

    public String getIdParte() {
        return idParte;
    }

    public void setIdParte(String idParte) {
        this.idParte = idParte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public Boolean getProcesado() {
        return procesado;
    }

    public void setProcesado(Boolean procesado) {
        this.procesado = procesado;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getPropIdParte() {
        return propIdParte.get();
    }

    public SimpleStringProperty propIdParteProperty() {
        return propIdParte;
    }

    public void setPropIdParte(String propIdParte) {
        this.propIdParte.set(propIdParte);
    }

    public String getPropFecha() {
        return propFecha.get();
    }

    public SimpleStringProperty propFechaProperty() {
        return propFecha;
    }

    public void setPropFecha(String propFecha) {
        this.propFecha.set(propFecha);
    }

    public String getPropDNICliente() {
        return propDNICliente.get();
    }

    public SimpleStringProperty propDNIClienteProperty() {
        return propDNICliente;
    }

    public void setPropDNICliente(String propDNICliente) {
        this.propDNICliente.set(propDNICliente);
    }

    public boolean isPropProcesado() {
        return propProcesado.get();
    }

    public SimpleBooleanProperty propProcesadoProperty() {
        return propProcesado;
    }

    public void setPropProcesado(boolean propProcesado) {
        this.propProcesado.set(propProcesado);
    }
}
