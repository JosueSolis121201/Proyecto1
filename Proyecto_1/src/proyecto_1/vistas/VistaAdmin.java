/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1.vistas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import proyecto_1.datos.Clientes;
import proyecto_1.datos.Productos;
import proyecto_1.datos.Sucursales;
import proyecto_1.datos.Vendedores;
import proyecto_1.logica.LogicaCliente;
import proyecto_1.logica.LogicaProductos;
import proyecto_1.logica.LogicaSucursales;
import proyecto_1.logica.LogicaVendedores;

/**
 *
 * @author Norki
 */
public class VistaAdmin {

    private JFrame principal;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private int sucursalSeleccionada;
    private int productoSeleccionado;
    private int clienteSeleccionado;
    private int vendedorSeleccionado;
    
    public VistaAdmin() {
    this.sucursalSeleccionada = 0;
    this.productoSeleccionado = 0;
    this.clienteSeleccionado = 0;
    this.vendedorSeleccionado = 0;
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 1000, 500);

        this.tabbedPane = new JTabbedPane();
        this.principal.add(tabbedPane);

        this.panel1 = new JPanel();
        tabbedPane.addTab("Sucursales", null, panel1,
                "Sucursales");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        this.panel2 = new JPanel();
        tabbedPane.addTab("Producto", null, panel2,
                "Producto");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        this.panel3 = new JPanel();
        tabbedPane.addTab("Cliente", null, panel3,
                "Cliente");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        this.panel4 = new JPanel();
        tabbedPane.addTab("Vendedor", null, panel4,
                "Vendedor");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        this.GenerarSucursal();
        this.GenerarProductos();
        this.GenerarClientes();
        this.GenerarVendedor();

        this.principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.principal.repaint();


    }

    public void GenerarSucursal() {
        //TABLA
       



        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);
        
        LogicaSucursales logic = new LogicaSucursales();
        table.setModel(logic.listadoOficial());
        
        
        JScrollPane scroll =  new JScrollPane(table);
        scroll.setVisible(true);
        this.panel1.add(scroll);
        scroll.setBounds(0, 0, 400, 300);
        
        
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
        
        //BOTON
        JButton btnCrear = new JButton("Crear");
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminCrearSucursal(table);
            }
        }
        );

        btnCrear.setBounds(550, 150, 150, 30);
        this.panel1.setLayout(null);
        this.panel1.add(btnCrear);

        JButton btnCargaMasiva = new JButton("Carga Masiva");

        btnCargaMasiva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnCargaMasiva.setBounds(725, 150, 150, 30);
        this.panel1.setLayout(null);
        this.panel1.add(btnCargaMasiva);

        JButton btnActualizar = new JButton("Actualizar");

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogicaSucursales logic  =  new LogicaSucursales();
                Sucursales buscar = logic.buscarUno(sucursalSeleccionada);
                if(buscar==null){
                    JOptionPane.showMessageDialog(null, "No se selecciono sucursal");
                }else{
                    new VistaAdminActualizarSucursal(buscar,table);
                }
               
                
                
            }
        }
        );

        btnActualizar.setBounds(550, 200, 150, 30);
        this.panel1.setLayout(null);
        this.panel1.add(btnActualizar);

       

        JButton btnExportarListado = new JButton("Exportar Listado a PDF");

        btnExportarListado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnExportarListado.setBounds(550, 250, 325, 30);
        this.panel1.setLayout(null);
        this.panel1.add(btnExportarListado);


    }

    public void GenerarProductos() {
        



        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);
        
        LogicaProductos logic = new LogicaProductos();
        table.setModel(logic.listadoOficial());
        
        
        JScrollPane scroll =  new JScrollPane(table);
        scroll.setVisible(true);
        this.panel2.add(scroll);
        scroll.setBounds(0, 0, 400, 300);
        
        
         table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int codSeleccionado = Integer.parseInt( (String)table.getValueAt(table.getSelectedRow() ,0));
                productoSeleccionado = codSeleccionado;
                int cantidadColumnas = table.getModel().getColumnCount()-1;
                int columnaActual = table.getSelectedColumn();
                if(columnaActual  == cantidadColumnas){
                    LogicaProductos logic = new LogicaProductos();
                    
                    
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
        
        
        
        
        
        
        
        
        

        JButton btnCrear2 = new JButton("Crear");

        btnCrear2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                   new VistaAdminCrearProducto(table);
                
                
            }
        }
        );

        btnCrear2.setBounds(550, 50, 150, 30);
        this.panel2.setLayout(null);
        this.panel2.add(btnCrear2);

        JButton btnCargaMasiva = new JButton("Carga Masiva");

        btnCargaMasiva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnCargaMasiva.setBounds(725, 50, 150, 30);
        this.panel2.setLayout(null);
        this.panel2.add(btnCargaMasiva);

        JButton btnActualizar = new JButton("Actualizar");

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                LogicaProductos logic  =  new LogicaProductos();
                Productos buscar = logic.buscarUno(productoSeleccionado);
                if(buscar==null){
                    JOptionPane.showMessageDialog(null, "No se selecciono producto");
                }else{
                    new VistaAdminActualizarProducto(buscar,table);
                }
            }
        }
        );

        btnActualizar.setBounds(550, 100, 150, 30);
        this.panel2.setLayout(null);
        this.panel2.add(btnActualizar);

        JButton btnEliminar = new JButton("Eleminar");

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnEliminar.setBounds(725, 100, 150, 30);
        this.panel2.setLayout(null);
        this.panel2.add(btnEliminar);

        JButton btnExportarListado = new JButton("Exportar Listado a PDF");

        btnExportarListado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnExportarListado.setBounds(550, 150, 325, 30);
        this.panel2.setLayout(null);
        this.panel2.add(btnExportarListado);

        this.panel2.repaint();
    }

    public void GenerarClientes() {
        
        
        
        
        
        

        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);
        
        LogicaCliente logic = new LogicaCliente();
        table.setModel(logic.listadoOficial());
        
        
        JScrollPane scroll =  new JScrollPane(table);
        scroll.setVisible(true);
        this.panel3.add(scroll);
        scroll.setBounds(0, 0, 400, 300);
        
        
         table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int codSeleccionado = Integer.parseInt( (String)table.getValueAt(table.getSelectedRow() ,0));
                clienteSeleccionado = codSeleccionado;
                int cantidadColumnas = table.getModel().getColumnCount()-1;
                int columnaActual = table.getSelectedColumn();
                if(columnaActual  == cantidadColumnas){
                    LogicaCliente logic = new LogicaCliente();
                    
                    
                    if(logic.eliminar(codSeleccionado)){
                        table.setModel(logic.listadoOficial());
                        JOptionPane.showMessageDialog(null, "Cliente Eliminado");
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
        
        
        
        
        
        
        
        
        
        
        

        JButton btnCrear2 = new JButton("Crear");

        btnCrear2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
          new VistaAdminCrearCliente(table);

            }
        }
        );

        btnCrear2.setBounds(550, 50, 150, 30);
        this.panel3.setLayout(null);
        this.panel3.add(btnCrear2);

        JButton btnCargaMasiva = new JButton("Carga Masiva");

        btnCargaMasiva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnCargaMasiva.setBounds(725, 50, 150, 30);
        this.panel3.setLayout(null);
        this.panel3.add(btnCargaMasiva);

        JButton btnActualizar = new JButton("Actualizar");

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               
                
                
                LogicaCliente logic  =  new LogicaCliente();
                Clientes buscar = logic.buscarUno(clienteSeleccionado);
                if(buscar==null){
                    JOptionPane.showMessageDialog(null, "No se selecciono producto");
                }else{
                    new VistaAdminActualizarCliente(buscar,table);
                }
            
                
                
            }
        }
        );

        btnActualizar.setBounds(550, 100, 150, 30);
        this.panel3.setLayout(null);
        this.panel3.add(btnActualizar);

        JButton btnEliminar = new JButton("Eleminar");

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnEliminar.setBounds(725, 100, 150, 30);
        this.panel3.setLayout(null);
        this.panel3.add(btnEliminar);

        JButton btnExportarListado = new JButton("Exportar Listado a PDF");

        btnExportarListado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnExportarListado.setBounds(550, 150, 325, 30);
        this.panel3.setLayout(null);
        this.panel3.add(btnExportarListado);

        this.panel3.repaint();

    }

    public void GenerarVendedor() {

        
        
        
        

        JTable table = new JTable();
        table.setPreferredSize(null);
        table.setFillsViewportHeight(true);
        
        LogicaVendedores logic = new LogicaVendedores();
        table.setModel(logic.listadoOficial());
        
        
        JScrollPane scroll =  new JScrollPane(table);
        scroll.setVisible(true);
        this.panel4.add(scroll);
        scroll.setBounds(0, 0, 400, 300);
        
        
         table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int codSeleccionado = Integer.parseInt( (String)table.getValueAt(table.getSelectedRow() ,0));
                vendedorSeleccionado = codSeleccionado;
                int cantidadColumnas = table.getModel().getColumnCount()-1;
                int columnaActual = table.getSelectedColumn();
                if(columnaActual  == cantidadColumnas){
                    LogicaVendedores logic = new LogicaVendedores();
                    
                    
                    if(logic.eliminar(codSeleccionado)){
                        table.setModel(logic.listadoOficial());
                        JOptionPane.showMessageDialog(null, "Vendedor Eliminado");
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
        
        
        JButton btnCrear2 = new JButton("Crear");

        btnCrear2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminCrearVendedor(table);
            }
        }
        );

        btnCrear2.setBounds(550, 50, 150, 30);
        this.panel4.setLayout(null);
        this.panel4.add(btnCrear2);

        JButton btnCargaMasiva = new JButton("Carga Masiva");

        btnCargaMasiva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
                
            }
        }
        );

        btnCargaMasiva.setBounds(725, 50, 150, 30);
        this.panel4.setLayout(null);
        this.panel4.add(btnCargaMasiva);

        JButton btnActualizar = new JButton("Actualizar");

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               
                
                
                LogicaVendedores logic  =  new LogicaVendedores();
                Vendedores buscar = logic.buscarUno(vendedorSeleccionado);
                if(buscar==null){
                    JOptionPane.showMessageDialog(null, "No se selecciono Vendedor");
                }else{
                    new VistaAdminActualizarVendedor(buscar,table);
                }
            }
        }
        );

        btnActualizar.setBounds(550, 100, 150, 30);
        this.panel4.setLayout(null);
        this.panel4.add(btnActualizar);

        JButton btnEliminar = new JButton("Eleminar");

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnEliminar.setBounds(725, 100, 150, 30);
        this.panel4.setLayout(null);
        this.panel4.add(btnEliminar);

        JButton btnExportarListado = new JButton("Exportar Listado a PDF");

        btnExportarListado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VistaAdminAutenticacion();
            }
        }
        );

        btnExportarListado.setBounds(550, 150, 325, 30);
        this.panel4.setLayout(null);
        this.panel4.add(btnExportarListado);

        this.panel4.repaint();

    }
}
