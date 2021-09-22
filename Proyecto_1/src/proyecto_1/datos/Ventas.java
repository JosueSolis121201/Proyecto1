
package proyecto_1.datos;

import java.io.Serializable;


public class Ventas implements Serializable{
    Clientes clienteFactura;
    
    
    public Ventas(Clientes clienteFactura){
        this.clienteFactura = clienteFactura;
    }
}
