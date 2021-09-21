
package proyecto_1.logica;

import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Productos;


public class LogicaProductos {
    
    
    
    public void exportarListadoPdf() {
        
        
        
        
        
        
        
        
        
        
        

}

public DefaultTableModel listadoOficial() {
    String [] header={"codigo","nombre","descripcion","cantidad","precio","Accion"};
    String [][] data={};
    DefaultTableModel modelo = new DefaultTableModel(data,header);
    

    for(int i=0;i<Proyecto_1.producto.length;i++){
    
        if(Proyecto_1.producto[i]!=null){
    
            String[] fila={Proyecto_1.producto[i].getCodigo()+ "",Proyecto_1.producto[i].getNombre(),
            Proyecto_1.producto[i].getDescripcion(),Proyecto_1.producto[i].getCantidad()+"",
            Proyecto_1.producto[i].getPrecio()+"","Eliminar"};
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
                    String descripcion = cadaConvertido.get("descripcion") + "";
                    int cantidad = Integer.parseInt(cadaConvertido.get("cantidad") + "");
                    double precio = Double.parseDouble(cadaConvertido.get("precio") + "");

                    if(this.crear(codigo, nombre, descripcion, cantidad, precio)==false){
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
    for (int i = 0; i < Proyecto_1.producto.length; i++) {

        if (Proyecto_1.producto[i] != null) {
            if (Proyecto_1.producto[i].getCodigo() == codigo) {
                return false;
            }
        }
    }

    return true;
}

public boolean crear(int codigo,String nombre,String descripcion,int cantidad,double precio) {
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



public boolean actualizar(int codigo,String nombre,String descripcion,int cantidad,double precio) {

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
