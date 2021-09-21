package proyecto_1.logica;

import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Sucursales;

public class LogicaSucursales {

    public void exportarListadoPdf() {

    }

    public DefaultTableModel listadoOficial() {
        String [] header={"codigo","nombre","direccion","correo","telefono","Accion"};
        String [][] data={};
        DefaultTableModel modelo = new DefaultTableModel(data,header);
        

        for(int i=0;i<Proyecto_1.sucursal.length;i++){
        
            if(Proyecto_1.sucursal[i]!=null){
        
                String[] fila={Proyecto_1.sucursal[i].getCodigo()+ "",Proyecto_1.sucursal[i].getNombre(),
                Proyecto_1.sucursal[i].getDireccion(),Proyecto_1.sucursal[i].getCorreo(),
                Proyecto_1.sucursal[i].getTelefono()+"","Eliminar"};
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
                    String direccion =cadaConvertido.get("direccion") + "";
                    String correo = cadaConvertido.get("correo") + "";
                    int telefono = Integer.parseInt(cadaConvertido.get("telefono") + "");

                    if(this.crear(codigo, nombre, direccion, correo, telefono)==false){
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
        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {
                if (Proyecto_1.sucursal[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean crear(int codigo, String nombre, String direccion, String correo, int telefono) {
        Sucursales nuevaSucursal = new Sucursales(codigo, nombre, direccion, correo, telefono);

        if (this.unico(codigo) == false) {
            return false;
        }

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] == null) {
                Proyecto_1.sucursal[i] = nuevaSucursal;
                return true;
            }
        }
        return false;
    }

    

    public boolean actualizar(int codigo, String nombre, String direccion, String correo, int telefono) {

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {
                if (Proyecto_1.sucursal[i].getCodigo() == codigo) {

                    Proyecto_1.sucursal[i].setNombre(nombre);
                    Proyecto_1.sucursal[i].setDireccion(direccion);
                    Proyecto_1.sucursal[i].setCorreo(correo);
                    Proyecto_1.sucursal[i].setTelefono(telefono);

                    return true;

                }

            }

        }

        return false;

    }
    public Sucursales buscarUno(int codigo) {

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {

                if (codigo == Proyecto_1.sucursal[i].getCodigo()) {

                    return Proyecto_1.sucursal[i];

                }
            }
        }
        return null;
    }
    public boolean eliminar(int codigo) {

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {

                if (codigo == Proyecto_1.sucursal[i].getCodigo()) {

                    Proyecto_1.sucursal[i] = null;
                    return true;

                }
            }
        }
        return false;
    }

}
