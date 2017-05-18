/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion implements Serializable{
    
    private Connection con=null;
    
    public Conexion() throws ClassNotFoundException{
        con= Conexion.realizaConexion();
    }

   
    public Connection getCon() {
        return con;
    }
    
    public static Connection realizaConexion() throws ClassNotFoundException{
        Connection c=null;
        
    try{
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Agendasql", "postgres", "root");
        System.out.println("Conectado correctamente!");
    }catch(SQLException e){
        System.out.println("error: "+e);
    }catch(ClassNotFoundException ex){
        System.out.println("error 2: "+ex);
    }
        return c;
    }
    
    public boolean ejecutarSQL (PreparedStatement sentencia){
        try{
            sentencia.execute();
            sentencia.close();
            return true;
        }catch(Exception e){
            System.out.println("error al ejecutar " + e);
            return false;
        }
        
    }
    
    public ResultSet ejecutarSQLSelect (String sql) {
    
        ResultSet resultado;
        try{
            PreparedStatement sentencia = con.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            return resultado;
        }catch(SQLException e){
            System.out.println("error al ejecutar la consulta: "+e);
            return null;
        }
        
    
    
    } 
}
