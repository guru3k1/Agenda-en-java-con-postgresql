/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Control.BLLUsuario;
import java.util.regex.Pattern;
import Control.ConvertirMayusculas;
import Control.Validar;
import Datos.Usuario;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TheGuru
 */
public class IU_GestionUsuarios extends javax.swing.JFrame {

    Validar v = new Validar();
    DefaultTableModel modelo_tabla;
    BLLUsuario bll = new BLLUsuario();
    FileInputStream fis;
    int longitudBytes, apretafoto=0;
    boolean consultar = false;
    Usuario u = new Usuario();
    
    public IU_GestionUsuarios() {
        initComponents();
        metodosdeInicio();
        modelo_tabla = new DefaultTableModel(){
            public boolean isCellEditable (int fila, int columna){
                return false;
            }  
        };
        tbldatos.setModel(modelo_tabla);
        modelo_tabla.addColumn("id");
        modelo_tabla.addColumn("DNI");
        modelo_tabla.addColumn("Nombre");
        modelo_tabla.addColumn("Apellido");
        modelo_tabla.addColumn("Correo");
        bll.mostrarLista(modelo_tabla, tbldatos);
        tbldatos.getTableHeader().setReorderingAllowed(false);
        tbldatos.getColumnModel().getColumn(0).setMaxWidth(0);
        tbldatos.getColumnModel().getColumn(0).setMinWidth(0);
        tbldatos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbldatos.getColumnModel().getColumn(1).setMaxWidth(100);
    }
    
    public final void metodosdeInicio(){
        v.validarSoloNumeros(txtdni);
        v.validarSoloLetras(txtnombre);
        v.validarSoloLetras(txtapellido);
        v.limitarCaracteres(txtdni, 8);
        v.limitarCaracteres(txtnombre, 100);
        v.limitarCaracteres(txtapellido, 100);
        v.limitarCaracteres(txtcorreo, 100);
        v.limitarCaracteres(txttelefono, 20);
        v.limitarCaracteres(txtusuario, 20);
        v.limitarCaracteres(txtpassword, 50);
        txtnombre.setDocument(new ConvertirMayusculas());
        txtapellido.setDocument(new ConvertirMayusculas());
        txtbuscar.setDocument(new ConvertirMayusculas());
        txtusuario.setDocument(new ConvertirMayusculas());
    }

    public boolean validarFormatoCorreo(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);

        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    public void validarIngreso() {

        String dni = txtdni.getText().trim();
        String nom = txtnombre.getText().trim();
        String ape = txtapellido.getText().trim();
        boolean estado = validarFormatoCorreo(txtcorreo.getText());
        String te = txttelefono.getText().trim();
        String usu = txtusuario.getText().trim();
        String contra = txtpassword.getText();
        Date fec = jdatefecha.getDate();
        if (dni.isEmpty() || nom.isEmpty() || ape.isEmpty() || estado == false
                || te.isEmpty() || usu.isEmpty() || contra.isEmpty()
                || fec == null) {

            btnguardar.setEnabled(false);
        } else {
            btnguardar.setEnabled(true);
        }

    }

    public void cargarFoto(){
        
        JFileChooser j = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & PNG"
                ,"jpg","png");
        j.setFileFilter(filtro);
        int estado = j.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            
            try {
                fis = new FileInputStream (j.getSelectedFile());
                this.longitudBytes= (int) j.getSelectedFile().length();
                
                try {
                    lblfoto.setIcon(null);
                    Image icono = ImageIO.read(j.getSelectedFile())
                            .getScaledInstance(lblfoto.getWidth()
                                    , lblfoto.getHeight(), Image.SCALE_DEFAULT);
                    lblfoto.setIcon(new ImageIcon(icono));
                    lblfoto.updateUI();
                    apretafoto =1;
                    System.out.println("Longitud de Bytes:" + longitudBytes);
                    
                } catch (IOException e) {
                    System.out.println("Error al cargar foto IO:" +e);
                }
            } catch (FileNotFoundException e) {
                System.out.println("error al cargar file:" +e);
            }
            
        }
        
    }
    
    public void botonGuardar(){
    
            String dni = txtdni.getText().trim();
            String nombre = txtnombre.getText().trim();
            String apellido = txtapellido.getText().trim();
            String correo = txtcorreo.getText().trim();
            String telefono = txttelefono.getText().trim();
            String usuario = txtusuario.getText().trim();
            String contra = txtpassword.getText();
            Date fecha = jdatefecha.getDate();
            u.setDni(dni);
            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setCorreo(correo);
            u.setTelefono(telefono);
            u.setUsuario(usuario);
            u.setClave(contra);
            u.setFecha(fecha);
            u.setFis(fis);
            u.setLongitudBytes(longitudBytes);
            
            if (consultar == false) {
            
        }
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldatos = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        lblbuscar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblfoto = new javax.swing.JLabel();
        btnfoto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jdatefecha = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbldatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbldatos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 69, 750, 360));

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        jPanel1.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 670, 30));

        lblbuscar.setText("Buscar");
        jPanel1.add(lblbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 30));

        jTabbedPane1.addTab("Lista de Usuarios", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(lblfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 25, 220, 280));

        btnfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Usuario.png"))); // NOI18N
        btnfoto.setText("Subir Foto");
        btnfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfotoActionPerformed(evt);
            }
        });
        jPanel2.add(btnfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 200, 30));

        jLabel4.setText("DNI");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 100, 30));

        txtdni.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtdniCaretUpdate(evt);
            }
        });
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });
        jPanel2.add(txtdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 320, 30));

        txtnombre.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtnombreCaretUpdate(evt);
            }
        });
        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 320, 30));

        jLabel5.setText("Nombres");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 30));

        txtapellido.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtapellidoCaretUpdate(evt);
            }
        });
        jPanel2.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 320, 30));

        jLabel6.setText("Apellidos");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 100, 30));

        txtcorreo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtcorreoCaretUpdate(evt);
            }
        });
        jPanel2.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 320, 30));

        jLabel7.setText("Correo");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 100, 30));

        txttelefono.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttelefonoCaretUpdate(evt);
            }
        });
        jPanel2.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 320, 30));

        jLabel8.setText("Telefono");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 100, 30));

        txtusuario.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtusuarioCaretUpdate(evt);
            }
        });
        jPanel2.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 320, 30));

        jLabel9.setText("Usuario");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 100, 30));

        jLabel10.setText("Fecha de Nacimiento");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 120, 30));

        txtpassword.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtpasswordCaretUpdate(evt);
            }
        });
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 320, 30));

        jdatefecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdatefechaPropertyChange(evt);
            }
        });
        jPanel2.add(jdatefecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 130, 30));

        jLabel11.setText("Contraseña");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 100, 30));

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar.png"))); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.setEnabled(false);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 110, 30));

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        jPanel2.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 110, 30));

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.setEnabled(false);
        jPanel2.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 110, -1));

        jTabbedPane1.addTab("Usuarios", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void btnfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfotoActionPerformed
        cargarFoto();
    }//GEN-LAST:event_btnfotoActionPerformed

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdniActionPerformed

    private void txtdniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtdniCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txtdniCaretUpdate

    private void txtnombreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtnombreCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txtnombreCaretUpdate

    private void txtapellidoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtapellidoCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txtapellidoCaretUpdate

    private void txtcorreoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtcorreoCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txtcorreoCaretUpdate

    private void txttelefonoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttelefonoCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txttelefonoCaretUpdate

    private void txtusuarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtusuarioCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txtusuarioCaretUpdate

    private void txtpasswordCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtpasswordCaretUpdate
        validarIngreso();
    }//GEN-LAST:event_txtpasswordCaretUpdate

    private void jdatefechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdatefechaPropertyChange
        validarIngreso();
    }//GEN-LAST:event_jdatefechaPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IU_GestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IU_GestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IU_GestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IU_GestionUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IU_GestionUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnfoto;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdatefecha;
    private javax.swing.JLabel lblbuscar;
    private javax.swing.JLabel lblfoto;
    private javax.swing.JTable tbldatos;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
