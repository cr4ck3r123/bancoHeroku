/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EnderecoDao;
import Model.Endereco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class EnderecoControl {
     //METODO PEGA ENDEREÇO DA API 
    public Endereco pegaEndereco(String txtCep) throws Exception {

        HttpExemplo http = new HttpExemplo();
        String chamada = "https://viacep.com.br/ws/" + txtCep + "/json/";
        
        String json = http.sendGet(chamada);
     
        Gson gson = new Gson();

        //  Usuario u = new Usuario();
        java.lang.reflect.Type usuarioType = new TypeToken<Endereco>() {
        }.getType();

                      // System.out.print(json);
        Endereco cep = new Endereco();
        cep = gson.fromJson(json, usuarioType);
        System.out.print(cep.getBairro());

        return cep;
    }
    
    
    //METODO VALIDA ENDERECO
    public int validaEndereco(Endereco endereco){
        int i = 0;
        if(endereco != null){
            EnderecoDao dao = new EnderecoDao();
          i = dao.inserir(endereco);
      
            JOptionPane.showMessageDialog(null, "Endereco inserido com sucesso !!!!");
        }
             
        return i;
    }
    
      //METODO VALIDAR LISTAR TABELA
    public Endereco listar(int id) throws SQLException {
        /* Cria o model*/
        EnderecoDao dao = new EnderecoDao();
        Endereco endereco = new Endereco();
        endereco = dao.listar(id);
        
        return endereco;
    }
    
}
