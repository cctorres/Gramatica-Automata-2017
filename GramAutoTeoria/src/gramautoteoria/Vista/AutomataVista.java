/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Vista;

import gramautoteoria.Modelo.Automata;
import gramautoteoria.Modelo.Gramatica;
import gramautoteoria.Vista.GramaticaVista;
import static gramautoteoria.Vista.GramaticaVista.gramaticaTexto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cristian.torresa
 */
public class AutomataVista extends javax.swing.JFrame {
    
    String gramaticaTexto = GramaticaVista.gramaticaTexto.getText();
    Gramatica gramaticaObjeto = new Gramatica();
    Automata automata = new Automata();
    private JFileChooser seleccionar = new JFileChooser();
    private File archivo;
    private FileInputStream entrada;
    private FileOutputStream salida;

    /**
     * Creates new form NewJFrame
     */
    public AutomataVista() {
        initComponents();
        
        gramaticaTexto = gramaticaTexto.replace(" ", "");
        gramaticaTexto = gramaticaTexto.replace("\r", "");
        gramaticaObjeto.generarGramaticaFichero(gramaticaTexto);
        automata = gramaticaObjeto.gramaticaAAutomata();
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        automata.imprimirAutomataTabla(modelo);
        
        
        
        
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

        resultadoTexto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        evaluarBoton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        deterministicoBoton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        guardarAutomataBoton = new javax.swing.JButton();
        leerBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Evaluar hilera");

        evaluarBoton.setText("Evaluar");
        evaluarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluarBotonActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar Estados Extraños");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        deterministicoBoton.setText("¿Es deterministico?");
        deterministicoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deterministicoBotonActionPerformed(evt);
            }
        });

        jButton4.setText("Estados Equivalentes");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(Tabla);

        guardarAutomataBoton.setText("Guardar automata");
        guardarAutomataBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAutomataBotonActionPerformed(evt);
            }
        });

        leerBoton.setText("Leer automata");
        leerBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leerBoton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardarAutomataBoton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(deterministicoBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(resultadoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(evaluarBoton))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarAutomataBoton)
                    .addComponent(leerBoton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultadoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(evaluarBoton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deterministicoBoton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void evaluarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evaluarBotonActionPerformed
        String hilera = resultadoTexto.getText();
        if(automata.evaluarHilera(hilera)){
            JOptionPane.showMessageDialog(null, "La hilera es válida");
        }
        else{
            JOptionPane.showMessageDialog(null, "La hilera no es válida");
        }
    }//GEN-LAST:event_evaluarBotonActionPerformed

    private void guardarAutomataBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAutomataBotonActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();            
            if (archivo.getName().endsWith("txt")) {
                String Documento = automata.imprimirAutomata();
                String mensaje = this.guardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha selecciona un archivo txt");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Archivo debe terminar con .txt");
            }
        }
    }//GEN-LAST:event_guardarAutomataBotonActionPerformed

    private void deterministicoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deterministicoBotonActionPerformed
        if(automata.esDeterministico()){
            resultadoTexto.setText("El automata es deterministico");
        }
        else{
            resultadoTexto.setText("El automata no es deterministico");
        }
    }//GEN-LAST:event_deterministicoBotonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Automata nuevo = automata.eliminarEstadosExtraños();
        automata = nuevo;
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        automata.imprimirAutomataTabla(modelo);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void leerBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerBotonActionPerformed
        if(seleccionar.showDialog(null, "Abrir")==JFileChooser.APPROVE_OPTION){
            archivo = seleccionar.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith("txt")){
                    String documento = this.abrirArchivo(archivo);
                    
                    documento = documento.replace(" ", "");
                    documento = documento.replace("\r", "");
                    DefaultTableModel modelo = new DefaultTableModel();
                    Tabla.setModel(modelo);
                    automata = automata.generarAutomataFichero(documento);
                    automata.imprimirAutomataTabla(modelo);
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado un archivo txt");
                }
            }
        }
    }//GEN-LAST:event_leerBotonActionPerformed

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
            java.util.logging.Logger.getLogger(AutomataVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutomataVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutomataVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutomataVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutomataVista().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton deterministicoBoton;
    private javax.swing.JButton evaluarBoton;
    private javax.swing.JButton guardarAutomataBoton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton leerBoton;
    private javax.swing.JTextField resultadoTexto;
    // End of variables declaration//GEN-END:variables
}
