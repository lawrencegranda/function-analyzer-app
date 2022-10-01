package com.analyzer.app ;

import javax.swing.* ;
import javax.swing.table.* ;
import java.awt.* ;

public class MyTable extends JTable {
    private DefaultTableModel model;
    private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() ;
    private JTableHeader header ;


    // Constructor
    MyTable(String[][] data, String[] columnNames, int editable) {
        setTable() ;
        setValues(data, columnNames, editable) ;
    }
    MyTable(String[][] data, String[] columnNames) {
        setTable() ;
        setValues(data, columnNames, -1) ;
    }
    MyTable() {
        setTable() ;
    }

    public void setTable() {
        this.setBackground(Color.white) ;
        header = this.getTableHeader() ;
        header.setBackground(new Color(0x8698b5)) ;
        this.setFont(new Font("Verdana", Font.PLAIN, 15)) ;
        this.setForeground(Color.black) ;
        this.setIntercellSpacing(new Dimension(25,5)) ;
        this.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255))) ;
    }
    public void setValues(String[][] data, String[] columnNames, int editable) {
        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (editable >= 0)
                    return column == editable ;
                return false ;
            }
        } ;
        this.model.addColumn(columnNames[0], data[0]) ;
        this.model.addColumn(columnNames[1], data[1]) ;
        this.setModel( this.model ) ;

        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.getColumnModel().getColumn(0).setPreferredWidth(100) ;
        this.getColumnModel().getColumn(0).setCellRenderer(cellRenderer) ;
        this.getColumnModel().getColumn(1).setPreferredWidth(100) ;
        this.getColumnModel().getColumn(1).setCellRenderer(cellRenderer) ;
    }

}
