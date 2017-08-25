/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yazlab.maze.demo;

import com.yazlab.maze.Matrix;
import com.yazlab.maze.QLearning;
import com.yazlab.maze.MazePanel;
import com.yazlab.maze.QLearnAlgorithmThread;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author ACER
 */
public class FrmDemo extends javax.swing.JFrame {

    private MazePanel maze;
    private QLearning learning;

    /**
     * Creates new form FrmDemo
     */
    public FrmDemo() {
        initComponents();
        maze = (MazePanel) pnlMaze;
        learning = new QLearning();
        learning.setHomeDir("C:\\Dosyalarim\\Maze");
        maze.setLearing(learning);

    }

    public void reInit(boolean solve) {
        try {

            int startNode = 0;
            int targetNode = 0;
            int iteration = 0;
            int matrixSize = 0;
            int nodeSize = 0;

            startNode = Integer.parseInt(cmbStartNode.getSelectedItem().toString().trim());
            targetNode = Integer.parseInt(cmbTargetNode.getSelectedItem().toString().trim());
            matrixSize = Integer.parseInt(cmbMatrixSize.getSelectedItem().toString().trim());
            nodeSize = Integer.parseInt(cmbNodeSize.getSelectedItem().toString().trim());
            iteration = Integer.parseInt(txtIteration.getText().trim());

            learning.setMatrixSize(matrixSize);
            learning.setStartNode(startNode);
            learning.setTargetNode(targetNode);
            learning.setIteration(iteration);
            learning.setLearningRate(0.8F);

            maze.setNodeSize((float) nodeSize);

            if (!solve) {
                return;
            }

            QLearnAlgorithmThread algorithmThread = new QLearnAlgorithmThread(learning) {

                @Override
                public void onStarted() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            cmbMatrixSize.setEnabled(false);
                            cmbNodeSize.setEnabled(false);
                            cmbStartNode.setEnabled(false);
                            cmbTargetNode.setEnabled(false);
                            txtIteration.setEnabled(false);
                            btnDrawMaze.setEnabled(false);
                            btnSovleMaze.setEnabled(false);
                            btnClear.setEnabled(false);

                        }
                    });
                }

                @Override
                public void onIterationDone(int state, int iteration, int percent) {

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            pbOperation.setValue(percent);
                            lblOperation.setText(percent + "%");
                        }
                    });

                }

                @Override
                public void onCompleted(final Matrix<Float> RMatrix, final Matrix<Float> QMatrix, final ArrayList<Integer> solitionPath) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            cmbMatrixSize.setEnabled(true);
                            cmbNodeSize.setEnabled(true);
                            cmbStartNode.setEnabled(true);
                            cmbTargetNode.setEnabled(true);
                            txtIteration.setEnabled(true);
                            btnDrawMaze.setEnabled(true);
                            btnSovleMaze.setEnabled(true);
                            btnClear.setEnabled(true);

                            if (solitionPath == null) {
                                JOptionPane.showMessageDialog(null, "Çözüm Bulunamadı.Lütfen, iterasyon sayısını artırın.");
                                return;
                            }

                            maze.solveMaze();

                            pbOperation.setValue(100);
                            lblOperation.setText(100 + "%");
                            System.out.println("R Matrix");
                            System.out.println(RMatrix.toString());

                            System.out.println("Q Matrix");
                            System.out.println(RMatrix.toString());

                            System.out.println("Path");

                            for (int i = 0; i < solitionPath.size(); i++) {
                                System.out.print(solitionPath.get(i) + "  ");

                            }

                        }
                    });

                }

            };
            algorithmThread.start();

        } catch (Exception e) {
            e.printStackTrace();
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

        pnlUp = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIteration = new javax.swing.JTextField();
        btnDrawMaze = new javax.swing.JButton();
        btnSovleMaze = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        pbOperation = new javax.swing.JProgressBar();
        lblOperation = new javax.swing.JLabel();
        cmbStartNode = new javax.swing.JComboBox<>();
        cmbTargetNode = new javax.swing.JComboBox<>();
        lblMatrixSize = new javax.swing.JLabel();
        cmbNodeSize = new javax.swing.JComboBox<>();
        lblMatrixSize1 = new javax.swing.JLabel();
        cmbMatrixSize = new javax.swing.JComboBox<>();
        pnlMaze = new com.yazlab.maze.MazePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Başlangıç Düğümü");

        jLabel2.setText("Hedef Düğüm");

        jLabel3.setText("İterasyon Sayısı");

        txtIteration.setText("3000");

        btnDrawMaze.setText("Labirenti Çizdir");
        btnDrawMaze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawMazeActionPerformed(evt);
            }
        });

        btnSovleMaze.setText("Labirenti Çöz");
        btnSovleMaze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSovleMazeActionPerformed(evt);
            }
        });

        btnClear.setText("Temizle");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        pbOperation.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblOperation.setText("0%");

        cmbStartNode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));

        cmbTargetNode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3" }));

        lblMatrixSize.setText("KareBoyutu");

        cmbNodeSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "50", "60", "70", "80", "90", "100" }));
        cmbNodeSize.setToolTipText("");

        lblMatrixSize1.setText("Matris Boyutu");

        cmbMatrixSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5" }));
        cmbMatrixSize.setToolTipText("");
        cmbMatrixSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMatrixSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUpLayout = new javax.swing.GroupLayout(pnlUp);
        pnlUp.setLayout(pnlUpLayout);
        pnlUpLayout.setHorizontalGroup(
            pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpLayout.createSequentialGroup()
                .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pbOperation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblOperation))
                    .addGroup(pnlUpLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMatrixSize1)
                            .addComponent(lblMatrixSize))
                        .addGap(18, 18, 18)
                        .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMatrixSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNodeSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStartNode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIteration, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTargetNode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDrawMaze)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSovleMaze, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlUpLayout.setVerticalGroup(
            pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbMatrixSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMatrixSize1)
                    .addComponent(cmbStartNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDrawMaze))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTargetNode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSovleMaze)
                    .addComponent(jLabel2)
                    .addComponent(cmbNodeSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMatrixSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIteration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpLayout.createSequentialGroup()
                        .addComponent(pbOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpLayout.createSequentialGroup()
                        .addComponent(lblOperation)
                        .addGap(17, 17, 17))))
        );

        pnlMaze.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlMazeLayout = new javax.swing.GroupLayout(pnlMaze);
        pnlMaze.setLayout(pnlMazeLayout);
        pnlMazeLayout.setHorizontalGroup(
            pnlMazeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlMazeLayout.setVerticalGroup(
            pnlMazeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlMaze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlMaze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        reInit(false);
        pbOperation.setValue(0);
        lblOperation.setText("0%");
        maze.clear();

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSovleMazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSovleMazeActionPerformed
        reInit(true);

    }//GEN-LAST:event_btnSovleMazeActionPerformed

    private void btnDrawMazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawMazeActionPerformed
        reInit(false);
        maze.drawMaze();
    }//GEN-LAST:event_btnDrawMazeActionPerformed

    private void cmbMatrixSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMatrixSizeActionPerformed

        int matrixSize;
        try {
            matrixSize = Integer.parseInt(cmbMatrixSize.getSelectedItem().toString().trim());
            cmbStartNode.removeAllItems();
            cmbTargetNode.removeAllItems();
            for (int i = 0; i < matrixSize; i++) {
                cmbStartNode.addItem("" + i);

            }
            cmbStartNode.setSelectedIndex(0);

            for (int i = matrixSize * (matrixSize - 1); i < matrixSize * matrixSize; i++) {
                cmbTargetNode.addItem("" + i);

            }
            cmbTargetNode.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_cmbMatrixSizeActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDrawMaze;
    private javax.swing.JButton btnSovleMaze;
    private javax.swing.JComboBox<String> cmbMatrixSize;
    private javax.swing.JComboBox<String> cmbNodeSize;
    private javax.swing.JComboBox<String> cmbStartNode;
    private javax.swing.JComboBox<String> cmbTargetNode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMatrixSize;
    private javax.swing.JLabel lblMatrixSize1;
    private javax.swing.JLabel lblOperation;
    private javax.swing.JProgressBar pbOperation;
    private javax.swing.JPanel pnlMaze;
    private javax.swing.JPanel pnlUp;
    private javax.swing.JTextField txtIteration;
    // End of variables declaration//GEN-END:variables
}
