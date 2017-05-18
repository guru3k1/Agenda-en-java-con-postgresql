/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Datos.Conexion;

/**
 *
 * @author TheGuru
 */
public class Main{

    public static Conexion hc;
    public static void main(String[] args) {
       
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
