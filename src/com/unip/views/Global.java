package com.unip.views;

import com.unip.conection.PlayerDAO;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Global extends javax.swing.JFrame {

    DefaultTableModel  m = new DefaultTableModel();
        
    public Global() {
        initComponents();
        configForm();
        tabConfig();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tabGlobal = new javax.swing.JTable();
        lblGlobal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabGlobal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Player", "Score", "Time", "Mode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabGlobal.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(tabGlobal);
        if (tabGlobal.getColumnModel().getColumnCount() > 0) {
            tabGlobal.getColumnModel().getColumn(0).setResizable(false);
            tabGlobal.getColumnModel().getColumn(1).setResizable(false);
            tabGlobal.getColumnModel().getColumn(2).setResizable(false);
            tabGlobal.getColumnModel().getColumn(3).setResizable(false);
            tabGlobal.getColumnModel().getColumn(4).setResizable(false);
        }

        lblGlobal.setFont(new java.awt.Font("Gisha", 0, 18)); // NOI18N
        lblGlobal.setText("Global Statistics");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(lblGlobal)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGlobal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblGlobal;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tabGlobal;
    // End of variables declaration//GEN-END:variables

    private void configForm(){
        this.setTitle("Get That Cherry");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void tabConfig(){
        m.addColumn("Player");
        m.addColumn("Score");
        tabGlobal.setModel(m);
        completeTable();
    }
    
    public void completeTable(){
        PlayerDAO pdao = new PlayerDAO();
        
        ResultSet rs = pdao.getGlobalStatistics();    
        if (rs!=null){
            try{
                do{
                    m.addRow(new String[] {rs.getString("nickname"), String.valueOf(rs.getInt("score")) });                    
                }while (rs.next());
            }
            catch(Exception e){
                System.out.println("Erro na config da tabela: " + e.getMessage());
            }
        }    
        
        tabGlobal.setModel(m);
    }         
}

