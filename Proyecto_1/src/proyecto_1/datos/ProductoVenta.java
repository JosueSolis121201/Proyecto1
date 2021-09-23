/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1.datos;

import java.io.Serializable;

/**
 *
 * @author Norki
 */
public class ProductoVenta  implements Serializable {
    int cantidadVenta;
    Productos producto;

    public ProductoVenta(int cantidadVenta, Productos producto) {
        this.cantidadVenta = cantidadVenta;
        this.producto = producto;
    }

    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
    
    
}
