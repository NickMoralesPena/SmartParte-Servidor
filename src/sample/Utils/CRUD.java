package sample.Utils;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Boolean;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD {
    Connection conexion = null;

    //Con este metodo realizamos la conexión
    public Connection realizarConexion()throws SQLException {

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/aseguradora", "administrador", "pass");
            System.out.println("Conexion exitosa guay");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }

    //Con este metodo cerramos conexion a la BBDD
    public void close() throws SQLException {
        conexion.close();
    }

    //Este metodo es para exportar un parte a una hoja de excel
    public int excelParte(String propIdParte) throws SQLException{
        int filas = 0;
        File file = new File("DatosExcel.xls");
        //interfaz para la hoja de excel
        WritableSheet excelSheet = null;
        WritableWorkbook workbook = null;
        try{
            workbook = Workbook.createWorkbook(file);
            workbook.createSheet("DatosParte", 0); //nombre de la hoja de excel
            excelSheet = workbook.getSheet(0);

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        String sql = "SELECT * FROM partes WHERE ID_parte = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, propIdParte);
            ResultSet rs = ps.executeQuery();
            System.out.println("Obteniendo datos");
            while (rs.next()){
                Label idParte = new Label(0, filas, rs.getString("ID_parte"));
                Label nombre = new Label (1, filas, rs.getString("nombre"));
                Label fecha = new Label(2, filas, rs.getString("fecha"));
                Boolean proceso = new Boolean(3, filas, rs.getBoolean("procesado"));
                Label matricula = new Label(4, filas, rs.getString("matricula_vehiculo"));
                Label dni = new Label(5, filas, rs.getString("DNI_cliete"));
                filas++;
                try{
                    excelSheet.addCell(idParte);
                    excelSheet.addCell(nombre);
                    excelSheet.addCell(fecha);
                    excelSheet.addCell(proceso);
                    excelSheet.addCell(matricula);
                    excelSheet.addCell(dni);
                }catch (WriteException w){
                    System.err.println(w.getMessage());
                }
            }
            rs.close();
        }catch (SQLException s){
            System.err.println(s.getMessage());
        }
        try {
            workbook.write();
            workbook.close();
        }catch (IOException ioException){
            System.err.println(ioException.getMessage());
        }catch (WriteException writeException){
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, writeException);
        }
        return filas;
    }
}
