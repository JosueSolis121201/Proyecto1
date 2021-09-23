
package proyecto_1.logica;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Productos;


public class LogicaProductos {

    public void descargarDatos(String nombre, Object guardar) {
        try {
            ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(nombre));
            serial.writeObject(guardar);
        } catch (Exception ex) {
            System.out.println("Error al serializar");
            System.out.println(ex);
        }
    }

    public JFreeChart top3Ventas(String Titulo) {
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        //  
        final String productos = "Productos";

        Productos top[] = new Productos[Proyecto_1.producto.length];
        for (int i = 0; i < Proyecto_1.producto.length; i++) {
            if (Proyecto_1.producto[i] != null) {
                top[i] = Proyecto_1.producto[i];
            }

        }
        for (int j = 0; j < Proyecto_1.producto.length; j++) {
            for (int i = 0; i < Proyecto_1.producto.length - 1; i++) {
                if (top[i] == null) {
                    break;
                } else {
                    if (top[i + 1] != null) {
                        if (top[i].getCantidad() < top[i + 1].getCantidad()) {
                            Productos actual = top[i];
                            top[i] = top[i + 1];
                            top[i + 1] = actual;

                        }
                    }
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            if (top[j] != null) {

                dataset.addValue(top[j].getCantidad(), top[j].getNombre(), productos);
            }
        }

        //
        JFreeChart barChart = ChartFactory.createBarChart(
                Titulo,
                "Productos",
                "Numero",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        return barChart;
    }

    public void exportarListadoPdf() {

        Document documento = new Document();
        FileOutputStream ficheroPdf = null;
        try {
            ficheroPdf = new FileOutputStream("ReporteProductos.pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            documento.open();
            documento.add(new Paragraph("Reporte Producto",
                    FontFactory.getFont("arial",
                            22,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabla = new PdfPTable(5);
            String[] header = {"codigo", "nombre", "descripcion", "cantidad", "precio"};
            for (String cada : header) {
                System.out.println(cada);
                tabla.addCell(cada);
            }

            for (int i = 0; i < Proyecto_1.producto.length; i++) {

                if (Proyecto_1.producto[i] != null) {

                    String[] fila = {Proyecto_1.producto[i].getCodigo() + "", Proyecto_1.producto[i].getNombre(),
                        Proyecto_1.producto[i].getDescripcion(), Proyecto_1.producto[i].getCantidad() + "",
                        Proyecto_1.producto[i].getPrecio() + ""};

                    for (String cada : fila) {
                        tabla.addCell(cada);

                    }

                }

            }

            documento.add(tabla);
            documento.close();
        } catch (Exception ex) {
            Logger.getLogger(Proyecto_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultTableModel listadoOficial() {
        String[] header = {"codigo", "nombre", "descripcion", "cantidad", "precio", "Accion"};
        String[][] data = {};
        DefaultTableModel modelo = new DefaultTableModel(data, header);

        for (int i = 0; i < Proyecto_1.producto.length; i++) {

            if (Proyecto_1.producto[i] != null) {

                String[] fila = {Proyecto_1.producto[i].getCodigo() + "", Proyecto_1.producto[i].getNombre(),
                    Proyecto_1.producto[i].getDescripcion(), Proyecto_1.producto[i].getCantidad() + "",
                    Proyecto_1.producto[i].getPrecio() + "", "Eliminar"};
                modelo.addRow(fila);

            }

        }

        this.descargarDatos("producto", Proyecto_1.producto);

        return modelo;
    }

    public void cargaMasiva() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("json", "json", "JSON");
        jfc.addChoosableFileFilter(filter);
        int returnValue = jfc.showOpenDialog(null);

        boolean repetido = false;

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = jfc.getSelectedFile();
                Object obj = new JSONParser().parse(new FileReader(selectedFile.getAbsolutePath()));
                JSONArray arregloJSON = (JSONArray) obj;
                for (Object cada : arregloJSON) {
                    JSONObject cadaConvertido = (JSONObject) cada;

                    int codigo = Integer.parseInt(cadaConvertido.get("codigo") + "");
                    String nombre = cadaConvertido.get("nombre") + "";
                    String descripcion = cadaConvertido.get("descripcion") + "";
                    int cantidad = Integer.parseInt(cadaConvertido.get("cantidad") + "");
                    double precio = Double.parseDouble(cadaConvertido.get("precio") + "");

                    if (this.crear(codigo, nombre, descripcion, cantidad, precio) == false) {
                        repetido = true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error al leer archivo productos");
            }
        }

        if (repetido == true) {
            JOptionPane.showMessageDialog(null, "Hay Datos Con Codigos Repetidos En El Archivo");
        }

    }

    public boolean unico(int codigo) {
        for (int i = 0; i < Proyecto_1.producto.length; i++) {

            if (Proyecto_1.producto[i] != null) {
                if (Proyecto_1.producto[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean crear(int codigo, String nombre, String descripcion, int cantidad, double precio) {
        Productos nuevaProducto = new Productos(codigo, nombre, descripcion, cantidad, precio);

        if (this.unico(codigo) == false) {
            return false;
        }

        for (int i = 0; i < Proyecto_1.producto.length; i++) {

            if (Proyecto_1.producto[i] == null) {
                Proyecto_1.producto[i] = nuevaProducto;
                return true;
            }
        }
        return false;
    }

    public boolean actualizar(int codigo, String nombre, String descripcion, int cantidad, double precio) {

        for (int i = 0; i < Proyecto_1.producto.length; i++) {

            if (Proyecto_1.producto[i] != null) {
                if (Proyecto_1.producto[i].getCodigo() == codigo) {
                    Proyecto_1.producto[i].setNombre(nombre);
                    Proyecto_1.producto[i].setDescripcion(descripcion);
                    Proyecto_1.producto[i].setCantidad(cantidad);
                    Proyecto_1.producto[i].setPrecio(precio);

                    return true;

                }

            }

        }

        return false;

    }

    public Productos buscarUno(int codigo) {

        for (int i = 0; i < Proyecto_1.producto.length; i++) {

            if (Proyecto_1.producto[i] != null) {

                if (codigo == Proyecto_1.producto[i].getCodigo()) {

                    return Proyecto_1.producto[i];

                }
            }
        }
        return null;
    }

    public boolean eliminar(int codigo) {

        for (int i = 0; i < Proyecto_1.producto.length; i++) {

            if (Proyecto_1.producto[i] != null) {

                if (codigo == Proyecto_1.producto[i].getCodigo()) {

                    Proyecto_1.producto[i] = null;
                    return true;

                }
            }
        }
        return false;
    }

    
}
