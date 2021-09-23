
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import proyecto_1.logica.LogicaVendedores;


public class VistaAdminAutenticacion {
    
    
    
    private JFrame principal;


    
    public VistaAdminAutenticacion() {
        
        this.principal = new JFrame();
        this.principal.setVisible(true);
        this.principal.setBounds(0, 0, 500, 350);
        this.Autenticar();

    }

    public void Autenticar() {
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");

       
        
        
        
        
        
       
        
        btnIniciarSesion.setBounds(167,220, 250, 30);
        this.principal.setLayout(null);
        this.principal.add(btnIniciarSesion);
        
        
        JTextField texto = new JTextField("");
        texto.setBounds(167, 150, 250, 30); 
        this.principal.add(texto);
        
        JTextField texto2 = new JTextField("");
        texto2.setBounds(167, 100, 250, 30); 
        this.principal.add(texto2);
        
        JLabel label = new JLabel("Contraseña");
        label.setBounds(70, 150, 110, 30); 
        this.principal.add(label);
        
        JLabel labe2 = new JLabel("Codigo");
        labe2.setBounds(70, 100, 110, 30); 
        this.principal.add(labe2);
        
        JLabel labe3 = new JLabel("POS");
        labe3.setBounds(250, 60, 110, 30); 
        this.principal.add(labe3);
        
        
         btnIniciarSesion .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogicaVendedores ve =  new LogicaVendedores();
                if(ve.loginAdmin(texto2.getText(), texto.getText())){
                    new VistaAdmin();
                }else if(ve.loginUsuario(texto2.getText(), texto.getText())!=null){
                    new VistaVendedor(ve.loginUsuario(texto2.getText(), texto.getText()));
                }else{
                     JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                }
            }
        });
         this.principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }

    
}
