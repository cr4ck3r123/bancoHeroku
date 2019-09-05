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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.printf("erro" + e);
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
    public Cliente buscarDados(int id) throws SQLException {

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

    //DELETE CLIENTE
    public int delete(int id) {
        int i = 0;
         String sql1 = "delete from endereco where idpessoa = ?";
         String sql2 = "delete from cliente where id = ?";
       
        conexao = Conexao.conector();
        try {
            pst = conexao.prepareStatement(sql1);
            pst.setInt(1, id);
            pst.execute();
           i =1;
            if (i == 1){
            pst = conexao.prepareStatement(sql2);
            pst.setInt(1, id);
            pst.execute();          
           }
           i =1;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);  
            i = 0;
        }
        return i;
    }
    
    
    //UPDATE 
    public int editar(Cliente cliente, Endereco endereco){
        int i = 0;
        conexao = Conexao.conector();  
       System.out.print("chegou");
        try {
        String sql1 = "update endereco set cep=?,  logradouro=?, complemento=?, bairro=?, localidade=?, uf=?, numero=? where idpessoa=?";
        pst = conexao.prepareStatement(sql1);
        pst.setString(1, endereco.getCep());
        pst.setString(2, endereco.getLogradouro());
        pst.setString(3, endereco.getComplemento());
        pst.setString(4, endereco.getBairro());
        pst.setString(5, endereco.getLocalidade());
        pst.setString(6, endereco.getUf());
        pst.setInt(7, endereco.getNumero());
        pst.setInt(8, cliente.getId());
        System.out.print(endereco.getPessoa_idpessoa());
        pst.execute();       
        
        String sql2 = "update cliente set nome=?, datanascimento=?, rg=?, cpf=?, telefone=?, celular=?, email=? where id=?";
            
        pst = conexao.prepareStatement(sql2);
        pst.setString(1, cliente.getNome());
        pst.setDate(2, cliente.getDataNasc());
        pst.setString(3, cliente.getRg());
        pst.setString(4, cliente.getCpf());
        pst.setString(5, cliente.getTelefone());
        pst.setString(6, cliente.getCelular());
        pst.setString(7, cliente.getEmail());
        pst.setInt(8, cliente.getId());
        pst.execute();
        i = 1;
        } catch (SQLException e) {
            
            System.out.print(e);
        }
     
       
        return i;
    }
}
