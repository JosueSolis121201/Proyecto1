
package proyecto_1.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyecto_1.logica.LogicaSucursales;


public class VistaAdminCrearSucursal {
    private JFrame principal;

    private JTable tabla;

    
    public VistaAdminCrearSucursal(JTable tabla) {
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 500, 650);
        this.tabla = tabla;
        

        
        this.Crear();
    }
    
    public void Crear() {
        JButton btnAgregar = new JButton("Agregar");
        
        
        
        

        
        btnAgregar.setBounds(167,350, 250, 30);
        this.principal.setLayout(null);
        this.principal.add(btnAgregar);
        
        
        JTextField texto = new JTextField("0");
        texto.setBounds(167, 100, 250, 30); 
        this.principal.add(texto);
        
        JTextField texto2 = new JTextField("");
        texto2.setBounds(167, 150, 250, 30); 
        this.principal.add(texto2);
        
        JLabel label = new JLabel("Nombre");
        label.setBounds(70, 150, 110, 30); 
        this.principal.add(label);
        
        JLabel labe2 = new JLabel("Codigo");
        labe2.setBounds(70, 100, 110, 30); 
        this.principal.add(labe2);
        
        JLabel labe3 = new JLabel("Crear Nueva Sucursal");
        labe3.setBounds(250, 60, 200, 30); 
        this.principal.add(labe3);
        
      
        
        JTextField texto3 = new JTextField("");
        texto3.setBounds(167, 200, 250, 30); 
        this.principal.add(texto3);
 
        JLabel labe4 = new JLabel("Direccion");
        labe4.setBounds(70, 200, 110, 30); 
        this.principal.add(labe4);

         
        JTextField texto4 = new JTextField("");
        texto4.setBounds(167, 250, 250, 30); 
        this.principal.add(texto4);
 
        JLabel labe5 = new JLabel("Correo");
        labe5.setBounds(70, 250, 110, 30); 
        this.principal.add(labe5);        
        
        
        JTextField texto5 = new JTextField("0");
        texto5.setBounds(167, 300, 250, 30); 
        this.principal.add(texto5);
 
        JLabel labe6 = new JLabel("Telefono");
        labe6.setBounds(70, 300, 110, 30); 
        this.principal.add(labe6);          
        
        btnAgregar .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogicaSucursales logic = new LogicaSucursales();
                int codigo=Integer.parseInt(texto.getText());
                int Telefono=Integer.parseInt(texto5.getText());

                if(logic.crear(codigo, texto2.getText(), texto3.getText(), texto4.getText(), Telefono)){
                    JOptionPane.showMessageDialog(null, "Usuario Creado");
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo crear usuario");
                }
                tabla.setModel(logic.listadoOficial());
                
                
                


            }
        });
        
    }
    
    
    
    
    
    
    
    
}
