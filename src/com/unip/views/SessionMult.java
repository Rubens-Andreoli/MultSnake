package com.unip.views;

import com.unip.conection.PlayerDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SessionMult extends javax.swing.JFrame {

    DefaultTableModel m = new DefaultTableModel();
    String nickname_p1;
    String nickname_p2;
    
    public SessionMult(String nickname_p1, String nickname_p2) {
        this.nickname_p1 = (nickname_p1!=null)? nickname_p1 : ""; 
        this.nickname_p2 = (nickname_p2!=null)? nickname_p2 : ""; 
        initComponents();
        configForm();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPlayer1 = new javax.swing.JLabel();
        lblPlayer2 = new javax.swing.JLabel();
        scrollP1 = new javax.swing.JScrollPane();
        tabPlayer1 = new javax.swing.JTable();
        scrollP2 = new javax.swing.JScrollPane();
        tabPlayer2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblPlayer1.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblPlayer1.setText("Player 1");

        lblPlayer2.setFont(new java.awt.Font("Gisha", 0, 14)); // NOI18N
        lblPlayer2.setText("Player 2");

        tabPlayer1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabPlayer1.getTableHeader().setReorderingAllowed(false);
        scrollP1.setViewportView(tabPlayer1);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollP1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(scrollP2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlayer1)
                            .addComponent(lblPlayer2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblPlayer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollP2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(lblPlayer2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollP1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JLabel lblPlayer2;
    private javax.swing.JScrollPane scrollP1;
    private javax.swing.JScrollPane scrollP2;
    private javax.swing.JTable tabPlayer1;
    private javax.swing.JTable tabPlayer2;
    // End of variables declaration//GEN-END:variables
    
    private void configForm(){
        this.setTitle("Get That Cherry");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        lblPlayer1.setText(nickname_p1);
        lblPlayer2.setText(nickname_p2);
        completeTable(nickname_p1, 1);
        completeTable(nickname_p2, 2);
    }

    private DefaultTableModel tabConfig(){
        DefaultTableModel m = new DefaultTableModel();
            m.addColumn("Date");
            m.addColumn("Player 01");
            m.addColumn("Score");
            m.addColumn("Player 02");
            m.addColumn("Score");
            m.addColumn("Time");
            m.addColumn("Level");      
        return m;
    }
    
    public void completeTable(String nickname, int player){
        PlayerDAO pdao = new PlayerDAO();
        DefaultTableModel n = tabConfig();
        DefaultTableCellRenderer centerRenderer;
        JTableHeader header;
        ResultSet rs;
        
        switch(player){
            case 1:
                rs = pdao.getMultStatistics(nickname);    
        
                if (rs!=null){
                    try{
                        do{
                            n.addRow(new String[] {rs.getString("game_date"), rs.getString("nickname_p1"), 
                                String.valueOf(rs.getInt("score_p1")), rs.getString("nickname_p2"), 
                                String.valueOf(rs.getInt("score_p2")), rs.getString("game_time"), rs.getString("difficulty") });                    
                        }while (rs.next());
                    }
                    catch(Exception e){
                        System.out.println("Erro na config da tabela: " + e.getMessage());
                    }
                }   

                centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                //tabPlayer1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                //tabPlayer1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                //tabPlayer1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                //tabPlayer1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                //tabPlayer1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);        
                //tabPlayer1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);        
                //tabPlayer1.getColumnModel().getColumn(6).setCellRenderer(centerRenderer); 

                header = tabPlayer1.getTableHeader();
                header.setDefaultRenderer(centerRenderer);        
        
                tabPlayer1.setModel(n);  
            break;
        
            case 2:
                rs = pdao.getMultStatistics(nickname);    

                if (rs!=null){
                    try{
                        do{
                            n.addRow(new String[] {rs.getString("game_date"), rs.getString("nickname_p1"), 
                                String.valueOf(rs.getInt("score_p1")), rs.getString("nickname_p2"), 
                                String.valueOf(rs.getInt("score_p2")), rs.getString("game_time"), rs.getString("difficulty") });                    
                        }while (rs.next());
                    }
                    catch(Exception e){
                        System.out.println("Erro na config da tabela: " + e.getMessage());
                    }
                }   

                centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
/*
                tabPlayer2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                tabPlayer2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                tabPlayer2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                tabPlayer2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                tabPlayer2.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);        
                tabPlayer2.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);        
                tabPlayer2.getColumnModel().getColumn(6).setCellRenderer(centerRenderer); */

                header = tabPlayer2.getTableHeader();
                header.setDefaultRenderer(centerRenderer);
                
                tabPlayer2.setModel(n);
            break;
        }
        
        
    }
}
