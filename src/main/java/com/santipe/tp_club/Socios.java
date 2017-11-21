/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santipe.tp_club;

import java.awt.Container;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Trabajo practico - Clubes
 * @author Santiago Perrin <1santiagoperrin@gmail.com>
 */
public class Socios extends javax.swing.JFrame {
    private Connection conexion;
    private int modificandoSocio;
    /**
     * Creates new form Socios
     */
    public Socios() {
        initComponents();
        actualizarListadoSocios();
    }
    
    private Connection getDatabaseConnection() {
        if (this.conexion == null) {
            File temp = new File("tablaSocios.db");
            String connstr = "jdbc:sqlite:" + temp.getAbsolutePath().replace("\\","\\\\");
            try {
                Class.forName("org.sqlite.JDBC");
                this.conexion = DriverManager.getConnection(connstr);
                return this.conexion;
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al conectarse con la base de datos, al parecer el Driver 'org.sqlite.JDBC' no esta disponible.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al conectarse con la base de datos.");
            }
            return null;
        }
        return this.conexion;
    }
    
    private void actualizarListadoSocios() {
        // Creamos un "modelo"
        DefaultTableModel model = new DefaultTableModel();
        
        // Le describimos sus columnas
        String[] columnNames = {"Id", "Nombre", "Apellido", "Calle", "Numero", "Telefono", "Documento"};
        model.setColumnIdentifiers(columnNames);
        
        // Le avisamos al objeto jTable1 que tiene que usar este modelo
        jTable1.setModel(model);
        
        try {
            // Nos conectamos a la base de datos
            Connection conn = this.getDatabaseConnection();
            
            // Generamos una nueva consulta
            Statement stmt = conn.createStatement();
            
            // Executamos la consulta
            ResultSet rs = stmt.executeQuery("SELECT Id, Nombre, Apellido, Calle, Numero, Telefono, Documento FROM Socios");
            
            // Obtenemos todas las filas
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("Id"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Calle"),
                    rs.getInt("Numero"),
                    rs.getString("Telefono"),
                    rs.getInt("Documento")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener los socios de la base de datos.");
            System.out.println(e.getMessage());
        }
    }
    
    public boolean insertarSocio(String nombre, String apellido, String calle, int numero, String telefono, int documento) {
        try {
            // Obtenemos una conexion a la BD
            Connection conn = this.getDatabaseConnection();
            
            // Creamos una nueva consulta
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Socios (Nombre, Apellido, Calle, Numero, Telefono, Documento) VALUES (?, ?, ?, ?, ?, ?)");
            
            // Le damos los datos
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, calle);
            pstmt.setInt(4, numero);
            pstmt.setString(5, telefono);
            pstmt.setInt(6, documento);
            
            // Ejecutamos la consulta
            return (pstmt.executeUpdate() == 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar en la base de datos.");
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean modificarSocio(int id, String nombre, String apellido, String calle, int numero, String telefono, int documento) {
        try {
            // Obtenemos una conexion a la BD
            Connection conn = this.getDatabaseConnection();
            
            // Creamos una nueva consulta
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Socios SET Nombre = ?, Apellido = ?, Calle = ?, Numero = ?, Telefono = ?, Documento = ? WHERE Id = ?");
            
            // Le damos los datos
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, calle);
            pstmt.setInt(4, numero);
            pstmt.setString(5, telefono);
            pstmt.setInt(6, documento);
            pstmt.setInt(7, id);
            
            // Ejecutamos la consulta
            return (pstmt.executeUpdate() == 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar en la base de datos.");
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean eliminarSocio(int id) {
        try {
            // Obtenemos una conexion a la BD
            Connection conn = this.getDatabaseConnection();
            
            // Creamos una nueva consulta
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Socios WHERE Id = ?");
            
            // Le damos los datos
            pstmt.setInt(1, id);
                       
            // Ejecutamos la consulta
            return (pstmt.executeUpdate() == 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar registro de la base de datos.");
            System.out.println(ex.getMessage());
            return false;
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

        AltaSocio = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        darAlta = new javax.swing.JButton();
        cancelarAlta = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ModificarSocio = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        darAlta1 = new javax.swing.JButton();
        cancelarAlta1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        BajaSocio = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        hacerBaja = new javax.swing.JButton();
        cancelarBaja = new javax.swing.JButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        botonAlta = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonBaja = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        AltaSocio.setMinimumSize(new java.awt.Dimension(400, 360));

        jLabel1.setText("INGRESE NOMBRE");

        jLabel2.setText("INGRESE APELLIDO");

        jLabel3.setText("INGRESE CALLE");

        jLabel4.setText("INGRESE NUMERO");

        jLabel5.setText("INGRESE TELEFONO");

        jLabel6.setText("INGRESE DOCUMENTO");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        darAlta.setText("Aceptar");
        darAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darAltaActionPerformed(evt);
            }
        });

        cancelarAlta.setText("Cancelar");
        cancelarAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAltaActionPerformed(evt);
            }
        });

        jLabel7.setText("ALTA");

        javax.swing.GroupLayout AltaSocioLayout = new javax.swing.GroupLayout(AltaSocio.getContentPane());
        AltaSocio.getContentPane().setLayout(AltaSocioLayout);
        AltaSocioLayout.setHorizontalGroup(
            AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaSocioLayout.createSequentialGroup()
                .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AltaSocioLayout.createSequentialGroup()
                        .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AltaSocioLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AltaSocioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(darAlta)
                                .addGap(43, 43, 43)))
                        .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cancelarAlta)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)))
                    .addGroup(AltaSocioLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel7)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        AltaSocioLayout.setVerticalGroup(
            AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaSocioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(36, 36, 36)
                .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AltaSocioLayout.createSequentialGroup()
                        .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AltaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(darAlta)
                    .addComponent(cancelarAlta))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        ModificarSocio.setMinimumSize(new java.awt.Dimension(400, 360));

        jLabel10.setText("INGRESE NOMBRE");

        jLabel11.setText("INGRESE APELLIDO");

        jLabel12.setText("INGRESE CALLE");

        jLabel13.setText("INGRESE NUMERO");

        jLabel14.setText("INGRESE TELEFONO");

        jLabel15.setText("INGRESE DOCUMENTO");

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        darAlta1.setText("Aceptar");
        darAlta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darAlta1ActionPerformed(evt);
            }
        });

        cancelarAlta1.setText("Cancelar");
        cancelarAlta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAlta1ActionPerformed(evt);
            }
        });

        jLabel16.setText("MODIFICACION");

        javax.swing.GroupLayout ModificarSocioLayout = new javax.swing.GroupLayout(ModificarSocio.getContentPane());
        ModificarSocio.getContentPane().setLayout(ModificarSocioLayout);
        ModificarSocioLayout.setHorizontalGroup(
            ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModificarSocioLayout.createSequentialGroup()
                .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModificarSocioLayout.createSequentialGroup()
                        .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ModificarSocioLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarSocioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(darAlta1)
                                .addGap(43, 43, 43)))
                        .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cancelarAlta1)
                            .addComponent(jTextField8)
                            .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jTextField10)
                            .addComponent(jTextField11)
                            .addComponent(jTextField12)
                            .addComponent(jTextField13)))
                    .addGroup(ModificarSocioLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel16)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        ModificarSocioLayout.setVerticalGroup(
            ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModificarSocioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(36, 36, 36)
                .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ModificarSocioLayout.createSequentialGroup()
                        .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ModificarSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(darAlta1)
                    .addComponent(cancelarAlta1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("BAJA");

        jLabel9.setText("INGRESE EL NUMERO DE SOCIO A ELIMINAR");

        hacerBaja.setText("Aceptar");

        cancelarBaja.setText("Cancelar");

        javax.swing.GroupLayout BajaSocioLayout = new javax.swing.GroupLayout(BajaSocio.getContentPane());
        BajaSocio.getContentPane().setLayout(BajaSocioLayout);
        BajaSocioLayout.setHorizontalGroup(
            BajaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BajaSocioLayout.createSequentialGroup()
                .addGroup(BajaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BajaSocioLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel8))
                    .addGroup(BajaSocioLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BajaSocioLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(BajaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BajaSocioLayout.createSequentialGroup()
                                .addComponent(hacerBaja)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelarBaja))
                            .addComponent(jLabel9))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        BajaSocioLayout.setVerticalGroup(
            BajaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BajaSocioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(47, 47, 47)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(BajaSocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hacerBaja)
                    .addComponent(cancelarBaja))
                .addGap(45, 45, 45))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Socio N°", "Nombre", "Apellido", "Calle", "Número", "Telefono", "DNI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        botonAlta.setText("Alta Socio");
        botonAlta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAltaMouseClicked(evt);
            }
        });
        botonAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAltaActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar Socio");
        botonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonModificarMouseClicked(evt);
            }
        });
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonBaja.setText("Baja Socio");
        botonBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonBajaMouseClicked(evt);
            }
        });
        botonBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBajaActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Importar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exportar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(botonAlta)
                .addGap(62, 62, 62)
                .addComponent(botonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(botonBaja)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAlta)
                    .addComponent(botonModificar)
                    .addComponent(botonBaja))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAltaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAltaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAltaMouseClicked

    private void botonModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModificarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botonModificarMouseClicked

    private void botonBajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBajaMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void cancelarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAltaActionPerformed
        int n = JOptionPane.showConfirmDialog(
            null,
            "Estas seguro que deseas cancelar?",
            "Cancelar Alta",
            JOptionPane.YES_NO_OPTION);
        
        if (n == 0) {
            AltaSocio.dispose();
        }
    }//GEN-LAST:event_cancelarAltaActionPerformed

    private void botonAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAltaActionPerformed
        // TODO add your handling code here:
        this.conexion = getDatabaseConnection();
        this.limpiarAltaSocio();
        AltaSocio.setVisible(true);
    }//GEN-LAST:event_botonAltaActionPerformed

    private void darAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darAltaActionPerformed
        String nombre = jTextField1.getText();
        String apellido = jTextField2.getText();
        String calle = jTextField3.getText();
        String telefono = jTextField5.getText();
        int numero;
        int documento;
        
        try {
            numero = Integer.parseInt(jTextField4.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El numero de domicilio debe ser numerico.");
            return;
        }

        try {
            documento = Integer.parseInt(jTextField6.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El numero de documento debe ser numerico.");
            return;
        }
        
        if (insertarSocio(nombre, apellido, calle, numero, telefono, documento)) {
            AltaSocio.dispose();
            JOptionPane.showMessageDialog(null, "Nuevo socio insertado con exito.");
            actualizarListadoSocios();
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al agregar socio.");
        }
    }//GEN-LAST:event_darAltaActionPerformed

    private void botonBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBajaActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            // Si no hay nada seleccionado, no hago nada :D
            return;
        }
        
        int n = JOptionPane.showConfirmDialog(
            null,
            "Estas seguro que deseas eliminar el socio?",
            "Dar de baja",
            JOptionPane.YES_NO_OPTION);
        
        if (n == 0) {
            int idSocio = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            eliminarSocio(idSocio);
            actualizarListadoSocios();
        }
    }//GEN-LAST:event_botonBajaActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void darAlta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darAlta1ActionPerformed
        int n = JOptionPane.showConfirmDialog(
            null,
            "Estas seguro que deseas realizar la modificacion?",
            "Modificar",
            JOptionPane.YES_NO_OPTION);
        
        if (n == 0) {
            String nombre = jTextField8.getText();
            String apellido = jTextField9.getText();
            String calle = jTextField10.getText();
            String telefono = jTextField12.getText();
            int numero;
            int documento;

            try {
                numero = Integer.parseInt(jTextField11.getText());
            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El numero de domicilio debe ser numerico.");
                return;
            }

            try {
                documento = Integer.parseInt(jTextField13.getText());
            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El numero de documento debe ser numerico.");
                return;
            }

            if (modificarSocio(this.modificandoSocio, nombre, apellido, calle, numero, telefono, documento)) {
                this.modificandoSocio = 0;
                ModificarSocio.dispose();
                JOptionPane.showMessageDialog(null, "Socio modificado con exito.");
                actualizarListadoSocios();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al modificar socio.");
            }
        }
    }//GEN-LAST:event_darAlta1ActionPerformed

    private void cancelarAlta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAlta1ActionPerformed
        int n = JOptionPane.showConfirmDialog(
            null,
            "Estas seguro que deseas cancelar?",
            "Cancelar Modificacion",
            JOptionPane.YES_NO_OPTION);
        
        if (n == 0) {
            ModificarSocio.dispose();
        }
    }//GEN-LAST:event_cancelarAlta1ActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            // Si no hay nada seleccionado, no hago nada :D
            return;
        }
        
        int id = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        String nombre = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String apellido = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String calle = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        String numero = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        String telefono = jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString();
        String documento = jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString();
        
        jTextField8.setText(nombre);
        jTextField9.setText(apellido);
        jTextField10.setText(calle);
        jTextField11.setText(numero);
        jTextField12.setText(telefono);
        jTextField13.setText(documento);
        
        this.modificandoSocio = id;
        ModificarSocio.setVisible(true);
    }//GEN-LAST:event_botonModificarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "Aguantiiiia importar");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(null, "Aguantiiiia exportar");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int n = JOptionPane.showConfirmDialog(
            null,
            "Estas seguro que deseas salir de la aplicacion?",
            "Salir",
            JOptionPane.YES_NO_OPTION);
        
        if (n == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Socios().setVisible(true);
            }
        });
    }
    
    public void limpiarAltaSocio() {
        this.jTextField1.setText("");
        this.jTextField2.setText("");
        this.jTextField3.setText("");
        this.jTextField4.setText("");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
        this.jTextField7.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AltaSocio;
    private javax.swing.JDialog BajaSocio;
    private javax.swing.JDialog ModificarSocio;
    private javax.swing.JButton botonAlta;
    private javax.swing.JButton botonBaja;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton cancelarAlta;
    private javax.swing.JButton cancelarAlta1;
    private javax.swing.JButton cancelarBaja;
    private javax.swing.JButton darAlta;
    private javax.swing.JButton darAlta1;
    private javax.swing.JButton hacerBaja;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
