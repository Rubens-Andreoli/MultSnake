package com.unip.conection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlayerDAO {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public boolean createAccount(Player p){
         try{
            String SQL = " INSERT INTO tb_player " +
                    " values(?,MD5(?),?,?)";
            
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                       
            cmd.setString(1, p.getNickname());
            cmd.setString(2, p.getPassword());
            cmd.setString(3, p.getSecurityQuestion());
            cmd.setString(4, p.getSecurityAnswer());
            
            if(cmd.executeUpdate()>0){
                ResultSet rs = cmd.getGeneratedKeys();
                return rs.next()? true : false;            }
            else{
                return false; 
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao criar conta: " + e.getMessage()); 
            return false;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }  
    
    public boolean hasLogin(String nickname, String pwd){
        
        try{
            String SQL = "SELECT fn_login(?,?) ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setString(1, nickname);
            cmd.setString(2, pwd);
            
            ResultSet rs = cmd.executeQuery();         
            if(rs.next()){
               System.out.println("Login efetuado: " + rs.getBoolean("fn_login"));
               return rs.getBoolean("fn_login"); 
            } 
            else{
               return false;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao efetuar login" + e.getMessage());
            return false;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public boolean resetPassword(String nickname, String pwd){
        
        try{
            String SQL = "SELECT fn_reset_password(?,MD5(?)) ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setString(1, nickname);
            cmd.setString(2, pwd);
            
            ResultSet rs = cmd.executeQuery();         
            if(rs.next()){
               System.out.println("Senha resetada: " + rs.getBoolean("fn_reset_password"));
               return rs.getBoolean("fn_reset_password"); 
            } 
            else{
               return false;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao resetar senha: " + e.getMessage());
            return false;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public boolean checkSecurityAnswer(String nickname, String answer){
        
        try{
            String SQL = "SELECT fn_check_security_answer(?,?) ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setString(1, nickname);
            cmd.setString(2, answer);
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               System.out.println("Resposta de segurança correta: " + rs.getBoolean("fn_check_security_answer")); 
               return rs.getBoolean("fn_check_security_answer"); 
            } 
            else{
               return false;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao confirmar resposta de segurança: " + e.getMessage());
            return false;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public String findQuestion(String nickname){
        
        try{
            String SQL = "SELECT security_question FROM tb_player WHERE nickname = ? ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setString(1, nickname);
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               System.out.println("Pergunta de Segurança encontrada: " + rs.getString("security_question"));
               return rs.getString("security_question"); 
            } 
            else{
               return null;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao encontrar pergunta de segurança: " + e.getMessage());
            return null;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public boolean statisticsSingle(int game_time, String difficulty, String game_mode, 
            String nickname, int score, int length){
        
        try{
            String SQL = "SELECT fn_set_statistics_single(?,?,?,?,?,?); ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setInt(1, game_time);
            cmd.setString(2, difficulty);
            cmd.setString(3, game_mode);
            cmd.setString(4, nickname);
            cmd.setInt(5, score);
            cmd.setInt(6, length);
            
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               System.out.println("Statistics Single inseridas: " + rs.getBoolean("fn_set_statistics_single"));
               return rs.getBoolean("fn_set_statistics_single"); 
            } 
            else{
               return false;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao inserir statistics single: " + e.getMessage());
            return false;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public boolean statisticsMult(int game_time, String difficulty, String game_mode, 
            String nickname_p1, int score_p1, int length_p1, String nickname_p2, int score_p2, int length_p2){
         
        System.out.println("Score p1: " + nickname_p1 + score_p1);
        System.out.println("Score p2: " + nickname_p2 + score_p2);
        try{
            String SQL = " SELECT fn_set_statistics_mult(?,?,?,?,?,?,?,?,?); ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setInt(1, game_time);
            cmd.setString(2, difficulty);
            cmd.setString(3, game_mode);
            cmd.setString(4, nickname_p1);
            cmd.setInt(5, score_p1);
            cmd.setInt(6, length_p1);
            cmd.setString(7, nickname_p2);
            cmd.setInt(8, score_p2);
            cmd.setInt(9, length_p2);            
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               System.out.println("Statistics Mult inseridas: " + rs.getBoolean("fn_set_statistics_mult"));
               return rs.getBoolean("fn_set_statistics_mult"); 
            } 
            else{
               return false;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao inserir statistics mult: " + e.getMessage());
            return false;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public ResultSet getSingleStatistics(String nickname){
         try{
            String SQL = "SELECT game_date, score, length, game_time, difficulty "
                    + " FROM viewSingleStatistics WHERE nickname = ? ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setString(1,  nickname);            
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               return rs; 
            } 
            else{
               return null;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao inserir statistics single: " + e.getMessage());
            return null;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public ResultSet getMultStatistics(String nickname){
         try{
            String SQL = " SELECT game_date, nickname_p1, nickname_p2, score_p1, "
                + " score_p2, game_time, difficulty FROM viewMultStatistics "
                + " WHERE nickname_p1 = ? OR nickname_p2 = ?";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);
                       
            cmd.setString(1,  nickname);       
            cmd.setString(2,  nickname); 
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               return rs; 
            } 
            else{
               return null;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao inserir statistics mult: " + e.getMessage());
            return null;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }
    
    public ResultSet getGlobalStatistics(){
         try{
            String SQL = "SELECT * "
                    + " FROM viewGlobalStatistics ";
                               
            con = Conexao.conectar();
            cmd = con.prepareStatement(SQL);                                
            
            ResultSet rs = cmd.executeQuery();         
            
            if(rs.next()){
               return rs; 
            } 
            else{
               return null;
            }
        }        
        catch(Exception e){
            System.out.println("Erro ao inserir statistics single: " + e.getMessage());
            return null;
        }
        finally{
            Conexao.desconectar(con);
        }        
    }    
}
