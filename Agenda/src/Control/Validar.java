/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;


public class Validar 
{
    public void validarSoloLetras (JTextField campo)
    {
        campo.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
            char c = e.getKeyChar();
            int k = (int)e.getKeyChar();
           if (Character.isDigit(c) || k<65 || k>122|| k==91 || k==92 || k==93 
                   || k==94 || k==95 || k==96 ) 
            {
                e.consume();
            }
            }
        });
    }
    
    public void validarSoloNumeros (JTextField campo)
    {
        campo.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
            char c = e.getKeyChar();
            
            if (!Character.isDigit(c)) 
            {
                e.consume();
            }
            }
        });
    }
    
        public void limitarCaracteres (JTextField campo, int cantidadTxt)
    {
        campo.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
            char c = e.getKeyChar();
            int  cantidad = campo.getText().length();
            
            if (cantidad>=cantidadTxt) 
            {
                e.consume();
            }
            }
        });
    }
    

    
}
