/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.UsuarioControl;
import Model.Usuario;
import View.TelaCliente;
import View.TelaUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando
 */
public class UsuarioDao {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //METODO LOGAR
    public Usuario logar(Usuario usuario) {
        Usuario user = new Usuario();
        String sql = "select * from usuario where login=? and senha=?";
        
        int i = 0;
        try {

            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario.getLogin().toString());
            pst.setString(2, usuario.getSenha().toString());
            rs = pst.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setNome(rs.getString("nome"));
            user.setLogin(rs.getString("login"));
            user.setSenha(rs.getString("senha"));
            user.setPerfil(rs.getString("perfil"));
            i = 1;
            conexao.close();
        } catch (Exception e) {
            System.out.printf("erro = ", e);
            i = 0;
        }

        return user;
    }

    //METODO INSERIR USUARIO
    public int inserirDao(Usuario user) {
        int i = 0;
        String sql = "insert into usuario (nome, login, senha, perfil) values\n"
                + "(?,?,?,?);";
        
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, user.getNome().toString());
            pst.setString(2, user.getLogin().toString());
            pst.setString(3, user.getSenha().toString());
            pst.setString(4, user.getPerfil().toString());
            pst.execute();
            conexao.close();
        } catch (Exception e) {
            System.out.printf("erro = " + e);

        }
       
        return i;
    }

    //METODO LISTAR USUARIOS
    public List<Usuario> listar() {
        UsuarioControl control = new UsuarioControl();
        String sql = "select * from usuario";
        List<Usuario> lista = new ArrayList<>();
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.printf("chegou");
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                System.out.print(rs.getString("nome"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setPerfil(rs.getString("perfil"));
                lista.add(user);
            }
            conexao.close();
        } catch (Exception e) {
        }

        return lista;
    }

    //METODO BUSCAR USUARIO POR ID
    public Usuario pegaUsuario(int id) {
        Usuario user = new Usuario();
        String sql = "select * from usuario where id = ?";
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setPerfil(rs.getString("perfil"));
            }
            conexao.close();
        } catch (Exception e) {
            System.out.printf("Erro = " + e);
        }

        return user;
    }

    //METODO DELETAR USUARIO POR ID
    public void deletar(int id) {
        String sql = "delete from usuario where id = ?";
        int i = 0;
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            if (pst.execute() == true) {
                i = 1;
            } else {
                i = 0;
            }
            conexao.close();
        } catch (Exception e) {
            System.out.printf("erro = ", i);
        }
    }

    //METODO EDITAR 
    public void editar(Usuario user) {
        String sql = "update usuario set nome = ?, login = ?, senha = ?, perfil = ? where id = ? ";
        int i = 0;
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getSenha());
            pst.setString(4, user.getPerfil());
            pst.setInt(5, user.getId());

            if (pst.execute() == true) {
                i = 1;
            } else {
                i = 0;
            }
            conexao.close();
        } catch (Exception e) {
            System.out.printf("erro = ", i);
        }
    }
}
