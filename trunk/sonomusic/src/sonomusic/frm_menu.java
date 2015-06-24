package sonomusic;

import Clases.Cl_Almacen;
import Clases.Cl_Caja;
import Clases.Cl_Conectar;
import Clases.Cl_Hilo_Notificacion;
import Clases.Cl_Productos;
import Clases.Cl_Usuario;
import Vistas.frm_ver_ofertas;
import Clases.Cl_Varios;
import Clases.Cl_cuenta;
import Clases.table_render;
import Forms.frm_cierre_caja;
import Forms.frm_conf_doc;
import Forms.frm_reg_deposito;
import Forms.frm_reg_empresa;
import Forms.frm_reg_venta;
import Forms.frm_rpt_fechas;
import Vistas.frm_banco;
import Vistas.frm_cargos;
import Vistas.frm_cuentas;
import Vistas.frm_metas;
import Vistas.frm_moneda;
import Vistas.frm_mov_bancos;
import Vistas.frm_movimientos;
import Vistas.frm_und_medida;
import Vistas.frm_ver_adelantos;
import Vistas.frm_ver_almacen;
import Vistas.frm_ver_clasificacion;
import Vistas.frm_ver_cliente;
import Vistas.frm_ver_compras_prod;
import Vistas.frm_ver_compras_serv;
import Vistas.frm_ver_cotizacion;
import Vistas.frm_ver_empleado;
import Vistas.frm_ver_guias;
import Vistas.frm_ver_notas;
import Vistas.frm_ver_pago;
import Vistas.frm_ver_prod_alm;
import Vistas.frm_ver_prod_alm_det;
import Vistas.frm_ver_productos;
import Vistas.frm_ver_proveedores;
import Vistas.frm_ver_solicitudes;
import Vistas.frm_ver_usuarios;
import Vistas.frm_ver_venta;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;

public class frm_menu extends javax.swing.JFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Hilo_Notificacion noti = null;
    public static Cl_cuenta cue = new Cl_cuenta();
    public static Cl_Almacen alm = new Cl_Almacen();
    public static Cl_Usuario usu = new Cl_Usuario();
    public static Cl_Caja caja = new Cl_Caja();

    public frm_menu() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        capt_nom_pc();
        ver_id_almacen();
        lbl_alm.setText(alm.getNom());
        lbl_ciudad.setText(alm.getCiudad());
        txt_ruc.setText(alm.getRuc());
        txt_raz.setText(alm.getRaz_soc());
        // carga todas las notificacion primera vez
        noti = new Cl_Hilo_Notificacion();
        noti.start();
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceRaspberryTheme");
    }

    private String leer_almacen() {
        String almacen = null;
        try {
            File Ffichero = new File("almacen.txt");

            /*Si existe el fichero*/
            if (Ffichero.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
                String Slinea;
                //System.out.println("**********Leyendo Fichero***********");  
                /*Lee el fichero linea a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    /*Imprime la linea leida*/
                    almacen = Slinea;
                    // System.out.println(Slinea);                
                }
                //System.out.println("*********Fin Leer Fichero**********");  
                /*Cierra el flujo*/
                Flee.close();
                //return txt_alm;
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (IOException ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
        return almacen;
    }

    private void capt_nom_pc() {
        lbl_pc.setText(System.getProperty("user.name").toUpperCase());
    }

    private void ver_id_almacen() {
        try {
            Statement st = con.conexion();
            String ver_id = "select idAlmacen, nom_alm, dir_alm, ciudad, raz_soc, ruc, cuenta from almacen where nom_alm = '" + leer_almacen() + "'";
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                alm.setId(rs.getInt("idAlmacen"));
                alm.setNom(rs.getString("nom_alm"));
                alm.setCiudad(rs.getString("ciudad"));
                alm.setRuc(rs.getString("ruc"));
                alm.setRaz_soc(rs.getString("raz_soc"));
                alm.setDireccion(rs.getString("dir_alm"));
                cue.setId_cuen(rs.getInt("cuenta"));
                caja.setId(caja.id_caja(alm.getId()));
            } else {
                alm.setId(0);
                alm.setNom("NO EXISTE");
                alm.setCiudad("SIN NOMBRE");
                alm.setRuc("SIN RUC");
                alm.setRaz_soc("SIN RAZON SOCIAL");
                JOptionPane.showMessageDialog(null, "Por Favor seleccione un almacen");
                frm_ver_almacen almacen = new frm_ver_almacen();
                ven.llamar_ventana(almacen);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            System.out.print(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        lbl_user = new javax.swing.JLabel();
        lbl_alm = new javax.swing.JLabel();
        lbl_ciudad = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        lbl_pc = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txt_ruc = new javax.swing.JLabel();
        txt_raz = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jp_ventas = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jp_pedido = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jp_envio = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jp_productos = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jp_prod_alm = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jp_caja = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jp_salir = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_nro = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        m_clientes = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        m_ofertas = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        m_notas = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu - SONOMUSIC");
        setBackground(new java.awt.Color(212, 2, 2));
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png")
        );

        contenedor.setBackground(new java.awt.Color(254, 254, 254));
        contenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel2.setBackground(new java.awt.Color(212, 2, 2));

        lbl_user.setForeground(new java.awt.Color(254, 254, 254));
        lbl_user.setText("Usuario");

        lbl_alm.setForeground(new java.awt.Color(254, 254, 254));
        lbl_alm.setText("Almacen");

        lbl_ciudad.setForeground(new java.awt.Color(254, 254, 254));
        lbl_ciudad.setText("Ciudad:");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/computer.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user_thief_baldie.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/house.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/comment.png"))); // NOI18N

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbl_pc.setForeground(new java.awt.Color(254, 254, 254));
        lbl_pc.setText("jLabel1");

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txt_ruc.setForeground(new java.awt.Color(254, 254, 254));
        txt_ruc.setText("RUC");

        txt_raz.setForeground(new java.awt.Color(254, 254, 254));
        txt_raz.setText("RAZON SOCIAL");

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ruc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_raz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_ciudad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_alm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_user)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_alm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_raz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_pc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jp_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_ventasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_ventasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_ventasMousePressed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ventas");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ventas2.png"))); // NOI18N

        javax.swing.GroupLayout jp_ventasLayout = new javax.swing.GroupLayout(jp_ventas);
        jp_ventas.setLayout(jp_ventasLayout);
        jp_ventasLayout.setHorizontalGroup(
            jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_ventasLayout.setVerticalGroup(
            jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ventasLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jp_pedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_pedidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_pedidoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_pedidoMousePressed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Pedidos");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/envios.png"))); // NOI18N

        javax.swing.GroupLayout jp_pedidoLayout = new javax.swing.GroupLayout(jp_pedido);
        jp_pedido.setLayout(jp_pedidoLayout);
        jp_pedidoLayout.setHorizontalGroup(
            jp_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_pedidoLayout.setVerticalGroup(
            jp_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_pedidoLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        jp_envio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_envio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_envioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_envioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_envioMousePressed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Traslados");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel18.setBackground(new java.awt.Color(204, 204, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/envios2.png"))); // NOI18N

        javax.swing.GroupLayout jp_envioLayout = new javax.swing.GroupLayout(jp_envio);
        jp_envio.setLayout(jp_envioLayout);
        jp_envioLayout.setHorizontalGroup(
            jp_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_envioLayout.setVerticalGroup(
            jp_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_envioLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jp_productos.setToolTipText("Mis Productos");
        jp_productos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_productosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_productosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_productosMousePressed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Mis Productos");
        jLabel14.setToolTipText("Mis Productos");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel19.setBackground(new java.awt.Color(204, 204, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/productos.png"))); // NOI18N

        javax.swing.GroupLayout jp_productosLayout = new javax.swing.GroupLayout(jp_productos);
        jp_productos.setLayout(jp_productosLayout);
        jp_productosLayout.setHorizontalGroup(
            jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jp_productosLayout.setVerticalGroup(
            jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_productosLayout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        jp_prod_alm.setToolTipText("Productos en Tiendas");
        jp_prod_alm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_prod_alm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_prod_almMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_prod_almMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_prod_almMousePressed(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Productos en Almacen");
        jLabel15.setToolTipText("Productos en Tiendas");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel20.setBackground(new java.awt.Color(204, 204, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/productos_almacen.png"))); // NOI18N

        javax.swing.GroupLayout jp_prod_almLayout = new javax.swing.GroupLayout(jp_prod_alm);
        jp_prod_alm.setLayout(jp_prod_almLayout);
        jp_prod_almLayout.setHorizontalGroup(
            jp_prod_almLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_prod_almLayout.setVerticalGroup(
            jp_prod_almLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_prod_almLayout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        jp_caja.setToolTipText("Caja Chica");
        jp_caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_cajaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_cajaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_cajaMousePressed(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Caja Chica");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setBackground(new java.awt.Color(204, 204, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/caja_chica2.png"))); // NOI18N

        javax.swing.GroupLayout jp_cajaLayout = new javax.swing.GroupLayout(jp_caja);
        jp_caja.setLayout(jp_cajaLayout);
        jp_cajaLayout.setHorizontalGroup(
            jp_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_cajaLayout.setVerticalGroup(
            jp_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_cajaLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jp_salir.setToolTipText("Salir");
        jp_salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_salirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_salirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_salirMousePressed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Salir");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/salir_sistema.png"))); // NOI18N

        javax.swing.GroupLayout jp_salirLayout = new javax.swing.GroupLayout(jp_salir);
        jp_salir.setLayout(jp_salirLayout);
        jp_salirLayout.setHorizontalGroup(
            jp_salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_salirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jp_salirLayout.setVerticalGroup(
            jp_salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_salirLayout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jLabel1.setText("Notificaciones:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        lbl_nro.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lbl_nro.setText("lbl_nro");
        lbl_nro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_nro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_nroMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_prod_alm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_caja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nro)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(lbl_nro))
                .addContainerGap())
            .addComponent(jp_pedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_ventas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_envio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_productos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_prod_alm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_caja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        contenedor.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        contenedor.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBar1.setBackground(new java.awt.Color(212, 2, 2));
        jMenuBar1.setForeground(new java.awt.Color(254, 254, 254));
        jMenuBar1.setBorderPainted(false);

        jMenu1.setBackground(new java.awt.Color(212, 2, 2));
        jMenu1.setForeground(new java.awt.Color(254, 254, 254));
        jMenu1.setText("RR.HH.");
        jMenu1.setToolTipText("");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cargo.png"))); // NOI18N
        jMenuItem7.setText("Cargos");
        jMenuItem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/meta.png"))); // NOI18N
        jMenuItem10.setText("Metas");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);
        jMenu1.add(jSeparator6);

        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user_thief_baldie.png"))); // NOI18N
        jMenuItem8.setText("Colaborador");
        jMenuItem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/adelanto.png"))); // NOI18N
        jMenuItem9.setText("Adelantos");
        jMenuItem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/pagos.png"))); // NOI18N
        jMenuItem11.setText("Pago Colaborador");
        jMenuItem11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem38.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem38.setText("Rpt.Pagos");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem38);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(212, 2, 2));
        jMenu2.setForeground(new java.awt.Color(254, 254, 254));
        jMenu2.setText("Ventas");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        m_clientes.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        m_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user.png"))); // NOI18N
        m_clientes.setText("Clientes");
        m_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_clientesActionPerformed(evt);
            }
        });
        jMenu2.add(m_clientes);

        jMenuItem12.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cotizacion.png"))); // NOI18N
        jMenuItem12.setText("Cotizacion a Cliente");
        jMenuItem12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/compras.png"))); // NOI18N
        jMenuItem13.setText("Ventas");
        jMenuItem13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        m_ofertas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        m_ofertas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/basket.png"))); // NOI18N
        m_ofertas.setText("Ofertas");
        m_ofertas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_ofertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_ofertasActionPerformed(evt);
            }
        });
        jMenu2.add(m_ofertas);
        jMenu2.add(jSeparator5);

        jMenuItem28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem28.setText("Rpt. Venta Total x Empresa");
        jMenuItem28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem28);

        jMenuItem32.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem32.setText("Rpt. Venta x Almacen");
        jMenuItem32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem32);

        jMenuItem33.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem33.setText("Rpt. Venta x Vendedor");
        jMenuItem33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem33);

        jMenuItem14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem14.setText("Rpt. Ventas y Comision");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);
        jMenu2.add(jSeparator16);

        jMenuItem41.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuItem41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem41.setText("Rpt. Producto x Cliente");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem41);

        jMenuItem42.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem42.setText("Rpt. Ganancias por Almacen");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem42);

        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(212, 2, 2));
        jMenu3.setForeground(new java.awt.Color(254, 254, 254));
        jMenu3.setText("Compras");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/proveedor.png"))); // NOI18N
        jMenuItem2.setText("Proveedor");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);
        jMenu3.add(jSeparator4);

        jMenuItem15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/compra_producto.png"))); // NOI18N
        jMenuItem15.setText("Compras Productos");
        jMenuItem15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuItem21.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/compra_servicio.png"))); // NOI18N
        jMenuItem21.setText("Compras Servicios");
        jMenuItem21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);
        jMenu3.add(jSeparator15);

        jMenuItem31.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem31.setText("Rpt. Compras por Empresa");
        jMenuItem31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem31);

        jMenuItem29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem29.setText("Rpt. Compras por Producto");
        jMenuItem29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem29);

        jMenuItem30.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem30.setText("Rpt. Compras por Proveedor");
        jMenuItem30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem30);

        jMenuBar1.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(212, 2, 2));
        jMenu4.setForeground(new java.awt.Color(254, 254, 254));
        jMenu4.setText("Almacen");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/almacen.png"))); // NOI18N
        jMenuItem3.setText("Almacenes");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem22.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/delivery.png"))); // NOI18N
        jMenuItem22.setText("Traslados");
        jMenuItem22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);
        jMenu4.add(jSeparator1);

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/articulos_almacen.png"))); // NOI18N
        jMenuItem5.setText("Articulos en Almacen");
        jMenuItem5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/house.png"))); // NOI18N
        jMenuItem6.setText("Articulos x Almacen");
        jMenuItem6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/articulos_general.png"))); // NOI18N
        jMenuItem4.setText("Articulos en General");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);
        jMenu4.add(jSeparator2);

        jMenuItem16.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Classification.png"))); // NOI18N
        jMenuItem16.setText("Clasificacion");
        jMenuItem16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem17.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/unidad_medida.png"))); // NOI18N
        jMenuItem17.setText("Und. Medida");
        jMenuItem17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);
        jMenu4.add(jSeparator8);

        jMenuItem23.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/requerimiento.png"))); // NOI18N
        jMenuItem23.setText("Requerimiento de Articulos");
        jMenuItem23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem23);
        jMenu4.add(jSeparator3);

        jMenuItem27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem27.setText("Rotulos por Almacen");
        jMenuItem27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem27);

        jMenuItem34.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem34.setText("Rpt. Articulos x Almacen");
        jMenuItem34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem34);

        jMenuItem39.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem39.setText("Rpt. Articulos en General");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem39);

        jMenuItem40.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuItem40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem40.setText("Rpt. Valorizado Art. x Empresa");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem40);

        jMenuBar1.add(jMenu4);

        jMenu5.setBackground(new java.awt.Color(212, 2, 2));
        jMenu5.setForeground(new java.awt.Color(254, 254, 254));
        jMenu5.setText("Contabilidad");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem18.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/book.png"))); // NOI18N
        jMenuItem18.setText("Conf. Documentos");
        jMenuItem18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem18);

        jMenuItem19.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/caja_chica.png"))); // NOI18N
        jMenuItem19.setText("Caja Chica");
        jMenuItem19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem19);

        jMenuItem35.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/connect.png"))); // NOI18N
        jMenuItem35.setText("Cierre de Caja");
        jMenuItem35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem35);

        jMenuItem36.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem36.setText("Mov. Caja en Tienda");
        jMenuItem36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem36);

        jMenuItem37.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem37.setText("Mov. Bancos");
        jMenuItem37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem37);

        jMenuBar1.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(212, 2, 2));
        jMenu6.setForeground(new java.awt.Color(254, 254, 254));
        jMenu6.setText("Datos Generales");
        jMenu6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenuItem43.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/almacen.png"))); // NOI18N
        jMenuItem43.setText("Empresa");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem43);

        jMenuItem24.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/banco.png"))); // NOI18N
        jMenuItem24.setText("Bancos");
        jMenuItem24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem24);
        jMenu6.add(jSeparator7);

        jMenuItem20.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/business_users.png"))); // NOI18N
        jMenuItem20.setText("Usuarios");
        jMenuItem20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem20);

        m_notas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        m_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/notas.png"))); // NOI18N
        m_notas.setText("Notas");
        m_notas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_notasActionPerformed(evt);
            }
        });
        jMenu6.add(m_notas);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/vcard.png"))); // NOI18N
        jMenuItem1.setText("Cuentas");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuItem25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/currency.png"))); // NOI18N
        jMenuItem25.setText("Moneda");
        jMenuItem25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem25);

        jMenuItem26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/deposito.png"))); // NOI18N
        jMenuItem26.setText("Deposito");
        jMenuItem26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem26);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_clientesActionPerformed
        frm_ver_cliente clientes = new frm_ver_cliente();
        ven.llamar_ventana(clientes);
    }//GEN-LAST:event_m_clientesActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frm_ver_proveedores prov = new frm_ver_proveedores();
        ven.llamar_ventana(prov);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        frm_ver_almacen alm = new frm_ver_almacen();
        ven.llamar_ventana(alm);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (usu.getPer_ver_rrhh().equals("1")) {
            frm_cargos car = new frm_cargos();
            ven.llamar_ventana(car);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        frm_und_medida med = new frm_und_medida();
        ven.llamar_ventana(med);
    }//GEN-LAST:event_jMenuItem17ActionPerformed


    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        frm_ver_clasificacion cla = new frm_ver_clasificacion();
        ven.llamar_ventana(cla);
    }//GEN-LAST:event_jMenuItem16ActionPerformed


    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        frm_ver_productos productos = new frm_ver_productos();
        ven.llamar_ventana(productos);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        if (usu.getPer_compra_productos().equals("1")) {
            frm_ver_compras_prod com = new frm_ver_compras_prod();
            ven.llamar_ventana(com);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jp_ventasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_ventasMouseEntered
        jp_ventas.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_ventasMouseEntered

    private void jp_ventasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_ventasMouseExited
        jp_ventas.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_ventasMouseExited

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        if (usu.getPer_compra_servicios().equals("1")) {
            frm_ver_compras_serv compra = new frm_ver_compras_serv();
            ven.llamar_ventana(compra);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if (usu.getPer_ver_rrhh().equals("1")) {
            frm_ver_empleado empleados = new frm_ver_empleado();
            ven.llamar_ventana(empleados);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        if (usu.getPer_usuario().equals("1")) {
            frm_ver_usuarios usu = new frm_ver_usuarios();
            ven.llamar_ventana(usu);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        if (usu.getPer_ver_caja().equals("1")) {
            frm_movimientos movi = new frm_movimientos();
            ven.llamar_ventana(movi);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }

    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        frm_ver_cotizacion coti = new frm_ver_cotizacion();
        ven.llamar_ventana(coti);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void m_ofertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_ofertasActionPerformed
        if (usu.getPer_reg_oferta().equals("1")) {
            frm_ver_ofertas ofer = new frm_ver_ofertas();
            ven.llamar_ventana(ofer);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_m_ofertasActionPerformed

    private void m_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_notasActionPerformed
        frm_ver_notas nota = new frm_ver_notas();
        ven.llamar_ventana(nota);
    }//GEN-LAST:event_m_notasActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        frm_ver_guias guia = new frm_ver_guias();
        ven.llamar_ventana(guia);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (usu.getPer_ver_rrhh().equals("1")) {
            frm_metas met = new frm_metas();
            ven.llamar_ventana(met);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        frm_ver_venta ver_vent = new frm_ver_venta();
        ven.llamar_ventana(ver_vent);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        frm_ver_prod_alm_det pro_det_alm = new frm_ver_prod_alm_det();
        ven.llamar_ventana(pro_det_alm);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        frm_ver_solicitudes solc = new frm_ver_solicitudes();
        ven.llamar_ventana(solc);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Cl_Productos prod = new Cl_Productos();
        frm_ver_prod_alm mat = new frm_ver_prod_alm();
        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.costo_compra, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos inner join clasificacion as "
                + "c on p.id_clas=c.id_clas inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + alm.getId() + "' "
                + "order by p.desc_pro asc, p.modelo asc";
        prod.mostrar_productos(query);
        mat.txt_ida.setText("" + alm.getId());
        mat.txt_noma.setText(alm.getNom());
        mat.t_productos.setDefaultRenderer(Object.class, new table_render());
        ven.llamar_ventana(mat);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jp_pedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_pedidoMouseEntered
        jp_pedido.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_pedidoMouseEntered

    private void jp_pedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_pedidoMouseExited
        jp_pedido.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_pedidoMouseExited

    private void jp_envioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_envioMouseEntered
        jp_envio.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_envioMouseEntered

    private void jp_envioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_envioMouseExited
        jp_envio.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_envioMouseExited

    private void jp_productosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_productosMouseEntered
        jp_productos.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_productosMouseEntered

    private void jp_productosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_productosMouseExited
        jp_productos.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_productosMouseExited

    private void jp_prod_almMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_prod_almMouseEntered
        jp_prod_alm.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_prod_almMouseEntered

    private void jp_prod_almMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_prod_almMouseExited
        jp_prod_alm.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_prod_almMouseExited

    private void jp_cajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_cajaMouseEntered
        jp_caja.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_cajaMouseEntered

    private void jp_cajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_cajaMouseExited
        jp_caja.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_cajaMouseExited

    private void jp_salirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_salirMouseEntered
        jp_salir.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_salirMouseEntered

    private void jp_salirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_salirMouseExited
        jp_salir.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_salirMouseExited

    private void jp_salirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_salirMousePressed
        System.exit(0);
    }//GEN-LAST:event_jp_salirMousePressed

    private void jp_cajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_cajaMousePressed
        if (usu.getPer_reg_venta().equals("1")) {
            frm_movimientos movi = new frm_movimientos();
            ven.llamar_ventana(movi);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jp_cajaMousePressed

    private void jp_ventasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_ventasMousePressed
        if (usu.getPer_reg_venta().equals("1")) {
            frm_reg_venta vent = new frm_reg_venta();
            ven.llamar_ventana(vent);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jp_ventasMousePressed

    private void jp_pedidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_pedidoMousePressed
        frm_ver_solicitudes solc = new frm_ver_solicitudes();
        ven.llamar_ventana(solc);
    }//GEN-LAST:event_jp_pedidoMousePressed

    private void jp_envioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_envioMousePressed
        frm_ver_guias guia = new frm_ver_guias();
        ven.llamar_ventana(guia);
    }//GEN-LAST:event_jp_envioMousePressed

    private void jp_prod_almMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_prod_almMousePressed
        frm_ver_prod_alm_det pro_det_alm = new frm_ver_prod_alm_det();
        ven.llamar_ventana(pro_det_alm);
    }//GEN-LAST:event_jp_prod_almMousePressed

    private void jp_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_productosMousePressed
        Cl_Productos prod = new Cl_Productos();
        frm_ver_prod_alm mat = new frm_ver_prod_alm();
        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos inner join clasificacion as "
                + "c on p.id_clas=c.id_clas inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + alm.getId() + "' "
                + "order by p.desc_pro asc, p.modelo asc limit 0,50";
        prod.mostrar_productos(query);
        mat.txt_ida.setText("" + alm.getId());
        mat.txt_noma.setText(alm.getNom());
        mat.t_productos.setDefaultRenderer(Object.class, new table_render());
        ven.llamar_ventana(mat);
    }//GEN-LAST:event_jp_productosMousePressed

    private void lbl_nroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nroMousePressed
        noti = new Cl_Hilo_Notificacion();
        noti.start();
    }//GEN-LAST:event_lbl_nroMousePressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        noti = new Cl_Hilo_Notificacion();
        noti.start();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        if (usu.getPer_conf_documento().equals("1")) {
            frm_conf_doc conf = new frm_conf_doc();
            ven.llamar_ventana(conf);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (usu.getPer_cuentas().equals("1")) {
            frm_cuentas cue = new frm_cuentas();
            ven.llamar_ventana(cue);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        frm_moneda mon = new frm_moneda();
        ven.llamar_ventana(mon);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        frm_banco ban = new frm_banco();
        ven.llamar_ventana(ban);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        frm_reg_deposito dep = new frm_reg_deposito();
        ven.llamar_ventana(dep);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            // IMPRIME ROTULO X ALMACEN
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idalmacen", alm.getId());
            ven.ver_reporte("rotulo_pro_alm", parametros);
            System.out.println("imprimiento " + "rotulo_pro_alm");
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            //        frm_ver_empresa empresa = new frm_ver_empresa(); 
//        empresa.ventana="venta_total";
//        ven.llamar_ventana(empresa);          
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }

    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_productos prod = new frm_ver_productos();
            prod.ventana = "compra_productos";
            ven.llamar_ventana(prod);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
//        frm_ver_empresa empresa = new frm_ver_empresa();
//        empresa.ventana="compra_empresa";
//        ven.llamar_ventana(empresa);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_proveedores prov = new frm_ver_proveedores();
            prov.funcion = "compras_prov";
            ven.llamar_ventana(prov);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            // IMPRIME TOTAL DE ARTICULOS EN ALMACEN
            // Selecciona almacen
            frm_ver_almacen almacen = new frm_ver_almacen();
            almacen.ventana = "rpt_venta_alm";
            ven.llamar_ventana(almacen);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_usuarios usuario = new frm_ver_usuarios();
            usuario.rpt = "venta_vendedor";
            ven.llamar_ventana(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_rpt_fechas fec = new frm_rpt_fechas();
            fec.rpt = "cajas_tienda";
            ven.llamar_ventana(fec);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_mov_bancos mov_ban = new frm_mov_bancos();
            ven.llamar_ventana(mov_ban);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem37ActionPerformed
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if (usu.getPer_reg_adelanto().equals("1")) {
            frm_ver_adelantos ad = new frm_ver_adelantos();
            ven.llamar_ventana(ad);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (usu.getPer_ver_rrhh().equals("1")) {
            frm_ver_pago pago = new frm_ver_pago();
            ven.llamar_ventana(pago);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        if (usu.getPer_reg_venta().equals("1")) {
            frm_cierre_caja cierre = new frm_cierre_caja();
            ven.llamar_ventana(cierre);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            // REPORTE PRODUCTOS GENERAL
            ven.ver_reporte("rpt_prod", null);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            //        frm_ver_empresa empresa = new frm_ver_empresa();
//        empresa.ventana = "rpt_ganancia_almacen";
//        ven.llamar_ventana(empresa);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_almacen almacen = new frm_ver_almacen();
            almacen.ventana = "rpt_prod_alm";
            ven.llamar_ventana(almacen);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_usuarios usuario = new frm_ver_usuarios();
            usuario.rpt = "ganancia_vendedor";
            ven.llamar_ventana(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_cliente cliente = new frm_ver_cliente();
            cliente.rpt = "rpt_cliente";
            ven.llamar_ventana(cliente);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {
            frm_ver_almacen almacen = new frm_ver_almacen();
            almacen.ventana = "rpt_gana_venta";
            ven.llamar_ventana(almacen);
        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        frm_reg_empresa empresa = new frm_reg_empresa();
        ven.llamar_ventana(empresa);
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        if (usu.getPer_ver_reportes().equals("1")) {

        } else {
            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        }
    }//GEN-LAST:event_jMenuItem38ActionPerformed
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
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jp_caja;
    private javax.swing.JPanel jp_envio;
    private javax.swing.JPanel jp_pedido;
    private javax.swing.JPanel jp_prod_alm;
    private javax.swing.JPanel jp_productos;
    private javax.swing.JPanel jp_salir;
    private javax.swing.JPanel jp_ventas;
    public static javax.swing.JLabel lbl_alm;
    private javax.swing.JLabel lbl_ciudad;
    public static javax.swing.JLabel lbl_nro;
    private javax.swing.JLabel lbl_pc;
    public static javax.swing.JLabel lbl_user;
    private javax.swing.JMenuItem m_clientes;
    private javax.swing.JMenuItem m_notas;
    private javax.swing.JMenuItem m_ofertas;
    private javax.swing.JLabel txt_raz;
    private javax.swing.JLabel txt_ruc;
    // End of variables declaration//GEN-END:variables
}
