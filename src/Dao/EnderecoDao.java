/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.EnderecoControl;
import Model.Cliente;
import Model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class EnderecoDao {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //METODO INSERIR ENDEREÇO
    public int inserir(Endereco endereco) {
        conexao = Conexao.conector();
        int i;
        String sql = "insert into endereco(logradouro, numero, complemento, bairro, localidade, uf, cep, idpessoa) values "
                + "(?, ?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "select max(id) from cliente";

        try {

            pst = conexao.prepareStatement(sql2);
            rs = pst.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            System.out.print(id);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, endereco.getLogradouro());
            pst.setInt(2, endereco.getNumero());
            pst.setString(3, endereco.getComplemento());
            pst.setString(4, endereco.getBairro());
            pst.setString(5, endereco.getLocalidade());
            pst.setString(6, endereco.getUf());
            pst.setString(7, endereco.getCep());
            pst.setInt(8, id);
            pst.executeQuery();
            i = 1;

            conexao.close();
        } catch (Exception e) {
            System.out.print(e);
            i = 0;
        }

        return i;
    }

    //METODO LISTAR ENDEREÇO
    public Endereco listar(int id) throws SQLException {
      
        Endereco endereco = new Endereco();
        EnderecoControl control = new EnderecoControl();
        String sql = "select * from endereco where idpessoa = ?";
        conexao = Conexao.conector();
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, id);
        rs = pst.executeQuery();
        rs.next();
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getInt("numero"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setLocalidade(rs.getString("localidade"));
        endereco.setCep(rs.getString("cep"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setUf(rs.getString("uf"));
        endereco.setPessoa_idpessoa(rs.getInt("idpessoa"));
        conexao.close();
        
        return endereco;
    }

}
