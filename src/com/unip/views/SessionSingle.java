package com.unip.views;

import com.unip.conection.PlayerDAO;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SessionSingle extends javax.swing.JFrame {

    DefaultTableModel m = new DefaultTableModel();
    String nickname;
    
    public SessionSingle(String nickname) {
        this.nickname = (nickname!=null)? nickname : "";        
        initComponents();
        configForm();
        tabConfig();
        
        lblPlayer1.setText(nickname);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPlayer1 = new javax.swing.JLabel();
        scrollP2 = new javax.swing.JScrollPane();
        tabPlayer2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblPlayer1.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblPlayer1.setText("Player 1");

        tabPlayer2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabPlayer2.getTableHeader().setReorderingAllowed(false);
        scrollP2.setViewportView(tabPlayer2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(lblPlayer1)
                .addGap(181, 181, 181))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollP2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblPlayer1)
                .addGap(18, 18, 18)
                .addComponent(scrollP2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JScrollPane scrollP2;
    private javax.swing.JTable tabPlayer2;
    // End of variables declaration//GEN-END:variables
    
    private void configForm(){
            this.setTitle("SinglePlayer Sessions");
            this.setResizable(false);
            this.setLocationRelativeTo(null);
    }

    private void tabConfig(){
        m.addColumn("Date");
        m.addColumn("Score");
        m.addColumn("Length");
        m.addColumn("Time");
        m.addColumn("Level");       
        
        tabPlayer2.setModel(m);
        completeTable();
    }
    
    public void completeTable(){
        PlayerDAO pdao = new PlayerDAO();
        
        ResultSet rs = pdao.getSingleStatistics(nickname);    
        if (rs!=null){
            try{
                do{
                    m.addRow(new String[] {rs.getString("game_date"), String.valueOf(rs.getInt("score")), 
                        String.valueOf(rs.getInt("length")), rs.getString("game_time"), rs.getString("difficulty") });                    
                }while (rs.next());
            }
            catch(Exception e){
                System.out.println("Erro na config da tabela: " + e.getMessage());
            }
        }  
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);        
        
        tabPlayer2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabPlayer2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tabPlayer2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabPlayer2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabPlayer2.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        
        JTableHeader header = tabPlayer2.getTableHeader();
        header.setDefaultRenderer(centerRenderer);
        tabPlayer2.setModel(m);                    
    }
}
