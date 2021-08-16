/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewGUI;

import javax.swing.table.DefaultTableModel;
import util.JDBCUtilities;
import java.sql.*;

/**
 *
 * @author Usuario
 */
public class VistaRequerimiento1Reto5 extends javax.swing.JFrame {

    DefaultTableModel model;

    /**
     * Creates new form VistaRequerimiento1
     */
    public VistaRequerimiento1Reto5() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Consulta 1");

        //colocar nombre a las columnas
        String[] titulo = {"ID_Proyecto", "Clasificacion", "Gasto_Compras", "Serial"};
        model = new DefaultTableModel(null, titulo);
        jTable1.setModel(model);

        mostrarDatos();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VistaRequerimiento1Reto5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRequerimiento1Reto5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRequerimiento1Reto5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRequerimiento1Reto5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaRequerimiento1Reto5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatos() {

        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        JDBCUtilities objConexion = new JDBCUtilities();

        try {
            ResultSet resultado = objConexion.consultarRegistros("SELECT c.ID_Proyecto, \n"
                    + "p.Clasificacion, \n"
                    + "SUM(c.Cantidad*m.Precio_Unidad) AS Gasto_Compras, \n"
                    + "p.Serial \n"
                    + "FROM Compra c \n"
                    + "JOIN MaterialConstruccion m \n"
                    + "ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion \n"
                    + "JOIN Proyecto p \n"
                    + "ON p.ID_Proyecto = c.ID_Proyecto \n"
                    + "GROUP BY c.ID_Proyecto \n"
                    + "ORDER BY Gasto_Compras \n"
                    + "LIMIT 5 ");
            while (resultado.next()) {
                Object[] datos = {resultado.getString("ID_Proyecto"),
                    resultado.getString("Clasificacion"),
                    resultado.getString("Gasto_Compras"),
                    resultado.getString("Serial")
                };
                model.addRow(datos);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}