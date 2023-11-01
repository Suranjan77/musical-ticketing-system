/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.musical.ticketing.view.components.calendar;

/**
 *
 * @author suranjanpoudel
 */
public class AccountingPane extends javax.swing.JPanel {

    /**
     * Creates new form TotalCostPane
     */
    public AccountingPane() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        accountingPanel = new javax.swing.JPanel();
        seatsRemainingLabel = new javax.swing.JLabel();
        seatsRemainingCountLabel = new javax.swing.JLabel();
        adultLabel = new javax.swing.JLabel();
        adultTicketCountSpinner = new javax.swing.JSpinner();
        seniorLabel = new javax.swing.JLabel();
        seniorTicketCountSpinner = new javax.swing.JSpinner();
        studentLabel = new javax.swing.JLabel();
        studentTicketCountSpinner = new javax.swing.JSpinner();
        purchaseButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setLayout(new java.awt.GridBagLayout());

        accountingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        accountingPanel.setLayout(new java.awt.GridLayout(0, 2, 0, 2));

        seatsRemainingLabel.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        seatsRemainingLabel.setText("    Remaining Seats");
        accountingPanel.add(seatsRemainingLabel);

        seatsRemainingCountLabel.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        seatsRemainingCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seatsRemainingCountLabel.setText("2");
        accountingPanel.add(seatsRemainingCountLabel);

        adultLabel.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        adultLabel.setText("    Adult");
        accountingPanel.add(adultLabel);

        adultTicketCountSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        adultTicketCountSpinner.setMaximumSize(new java.awt.Dimension(50, 40));
        adultTicketCountSpinner.setMinimumSize(new java.awt.Dimension(40, 22));
        adultTicketCountSpinner.setPreferredSize(new java.awt.Dimension(40, 22));
        accountingPanel.add(adultTicketCountSpinner);

        seniorLabel.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        seniorLabel.setText("    Senior");
        accountingPanel.add(seniorLabel);

        seniorTicketCountSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        seniorTicketCountSpinner.setMaximumSize(new java.awt.Dimension(50, 40));
        seniorTicketCountSpinner.setMinimumSize(new java.awt.Dimension(40, 22));
        seniorTicketCountSpinner.setPreferredSize(new java.awt.Dimension(40, 22));
        accountingPanel.add(seniorTicketCountSpinner);

        studentLabel.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        studentLabel.setText("    Student");
        accountingPanel.add(studentLabel);

        studentTicketCountSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        studentTicketCountSpinner.setMaximumSize(new java.awt.Dimension(20, 40));
        studentTicketCountSpinner.setMinimumSize(new java.awt.Dimension(20, 22));
        studentTicketCountSpinner.setPreferredSize(new java.awt.Dimension(20, 22));
        accountingPanel.add(studentTicketCountSpinner);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        add(accountingPanel, gridBagConstraints);

        purchaseButton.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        purchaseButton.setText("Purchase");
        purchaseButton.addActionListener(this::purchaseButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 0, 25);
        add(purchaseButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void purchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountingPanel;
    private javax.swing.JLabel adultLabel;
    private javax.swing.JSpinner adultTicketCountSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton purchaseButton;
    private javax.swing.JLabel seatsRemainingCountLabel;
    private javax.swing.JLabel seatsRemainingLabel;
    private javax.swing.JLabel seniorLabel;
    private javax.swing.JSpinner seniorTicketCountSpinner;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JSpinner studentTicketCountSpinner;
    // End of variables declaration//GEN-END:variables
}