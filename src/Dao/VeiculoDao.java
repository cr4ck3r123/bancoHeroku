/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class VeiculoDao {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List jcomboxMarca() {

        String sql = "select * from marcas";
        List<String> lista = new ArrayList<String>();

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {                
                lista.add(rs.getString("marca"));                
            }
        //    System.out.print(lista.get(1));
        pst.close();                   
           
        } catch (Exception e) {
        }
        return lista;
    }

}
