/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Veiculo;
import static View.TelaCliente.cbmarca;
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
public class VeiculoDao {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //BUSCA MARCAS PARA COMBOBOX MARCAS
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
//PEGA O ID DA MARCA

    public int pegarIdMarca() {
        int id = 0;

        String sql = "select * from marcas where marca = ?";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cbmarca.getSelectedItem().toString());
            rs = pst.executeQuery();

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));
            }

        } catch (Exception e) {
        }
        //     System.out.printf("aki ->" + cbmarca.getSelectedItem().toString());

        return id;
    }

    //BUSCA MODELOS PELA MARCA
    public List jcomboxModelo(int id) {
       
      
        String sql = "select * from modelos where idmarca = ?";
        List<String> lista = new ArrayList<String>();
        
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
            //    System.out.print(lista.get(1));
            pst.close();
          //  System.out.print("este"+lista);
        } catch (Exception e) {
        }

        return lista;
    }
    
    
    // LISTAR VEICULO
    //METODO LISTAR CLIENTES
    public List<Veiculo> listar(int id) {
        
        String sql = "select * from veiculo where idcliente = ?";
        List<Veiculo> lista = new ArrayList<>();
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAno(rs.getInt("ano"));
                v.setCor(rs.getString("cor"));
                v.setKm(rs.getDouble("km"));
                lista.add(v);
            }

            conexao.close();
        } catch (Exception e) {
            System.out.printf("erro" + e);
        }
        
        return lista;
    }
    
      //METODO BUSCAR DADOS CLIENTE E ENDERECO
    public Veiculo buscarDados(int id) throws SQLException {

        String sql = "select * from veiculo where id = ?";
        Veiculo v = new Veiculo();
        conexao = Conexao.conector();
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, id);
        rs = pst.executeQuery();
        rs.next();

        v.setId(rs.getInt("id"));
        v.setMarca(rs.getString("marca"));
        v.setModelo(rs.getString("modelo"));
        v.setAno(rs.getInt("ano"));
        v.setCor(rs.getString("cor"));
        v.setKm(rs.getDouble("km"));
        
        conexao.close();
        return v;
    }
    
    //METODO INSERIR NO BANCO VEICULO
    public int inserir(Veiculo veiculo) {
        int i = 0;
        String sql = "insert into veiculo(marca, modelo, ano, cor, km, idcliente) values\n"
                + "(?, ?, ?, ?, ?, ?);";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, veiculo.getMarca());
            pst.setString(2, veiculo.getModelo());
            pst.setInt(3, veiculo.getAno());
            pst.setString(4, veiculo.getCor());
            pst.setDouble(5, veiculo.getKm());
            pst.setInt(6, veiculo.getId());
            pst.execute();
            i = 1;
        } catch (Exception e) {
            System.out.printf("erro = xxxxxx" + e);
            i = 0;
        }
        return i;
    }

}
