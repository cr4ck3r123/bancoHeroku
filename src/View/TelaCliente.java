/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ClienteControl;
import Controller.EnderecoControl;
import Controller.GoogleAPIControl;
import static Controller.GoogleAPIControl.getJson;
import static Controller.GoogleAPIControl.loadResultFromJSONGson;
import Controller.HttpExemplo;
import Dao.ClienteDao;
import Dao.VeiculoDao;
import Model.Cliente;
import Model.Endereco;
import Model.GoogleAPI;
import Model.Veiculo;
import Utilitarios.Mapa;
import Utilitarios.MapaJ;
import Utilitarios.MapaX;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.internal.Browser;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class TelaCliente extends javax.swing.JFrame {

    int btnInsert = 0;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {  
        initComponents();
        int id = listarMarcas();
        listarModelos(id);
     
    }
    
    //LISTAR MARCAS
    public int  listarMarcas(){
        VeiculoDao v = new VeiculoDao();
        List lista = v.jcomboxMarca();
        System.out.printf(String.valueOf(lista.size()));
       // cbmarca.setEnabled(false);
        for(int i=0;i<lista.size();i++){
        cbmarca.addItem(lista.get(i));
        }
        System.out.print(v.pegarIdMarca());
        return v.pegarIdMarca();
    }
    
    //LISTAR MODELOS
    public void listarModelos(int id){
        
        VeiculoDao v = new VeiculoDao();
        List lista = v.jcomboxModelo(id);
        System.out.printf(String.valueOf(lista.size()));
       // cbmodelo.setEnabled(true);
        for(int i=0;i<lista.size();i++){
        cbmodelo.addItem(lista.get(i));
        }
        
        
    }

    //LISTAR TABELA CLIENTES
    public void listarTabela() throws Exception {

        ClienteControl control = new ClienteControl();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "NOME", "CELULAR"});

        List<Cliente> lista = new ArrayList();
        lista = control.listar();

        for (Cliente listaRetorno : lista) {
            model.addRow(new Object[]{listaRetorno.getId(), listaRetorno.getNome(), listaRetorno.getCelular()});
        }

        TelaCliente.tblCliente.setModel(model);
        TelaCliente.tblCliente.getColumnModel().getColumn(0).setPreferredWidth(2);
        TelaCliente.tblCliente.getColumnModel().getColumn(1).setPreferredWidth(260);
    }

    //PEGA ENDEREÇO
    public void tabelaEndereco(Endereco endereco) throws Exception {

        EnderecoControl control = new EnderecoControl();

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "RUA", "N°", "BAIRRO"});

        model.addRow(new Object[]{endereco.getId(), endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro()});

        tbEndereco.setModel(model);
        tbEndereco.getColumnModel().getColumn(0).setPreferredWidth(2);
        tblCliente.getColumnModel().getColumn(1).setPreferredWidth(260);
    }

    //ATIVA CAMPOS CLIENTE
    void ativarCliente() {
        
        tabelas.setEnabled(true);
        tabelas.setEnabledAt(2, false);
        lbNome.setEnabled(true);
        lbRg.setEnabled(true);
        lbCelular.setEnabled(true);
        lbEmail.setEnabled(true);
        lbDataNasc.setEnabled(true);
        lbCpf.setEnabled(true);
        lbTelefone.setEnabled(true);
        txtNome.setEnabled(true);
        txtRG.setEnabled(true);
        txtCelular.setEnabled(true);
        txtEmail.setEnabled(true);
        txtDatNasc.setEnabled(true);
        txtCpf.setEnabled(true);
        txtTelefone.setEnabled(true);
        lbPesq.setEnabled(false);
        txtPesq.setEnabled(false);
        tblCliente.setEnabled(true);

        btnPesq.setEnabled(true);
        btnEditar.setEnabled(true);
        btnInserir.setEnabled(false);
        btnRemover.setEnabled(true);
        btnNovo.setEnabled(false);
    }

    public void ativarNovo() {
        btnPesq.setEnabled(false);
        btnEditar.setEnabled(false);
        btnInserir.setEnabled(true);
        btnRemover.setEnabled(false);
        btnNovo.setEnabled(false);
    }

    //DESATIVA CAMPOS CLIENTE
    void desativarCliente() {

        lbNome.setEnabled(false);
        lbRg.setEnabled(false);
        lbCelular.setEnabled(false);
        lbEmail.setEnabled(false);
        lbDataNasc.setEnabled(false);
        lbCpf.setEnabled(false);
        lbTelefone.setEnabled(false);
        txtNome.setEnabled(false);
        txtRG.setEnabled(false);
        txtCelular.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDatNasc.setEnabled(false);
        txtCpf.setEnabled(false);
        txtTelefone.setEnabled(false);
        lbPesq.setEnabled(false);
        txtPesq.setEnabled(true);
        tblCliente.setEnabled(true);

        btnPesq.setEnabled(true);
        btnEditar.setEnabled(false);
        btnInserir.setEnabled(true);
        btnRemover.setEnabled(false);
        btnNovo.setEnabled(false);
    }

    //METODO LIMPAR CLIENTE
    public void limparCliente() {
        lbNome.setText(null);
        lbRg.setText(null);
        lbCelular.setText(null);
        lbEmail.setText(null);
        lbDataNasc.setText(null);
        lbCpf.setText(null);
        lbTelefone.setText(null);
        txtNome.setText(null);
        txtRG.setText(null);
        txtCelular.setText(null);
        txtEmail.setText(null);
        txtDatNasc.setText(null);
        txtCpf.setText(null);
        txtTelefone.setText(null);
        lbPesq.setText(null);
        txtPesq.setText(null);
        //  tblCliente.setEnabled(false);
    }

    //METODO INSERIR ENDERECO JSON
    public Endereco inserirEndereco() throws Exception {

        Endereco endereco = new Endereco();
        endereco.setLogradouro(txtRua.getText().toUpperCase());
        endereco.setNumero(Integer.parseInt(txtNum.getText()));
        endereco.setComplemento(txtComplemento.getText().toUpperCase());
        endereco.setCep(txtCep.getText());
        endereco.setBairro(txtBairro.getText().toUpperCase());
        endereco.setLocalidade(txtCidade.getText().toUpperCase());
        endereco.setUf(cbEstado.getSelectedItem().toString().toUpperCase());
        endereco.getPessoa_idpessoa();
        return endereco;
    }

    //METODO ATIVAR ENDEREÇO
    public void ativaEndereco() {
        lbRua.setEnabled(true);
        txtRua.setEnabled(true);
        lbNum.setEnabled(true);
        txtNum.setEnabled(true);
        lbComplemento.setEnabled(true);
        txtComplemento.setEnabled(true);
        lbCep.setEnabled(true);
        txtCep.setEnabled(true);
        lbBairro.setEnabled(true);
        txtBairro.setEnabled(true);
        lbCidade.setEnabled(true);
        txtCidade.setEnabled(true);
        lbEstado.setEnabled(true);
        cbEstado.setEnabled(true);
    }

    //METODO LIMPAR CAMPOS
    public void limparEndereco() {
        lbRua.setText(null);
        txtRua.setText(null);
        lbNum.setText(null);
        txtNum.setText(null);
        lbComplemento.setText(null);
        txtComplemento.setText(null);
        lbCep.setText(null);
        txtCep.setText(null);
        lbBairro.setText(null);
        txtBairro.setText(null);
        lbCidade.setText(null);
        txtCidade.setText(null);
        lbEstado.setText(null);
        tabelas.setEnabledAt(1, false);
        tabelas.setEnabledAt(0, true);
        tabelas.setSelectedIndex(0);

    }

    //METODO LIMPAR CAMPOS
    void limpar() {

        txtDatNasc.setText("");
        txtDatNasc.setEnabled(false);
        lbDataNasc.setEnabled(false);
        txtCelular.setText("");
        lbCelular.setEnabled(false);
        txtCelular.setEnabled(false);
        lbTelefone.setEnabled(false);
        txtTelefone.setText("");
        txtTelefone.setEnabled(false);
        txtNome.setText(null);
        lbNome.setEnabled(false);
        txtRG.setText(null);
        lbRg.setEnabled(false);
        txtRG.setEnabled(false);
        txtEmail.setText(null);
        txtEmail.setEnabled(false);
        lbEmail.setEnabled(false);
        lbCpf.setEnabled(false);
        txtNome.setEnabled(false);
        txtNome.setText(null);
        txtCpf.setEnabled(false);
        txtCpf.setText(null);
        lbPesq.setEnabled(true);
        txtPesq.setEnabled(true);

        //LIMPA DADOS ENDEREÇO
        txtRua.setText(null);
        txtNum.setText(null);
        txtComplemento.setText(null);
        txtCep.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);

        btnEditar.setEnabled(false);
        btnInserir.setEnabled(false);
        btnRemover.setEnabled(false);
        btnNovo.setEnabled(true);

        tabelas.setEnabledAt(0, false);
        tabelas.setEnabledAt(1, false);
        tabelas.setEnabledAt(2, false);
        tabelas.setSelectedIndex(0);

    }

    //EDITAR PESSOA
    public void editar() {

        String dataRecebida = txtDatNasc.getText();
        String[] dataSeparada = dataRecebida.split("/");
        LocalDate hoje = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));

        try {
            Cliente cliente = new Cliente();
            cliente.setNome(txtNome.getText().toUpperCase());
            cliente.setDataNasc(Date.valueOf(hoje));
            cliente.setCpf(txtCpf.getText().toUpperCase());
            cliente.setRg(txtRG.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setCelular(txtCelular.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setId(Integer.parseInt(txtId.getText()));

            Endereco endereco = new Endereco();
            ClienteControl controllerEndereco = new ClienteControl();
            endereco.setLogradouro(txtRua.getText().toUpperCase());
            endereco.setNumero(Integer.parseInt(txtNum.getText()));
            endereco.setComplemento(txtComplemento.getText().toUpperCase());
            endereco.setCep(txtCep.getText());
            endereco.setBairro(txtBairro.getText().toUpperCase());
            endereco.setLocalidade(txtCidade.getText().toUpperCase());
            endereco.setUf(cbEstado.getSelectedItem().toString().toUpperCase());
            endereco.setId(Integer.parseInt(idEnd.getText()));

            ClienteControl control = new ClienteControl();
            int i = 0;
            i = control.editar(cliente, endereco);
            if(i == 1){
                JOptionPane.showMessageDialog(txtComplemento, "Usuario editado com sucesso!!!");
            }
        } catch (Exception ex) {

            System.out.printf(dataRecebida, ex);
            //  Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        btnInserir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        tabelas = new javax.swing.JTabbedPane();
        tbcliente = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        txtDatNasc = new javax.swing.JFormattedTextField();
        txtRG = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        lbTelefone = new javax.swing.JLabel();
        lbCelular = new javax.swing.JLabel();
        lbDataNasc = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        lbRg = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        txtPesq = new javax.swing.JTextField();
        btnPesq = new javax.swing.JToggleButton();
        lbPesq = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        tbendereco = new javax.swing.JPanel();
        txtRua = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        lbCidade = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbNum = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        idEnd = new javax.swing.JTextField();
        lbRua = new javax.swing.JLabel();
        lbCep = new javax.swing.JLabel();
        lbBairro = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEndereco = new javax.swing.JTable();
        txtNum = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        cbEstado = new javax.swing.JComboBox();
        txtComplemento = new javax.swing.JTextField();
        lbComplemento = new javax.swing.JLabel();
        btnVisualizar = new javax.swing.JButton();
        tbveiculo = new javax.swing.JPanel();
        txtCor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        idVeic = new javax.swing.JTextField();
        lbRua1 = new javax.swing.JLabel();
        lbCep1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbVeiculo = new javax.swing.JTable();
        txtAno = new javax.swing.JTextField();
        lbComplemento1 = new javax.swing.JLabel();
        cbmodelo = new javax.swing.JComboBox();
        lbRua2 = new javax.swing.JLabel();
        cbmarca = new javax.swing.JComboBox();
        txtKm = new javax.swing.JTextField();
        lbCep2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        jLabel1.setText("Cadastro de Clientes");

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

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/insert-card.png"))); // NOI18N
        btnInserir.setText("Inserir");
        btnInserir.setEnabled(false);
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
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

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/list.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        tabelas.setEnabled(false);
        tabelas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        tbcliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNome.setEnabled(false);

        try {
            txtDatNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDatNasc.setEnabled(false);

        txtRG.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRG.setEnabled(false);

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setEnabled(false);

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setEnabled(false);

        lbTelefone.setText("TELEFONE");
        lbTelefone.setEnabled(false);

        lbCelular.setText("CELULAR");
        lbCelular.setEnabled(false);

        lbDataNasc.setText("DATA NASCIMENTO");
        lbDataNasc.setEnabled(false);

        txtEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtEmail.setEnabled(false);

        lbEmail.setText("E-MAIL");
        lbEmail.setEnabled(false);

        jLabel7.setText("ID");
        jLabel7.setEnabled(false);

        txtId.setEnabled(false);

        lbNome.setText("NOME");
        lbNome.setEnabled(false);

        lbRg.setText("RG");
        lbRg.setEnabled(false);

        lbCpf.setText("CPF");
        lbCpf.setEnabled(false);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "DATA NASC", "E-MAIL", "CELULAR", "TELEFONE", "CPF", "RG"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCliente);

        txtPesq.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesqActionPerformed(evt);
            }
        });

        btnPesq.setBackground(new java.awt.Color(153, 255, 255));
        btnPesq.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPesq.setText("PESUISAR");

        lbPesq.setText("DIGITE");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setEnabled(false);

        javax.swing.GroupLayout tbclienteLayout = new javax.swing.GroupLayout(tbcliente);
        tbcliente.setLayout(tbclienteLayout);
        tbclienteLayout.setHorizontalGroup(
            tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbclienteLayout.createSequentialGroup()
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbclienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbclienteLayout.createSequentialGroup()
                                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tbclienteLayout.createSequentialGroup()
                                        .addComponent(lbRg)
                                        .addGap(214, 214, 214))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbclienteLayout.createSequentialGroup()
                                        .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(tbclienteLayout.createSequentialGroup()
                                                .addComponent(lbTelefone)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtRG))
                                        .addGap(18, 18, 18)))
                                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tbclienteLayout.createSequentialGroup()
                                        .addComponent(lbCpf)
                                        .addGap(104, 104, 104))
                                    .addComponent(txtCpf)))
                            .addGroup(tbclienteLayout.createSequentialGroup()
                                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbEmail)
                                    .addGroup(tbclienteLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(334, 334, 334)
                                        .addComponent(lbDataNasc))
                                    .addComponent(txtEmail))
                                .addGap(0, 45, Short.MAX_VALUE))))
                    .addGroup(tbclienteLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNome)
                            .addGroup(tbclienteLayout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDatNasc))))
                    .addGroup(tbclienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addGroup(tbclienteLayout.createSequentialGroup()
                                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tbclienteLayout.createSequentialGroup()
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbCelular)
                                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbPesq)
                                        .addGroup(tbclienteLayout.createSequentialGroup()
                                            .addComponent(txtPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        tbclienteLayout.setVerticalGroup(
            tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbclienteLayout.createSequentialGroup()
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataNasc)
                    .addComponent(jLabel7)
                    .addComponent(lbNome))
                .addGap(5, 5, 5)
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDatNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRg)
                    .addComponent(lbCpf))
                .addGap(1, 1, 1)
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtRG, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefone)
                    .addComponent(lbCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPesq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tbclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelas.addTab("Cliente", tbcliente);

        tbendereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtRua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRua.setEnabled(false);

        txtCep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCep.setEnabled(false);
        txtCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCepFocusLost(evt);
            }
        });

        txtBairro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBairro.setEnabled(false);

        lbCidade.setText("CIDADE");
        lbCidade.setEnabled(false);

        lbEstado.setText("UF");
        lbEstado.setEnabled(false);

        lbNum.setText("N°");
        lbNum.setEnabled(false);

        jLabel8.setText("ID");
        jLabel8.setEnabled(false);

        idEnd.setEnabled(false);

        lbRua.setText("RUA");
        lbRua.setEnabled(false);

        lbCep.setText("CEP");
        lbCep.setEnabled(false);

        lbBairro.setText("BAIRRO");
        lbBairro.setEnabled(false);

        tbEndereco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "RUA", "N°", "CEP", "BAIRRO", "CIDADE", "UF"
            }
        ));
        tbEndereco.setEnabled(false);
        jScrollPane3.setViewportView(tbEndereco);

        txtNum.setEnabled(false);

        txtCidade.setEnabled(false);

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR" }));
        cbEstado.setEnabled(false);
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        txtComplemento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtComplemento.setEnabled(false);

        lbComplemento.setText("COMPLEMENTO");
        lbComplemento.setEnabled(false);

        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/medium.png"))); // NOI18N
        btnVisualizar.setText("visualizar");
        btnVisualizar.setEnabled(false);
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tbenderecoLayout = new javax.swing.GroupLayout(tbendereco);
        tbendereco.setLayout(tbenderecoLayout);
        tbenderecoLayout.setHorizontalGroup(
            tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbenderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(tbenderecoLayout.createSequentialGroup()
                        .addComponent(idEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRua)
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tbenderecoLayout.createSequentialGroup()
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBairro)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCidade)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(tbenderecoLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(344, 344, 344)
                            .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbNum)
                                .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(tbenderecoLayout.createSequentialGroup()
                            .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbComplemento)
                                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbCep)
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        tbenderecoLayout.setVerticalGroup(
            tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbenderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNum)
                    .addComponent(jLabel8)
                    .addComponent(lbRua))
                .addGap(6, 6, 6)
                .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tbenderecoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtRua, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbenderecoLayout.createSequentialGroup()
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbComplemento)
                            .addComponent(lbCep))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbBairro)
                        .addGap(1, 1, 1)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbenderecoLayout.createSequentialGroup()
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCidade)
                            .addComponent(lbEstado))
                        .addGap(6, 6, 6)
                        .addGroup(tbenderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVisualizar))))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabelas.addTab("Endereço", tbendereco);

        tbveiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCor.setEnabled(false);
        txtCor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorFocusLost(evt);
            }
        });

        jLabel9.setText("ID");
        jLabel9.setEnabled(false);

        idVeic.setEnabled(false);

        lbRua1.setText("MODELO");
        lbRua1.setEnabled(false);

        lbCep1.setText("COR");
        lbCep1.setEnabled(false);

        tbVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MARCA", "MODELO", "ANO", "COR", "KM"
            }
        ));
        tbVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVeiculoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbVeiculo);

        txtAno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAno.setEnabled(false);

        lbComplemento1.setText("ANO");
        lbComplemento1.setEnabled(false);

        cbmodelo.setEnabled(false);

        lbRua2.setText("MARCA");
        lbRua2.setEnabled(false);

        cbmarca.setEnabled(false);
        cbmarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmarcaItemStateChanged(evt);
            }
        });
        cbmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmarcaActionPerformed(evt);
            }
        });

        txtKm.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtKm.setEnabled(false);
        txtKm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKmFocusLost(evt);
            }
        });

        lbCep2.setText("KM");
        lbCep2.setEnabled(false);

        jLabel3.setText("VEICULOS DO CLIENTE");

        javax.swing.GroupLayout tbveiculoLayout = new javax.swing.GroupLayout(tbveiculo);
        tbveiculo.setLayout(tbveiculoLayout);
        tbveiculoLayout.setHorizontalGroup(
            tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbveiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbveiculoLayout.createSequentialGroup()
                        .addComponent(lbComplemento1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tbveiculoLayout.createSequentialGroup()
                        .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(tbveiculoLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(485, 485, 485))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tbveiculoLayout.createSequentialGroup()
                                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCor)
                                        .addGroup(tbveiculoLayout.createSequentialGroup()
                                            .addComponent(lbCep1)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbCep2))
                                    .addGap(15, 15, 15)))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tbveiculoLayout.createSequentialGroup()
                        .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbveiculoLayout.createSequentialGroup()
                                .addComponent(idVeic, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(tbveiculoLayout.createSequentialGroup()
                                        .addComponent(lbRua2)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbRua1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4))
                        .addContainerGap())))
        );
        tbveiculoLayout.setVerticalGroup(
            tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbveiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbRua1)
                    .addComponent(lbRua2))
                .addGap(7, 7, 7)
                .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tbveiculoLayout.createSequentialGroup()
                        .addComponent(idVeic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbveiculoLayout.createSequentialGroup()
                        .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbmodelo)
                            .addComponent(cbmarca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tbveiculoLayout.createSequentialGroup()
                        .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCep1)
                            .addComponent(lbComplemento1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tbveiculoLayout.createSequentialGroup()
                        .addComponent(lbCep2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tbveiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabelas.addTab("Veiculo", tbveiculo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelas)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(tabelas)
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed

        if (btnInsert == 0) {
            String dataRecebida = txtDatNasc.getText();
            String[] dataSeparada = dataRecebida.split("/");
            LocalDate hoje = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));

            ClienteControl control = new ClienteControl();
            Cliente cliente = new Cliente();
            cliente.setNome(txtNome.getText().toUpperCase());
            cliente.setDataNasc(Date.valueOf(hoje));
            cliente.setRg(txtDatNasc.getText().toUpperCase());
            cliente.setCpf(txtCpf.getText());
            cliente.setTelefone(txtTelefone.getText().toUpperCase());
            cliente.setCelular(txtCelular.getText().toUpperCase());
            cliente.setEmail(txtEmail.getText().toUpperCase());
            control.inserir(cliente);
            limparCliente();
            ativaEndereco();
            tabelas.setEnabledAt(0, false);
            tabelas.setEnabledAt(1, true);
            tabelas.setSelectedIndex(1);
            btnInsert = 1;
            try {
                listarTabela();
            } catch (Exception ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (btnInsert == 1) {
            EnderecoControl control = new EnderecoControl();
            Endereco endereco = new Endereco();
            endereco.setLogradouro(txtRua.getText());
            endereco.setNumero(Integer.parseInt(txtNum.getText()));
            endereco.setComplemento(txtComplemento.getText());
            endereco.setCep(txtCep.getText());
            endereco.setBairro(txtBairro.getText());
            endereco.setLocalidade(txtCidade.getText());
            endereco.setUf(cbEstado.getSelectedItem().toString());

            control.validaEndereco(endereco);
            limparEndereco();
            desativarCliente();
            tabelas.setEnabledAt(0, false);
            tabelas.setEnabledAt(1, false);
            tabelas.setEnabledAt(2, true);
            tabelas.setSelectedIndex(2);
            btnInsert = 2;
        } else if (btnInsert == 2){

            Veiculo v = new Veiculo();
            VeiculoDao dao = new VeiculoDao();
            
//            v.setMarca(cbmarca.getSelectedItem().toString());
//            v.setModelo(cbmodelo.getSelectedItem().toString());
//            v.setAno(Integer.parseInt(txtAno.getText()));
//            v.setCor(txtCor.getText());
//            v.setKm(Double.parseDouble(txtKm.getText()));
//            v.setId(Integer.parseInt(txtId.getText()));
            
            System.out.print(txtId.getText());
            
            
            btnInsert = 0;

        }

    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        try {
            editar();
            listarTabela();

        } catch (Exception ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);

        }
        limpar();
        tabelas.setEnabled(false);
        btnInserir.setEnabled(false);
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
        tabelas.setSelectedIndex(0);

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed

        int resposta = JOptionPane.showConfirmDialog(txtComplemento, "Deseja realmente excluir os dados deste cliente", "Atenção!!!", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtId.getText());
            ClienteControl cliente = new ClienteControl();
            try {
                cliente.deletar(id);
                listarTabela();
                limpar();

            } catch (Exception ex) {
                Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        cbmarca.setEnabled(true);
        cbmodelo.setEnabled(true);
        try {
            ClienteDao dao = new ClienteDao();
            txtId.setText(String.valueOf(dao.buscarId()));
            ativarCliente();
            ativarNovo();
        } catch (Exception ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnNovoActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        try {
            
            setarCampos();
            setarEndereco();
            setarVeiculo();
            
            
        } catch (Exception ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblClienteMouseClicked

    private void txtPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesqActionPerformed

    private void txtCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCepFocusLost
        EnderecoControl controllerEndereco = new EnderecoControl();
        String cep = txtCep.getText();
        Endereco endereco = new Endereco();
        try {

            endereco = controllerEndereco.pegaEndereco(cep);
            txtRua.setText(endereco.getLogradouro());
            txtCidade.setText(endereco.getLocalidade());
            txtBairro.setText(endereco.getBairro());
            cbEstado.setSelectedItem(endereco.getUf());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "CPF invalido!!!");
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCepFocusLost

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tabelas.setEnabledAt(1, false);
        ativaEndereco();
        try {
            listarTabela();
        } catch (Exception ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        String rua = txtRua.getText();
        int num = Integer.parseInt(txtNum.getText());
        String cep = txtCep.getText();
        String bairro = txtBairro.getText();
        String cidade = txtCidade.getText();
        String url2 = "https://www.google.com.br/maps/search/" + rua.replace(" ", "%20") + ",+" + num + ",+foz%20do%20iguacu/data=!3m1!4b1";

        try {
            GoogleAPIControl control = new GoogleAPIControl();
            control.abriMapa2(url2);

            //String key = "AIzaSyDZL37hvqGfIKRTun_KQ6uzt0SpuFRKJos";
            HttpExemplo http = new HttpExemplo();
            String key = "AIzaSyDZL37hvqGfIKRTun_KQ6uzt0SpuFRKJos";
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + rua.replace(" ", "%20") + "," + num + "," + bairro.replace(" ", "%20") + "," + cidade.replace(" ", "%20") + "+CA&key=" + key + "";
            String json = http.sendGet(url);

            Gson gson = new Gson();

            java.lang.reflect.Type usuarioType = new TypeToken<GoogleAPI>() {
            }.getType();

            GoogleAPI j = gson.fromJson(json, usuarioType);

           // JOptionPane.showMessageDialog(txtComplemento, j.results[0].geometry.location.lat);
           // j = loadResultFromJSONGson(getJson(new URL(url)));
//            
            double lat = j.results[0].geometry.location.lat;
            double lng = j.results[0].geometry.location.lng;
//            
//            System.out.printf(" akii ------------> "+lat +"/"+lng);
//          //  double lat = control.pegaLatitude(rua, num, bairro, cidade);          
//
            control.abrirMapa(rua, String.valueOf(num), bairro, cidade, cep, String.valueOf(lat), String.valueOf(lng));

            //JFrame frame = new JFrame("JxMaps - Foz do iguaçu Paraná!");
            //  frame.setVisible(true);
            // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//             frame.setSize(700, 500);
//             frame.setLocationRelativeTo(null);
            //SETO AS VARIAVEIS
            //CHAMO A API DE CONTROLE PEGA O LATITUDE E LOGINTUDE DO JSON
        } catch (MalformedURLException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void txtCorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorFocusLost

    private void txtKmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKmFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKmFocusLost

    private void cbmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmarcaActionPerformed
       
    }//GEN-LAST:event_cbmarcaActionPerformed

    private void cbmarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmarcaItemStateChanged
         
    }//GEN-LAST:event_cbmarcaItemStateChanged

    private void tbVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVeiculoMouseClicked
      
        try {
            Veiculo veiculo = new Veiculo();
            VeiculoDao v = new VeiculoDao();
              int row = tbVeiculo.getSelectedRow();
              int id = Integer.parseInt(tbVeiculo.getValueAt(row, 0).toString());
               veiculo =  v.buscarDados(id);
             
              idVeic.setText(String.valueOf(veiculo.getId()));
              cbmarca.setEnabled(true);
              cbmodelo.setEnabled(true);
              cbmarca.getModel().setSelectedItem(veiculo.getMarca());
              cbmodelo.getModel().setSelectedItem(veiculo.getModelo());
              txtAno.setEnabled(true);
              txtAno.setText(String.valueOf(veiculo.getAno()));
              txtCor.setEnabled(true);
              txtCor.setText(veiculo.getCor());
              txtKm.setEnabled(true);
              txtKm.setText(String.valueOf(veiculo.getKm()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbVeiculoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JToggleButton btnPesq;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox cbEstado;
    public static javax.swing.JComboBox cbmarca;
    private javax.swing.JComboBox cbmodelo;
    private javax.swing.JTextField idEnd;
    private javax.swing.JTextField idVeic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCep1;
    private javax.swing.JLabel lbCep2;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbComplemento;
    private javax.swing.JLabel lbComplemento1;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbDataNasc;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNum;
    private javax.swing.JLabel lbPesq;
    private javax.swing.JLabel lbRg;
    private javax.swing.JLabel lbRua;
    private javax.swing.JLabel lbRua1;
    private javax.swing.JLabel lbRua2;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JTabbedPane tabelas;
    public static javax.swing.JTable tbEndereco;
    public static javax.swing.JTable tbVeiculo;
    private javax.swing.JPanel tbcliente;
    private javax.swing.JPanel tbendereco;
    public static javax.swing.JTable tblCliente;
    private javax.swing.JPanel tbveiculo;
    private javax.swing.JTextField txtAno;
    public static javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    public static javax.swing.JTextField txtCep;
    public static javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    public static javax.swing.JTextField txtCor;
    public static javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDatNasc;
    private javax.swing.JTextField txtEmail;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtKm;
    private javax.swing.JTextField txtNome;
    public static javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtPesq;
    private javax.swing.JTextField txtRG;
    public static javax.swing.JTextField txtRua;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables

    private void setarCampos() throws SQLException {

        int row = tblCliente.getSelectedRow();
        int id = Integer.parseInt(tblCliente.getValueAt(row, 0).toString());
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        
        Cliente cliente = new Cliente();
        ClienteControl control = new ClienteControl();
        cliente = control.setarCampos(id);
        
        txtId.setText(String.valueOf(id));
        txtNome.setText(cliente.getNome());
        txtDatNasc.setText(fmt.format(cliente.getDataNasc()));
        txtRG.setText(cliente.getRg());
        txtCpf.setText(cliente.getCpf());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());

        ativarCliente();
        ativaEndereco();
        tabelas.setEnabledAt(0, true);
        tabelas.setEnabledAt(2, true);
         
    }
//SETAR ENDEREÇO
    private void setarEndereco() throws SQLException {
        tabelas.setEnabled(true);
        tabelas.setEnabledAt(1, true);
        int row = tblCliente.getSelectedRow();
        int id = Integer.parseInt(tblCliente.getValueAt(row, 0).toString());

        EnderecoControl control = new EnderecoControl();
        Endereco endereco = new Endereco();
        endereco = control.listar(id);

        idEnd.setText(String.valueOf(endereco.getId()));
        txtRua.setText(endereco.getLogradouro());
        txtNum.setText(String.valueOf(endereco.getNumero()));
        txtComplemento.setText(endereco.getComplemento());
        txtCep.setText(endereco.getCep());
        txtBairro.setText(endereco.getBairro());
        txtCidade.setText(endereco.getLocalidade());
        cbEstado.setSelectedItem(endereco.getUf());
        btnVisualizar.setEnabled(true);
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"RUA", "NUMERO"});
        model.addRow(new Object[]{endereco.getLogradouro(), endereco.getNumero()});

        tbEndereco.setModel(model);
        tbEndereco.getColumnModel().getColumn(0).setPreferredWidth(260);
        tbEndereco.getColumnModel().getColumn(1).setPreferredWidth(60);

    }
    //SETAR VEICULO
    public void setarVeiculo(){
        tabelas.setEnabled(true);
        tabelas.setEnabledAt(2, true);
        
        VeiculoDao v = new VeiculoDao();
        
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "MARCA", "MODELO", "COR", "ANO", "KM"});

        List<Veiculo> lista = new ArrayList();
        lista =  v.listar(Integer.parseInt(txtId.getText()));

        for (Veiculo listaRetorno : lista) {
            model.addRow(new Object[]{listaRetorno.getId(), listaRetorno.getMarca(), listaRetorno.getModelo(), listaRetorno.getCor(), listaRetorno.getAno(), listaRetorno.getKm()});
        }

        TelaCliente.tbVeiculo.setModel(model);
        TelaCliente.tbVeiculo.getColumnModel().getColumn(0).setPreferredWidth(25);
        TelaCliente.tbVeiculo.getColumnModel().getColumn(1).setPreferredWidth(150);
    }

}
