/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Conexao {

    //jdbc:postgresql://

    static String host = "ec2-23-21-186-85.compute-1.amazonaws.com:";
    static String Database = "d3jg2dj4rdcpg2";
    static String user = "uxkrjzhvgkbvku";
    //
    static String porta = "5432/";
    static String password = "dcd996e67c3a370d054e6ceede94e5bf59eef6c958638a3b9619240ce632b936";

    /*
     static String host = "localhost";
     static String Database = "postgres";
     static String user = "postgres";
     //
     static String porta = "5432";
     static String password = "postgres";
    
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://" + host + ":" + porta + "/" + Database + "/", user, password);

    }

    //METODO RESPONSAVEL DE ESTABELECER A CONEXAO COM O BANCO
    public static Connection conector() {
        java.sql.Connection conexao = null;
        //A LINHA ABAIXO CHAMA O DRIVER 
        String driver = "org.postgresql.Driver";
        // ARMAZENANDO INFORMAÇÕES REFERENTES AO BANCO DE DADOS
        //String url = "jdbc:postgresql://ec2-23-21-186-85.compute-1.amazonaws.com:5432/d3jg2dj4rdcpg2";

        String url = "jdbc:postgresql://" + host + "" + porta + "" + Database + "";
     //   System.out.printf(url);

        //ESTABELECENDO A CONEXAO COM O BANCO DE DADOS
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso " + conexao);
            return conexao;

        } catch (Exception e) {
            System.out.println("erro " + e);
            return null;
        }

    }

    //METODO DESCONECTAR
    protected static void desconectar() {
        Connection conexao = null;
        conexao = Conexao.conector();
        try {
            conexao.close();
        } catch (SQLException ex) {
            System.out.print("SQLException: ");
            System.out.println(ex.getMessage());
        }
    }
}
