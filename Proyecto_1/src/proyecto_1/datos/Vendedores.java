
package proyecto_1.datos;

import java.io.Serializable;
import proyecto_1.Proyecto_1;


public class Vendedores implements Serializable{
    //Código, Nombre, Caja, Ventas y Género
    private int codigo;
    private String nombre;
    private int caja;
    private int ventas;
    private String genero;
    private String password;
    
    
    public Vendedores(int codigo,String nombre,int caja,int ventas,String genero,String password){
    this.codigo=codigo;
    this.nombre=nombre;
    this.caja=caja;
    this.ventas=ventas;
    this.genero=genero;
    this.password=password;
            
    
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

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
































    
}
