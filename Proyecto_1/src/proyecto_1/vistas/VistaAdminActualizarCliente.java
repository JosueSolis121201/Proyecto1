/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartPanel;
import proyecto_1.datos.Clientes;
import proyecto_1.datos.Productos;
import proyecto_1.logica.LogicaCliente;
import proyecto_1.logica.LogicaProductos;

/**
 *
 * @author Norki
 */
public class VistaAdminActualizarCliente {
    
    
    
    
    
    
    
    
    
    
    private JFrame principal;

    Clientes actual;
    JTable tabla; 
    private ChartPanel panelG;


    
    public VistaAdminActualizarCliente(Clientes actual,JTable tabla,ChartPanel panelGrafica) {
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 500, 650);
        this.actual = actual;
        this.tabla = tabla;
        this.panelG = panelGrafica;

        
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
        
        JLabel labe3 = new JLabel("Actualizar datos de Cliente");
        labe3.setBounds(250, 60, 200, 30); 
        this.principal.add(labe3);
        
      
        
        JTextField texto3 = new JTextField(this.actual.getNit()+"");
        texto3.setBounds(167, 200, 250, 30); 
        this.principal.add(texto3);
 
        JLabel labe4 = new JLabel("NIT");
        labe4.setBounds(70, 200, 110, 30); 
        this.principal.add(labe4);

         
        JTextField texto4 = new JTextField(this.actual.getCorreo());
        texto4.setBounds(167, 250, 250, 30); 
        this.principal.add(texto4);
 
        JLabel labe5 = new JLabel("Correo");
        labe5.setBounds(70, 250, 110, 30); 
        this.principal.add(labe5);        
        
        
        JTextField texto5 = new JTextField(this.actual.getGenero());
        texto5.setBounds(167, 300, 250, 30); 
        this.principal.add(texto5);
 
        JLabel labe6 = new JLabel("Genero");
        labe6.setBounds(70, 300, 110, 30); 
        this.principal.add(labe6);          

        
        
        
        
        
        btnAgregar .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                

                LogicaCliente logic = new LogicaCliente();
                if (logic.actualizar(Integer.parseInt(texto2.getText()+""), texto.getText(),(texto3.getText()+""),
                        texto4.getText(), texto5.getText())) {
                    JOptionPane.showMessageDialog(null, "Cliente Actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar Cliente");
                }
                tabla.setModel(logic.listadoOficial());
                panelG.setChart(logic.graficaGenero());
                    
                
                
                
                
                
            }
        });

    }
    
    
    
}
