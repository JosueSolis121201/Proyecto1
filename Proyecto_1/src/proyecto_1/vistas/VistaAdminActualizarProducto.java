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
import proyecto_1.datos.Productos;
import proyecto_1.logica.LogicaProductos;

public class VistaAdminActualizarProducto {

    private JFrame principal;
    Productos actual;
    JTable tabla;
    private ChartPanel panelG;


    public VistaAdminActualizarProducto(Productos actual,JTable tabla,ChartPanel panelGrafica) {

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

        btnAgregar.setBounds(167, 350, 250, 30);
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

        JLabel labe3 = new JLabel("Actualziar Producto");
        labe3.setBounds(250, 60, 200, 30);
        this.principal.add(labe3);

        JTextField texto3 = new JTextField(this.actual.getDescripcion());
        texto3.setBounds(167, 200, 250, 30);
        this.principal.add(texto3);

        JLabel labe4 = new JLabel("Descripcion");
        labe4.setBounds(70, 200, 110, 30);
        this.principal.add(labe4);

        JTextField texto4 = new JTextField(this.actual.getCantidad() + "");
        texto4.setBounds(167, 250, 250, 30);
        this.principal.add(texto4);

        JLabel labe5 = new JLabel("Cantidad");
        labe5.setBounds(70, 250, 110, 30);
        this.principal.add(labe5);

        JTextField texto5 = new JTextField(this.actual.getPrecio() + "");
        texto5.setBounds(167, 300, 250, 30);
        this.principal.add(texto5);

        JLabel labe6 = new JLabel("Precio");
        labe6.setBounds(70, 300, 110, 30);
        this.principal.add(labe6);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                LogicaProductos logic = new LogicaProductos();
                if (logic.actualizar(Integer.parseInt(texto2.getText()), texto.getText(), texto3.getText(),
                        Integer.parseInt(texto4.getText()), Double.parseDouble(texto5.getText()))) {
                    JOptionPane.showMessageDialog(null, "Producto Actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar producto");
                }
                tabla.setModel(logic.listadoOficial());
                panelG.setChart(logic.top3Ventas("Top 3 Producots"));
               
            }
        });

    }

}
