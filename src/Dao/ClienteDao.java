/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.ClienteControl;
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
 * @author PC
 */
public class ClienteDao {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //METODO LISTAR CLIENTES
    public List<Cliente> listar() {
        ClienteControl control = new ClienteControl();
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
                           
            while (rs.next()) {                
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNasc(rs.getDate("datanascimento"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEmail(rs.getString("email"));
                lista.add(cliente);             
                }

            conexao.close();
        } catch (Exception e) {
            System.out.printf("erro"+ e);
        }

        return lista;
    }

    //METODO INSERIR NO BANCO CLIENTE
    public int inserir(Cliente cliente) {
        int i = 0;
        String sql = "insert into cliente(nome, datanascimento, rg, cpf, telefone, celular, email) values\n"
                + "(?, ?, ?, ?, ?, ?, ?);";
        
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setDate(2, cliente.getDataNasc());
            pst.setString(3, cliente.getRg());
            pst.setString(4, cliente.getCpf());
            pst.setString(5, cliente.getTelefone());
            pst.setString(6, cliente.getCelular());
            pst.setString(7, cliente.getEmail());
            
            pst.execute();
            i = 1;
        } catch (Exception e) {
            System.out.printf("erro = xxxxxx" + e);
            i = 0;
        }
        return i;
    }

    
    //METODO BUSCAR DADOS CLIENTE E ENDERECO
    public Cliente buscarDados(int id) throws SQLException{
        
       String sql = "select * from cliente where id = ?"; 
       Cliente cliente = new Cliente();
       conexao = Conexao.conector();
       pst = conexao.prepareStatement(sql);
       pst.setInt(1, id);
       rs = pst.executeQuery();
       rs.next();
       
       
       cliente.setNome(rs.getString("nome"));
       cliente.setDataNasc(rs.getDate("datanascimento"));
       cliente.setRg(rs.getString("rg"));
       cliente.setCpf(rs.getString("cpf"));
       cliente.setTelefone(rs.getString("telefone"));
       cliente.setCelular(rs.getString("celular"));
       cliente.setEmail(rs.getString("email"));
       
       conexao.close();
       return cliente;      
    }
}
