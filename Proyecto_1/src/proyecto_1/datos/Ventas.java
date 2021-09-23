
package proyecto_1.datos;

import java.io.Serializable;


public class Ventas implements Serializable{
    Clientes clienteFactura;
    Vendedores vendedor;
    ProductoVenta[] listadoProductos;
    int id;
    String fecha;
    double total;

    public Ventas(Clientes clienteFactura, Vendedores vendedor, ProductoVenta[] listadoProductos, int id, String fecha, double total) {
        this.clienteFactura = clienteFactura;
        this.vendedor = vendedor;
        this.listadoProductos = listadoProductos;
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    public Clientes getClienteFactura() {
        return clienteFactura;
    }

    public void setClienteFactura(Clientes clienteFactura) {
        this.clienteFactura = clienteFactura;
    }

    public Vendedores getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedores vendedor) {
        this.vendedor = vendedor;
    }

    public ProductoVenta[] getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(ProductoVenta[] listadoProductos) {
        this.listadoProductos = listadoProductos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
       
     
     
     
     
        
    }


    
    
    
    
   

