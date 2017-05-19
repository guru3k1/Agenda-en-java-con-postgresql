/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Presentacion.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TheGuru
 */
public class DALUsuario {

    Conexion con = Main.hc;
    cargarDatos c = new cargarDatos();

    public void mostrarLista(DefaultTableModel model, JTable tabla) {

        try {

            String sql = "select id_usuario, dni, nombre,apellido,correo "
                    + "from usuario where estado=1 order by apellido asc";
            ResultSet rs = con.ejecutarSQLSelect(sql);
            c.cargarTabla(5, rs, model, tabla);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar tabla DAL:" + e);
        }

    }

    public void insertarDatos(Usuario u) {
        try {
            String sql = "INSERT INTO usuario(\n"
                    + "            dni, nombre, apellido, correo, telefono, usuario, \n"
                    + "            clave, fecha, foto)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, '"+u.getFecha()+"', ?);";
            PreparedStatement ps= con.con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getUsuario());
            ps.setString(7, u.getClave());
            ps.setBinaryStream(8, u.getFis(),u.getLongitudBytes());
            boolean ejecucion= con.ejecutarSQL(ps);
            if (ejecucion==true) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            }else if(ejecucion==false){
                JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            }
            
        } catch (Exception e) {
            
            System.out.println("error al insertar: " +e);
        }

    }
    
    public void modificarDatossinfoto(Usuario u) {
        try {
            String sql = "";
            PreparedStatement ps= con.con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getUsuario());
            ps.setString(7, u.getClave());
            ps.setBinaryStream(8, u.getFis(),u.getLongitudBytes());
            boolean ejecucion= con.ejecutarSQL(ps);
            if (ejecucion==true) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            }else if(ejecucion==false){
                JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            }
            
        } catch (Exception e) {
            
            System.out.println("error al insertar: " +e);
        }

    }
    public void modificarDatosconfoto(Usuario u) {
        try {
            String sql = "";
            PreparedStatement ps= con.con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getUsuario());
            ps.setString(7, u.getClave());
            ps.setBinaryStream(8, u.getFis(),u.getLongitudBytes());
            boolean ejecucion= con.ejecutarSQL(ps);
            if (ejecucion==true) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            }else if(ejecucion==false){
                JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            }
            
        } catch (Exception e) {
            
            System.out.println("error al insertar: " +e);
        }

    }

}
