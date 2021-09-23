
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
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Clientes;


public class LogicaCliente {
    

        
    
    public void descargarDatos(String nombre, Object guardar) {
        try {
            ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(nombre));
            serial.writeObject(guardar);
        } catch (Exception ex) {
            System.out.println("Error al serializar");
            System.out.println(ex);
        }
    }

    public void exportarListadoPdf() {

        Document documento = new Document();
        FileOutputStream ficheroPdf = null;
        try {
            ficheroPdf = new FileOutputStream("ReporteClientes.pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            documento.open();
            documento.add(new Paragraph("Reporte Cliente",
                    FontFactory.getFont("arial",
                            22,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabla = new PdfPTable(5);
            String[] header = {"codigo", "nombre", "nit", "correo", "genero"};
            for (String cada : header) {
                System.out.println(cada);
                tabla.addCell(cada);
            }

            for (int i = 0; i < Proyecto_1.cliente.length; i++) {

                if (Proyecto_1.cliente[i] != null) {

                    String[] fila = {Proyecto_1.cliente[i].getCodigo() + "", Proyecto_1.cliente[i].getNombre(),
                        Proyecto_1.cliente[i].getNit() + "", Proyecto_1.cliente[i].getCorreo(),
                        Proyecto_1.cliente[i].getGenero() + ""};

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
        String[] header = {"codigo", "nombre", "nit", "correo", "genero", "Accion"};
        String[][] data = {};
        DefaultTableModel modelo = new DefaultTableModel(data, header);

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {

            if (Proyecto_1.cliente[i] != null) {

                String[] fila = {Proyecto_1.cliente[i].getCodigo() + "", Proyecto_1.cliente[i].getNombre(),
                    Proyecto_1.cliente[i].getNit() + "", Proyecto_1.cliente[i].getCorreo(),
                    Proyecto_1.cliente[i].getGenero() + "", "Eliminar"};
                modelo.addRow(fila);

            }

        }
        this.descargarDatos("cliente", Proyecto_1.cliente);

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
                    String nit = (cadaConvertido.get("nit") + "");
                    String correo = cadaConvertido.get("correo") + "";
                    String genero = cadaConvertido.get("genero") + "";

                    if (this.crear(codigo, nombre, nit, correo, genero) == false) {
                        repetido = true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error al leer archivo vendedores");
            }
        }

        if (repetido == true) {
            JOptionPane.showMessageDialog(null, "Hay Datos Con Codigos Repetidos En El Archivo");
        }

    }

    public boolean unico(int codigo) {
        for (int i = 0; i < Proyecto_1.cliente.length; i++) {

            if (Proyecto_1.cliente[i] != null) {
                if (Proyecto_1.cliente[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean crear(int codigo, String nombre, String nit, String correo, String genero) {
        Clientes nuevaCliente = new Clientes(codigo, nombre, nit, correo, genero);

        if (this.unico(codigo) == false) {
            return false;
        }

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {

            if (Proyecto_1.cliente[i] == null) {
                Proyecto_1.cliente[i] = nuevaCliente;
                return true;
            }
        }
        return false;
    }

    public boolean actualizar(int codigo, String nombre, String nit, String correo, String genero) {

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {

            if (Proyecto_1.cliente[i] != null) {
                if (Proyecto_1.cliente[i].getCodigo() == codigo) {
                    Proyecto_1.cliente[i].setNombre(nombre);
                    Proyecto_1.cliente[i].setNit(nit);
                    Proyecto_1.cliente[i].setCorreo(correo);
                    Proyecto_1.cliente[i].setGenero(genero);

                    return true;

                }

            }

        }

        return false;

    }

    public Clientes buscarUno(int codigo) {

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {

            if (Proyecto_1.cliente[i] != null) {

                if (codigo == Proyecto_1.cliente[i].getCodigo()) {

                    return Proyecto_1.cliente[i];

                }
            }
        }
        return null;
    }

    public boolean eliminar(int codigo) {

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {

            if (Proyecto_1.cliente[i] != null) {

                if (codigo == Proyecto_1.cliente[i].getCodigo()) {

                    Proyecto_1.cliente[i] = null;
                    return true;

                }
            }
        }
        return false;
    }

    public JFreeChart graficaGenero() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        Clientes top[] = new Clientes[Proyecto_1.cliente.length];
        double hombre = 0;
        double mujer = 0;
        double total = 0;

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {
            Clientes actual = Proyecto_1.cliente[i];

            if (actual != null) {
                total++;
                if (actual.getGenero().toLowerCase().equals("m")) {

                    mujer++;
                } else {
                    hombre++;
                }

                top[i] = Proyecto_1.cliente[i];
            }

        }

        dataset.setValue("Mujer", mujer);
        dataset.setValue("Hombre", hombre);
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Hombres y Mujeres",
                dataset,
                true,
                true,
                true);
        return chart;

    }


    
    
    
    
}
