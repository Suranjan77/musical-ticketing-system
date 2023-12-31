/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.musical.ticketing.view.components.calendar;

import org.musical.ticketing.view.models.CellData;
import java.awt.Color;
import org.musical.ticketing.view.messaging.ListenerRegistry;
import org.musical.ticketing.view.messaging.events.CalendarCellClicked;

/**
 *
 * @author suranjanpoudel
 */
public class CalendarCell extends javax.swing.JPanel {

    private final CellData cellData;

    /**
     * Creates new form CalendarCell
     *
     * @param cellData
     */
    public CalendarCell(CellData cellData) {
        this.cellData = cellData;

        initComponents();

        dayOfMonthLabel.setText(cellData.title());

        if (cellData.isDisabled()) {
            dayOfMonthLabel.setForeground(new java.awt.Color(204, 204, 204));
        }

        if (cellData.isToday()) {
            dayOfMonthLabel.setForeground(new Color(255, 255, 255));
            setBackground(new Color(0, 102, 51));
        }

        if (cellData.isDayName()) {
            dayOfMonthLabel.setForeground(new Color(255, 255, 255));
            setBackground(new Color(0, 0, 255));
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

        dayOfMonthLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        dayOfMonthLabel.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 18)); // NOI18N
        dayOfMonthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayOfMonthLabel.setText("5");
        dayOfMonthLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dayOfMonthLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dayOfMonthLabelMouseExited(evt);
            }
        });
        add(dayOfMonthLabel, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        var sourceCell = (CalendarCell) evt.getSource();
        var metaData = sourceCell.cellData;
        if (!metaData.isDisabled() && !cellData.isDayName()) {
             ListenerRegistry.notify(new CalendarCellClicked(cellData));
        }
    }//GEN-LAST:event_formMouseClicked

    private void dayOfMonthLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dayOfMonthLabelMouseEntered

    }//GEN-LAST:event_dayOfMonthLabelMouseEntered

    private void dayOfMonthLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dayOfMonthLabelMouseExited

    }//GEN-LAST:event_dayOfMonthLabelMouseExited

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (!cellData.isDayName() && !cellData.isDisabled()) {
            setBackground(new Color(204, 255, 255));
            if (cellData.isToday()) {
                setBackground(new Color(0, 204, 51));
            }
        }

    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        if (!cellData.isDayName() && !cellData.isDisabled()) {
            if (cellData.isToday()) {
                dayOfMonthLabel.setForeground(new Color(255, 255, 255));
                setBackground(new Color(0, 102, 51));
            } else {
                setBackground(new Color(246, 246, 246));
            }
        }
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dayOfMonthLabel;
    // End of variables declaration//GEN-END:variables
}
