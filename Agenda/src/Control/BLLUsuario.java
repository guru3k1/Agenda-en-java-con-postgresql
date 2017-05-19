/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Datos.DALUsuario;
import Datos.Usuario;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TheGuru
 */
public class BLLUsuario {
    
    DALUsuario dal = new DALUsuario();
    
    public void mostrarLista(DefaultTableModel model, JTable tabla){
        
        dal.mostrarLista(model, tabla);
    }
    
public void insertarDatos(Usuario u){
    dal.insertarDatos(u);
}
}
