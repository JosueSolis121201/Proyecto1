package proyecto_1.vistas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Clientes;
import proyecto_1.datos.ProductoVenta;
import proyecto_1.datos.Productos;
import proyecto_1.datos.Vendedores;
import proyecto_1.datos.Ventas;
import proyecto_1.logica.LogicaGenerarVenta;
import proyecto_1.logica.LogicaVendedores;

public class VistaVendedor {

    private JFrame principal;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panelPrincipal;
    private JPanel panel5;
    private Vendedores vendedor;
    private ProductoVenta ventaActual[];
    private JTable tablen; 

    public VistaVendedor(Vendedores vendedor) {
        /*this.sucursalSeleccionada = 0;
    this.productoSeleccionado = 0;
    this.clienteSeleccionado = 0;
    this.vendedorSeleccionado = 0;*/
        this.vendedor = vendedor;
        this.ventaActual = new ProductoVenta[1000];

        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 1000, 600);

        this.panelPrincipal = new JPanel();

        this.panelPrincipal.setBounds(0, 0, 1000, 600);
        this.panelPrincipal.setBackground(Color.WHITE);

        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBounds(40, 20, 900, 500);

        this.panel1 = new JPanel();

        tabbedPane.addTab("Sucursales", null, panel1,
                "Sucursales");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        this.panel2 = new JPanel();
        tabbedPane.addTab("Producto", null, panel2,
                "Producto");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        this.panelPrincipal.add(tabbedPane);
        this.panel1.setLayout(null);
        this.panel2.setLayout(null);
        this.panelPrincipal.setLayout(null);

        /* this.GenerarSucursal();
        this.GenerarProductos();
        this.GenerarClientes();
        this.GenerarVendedor();*/

        this.GenerarNuevaVenta();
        this.GenerarVentas();

        this.principal.add(panelPrincipal);
        this.principal.repaint();
    }

    public void GenerarNuevaVenta() {

        JLabel label = new JLabel("¡Bievenido" + "("+this.vendedor.getNombre()+")" + "!");
        label.setBounds(750, 0, 200, 20);
        this.panelPrincipal.add(label);

        this.panel3 = new JPanel();
        this.panel3.setBounds(50, 20, 800, 200);
        this.panel3.setBackground(Color.WHITE);
        this.panel1.add(panel3);

        this.panel3.setLayout(null);

        //Seleccionar cliente
        JLabel labe2 = new JLabel("Seleccionar cliente");
        labe2.setBounds(10, 0, 300, 20);
        this.panel3.add(labe2);

        //Filtrar por:
        JLabel labe3 = new JLabel("Filtrar por:");
        labe3.setBounds(20, 25, 300, 20);
        this.panel3.add(labe3);

        JLabel labe4 = new JLabel("Nombre");
        labe4.setBounds(182, 32, 300, 20);
        this.panel3.add(labe4);

        JTextField texto1 = new JTextField("");
        texto1.setBounds(250, 32, 200, 20);
        this.panel3.add(texto1);

        JLabel labe5 = new JLabel("NIT");
        labe5.setBounds(475, 32, 300, 20);
        this.panel3.add(labe5);

        JTextField texto2 = new JTextField("");
        texto2.setBounds(525, 32, 200, 20);
        this.panel3.add(texto2);

        JLabel labe6 = new JLabel("Correo");
        labe6.setBounds(182, 75, 300, 20);
        this.panel3.add(labe6);

        JTextField texto3 = new JTextField("");
        texto3.setBounds(250, 75, 200, 20);
        this.panel3.add(texto3);

        JLabel labe7 = new JLabel("Genero");
        labe7.setBounds(475, 75, 300, 20);
        this.panel3.add(labe7);

        JTextField texto4 = new JTextField("");
        texto4.setBounds(525, 75, 200, 20);
        this.panel3.add(texto4);

        //Filtrados
        JLabel labep = new JLabel("Filtrados:");
        labep.setBounds(20, 150, 300, 20);
        this.panel3.add(labep);

        LogicaGenerarVenta logica = new LogicaGenerarVenta();
        JComboBox comboFiltroUno = new JComboBox();
        comboFiltroUno.setBounds(250, 150, 300, 20);
        this.panel3.add(comboFiltroUno);

        JButton btnFiltros = new JButton("Aplicar Filtros");
        logica.filtrarClientes(texto1.getText(), texto3.getText(), texto2.getText(), texto4.getText(), comboFiltroUno);

        btnFiltros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logica.filtrarClientes(texto1.getText(), texto3.getText(), texto2.getText(), texto4.getText(), comboFiltroUno);
            }
        }
        );

        btnFiltros.setBounds(182, 120, 540, 20);
        this.panel3.setLayout(null);
        this.panel3.add(btnFiltros);

        JLabel labe8 = new JLabel("Cliente");
        labe8.setBounds(182, 150, 300, 20);
        this.panel3.add(labe8);

        /*
        JButton btnNuevoCliente = new JButton("Nuevo Cliente");

        btnNuevoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new VistaAdminAutenticacion();
            }
        }
        );
 this.panel3.add(btnNuevoCliente);
        btnNuevoCliente.setBounds(600, 150, 125, 20);*/
        this.panel3.setLayout(null);

        //Agregar Productos
        this.panel4 = new JPanel();
        this.panel4.setBounds(50, 245, 800, 215);
        this.panel4.setBackground(Color.WHITE);
        this.panel1.add(panel4);
        this.panel4.setLayout(null);

        JLabel labe9 = new JLabel("Fecha:");
        labe9.setBounds(385, 10, 60, 20);
        this.panel4.add(labe9);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        JLabel labe10 = new JLabel(dtf.format(now));
        labe10.setBounds(450, 10, 200, 20);
        this.panel4.add(labe10);

        JLabel labe11 = new JLabel("No.");
        labe11.setBounds(600, 10, 60, 20);
        this.panel4.add(labe11);

        JLabel labe12 = new JLabel("<NO.>");
        labe12.setBounds(650, 10, 60, 20);
        this.panel4.add(labe12);

        JLabel labe13 = new JLabel("Agregar Productos");
        labe13.setBounds(5, 5, 300, 20);
        this.panel4.add(labe13);

        JLabel labe14 = new JLabel("Codigo");
        labe14.setBounds(30, 45, 300, 20);
        this.panel4.add(labe14);

        JComboBox comboCrear = new JComboBox();

        for (int k = 0; k < Proyecto_1.producto.length; k++) {
            if (Proyecto_1.producto[k] != null) {
                comboCrear.addItem(Proyecto_1.producto[k]);
            }
        }

        comboCrear.setBounds(80, 45, 200, 20);
        this.panel4.add(comboCrear);

        JLabel labe15 = new JLabel("Cantidad");
        labe15.setBounds(335, 45, 300, 20);
        this.panel4.add(labe15);

        JTextField texto7 = new JTextField("");
        texto7.setBounds(400, 45, 200, 20);
        this.panel4.add(texto7);

        JButton btnAgregar = new JButton("Agregar");

        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);

        LogicaGenerarVenta logic = new LogicaGenerarVenta();
        table.setModel(logic.listadoProductos(this.ventaActual));

        btnAgregar.setBounds(670, 45, 100, 20);
        this.panel4.setLayout(null);
        this.panel4.add(btnAgregar);

        //----------------------------------------------------
        //---------------Tabla--------------------------------
        //----------------------------------------------------       
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVisible(true);
        this.panel4.add(scroll);
        scroll.setBounds(50, 75, 700, 70);

        JButton btnVender = new JButton("Vender");

       
// -------------------Fin tabla--------------------------------
        btnVender.setBounds(50, 160, 500, 20);
        this.panel4.setLayout(null);
        this.panel4.add(btnVender);

        JLabel labe16 = new JLabel("Total");
        labe16.setBounds(560, 160, 300, 20);
        this.panel4.add(labe16);

        JTextField texto8 = new JTextField("");
        texto8.setBounds(600, 160, 150, 20);
        texto8.setEnabled(false);
        this.panel4.add(texto8);
        LogicaGenerarVenta l = new LogicaGenerarVenta();
        labe12.setText(l.numFactura() + "");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Productos seleccionado = (Productos) comboCrear.getSelectedItem();
                if (seleccionado != null) {

                    l.agregarAVenta(ventaActual, new ProductoVenta(Integer.parseInt(texto7.getText()), seleccionado));
                    table.setModel(l.listadoProductos(ventaActual));
                    texto8.setText(l.total(ventaActual) + "");

                }
            }
        }
        );
        
         btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogicaGenerarVenta l = new LogicaGenerarVenta();
                l.crearVenta((Clientes)comboFiltroUno.getSelectedItem(), vendedor, ventaActual, l.numFactura(), labe10.getText(), l.total(ventaActual));

                LogicaGenerarVenta logic = new LogicaGenerarVenta();
                tablen.setModel(logic.listadoOficial(vendedor));
                ventaActual = new ProductoVenta[ventaActual .length];
                 JOptionPane.showMessageDialog(null, "venta hecha");
                 table.setModel(l.listadoProductos(ventaActual));
                 labe12.setText(l.numFactura() + "");
                        
            }
        }
        );

    }

    public void GenerarVentas() {

        JLabel labe1 = new JLabel("Listado General");
        labe1.setBounds(10, 10, 300, 20);
        this.panel2.add(labe1);

        JLabel labe2 = new JLabel("Seleccionar cliente");
        labe2.setBounds(10, 0, 300, 20);
        this.panel3.add(labe2);

        //Filtrar por:
        JLabel labe3 = new JLabel("Filtrar por:");
        labe3.setBounds(20, 25, 300, 20);
        this.panel2.add(labe3);

        JLabel labe4 = new JLabel("No. Factura");
        labe4.setBounds(182, 32, 300, 20);
        this.panel2.add(labe4);

        JTextField texto = new JTextField("");
        texto.setBounds(250, 32, 200, 20);
        this.panel2.add(texto);

        JLabel labe5 = new JLabel("NIT");
        labe5.setBounds(475, 32, 300, 20);
        this.panel2.add(labe5);

        JTextField texto2 = new JTextField("");
        texto2.setBounds(525, 32, 200, 20);
        this.panel2.add(texto2);

        JLabel labe6 = new JLabel("Nombre");
        labe6.setBounds(182, 75, 300, 20);
        this.panel2.add(labe6);

        JTextField texto3 = new JTextField("");
        texto3.setBounds(250, 75, 200, 20);
        this.panel2.add(texto3);

        JLabel labe7 = new JLabel("Fecha");
        labe7.setBounds(475, 75, 300, 20);
        this.panel2.add(labe7);

        JTextField texto4 = new JTextField("");
        texto4.setBounds(525, 75, 200, 20);
        this.panel2.add(texto4);

        //Filtrados
        JLabel labep = new JLabel("Filtrados:");
        labep.setBounds(20, 150, 300, 20);
        this.panel2.add(labep);

        JButton btnFiltros = new JButton("Aplicar Filtros");
        LogicaGenerarVenta l = new LogicaGenerarVenta();
        btnFiltros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tablen.setModel(l.filtrarVentas(texto.getText(), texto3.getText(), texto2.getText(), texto4.getText()));
            }
        }
        );

        btnFiltros.setBounds(182, 120, 540, 20);
        this.panel2.setLayout(null);
        this.panel2.add(btnFiltros);

        //---------------------Tabla-----------------------------------
        //---------------------Tabla-----------------------------------
        //---------------------Tabla-----------------------------------
        this.tablen = new JTable();
        tablen.setPreferredSize(null);
        tablen.setFillsViewportHeight(true);

        LogicaGenerarVenta logic = new LogicaGenerarVenta();
        tablen.setModel(logic.listadoOficial(vendedor));

        JScrollPane scroll = new JScrollPane(tablen);
        scroll.setVisible(true);
        this.panel2.add(scroll);
        scroll.setBounds(50, 200, 800, 250);

        tablen.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int codSeleccionado = Integer.parseInt((String) tablen.getValueAt(tablen.getSelectedRow(), 0));
                int cantidadColumnas = tablen.getModel().getColumnCount() - 1;
                int columnaActual = tablen.getSelectedColumn();
                if (columnaActual == cantidadColumnas) {
                    
                     LogicaGenerarVenta logic = new LogicaGenerarVenta();
                     logic.exportarListadoPdf(codSeleccionado);

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

        });

      

        //-----------------------Fin Tabla---------
    }

}
