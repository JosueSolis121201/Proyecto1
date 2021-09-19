
package proyecto_1.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import proyecto_1.datos.Vendedores;
import proyecto_1.logica.LogicaCliente;
import proyecto_1.logica.LogicaVendedores;


public class VistaAdminActualizarVendedor {
    
    
    
    
    private JFrame principal;
    Vendedores actual;
    JTable tabla; 

    

    
    public VistaAdminActualizarVendedor(Vendedores actual,JTable tabla) {
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 500, 650);
         this.actual = actual;
         this.tabla = tabla;

    
        
        this.Crear();
    }
    
    public void Crear() {
        JButton btnAgregar = new JButton("Actualizar");

        
        
        btnAgregar.setBounds(167,400, 250, 30);
        this.principal.setLayout(null);
        this.principal.add(btnAgregar);
        
        
        JTextField texto = new JTextField(this.actual.getNombre());
        texto.setBounds(167, 150, 250, 30); 
        this.principal.add(texto);
        
        JTextField texto2 = new JTextField(this.actual.getCodigo() + "");
        texto2.setBounds(167, 100, 250, 30); 
        texto2.setEnabled(false);
        this.principal.add(texto2);
        
        JLabel label = new JLabel("Nombre");
        label.setBounds(70, 150, 110, 30); 
        this.principal.add(label);
        
        JLabel labe2 = new JLabel("Codigo");
        labe2.setBounds(70, 100, 110, 30); 
        this.principal.add(labe2);
        
        JLabel labe3 = new JLabel("Actualizar datos de vendedor");
        labe3.setBounds(250, 60, 200, 30); 
        this.principal.add(labe3);
        
      
        
        JTextField texto3 = new JTextField(this.actual.getCaja()+"");
        texto3.setBounds(167, 200, 250, 30); 
        this.principal.add(texto3);
 
        JLabel labe4 = new JLabel("caja");
        labe4.setBounds(70, 200, 110, 30); 
        this.principal.add(labe4);

         
        JTextField texto4 = new JTextField(this.actual.getVentas()+"");
        texto4.setBounds(167, 250, 250, 30); 
        this.principal.add(texto4);
 
        JLabel labe5 = new JLabel("Venta");
        labe5.setBounds(70, 250, 110, 30); 
        this.principal.add(labe5);        
        
        
        JTextField texto5 = new JTextField(this.actual.getGenero());
        texto5.setBounds(167, 300, 250, 30); 
        this.principal.add(texto5);
 
        JLabel labe6 = new JLabel("Genero");
        labe6.setBounds(70, 300, 110, 30); 
        this.principal.add(labe6);          

        JTextField texto6 = new JTextField(this.actual.getPassword());
        texto6.setBounds(167, 350, 250, 30); 
        this.principal.add(texto6);
 
        JLabel labe7 = new JLabel("Password");
        labe7.setBounds(70, 350, 110, 30); 
        this.principal.add(labe7);          
                
        
        
        
        
        
        
        
        
        
                
                
        btnAgregar .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                

                LogicaVendedores logic = new LogicaVendedores();
                if (logic.actualizar(Integer.parseInt(texto2.getText()+""), texto.getText(),Integer.parseInt(texto3.getText()+""),
                        Integer.parseInt(texto4.getText()+""),texto5.getText(), texto6.getText())) {
                    JOptionPane.showMessageDialog(null, "Vendedores Actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar Vendedor");
                }
                tabla.setModel(logic.listadoOficial());
                
                
                
                
                
                
            }
        });

    }
    
    
    
}