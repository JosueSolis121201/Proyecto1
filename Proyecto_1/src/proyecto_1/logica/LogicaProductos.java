
package proyecto_1.logica;

import javax.swing.table.DefaultTableModel;
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

public boolean crear(int codigo,String nombre,String descripcion,int cantidad,int precio) {
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



public boolean actualizar(int codigo,String nombre,String descripcion,int cantidad,int precio) {

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
