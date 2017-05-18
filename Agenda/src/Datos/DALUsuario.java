/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Presentacion.Main;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TheGuru
 */
public class DALUsuario {
    Conexion con = Main.hc;
    cargarDatos c = new cargarDatos();
    public void mostrarLista(DefaultTableModel model, JTable tabla){
        
        try {
            
            String sql="select id_usuario, dni, nombre,apellido,correo "
                    + "from usuario where estado=1 order by apellido asc";
            ResultSet rs= con.ejecutarSQLSelect(sql);
            c.cargarTabla(5, rs, model, tabla);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar tabla DAL:"+e);
        }
        
    }
}
