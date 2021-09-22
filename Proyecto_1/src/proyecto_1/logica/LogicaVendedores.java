package proyecto_1.logica;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Vendedores;

public class LogicaVendedores {
    
    public  void descargarDatos(String nombre,Object guardar) {
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
        final String ventas = "ventas";

        Vendedores top[] = new Vendedores[Proyecto_1.vendedor.length];
        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {
           if(Proyecto_1.vendedor[i]!=null){
               top[i] =Proyecto_1.vendedor[i];
           }
            
        }
        for(int j = 0; j < Proyecto_1.vendedor.length; j++){
            for (int i = 0; i < Proyecto_1.vendedor.length-1; i++) {
                if(top[i]==null){
                    break;
                }else{
                    if(top[i+1]!=null){
                        if(top[i].getVentas()<top[i+1].getVentas()){
                            Vendedores actual = top[i];
                            top[i] = top[i+1];
                            top[i+1] = actual;

                        }
                    }
                }
            }
        }
        
        
        
        for (int j = 0; j < 3; j++) {
            if (top[j] != null) {
                
                dataset.addValue(top[j].getVentas(), top[j].getNombre(), ventas);
            }
        }

        //
        JFreeChart barChart = ChartFactory.createBarChart(
                Titulo,
                "Ventas",
                "Numero",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        return barChart;
    }

    public boolean loginAdmin(String codigo, String contraseña) {
        boolean resultado = false;

        if (codigo.equals("admin") && contraseña.equals("admin")) {

            resultado = true;
        } else {
            resultado = false;
        }

        return resultado;
    }

    public boolean loginUsuario(String codigo, String contraseña) {
        boolean resultado = false;
        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {
            Vendedores vendedorActual = Proyecto_1.vendedor[i];
            if (vendedorActual != null) {
                if (codigo.equals(vendedorActual.getCodigo()) && contraseña.equals(vendedorActual.getPassword())) {

                    resultado = true;
                } else {

                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public void exportarListadoPdf() {
        Document documento = new Document();
        FileOutputStream ficheroPdf = null;
        try {
            ficheroPdf = new FileOutputStream("ReporteVendedores.pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            documento.open();
            documento.add(new Paragraph("Reporte Vendedores",
                    FontFactory.getFont("arial",
                            22,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabla = new PdfPTable(5);
            String[] header = {"codigo", "nombre", "caja", "Ventas", "genero"};
            for (String cada : header) {
                System.out.println(cada);
                tabla.addCell(cada);
            }

            for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

                if (Proyecto_1.vendedor[i] != null) {

                    String[] fila = {Proyecto_1.vendedor[i].getCodigo() + "", Proyecto_1.vendedor[i].getNombre(),
                        Proyecto_1.vendedor[i].getCaja() + "", Proyecto_1.vendedor[i].getVentas() + "",
                        Proyecto_1.vendedor[i].getGenero() + ""};

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
        String[] header = {"codigo", "nombre", "caja", "Ventas", "genero", "Accion"};
        String[][] data = {};
        DefaultTableModel modelo = new DefaultTableModel(data, header);

        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

            if (Proyecto_1.vendedor[i] != null) {

                String[] fila = {Proyecto_1.vendedor[i].getCodigo() + "", Proyecto_1.vendedor[i].getNombre(),
                    Proyecto_1.vendedor[i].getCaja() + "", Proyecto_1.vendedor[i].getVentas() + "",
                    Proyecto_1.vendedor[i].getGenero() + "", "Eliminar"};
                modelo.addRow(fila);

            }

        }
        this.descargarDatos("vendedor",Proyecto_1.vendedor );
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
                    int caja = Integer.parseInt(cadaConvertido.get("caja") + "");
                    int ventas = Integer.parseInt(cadaConvertido.get("ventas") + "");
                    String genero = cadaConvertido.get("genero") + "";
                    String password = cadaConvertido.get("password") + "";

                    if (this.crear(codigo, nombre, caja, ventas, genero, password) == false) {
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
        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

            if (Proyecto_1.vendedor[i] != null) {
                if (Proyecto_1.vendedor[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean crear(int codigo, String nombre, int caja, int ventas, String genero, String password) {
        Vendedores nuevaVendedor = new Vendedores(codigo, nombre, caja, ventas, genero, password);

        if (this.unico(codigo) == false) {
            return false;
        }

        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

            if (Proyecto_1.vendedor[i] == null) {
                Proyecto_1.vendedor[i] = nuevaVendedor;
                return true;
            }
        }
        return false;
    }

    public boolean actualizar(int codigo, String nombre, int caja, int ventas, String genero, String password) {

        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

            if (Proyecto_1.vendedor[i] != null) {
                if (Proyecto_1.vendedor[i].getCodigo() == codigo) {
                    Proyecto_1.vendedor[i].setNombre(nombre);
                    Proyecto_1.vendedor[i].setCaja(caja);
                    Proyecto_1.vendedor[i].setVentas(ventas);
                    Proyecto_1.vendedor[i].setGenero(genero);
                    Proyecto_1.vendedor[i].setPassword(password);

                    return true;

                }

            }

        }

        return false;

    }

    public Vendedores buscarUno(int codigo) {

        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

            if (Proyecto_1.vendedor[i] != null) {

                if (codigo == Proyecto_1.vendedor[i].getCodigo()) {

                    return Proyecto_1.vendedor[i];

                }
            }
        }
        return null;
    }

    public boolean eliminar(int codigo) {

        for (int i = 0; i < Proyecto_1.vendedor.length; i++) {

            if (Proyecto_1.vendedor[i] != null) {

                if (codigo == Proyecto_1.vendedor[i].getCodigo()) {

                    Proyecto_1.vendedor[i] = null;
                    return true;

                }
            }
        }
        return false;
    }

}
