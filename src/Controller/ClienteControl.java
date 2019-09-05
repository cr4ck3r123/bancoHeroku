/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ClienteDao;
import Model.Cliente;
import Model.Endereco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ClienteControl {

    //METODO VALIDAR LISTAR TABELA
    public List<Cliente> listar() {
        /* Cria o model*/
        ClienteDao dao = new ClienteDao();
        return dao.listar();
    }
    
    //METODO PARA VALIDAR INSERÇÃO
    public int inserir(Cliente cliente){
        int i =0;
        ClienteDao dao = new ClienteDao();
        if(cliente != null){
            dao.inserir(cliente);
            i = 1;
            JOptionPane.showMessageDialog(null, "Usuario Inserido com sucesso");
            
        }else{
            System.out.printf("dados invalidos");
        }
        return i;
                }
    
    
    //METODO PARA SETAR CAMPOS CLIENTE E ENDEREÇO
    public Cliente setarCampos(int id) throws SQLException{
        
        ClienteDao dao = new ClienteDao();
        Cliente cliente = new Cliente();
        
        cliente = dao.buscarDados(id);
        
        return cliente;
    }
    
    //CONTROLE DELETAR 
    public void deletar(int id){
       int i = 0; 
        ClienteDao dao = new ClienteDao();
        
      i = dao.delete(id);
        if(i==1){
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!!!");
        }
    }

    public int editar(Cliente cliente, Endereco endereco) {
        int i =0;
        try {
        ClienteDao dao = new ClienteDao();
      i =  dao.editar(cliente, endereco); 
        
        System.out.print("CHEGOU!!!!");
        } catch (Exception e) {
        }
          
       return i; 
        
    }
}
