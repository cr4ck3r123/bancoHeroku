/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

//import controller.ControllerUsuario;
import Controller.ClienteControl;
import Controller.UsuarioControl;
import Dao.UsuarioDao;
import Model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import modelo.Usuario;
//import net.proteanit.sql.DbUtils;

/**
 *
 * @author Fernando
 */
public class TelaUsuario extends javax.swing.JFrame {

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() throws Exception {
        initComponents();
        id();
        //tabela();
       
    }  
    
    public void desabilitarCampos(){
        lbId.setEnabled(false);
        lbNome.setEnabled(false);
        txtNome.setEnabled(false);
        lbLogin.setEnabled(false);
        txtLogin.setEnabled(false);
        lbSenha.setEnabled(false);
        txtSenha.setEnabled(false);
        lbConfSenha.setEnabled(false);
        txtConfSenha.setEnabled(false);
        lbEmail.setEnabled(false);
        cbPerfil.setEnabled(false);
        lbPesq.setEnabled(true);
        txtPesq.setEnabled(true);
        btnNovo.setEnabled(true);
        btnEditar.setEnabled(false);
        btnInserir.setEnabled(false);
        btnRemover.setEnabled(false);
        tblUsuario.setEnabled(true);
    }
    
    
    //Metodo Setar Campos
    public void setarcampos(){
        UsuarioControl control = new UsuarioControl();
        Usuario user = new Usuario();
         int setar = tblUsuario.getSelectedRow();
         txtId.setText(tblUsuario.getModel().getValueAt(setar, 0).toString());
         int id = Integer.parseInt(tblUsuario.getModel().getValueAt(setar, 0).toString());
         user = control.idusuario(id);
        // txtNome.setText(tblUsuario.getModel().getValueAt(setar, 1).toString());
       //  txtLogin.setText(tblUsuario.getModel().getValueAt(setar, 2).toString());
         txtNome.setText(user.getNome());
         txtLogin.setText(user.getLogin());
         txtSenha.setText(user.getSenha());
         txtConfSenha.setText(user.getSenha());
         cbPerfil.setSelectedItem(user.getPerfil().toString());
         
         btnNovo.setEnabled(false);
         btnInserir.setEnabled(false);
         btnRemover.setEnabled(true);
         btnEditar.setEnabled(true);
    }
    
    
    public void abilitarCampos(){
        txtNome.requestFocus();
        lbId.setEnabled(true);
        lbNome.setEnabled(true);
        txtNome.setEnabled(true);
        lbLogin.setEnabled(true);
        txtLogin.setEnabled(true);
        lbSenha.setEnabled(true);
        txtSenha.setEnabled(true);
        lbConfSenha.setEnabled(true);
        txtConfSenha.setEnabled(true);
        lbEmail.setEnabled(true);
        cbPerfil.setEnabled(true);
        lbPesq.setEnabled(false);
        txtPesq.setEnabled(false);
        btnNovo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnInserir.setEnabled(true);
        btnRemover.setEnabled(false);
        tblUsuario.setEnabled(true);
               
    }
    
    
     //Metodo pesquisar
    public void pesquisar() throws Exception{
             
//        ControllerUsuario controllerUsuario = new ControllerUsuario();
//        String lista = controllerUsuario.listar();
        try {            
           
            
           // tblUsuario.setModel(DbUtils.resultSetToTableModel(lista);
            tblUsuario.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblUsuario.getColumnModel().getColumn(1).setPreferredWidth(350); 
            tblUsuario.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblUsuario.getColumnModel().getColumn(3).setPreferredWidth(120);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
               
    }
   
    void limpaCampos(){
        txtNome.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtConfSenha.setText(null);
        }
    
       
    
    
    
    public void id(){
/*         ControllerUsuario controllerUsuario = new ControllerUsuario();
        try {
           String id = controllerUsuario.retornoid();
           txtId.setText(id);
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
  */
        }
    
    
    public void  listarTabela() throws Exception{
        
        UsuarioControl control = new UsuarioControl();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Id", "Nome", "Login"});
        
        List<Usuario> lista = new ArrayList();
        lista = control.listar();
        
        for (Usuario listaRetorno : lista) {
            model.addRow(new Object[]{listaRetorno.getId(), listaRetorno.getNome(), listaRetorno.getLogin()});
        }
        
        TelaUsuario.tblUsuario.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        lbLogin = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        lbSenha = new javax.swing.JLabel();
        lbConfSenha = new javax.swing.JLabel();
        txtConfSenha = new javax.swing.JPasswordField();
        lbEmail = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        lbPesq = new javax.swing.JLabel();
        txtPesq = new javax.swing.JTextField();
        cbPerfil = new javax.swing.JComboBox<String>();
        btnInserir = new javax.swing.JButton();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(0, 0, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Cadastro de Usuário");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/question.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pencil-black-tool-interface-symbol.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/recycle-bin.png"))); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.setEnabled(false);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNome.setEnabled(false);
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lbNome.setText("Nome");
        lbNome.setEnabled(false);

        txtLogin.setEnabled(false);

        lbLogin.setText("Login");
        lbLogin.setEnabled(false);

        txtSenha.setText("jPassw");
        txtSenha.setEnabled(false);
        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
        });

        lbSenha.setText("Senha");
        lbSenha.setEnabled(false);

        lbConfSenha.setText("Confirme a senha");
        lbConfSenha.setEnabled(false);

        txtConfSenha.setText("jield2");
        txtConfSenha.setEnabled(false);
        txtConfSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConfSenhaFocusGained(evt);
            }
        });

        lbEmail.setText("Perfil");
        lbEmail.setEnabled(false);

        lbId.setText("Id");
        lbId.setEnabled(false);

        txtId.setEnabled(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Login"
            }
        ));
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

        lbPesq.setText("Pesq.");

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "USUARIO", "ADMINISTRADOR" }));
        cbPerfil.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbId)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbNome)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(lbLogin)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail)
                            .addComponent(lbConfSenha)
                            .addComponent(lbSenha)
                            .addComponent(txtSenha)
                            .addComponent(txtConfSenha)))
                    .addComponent(cbPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbPesq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(lbId))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPesq)
                    .addComponent(txtPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbLogin)
                        .addGap(3, 3, 3)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbConfSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/insert-card.png"))); // NOI18N
        btnInserir.setText("Inserir");
        btnInserir.setEnabled(false);
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnRemover)
                    .addComponent(btnInserir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
    txtSenha.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaFocusGained

    private void txtConfSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfSenhaFocusGained
txtConfSenha.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfSenhaFocusGained

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        
        Usuario user = new Usuario();
        UsuarioControl controllerUsuario = new UsuarioControl();
        
        if(txtSenha.getText().equals(txtConfSenha.getText())){
        user.setLogin(txtLogin.getText().toLowerCase());        
        user.setSenha(txtSenha.getText().toLowerCase());
        user.setNome(txtNome.getText().toLowerCase());
        user.setPerfil(cbPerfil.getSelectedItem().toString());
        controllerUsuario.inserir(user);
        
        JOptionPane.showMessageDialog(rootPane, "Usuario inserido com sucesso!");
        desabilitarCampos();
        limpaCampos();
        }else{
            txtSenha.setText("");
            txtConfSenha.setText("");
            JOptionPane.showMessageDialog(rootPane, "As senhas não são iguais!");
        }
           
        try {
            listarTabela();
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btnInserirActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
      
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        abilitarCampos();
        tblUsuario.setEnabled(false);
       
      
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        // TODO add your handling code here:
       abilitarCampos();
        setarcampos();
       
        
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
       
        int id = Integer.parseInt(txtId.getText());
        UsuarioControl user = new UsuarioControl();
        try {
             user.deletar(id);
             listarTabela();
             limpaCampos();
             desabilitarCampos();
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        Usuario user = new Usuario();
        UsuarioControl controllerUsuario = new UsuarioControl();
        
         if(txtSenha.getText().equals(txtConfSenha.getText())){
        user.setLogin(txtLogin.getText());        
        user.setSenha(txtSenha.getText());
        user.setNome(txtNome.getText());
        user.setPerfil(cbPerfil.getSelectedItem().toString());
        user.setId(Integer.parseInt(txtId.getText()));
        try {
             controllerUsuario.editar(user);
              listarTabela();
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(rootPane, "Usuario alterado com sucesso!");
        }else{
            txtSenha.setText("");
            txtConfSenha.setText("");
            JOptionPane.showMessageDialog(rootPane, "As senhas não são iguais!");
        }
           desabilitarCampos();
           limpaCampos();
        try {
              listarTabela();
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       
    }//GEN-LAST:event_btnEditarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        try {  
            listarTabela();
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaUsuario().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cbPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbConfSenha;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbPesq;
    private javax.swing.JLabel lbSenha;
    public static javax.swing.JTable tblUsuario;
    private javax.swing.JPasswordField txtConfSenha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesq;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
