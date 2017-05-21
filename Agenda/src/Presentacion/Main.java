/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Datos.Conexion;
import javax.swing.UIManager;

/**
 *
 * @author TheGuru
 */
public class Main{

    public static Conexion hc;
    public static void main(String[] args) {
       try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	} catch (Exception e) {
		e.printStackTrace();
	}
        try {
             hc= new Conexion();
             System.out.println("Conectado!");
             IU_GestionUsuarios g = new IU_GestionUsuarios();
             g.setVisible(true);
             
        } catch (Exception e) {
            System.out.println("error al iniciar: "+e);
        }
        
    }
    
}
