
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
import static proyecto_1.Proyecto_1.cliente;
import proyecto_1.datos.Clientes;


public class LogicaCliente {
    
    
    
    
    
    
    public void exportarListadoPdf() {

}

public DefaultTableModel listadoOficial() {
    String [] header={"codigo","nombre","nit","correo","genero","Accion"};
    String [][] data={};
    DefaultTableModel modelo = new DefaultTableModel(data,header);
    

    for(int i=0;i<Proyecto_1.cliente.length;i++){
    
        if(Proyecto_1.cliente[i]!=null){
    
            String[] fila={Proyecto_1.cliente[i].getCodigo()+ "",Proyecto_1.cliente[i].getNombre(),
            Proyecto_1.cliente[i].getNit()+"",Proyecto_1.cliente[i].getCorreo(),
            Proyecto_1.cliente[i].getGenero()+"","Eliminar"};
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
                    int nit = Integer.parseInt(cadaConvertido.get("nit") + "");
                    String correo = cadaConvertido.get("correo") + "";
                    String genero = cadaConvertido.get("genero") + "";

                    if(this.crear(codigo, nombre, nit, correo, genero)==false){
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
    for (int i = 0; i < Proyecto_1.cliente.length; i++) {

        if (Proyecto_1.cliente[i] != null) {
            if (Proyecto_1.cliente[i].getCodigo() == codigo) {
                return false;
            }
        }
    }

    return true;
}

public boolean crear(int codigo,String nombre,int nit,String correo,String genero) {
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



public boolean actualizar(int codigo,String nombre,int nit,String correo,String genero) {

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

    
    
    
    
    
}
