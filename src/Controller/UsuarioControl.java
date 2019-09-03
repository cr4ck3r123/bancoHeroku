/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UsuarioDao;
import Model.Usuario;
import View.TelaPrincipal;
import View.TelaUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando
 */
public class UsuarioControl {

    //METODO VALIDAR USUARIO
    public int validaUsuario(Usuario user) {
        int i = 0;
        UsuarioDao dao = new UsuarioDao();
        Usuario retornoUsuario = new Usuario();
        retornoUsuario = dao.logar(user);
        
        String login = user.getLogin();
        String senha = user.getSenha();        
        
        if(login.equals(retornoUsuario.getLogin()) && (senha.equals(retornoUsuario.getSenha())) && (retornoUsuario.getPerfil().equals("ADMINISTRADOR"))){
              i =1;
         }else if(login.equals(retornoUsuario.getLogin()) && (senha.equals(retornoUsuario.getSenha())) && (retornoUsuario.getPerfil().equals("USUARIO"))){
                 i = 2;
                 }else {
             i = 0;
         }
         
        return i;
    }

    //METODO VALIDAR INSERÇÃO USUARIO
    public int inserir(Usuario user) {
        int i = 0;
        UsuarioDao dao = new UsuarioDao();
        i = dao.inserirDao(user);
        return i;
    }

    //METODO VALIDAR LISTAR TABELA
    public List<Usuario> listar() {
        /* Cria o model*/
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> lista = new ArrayList<>();

        return dao.listar();
    }

    //PEGA ID USUARIO
    public Usuario idusuario(int id) {
        UsuarioDao dao = new UsuarioDao();
        Usuario user = new Usuario();
        user = dao.pegaUsuario(id);

        return user;
    }

    //METODO DELETA USUARIO
    public void deletar(int id) {
        UsuarioDao dao = new UsuarioDao();
        dao.deletar(id);
        
    }
    
    //CONTROLE EDITAR
    public void editar(Usuario user){
        UsuarioDao dao = new UsuarioDao();
        
        dao.editar(user);
        
    }

}
