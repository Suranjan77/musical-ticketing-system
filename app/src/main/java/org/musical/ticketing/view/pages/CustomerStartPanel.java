/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.musical.ticketing.view.pages;

import org.musical.ticketing.domain.Customer;
import org.musical.ticketing.service.CustomerService;
import org.musical.ticketing.util.ErrorUtils;
import org.musical.ticketing.view.messaging.ListenerRegistry;
import org.musical.ticketing.view.messaging.events.CustomerCreated;

/**
 *
 * @author suranjanpoudel
 */
public class CustomerStartPanel extends javax.swing.JPanel {

    private final CustomerService customerService;

    /**
     * Creates new form CustomerStartPage
     */
    public CustomerStartPanel() {
        this.customerService = new CustomerService();
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

        phoneNumbser = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JTextField();
        startCustomerBtn = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        phoneNumbser.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 18)); // NOI18N
        phoneNumbser.setText("Enter customer's phone number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        add(phoneNumbser, gridBagConstraints);

        phoneNumber.setPreferredSize(new java.awt.Dimension(83, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        add(phoneNumber, gridBagConstraints);

        startCustomerBtn.setText("Continue");
        startCustomerBtn.setPreferredSize(new java.awt.Dimension(85, 35));
        startCustomerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startCustomerBtnMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 60, 0, 60);
        add(startCustomerBtn, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void startCustomerBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startCustomerBtnMouseClicked
        var customerPhoneNumber = phoneNumber.getText();

        if (customerPhoneNumber.length() < 10 || customerPhoneNumber.length() > 11) {
            ErrorUtils.showErrorPane(
                    "Phone number must be of 10 digits or 11 digits including prefix 0");
        } else {
            try {
                Long.valueOf(customerPhoneNumber);

                var customer
                        = customerService.createCustomer(new Customer(null, customerPhoneNumber));

                ListenerRegistry.notify(new CustomerCreated(customer.id()));
            } catch (NumberFormatException e) {
                ErrorUtils.showErrorPane("Phone number invalid");
            }
        }


    }//GEN-LAST:event_startCustomerBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JLabel phoneNumbser;
    private javax.swing.JButton startCustomerBtn;
    // End of variables declaration//GEN-END:variables
}
