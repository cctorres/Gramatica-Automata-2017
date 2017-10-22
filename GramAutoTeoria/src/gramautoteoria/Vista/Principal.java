/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Vista;

import gramautoteoria.Modelo.Automata;
import gramautoteoria.Modelo.Gramatica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Principal extends javax.swing.JFrame {
    
    private Automata automata = new Automata();
    private Gramatica gramatica = new Gramatica();
    private JFileChooser seleccionar = new JFileChooser();
    private File archivo;
    private FileInputStream entrada;
    private FileOutputStream salida;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }
    
    public String abrirArchivo(File Archivo) {
        String documento = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;
            }
        } catch (Exception e) {

        }

        return documento;

    }
    
    public String guardarArchivo(File archivo, String documento){
        String mensaje = null;
        try{
            salida = new FileOutputStream(archivo);
            byte [] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje = "Archivo guardado";
        } catch (Exception e){
            
        } return mensaje;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guardarBoton = new javax.swing.JButton();
        leerBoton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        derechoTexto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        izquierdoTexto = new javax.swing.JTextField();
        ingresarBotón = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        gramaticaTexto = new javax.swing.JTextPane();
        simplificarBoton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        NTTexto = new javax.swing.JTextPane();
        NTVivosBoton = new javax.swing.JButton();
        NTMuertosBoton = new javax.swing.JButton();
        NTInalcanzablesBoton = new javax.swing.JButton();
        regularBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        guardarBoton.setText("Guardar archivo");
        guardarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBotonActionPerformed(evt);
            }
        });

        leerBoton.setText("Leer archivo");
        leerBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerBotonActionPerformed(evt);
            }
        });

        jLabel1.setText("Lado derecho");

        jLabel2.setText("Lado izquierdo");

        ingresarBotón.setText("Ingresar producción");
        ingresarBotón.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBotónActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(gramaticaTexto);

        simplificarBoton.setText("Simplificar");
        simplificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simplificarBotonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(NTTexto);

        NTVivosBoton.setText("NT Vivos");
        NTVivosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTVivosBotonActionPerformed(evt);
            }
        });

        NTMuertosBoton.setText("NT Muertos");
        NTMuertosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTMuertosBotonActionPerformed(evt);
            }
        });

        NTInalcanzablesBoton.setText("NT Inalcanzables");
        NTInalcanzablesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTInalcanzablesBotonActionPerformed(evt);
            }
        });

        regularBoton.setText("¿Es regular?");
        regularBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regularBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(leerBoton)
                                    .addComponent(derechoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ingresarBotón, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(guardarBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(simplificarBoton)
                        .addGap(18, 18, 18)
                        .addComponent(NTVivosBoton)
                        .addGap(18, 18, 18)
                        .addComponent(NTMuertosBoton)
                        .addGap(18, 18, 18)
                        .addComponent(NTInalcanzablesBoton)
                        .addGap(18, 18, 18)
                        .addComponent(regularBoton))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(izquierdoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(490, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBoton)
                    .addComponent(leerBoton))
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingresarBotón)
                    .addComponent(derechoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simplificarBoton)
                    .addComponent(NTVivosBoton)
                    .addComponent(NTMuertosBoton)
                    .addComponent(NTInalcanzablesBoton)
                    .addComponent(regularBoton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(izquierdoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(367, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void leerBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerBotonActionPerformed
        if(seleccionar.showDialog(null, "Abrir")==JFileChooser.APPROVE_OPTION){
            archivo = seleccionar.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith("txt")){
                    String documento = this.abrirArchivo(archivo);
                    gramaticaTexto.setText(documento);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado un archivo txt");
                }
            }
        }
    }//GEN-LAST:event_leerBotonActionPerformed

    private void guardarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBotonActionPerformed
        if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.getName().endsWith("txt")) {
                String Documento = gramaticaTexto.getText();
                String mensaje = this.guardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha selecciona un archivo txt");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Archivo guardado con éxito");
            }
        }
    }//GEN-LAST:event_guardarBotonActionPerformed

    private void simplificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simplificarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simplificarBotonActionPerformed

    private void ingresarBotónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBotónActionPerformed
        String izquierdo = izquierdoTexto.getText();
        izquierdo = izquierdo.replace(" ", "");
        String derecho = derechoTexto.getText();
        derecho = derecho.replace(" ", "");
        if(izquierdo.equals("") | derecho.equals("")){
            JOptionPane.showMessageDialog(null, "Por favor, rellenar los campos de izquierdo y derecho");
            return;
        }
        if(!izquierdo.substring(0,1).equals("<") | !izquierdo.substring(izquierdo.length()-1).equals(">")){
            JOptionPane.showMessageDialog(null, "El lado izquierdo debe ser de la forma <nombre>");
            return;
        }
        gramatica.agregarProduccion(izquierdo, derecho);
        gramaticaTexto.setText(gramatica.imprimirGramatica());
    }//GEN-LAST:event_ingresarBotónActionPerformed

    private void NTVivosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTVivosBotonActionPerformed
        if(gramaticaTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No se ha ingresado un automata a evaluar");
            return;
        }
        ArrayList<String> ntVivos = gramatica.noTerminalesVivos();
        NTTexto.setText(ntVivos.toString());
    }//GEN-LAST:event_NTVivosBotonActionPerformed

    private void NTMuertosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTMuertosBotonActionPerformed
        if(gramaticaTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No se ha ingresado un automata a evaluar");
            return;
        }
        ArrayList<String> ntMuertos = gramatica.noTerminalesMuertos();
        NTTexto.setText(ntMuertos.toString());
    }//GEN-LAST:event_NTMuertosBotonActionPerformed

    private void NTInalcanzablesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTInalcanzablesBotonActionPerformed
        if(gramaticaTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No se ha ingresado un automata a evaluar");
            return;
        }
        ArrayList<String> ntInalcanzables = gramatica.noTerminalesInalcanzables();
        NTTexto.setText(ntInalcanzables.toString());
    }//GEN-LAST:event_NTInalcanzablesBotonActionPerformed

    private void regularBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regularBotonActionPerformed
        if(gramaticaTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No se ha ingresado un automata a evaluar");
            return;
        }
        if(gramatica.esRegular()){
            NTTexto.setText("La gramática es regular");
        }
        else{
            NTTexto.setText("La gramática no es regular");
        }
    }//GEN-LAST:event_regularBotonActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NTInalcanzablesBoton;
    private javax.swing.JButton NTMuertosBoton;
    private javax.swing.JTextPane NTTexto;
    private javax.swing.JButton NTVivosBoton;
    private javax.swing.JTextField derechoTexto;
    private javax.swing.JTextPane gramaticaTexto;
    private javax.swing.JButton guardarBoton;
    private javax.swing.JButton ingresarBotón;
    private javax.swing.JTextField izquierdoTexto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton leerBoton;
    private javax.swing.JButton regularBoton;
    private javax.swing.JButton simplificarBoton;
    // End of variables declaration//GEN-END:variables
}