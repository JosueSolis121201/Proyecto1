
package proyecto_1.datos;


public class Productos{
    private int codigo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int precio;
    private int numVentas;
    
    
    
    
    //Código, Nombre, Descripción, Cantidad y Precio
    
    public Productos(int codigo,String nombre,String descripcion,int cantidad,int precio){
    this.codigo=codigo;
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.cantidad=cantidad;
    this.precio=precio;
    this.numVentas=0;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(int numVentas) {
        this.numVentas = numVentas;
    }
    
    
    
    
}
