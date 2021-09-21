package proyecto_1.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Vendedores;

public class LogicaVendedores {

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

                    if(this.crear(codigo, nombre, caja, ventas, genero, password)==false){
                        repetido=true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error al leer archivo vendedores");
            }
        }
        
        if(repetido == true){
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
