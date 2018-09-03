package com.unip.conection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
     
    //Parâmetros da Conexão
    private static final String DATABASE = "get_that_cherry";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String SERVIDOR = "localhost:5432";
    private static final String URL = "jdbc:postgresql://"+SERVIDOR+"/"+DATABASE;
    private static final String USR = "postgres";
    private static final String PWD = "1234";
        
    public static Connection conectar(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR, PWD);
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
    
    public static void desconectar(Connection conexao){
        try
        {
            conexao.close();
        }
        catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
    //Teste para ver se o banco conecta
    //Não esquecer de importar biblioteca do driver
    public static void main(String[] args){
        Connection conexao = conectar();
        if(conexao != null){
            System.out.println("Conexão realizada com sucesso!");
            desconectar(conexao);
        }
    }    
    
}
