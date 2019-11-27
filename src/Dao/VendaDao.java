/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Fernando
 */
public class VendaDao {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

     //METODO INSERIR NO BANCO CLIENTE
    public int id() {
        int i = 0;
        String sql = "select max(id)+1 from venda";

        try {
            conexao = Conexao.conector();
          

            pst.execute();
            i = 1;
        } catch (Exception e) {
            System.out.printf("erro = xxxxxx" + e);
            i = 0;
        }
        return i;
    }
    
}
