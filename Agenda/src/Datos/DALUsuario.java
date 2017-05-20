/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Presentacion.Main;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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

    public void buscarLista(DefaultTableModel model, JTable tabla, String dato) {

        try {

            String sql = "select id_usuario, dni, nombre,apellido,correo "
                    + "from usuario where estado=1 and(nombre like '%" + dato
                    + "%' or apellido like '%" + dato
                    + "%')order by apellido asc";
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
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, '" + u.getFecha() + "', ?);";
            PreparedStatement ps = con.con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getUsuario());
            ps.setString(7, u.getClave());
            ps.setBinaryStream(8, u.getFis(), u.getLongitudBytes());
            boolean ejecucion = con.ejecutarSQL(ps);
            if (ejecucion == true) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            } else if (ejecucion == false) {
                JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            }

        } catch (Exception e) {

            System.out.println("error al insertar: " + e);
        }

    }

    public void modificarDatossinfoto(Usuario u) {
        try {
            String sql = "UPDATE usuario SET dni=?, nombre=?, apellido=?, correo=?, telefono=?, \n"
                    + "       usuario=?, clave=?, fecha='" + u.getFecha() + "'\n"
                    + " WHERE id_usuario=?;";
            PreparedStatement ps = con.con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getUsuario());
            ps.setString(7, u.getClave());
            //ps.setBinaryStream(8, u.getFis(), u.getLongitudBytes());
            ps.setInt(8, u.getId_usuario());
            boolean ejecucion = con.ejecutarSQL(ps);
            if (ejecucion == true) {
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            } else if (ejecucion == false) {
                JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
            }

        } catch (Exception e) {

            System.out.println("error al insertar: " + e);
        }

    }

    public void modificarDatosconfoto(Usuario u) {
        try {
            String sql = "UPDATE usuario SET dni=?, nombre=?, apellido=?, correo=?, telefono=?, \n"
                    + "       usuario=?, clave=?, foto=? fecha='" + u.getFecha() + "'\n"
                    + " WHERE id_usuario=?;";
            PreparedStatement ps = con.con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getUsuario());
            ps.setString(7, u.getClave());
            ps.setBinaryStream(8, u.getFis(), u.getLongitudBytes());
            ps.setInt(9, u.getId_usuario());
            boolean ejecucion = con.ejecutarSQL(ps);
            if (ejecucion == true) {
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            } else if (ejecucion == false) {
                JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
            }

        } catch (Exception e) {

            System.out.println("error al insertar: " + e);
        }

    }
    public void elimiarUsuario(Usuario u) {
        try {
            String sql = "UPDATE usuario SET estado =0 WHERE id_usuario=?;";
            PreparedStatement ps = con.con.prepareStatement(sql);
            ps.setInt(1, u.getId_usuario());
            boolean ejecucion = con.ejecutarSQL(ps);
            if (ejecucion == true) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            } else if (ejecucion == false) {
                JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
            }

        } catch (Exception e) {

            System.out.println("error al insertar: " + e);
        }

    }

    public Object[] consultarporID(int id, JLabel lblfoto) {
        Object[] datos = new Object[9];
        ImageIcon foto;
        InputStream is;
        try {
            String sql = "SELECT dni, nombre, apellido, correo, telefono, usuario, \n"
                    + "       clave, fecha, foto\n"
                    + "  FROM usuario WHERE id_usuario=?;";
            PreparedStatement ps =con.con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                datos[0] = rs.getString(1);//dni
                datos[1] = rs.getString(2);//nombre
                datos[2] = rs.getString(3);//apellido
                datos[3] = rs.getString(4);//correo
                datos[4] = rs.getString(5);//telefono
                datos[5] = rs.getString(6);//usuario
                datos[6] = rs.getString(7);//clave
                datos[7] = rs.getDate(8);//fecha
                is=rs.getBinaryStream(9);
                BufferedImage bi= ImageIO.read(is);
                foto = new ImageIcon(bi);
                Image img = foto.getImage();
                Image newimg = img.getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), java.awt.Image.SCALE_SMOOTH);
                ImageIcon newicon = new ImageIcon(newimg);
                datos [8]=newicon;
                
            }
        } catch (Exception e) {
            System.out.println("Error al consultar" +e);
        }
        return datos;
    }
    
}
