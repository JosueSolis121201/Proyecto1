
package proyecto_1.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;
import proyecto_1.logica.LogicaVendedores;


public class VistaAdminCrearVendedor {
    
    
    
    
    
    private JFrame principal;
   private JTable tabla;
   private ChartPanel panelG;

    

    
    public VistaAdminCrearVendedor(JTable tabla,ChartPanel panelGrafica) {
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 500, 650);
         this.tabla = tabla;
        
         this.panelG = panelGrafica;
    
        
        this.Crear();
    }
    
    public void Crear() {
        JButton btnAgregar = new JButton("Agregar");

        btnAgregar .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        
        btnAgregar.setBounds(167,400, 250, 30);
        this.principal.setLayout(null);
        this.principal.add(btnAgregar);
        
        
        JTextField texto = new JTextField("");
        texto.setBounds(167, 150, 250, 30); 
        this.principal.add(texto);
        
        JTextField texto2 = new JTextField("");
        texto2.setBounds(167, 100, 250, 30); 
        this.principal.add(texto2);
        
        JLabel label = new JLabel("Nombre");
        label.setBounds(70, 150, 110, 30); 
        this.principal.add(label);
        
        JLabel labe2 = new JLabel("Codigo");
        labe2.setBounds(70, 100, 110, 30); 
        this.principal.add(labe2);
        
        JLabel labe3 = new JLabel("Crear Nuevo Vendedor");
        labe3.setBounds(250, 60, 200, 30); 
        this.principal.add(labe3);
        
      
        
        JTextField texto3 = new JTextField("");
        texto3.setBounds(167, 200, 250, 30); 
        this.principal.add(texto3);
 
        JLabel labe4 = new JLabel("Caja");
        labe4.setBounds(70, 200, 110, 30); 
        this.principal.add(labe4);

         
        JTextField texto4 = new JTextField("");
        texto4.setBounds(167, 250, 250, 30); 
        this.principal.add(texto4);
 
        JLabel labe5 = new JLabel("Ventas");
        labe5.setBounds(70, 250, 110, 30); 
        this.principal.add(labe5);        
        
        
        JTextField texto5 = new JTextField("");
        texto5.setBounds(167, 300, 250, 30); 
        this.principal.add(texto5);
 
        JLabel labe6 = new JLabel("Genero");
        labe6.setBounds(70, 300, 110, 30); 
        this.principal.add(labe6);          
        
        JTextField texto6 = new JTextField("");
        texto6.setBounds(167, 350, 250, 30); 
        this.principal.add(texto6);
 
        JLabel labe7 = new JLabel("Password");
        labe7.setBounds(70, 350, 110, 30); 
        this.principal.add(labe7);          
                
        
        
        this.principal.repaint();
        
        
        
                btnAgregar .addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                LogicaVendedores logic = new LogicaVendedores();
                int codigo=Integer.parseInt(texto2.getText());
                int caja=Integer.parseInt(texto3.getText());
                int ventas=Integer.parseInt(texto4.getText());
                


                
                if(logic.crear(codigo, texto.getText(), caja,ventas, texto5.getText(),texto5.getText())){
                    JOptionPane.showMessageDialog(null, "Vendedor Creado");
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo crear Vendedor");
                }
                tabla.setModel(logic.listadoOficial()); 
                panelG.setChart(logic.top3Ventas("Top 3 Ventas"));
        
        
            }
        });
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
}
