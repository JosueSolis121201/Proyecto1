
package proyecto_1.vistas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import proyecto_1.logica.LogicaSucursales;



public class VistaVendedor {
    

    private JFrame principal;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
        private JPanel panelPrincipal;
        private JPanel panel5;

    
    
    private int sucursalSeleccionada;
    private int productoSeleccionado;
    private int clienteSeleccionado;
    private int vendedorSeleccionado;
    
    public VistaVendedor() {
    /*this.sucursalSeleccionada = 0;
    this.productoSeleccionado = 0;
    this.clienteSeleccionado = 0;
    this.vendedorSeleccionado = 0;*/
    
    
    
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 1000, 600);
         
         
        
        
       
        
        this.panelPrincipal = new JPanel();
       
        
        
        this.panelPrincipal.setBounds(0, 0, 1000, 600);
        this.panelPrincipal.setBackground(Color.WHITE);
        
        
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBounds(40, 20,900, 500);

        
        
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

        this.principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.principal.repaint();

   
        this.GenerarNuevaVenta();
        this.GenerarVentas();
    
         this.principal.add(panelPrincipal);
    
    
    }
    public void GenerarNuevaVenta(){
        
        
        
        JLabel label = new JLabel("¡Bievenido"+"(USURARIO)"+"!");
        label.setBounds(750, 0, 200, 20); 
        this.panelPrincipal.add(label);
        
        
        
        this.panel3 = new JPanel();
        this.panel3.setBounds(50, 20, 800,200);
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

        JLabel labe4 = new JLabel("No. Factura");
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

        JLabel labe6 = new JLabel("Nombre");
        labe6.setBounds(182, 75, 300, 20);
        this.panel3.add(labe6);

        JTextField texto3 = new JTextField("");
        texto3.setBounds(250, 75, 200, 20);
        this.panel3.add(texto3);

        JLabel labe7 = new JLabel("Fecha");
        labe7.setBounds(475, 75, 300, 20);
        this.panel3.add(labe7);

        JTextField texto4 = new JTextField("");
        texto4.setBounds(525, 75, 200, 20);
        this.panel3.add(texto4);

        
        
      
        
        //Filtrados
        JLabel labep = new JLabel("Filtrados:");
        labep.setBounds(20, 150, 300, 20);
        this.panel3.add(labep);

        JButton btnFiltros = new JButton("Aplicar Filtros");

        btnFiltros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnFiltros.setBounds(182, 120, 540, 20);
        this.panel3.setLayout(null);
        this.panel3.add(btnFiltros);

        JLabel labe8 = new JLabel("Cliente");
        labe8.setBounds(182, 150, 300, 20);
        this.panel3.add(labe8);

        JTextField texto5 = new JTextField("");
        texto5.setBounds(250, 150, 300, 20);
        this.panel3.add(texto5);

        JButton btnNuevoCliente = new JButton("Nuevo Cliente");

        btnNuevoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new VistaAdminAutenticacion();
            }
        }
        );

        btnNuevoCliente.setBounds(600, 150, 125, 20);
        this.panel3.setLayout(null);
        this.panel3.add(btnNuevoCliente);

        
        
        //Agregar Productos

        this.panel4 = new JPanel();
        this.panel4.setBounds(50, 245, 800,215);
        this.panel4.setBackground(Color.WHITE);
        this.panel1.add(panel4);
        this.panel4.setLayout(null);
        
        JLabel labe9 = new JLabel("Fecha:");
        labe9.setBounds(385, 10, 60, 20);
        this.panel4.add(labe9);
        
        JLabel labe10 = new JLabel("<fecha>");
        labe10.setBounds(450, 10, 60, 20);
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

        JTextField texto6 = new JTextField("");
        texto6.setBounds(80, 45, 200, 20);
        this.panel4.add(texto6);
    
        JLabel labe15 = new JLabel("Cantidad");
        labe15.setBounds(335, 45, 300, 20);
        this.panel4.add(labe15);

        JTextField texto7 = new JTextField("");
        texto7.setBounds(400, 45, 200, 20);
        this.panel4.add(texto7);
        
        JButton btnAgregar = new JButton("Agregar");

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new VistaAdminAutenticacion();
            }
        }   
        );

        btnAgregar.setBounds(670, 45, 100, 20);
        this.panel4.setLayout(null);
        this.panel4.add(btnAgregar);
        
        
        //----------------------------------------------------
        //---------------Tabla--------------------------------
        //----------------------------------------------------       
    
        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);
        
        LogicaSucursales logic = new LogicaSucursales();
        table.setModel(logic.listadoOficial());
        
        
        JScrollPane scroll =  new JScrollPane(table);
        scroll.setVisible(true);
        this.panel4.add(scroll);
        scroll.setBounds(50, 75, 700, 70);
        
        
         table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int codSeleccionado = Integer.parseInt( (String)table.getValueAt(table.getSelectedRow() ,0));
                sucursalSeleccionada = codSeleccionado;
                int cantidadColumnas = table.getModel().getColumnCount()-1;
                int columnaActual = table.getSelectedColumn();
                if(columnaActual  == cantidadColumnas){
                    LogicaSucursales logic = new LogicaSucursales();
                    
                    
                    if(logic.eliminar(codSeleccionado)){
                        table.setModel(logic.listadoOficial());
                        JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                    }else{
                         JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                    }
                    
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
        
         
        JButton btnVender = new JButton("Vender");

        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new VistaAdminAutenticacion();
            }
        }   
        );
// -------------------Fin tabla--------------------------------
        btnVender.setBounds(50, 160, 500, 20);
        this.panel4.setLayout(null);
        this.panel4.add(btnVender);
         
        JLabel labe16 = new JLabel("Total");
        labe16.setBounds(560, 160, 300, 20);
        this.panel4.add(labe16);

        JTextField texto8 = new JTextField("");
        texto8.setBounds(600, 160, 150, 20);
        this.panel4.add(texto8); 
         
        
        
    }
    
    public void GenerarVentas(){
        
        
        
        
        
    
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

        btnFiltros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );
        

        btnFiltros.setBounds(182, 120, 540, 20);
        this.panel2.setLayout(null);
        this.panel2.add(btnFiltros);
        
        
        //---------------------Tabla-----------------------------------
        //---------------------Tabla-----------------------------------
        //---------------------Tabla-----------------------------------

        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);
        
        LogicaSucursales logic = new LogicaSucursales();
        table.setModel(logic.listadoOficial());
        
        
        JScrollPane scroll =  new JScrollPane(table);
        scroll.setVisible(true);
        this.panel2.add(scroll);
        scroll.setBounds(50, 200, 800, 250);
        
        
         table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int codSeleccionado = Integer.parseInt( (String)table.getValueAt(table.getSelectedRow() ,0));
                sucursalSeleccionada = codSeleccionado;
                int cantidadColumnas = table.getModel().getColumnCount()-1;
                int columnaActual = table.getSelectedColumn();
                if(columnaActual  == cantidadColumnas){
                    LogicaSucursales logic = new LogicaSucursales();
                    
                    
                    if(logic.eliminar(codSeleccionado)){
                        table.setModel(logic.listadoOficial());
                        JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                    }else{
                         JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                    }
                    
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
        
         
        JButton btnVender = new JButton("Vender");

        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new VistaAdminAutenticacion();
            }
        }   
        );
        
        //-----------------------Fin Tabla---------

    
    }
    
    
    
}
